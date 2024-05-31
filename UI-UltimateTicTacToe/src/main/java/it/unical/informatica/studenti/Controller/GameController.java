package it.unical.informatica.studenti.Controller;


import it.unical.informatica.studenti.Model.EmbaspManager;
import it.unical.informatica.studenti.Settings;
import it.unical.informatica.studenti.Model.InfoGame;
import it.unical.informatica.studenti.View.GameStartView;
import it.unical.informatica.studenti.View.GameView;
import it.unical.informatica.studenti.View.GameWinView;
import it.unical.informatica.studenti.WorldGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController extends KeyAdapter implements ActionListener, WinnerListener {

    private final JFrame frame;
    private final GameView gameView;
    private final WorldGame worldGame = WorldGame.getInstance();

    public GameController(JFrame frame, GameView view) {
        this.frame = frame;
        this.gameView = view;
        worldGame.addWinnerListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton o = (JButton) e.getSource();
        String[] indexes = o.getName().split("\\s+");
        int indBigBoard = Integer.parseInt(indexes[0]);
        int indSmallBoard = Integer.parseInt(indexes[1]);
        int rowSmallBoard = indSmallBoard / 3;
        int colSmallBoard = indSmallBoard % 3;

        switch (worldGame.getCurrentGameMode()){
            case IAVsIA -> MoveIAvsIA(o,indBigBoard,rowSmallBoard,colSmallBoard);
            case PlayerVsIA -> MovePlayerVsIA(o,indBigBoard,rowSmallBoard,colSmallBoard);
        }

        gameView.setTeamsLabelColors(WorldGame.getInstance().getUserToPlay()==1);
    }

    private void MovePlayerVsIA(JButton o,int id, int i , int j){

        DoMove(o,id,i,j);       //va a colorare il bordo della prossima board di gioco con il calcolo (3*indiceriga)+indicecolonna di dove è stato inserito l'ultimo mark

//        WorldGame.getInstance().getBigBoard().PrintSmallBoards();
//
//        for (SmallBoard b : WorldGame.getInstance().getBigBoard().getSmallBoards()){
//            if (b.getId()==WorldGame.getInstance().getBigBoard().getNextBoard()){
//                for (int f=0; i<3; i++){
//                    for (int k=0; k<3; k++){
//                        System.out.print(b.getSubBoard()[f][k]);
//                    }
//                    System.out.println();
//                }
//            }
//        }

        if(!worldGame.isIACalling()) {
            SwingUtilities.invokeLater( () -> {
                try {
                    Thread.sleep(1200);
                    worldGame.setIACalling(true);
                    ArrayList<Integer> coords = EmbaspManager.avviaASP(Settings.IAPlayingVsPLayer);
                    assert coords != null;
                    GameView.getButton(coords.get(0), coords.get(1), coords.get(2)).doClick();
                }
                catch (Exception ignored) {}
            });
        }
        else
            WorldGame.getInstance().setIACalling(false);
    }

    private void MoveIAvsIA(JButton o, int i, int j, int id){

        DoMove(o,i,j,id);

        SwingUtilities.invokeLater( () -> {
            try {
                //Thread.sleep(600);
                for (int v = 0; v < 2; v++) {
                    if (!worldGame.getIAStartingPlaying()[v]) {
                        worldGame.SwapIAPlaying();
                        ArrayList<Integer> coords = EmbaspManager.avviaASP(Settings.TeamsPlaying[v]);
                        assert coords != null;
                        GameView.getButton(coords.get(0), coords.get(1), coords.get(2)).doClick();
                        return;
                    }
                }
            }
            catch (Exception ignored){}
        });
    }

    private void DoMove(JButton o,int indBigBoard, int rowSmallBoard, int colSmallBoard){

        if (worldGame.getBigBoard().UpdateBoardStatus(rowSmallBoard, colSmallBoard, indBigBoard, worldGame.getUserToPlay()))    /*  aggiorna il Model  */     {
            if (worldGame.getUserToPlay() == 1) gameView.setIconX(o); else if (worldGame.getUserToPlay() == -1) gameView.setIconO(o);
            worldGame.AlternateUserToPlay();
        }

        List<JPanel> jpanels = GameView.getjPanels();
        for(int i = 0; i < 9; i++) {

            // Sets the border green if it is the next board or if any board can be played when the game has no winner
            if ((worldGame.getBigBoard().getBigBoardWinner() == InfoGame.Winner.NOWINNER
                    && (worldGame.getBigBoard().getNextBoard() == i || worldGame.getBigBoard().getNextBoard() == -1)))  /*  aggiorna la View  */   {
                if (worldGame.getUserToPlay() == 1) {
                    // X = 1, O = -1
                    jpanels.get(i).setBorder(BorderFactory.createLineBorder(Settings.State.O.getColor(), 5));
                } else {
                    jpanels.get(i).setBorder(BorderFactory.createLineBorder(Settings.State.X.getColor(), 5));
                }
                for( Component b : jpanels.get(i).getComponents()){
                    JButton button = (JButton) b;
                    if(jpanels.get(i).getComponents().length != 1 && button.getIcon() == null)
                        button.setEnabled(true);
                }
            } else   /*   se c'è una board con un vincitore disattiva i bottoni   */    {
                jpanels.get(i).setBorder(BorderFactory.createLineBorder(Settings.State.BIG_LINES_COLOR.getColor(), 5));
                for( Component b : jpanels.get(i).getComponents()){
                    b.setEnabled(false);
                }
            }
        }
        o.setEnabled(false);
    }

    @Override
    public void onNewWinner(InfoGame.Winner winner, int id) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));
        JButton button = new JButton();
        button.setEnabled(false);

        ImageIcon icon = switch (winner) {
            case CROSS -> new ImageIcon(
                    gameView.getIconX().getImage().getScaledInstance(
                    gameView.getIconX().getIconWidth() * 2, gameView.getIconX().getIconHeight() * 2, Image.SCALE_DEFAULT));
            case CIRCLE -> new ImageIcon(
                    gameView.getIconO().getImage().getScaledInstance(
                    gameView.getIconO().getIconWidth() * 2, gameView.getIconO().getIconHeight() * 2, Image.SCALE_DEFAULT));
            case DRAW -> new ImageIcon(
                    gameView.getIconDraw().getImage().getScaledInstance(
                    gameView.getIconDraw().getIconWidth() * 2, gameView.getIconDraw().getIconHeight() * 2, Image.SCALE_DEFAULT));
            default -> null;
        };
        button.setIcon(icon);
        button.setDisabledIcon(icon);

        panel.add(button);
        gameView.setJPanel(panel, id);
    }

    @Override
    public void onGameWinner(InfoGame.Winner winner) {

        gameView.disableAll();
        try {
            Settings.Img imgPath;

            imgPath = switch (winner) {
                case CROSS -> Settings.Img.X;
                case CIRCLE ->  Settings.Img.O;
                case DRAW, NOWINNER -> Settings.Img.Draw;
            };

            System.out.println("Launch GameWinView");
            GameWinView.launch(frame, gameView, imgPath);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            try {
                GameStartView.launch(frame, gameView);
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            try {
                GameView.launch(frame, gameView);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

}

