package it.unical.informatica.studenti.Controller;


import it.unical.informatica.studenti.Model.EmbaspManager;
import it.unical.informatica.studenti.Settings;
import it.unical.informatica.studenti.Model.InfoGame;
import it.unical.informatica.studenti.View.GameView;
import it.unical.informatica.studenti.WorldGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameController implements ActionListener, WinnerListener {

    private final GameView gameView;
    private final WorldGame worldGame = WorldGame.getInstance();

    private boolean firstMove = true;

    private int UserToPlay = 1; // X = 1, O = -1
    public GameController(JFrame frame, GameView view) {
        this.gameView = view;
        worldGame.addWinnerListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton o = (JButton)e.getSource();
        String[] indexes = o.getName().split("\\s+");
        int indBigBoard = Integer.parseInt(indexes[0]);
        int indSmallBoard = Integer.parseInt(indexes[1]);
        int rowSmallBoard = indSmallBoard / 3;
        int colSmallBoard = indSmallBoard % 3;

        switch (worldGame.getCurrentGameMode()){
            case IAVsIA -> MoveIAvsIA(o,indBigBoard,rowSmallBoard,colSmallBoard);
            case PlayerVsIA -> MovePlayerVsIA(o,indBigBoard,rowSmallBoard,colSmallBoard);
        }

    }

    private void MovePlayerVsIA(JButton o,int id, int i , int j){
        if(worldGame.isIACalling() && !firstMove){
            WorldGame.getInstance().setIACalling(false);
            return;
        }

        DoMove(o,id,i,j);

        if(!worldGame.isIACalling()) {
            worldGame.setIACalling(true);
            ArrayList<Integer> coords = EmbaspManager.avviaASP(Settings.IAPlayingVsPLayer);
            GameView.getButton(coords.get(0),coords.get(1),coords.get(2)).doClick();
        }
    }

    private void MoveIAvsIA(JButton o, int i, int j, int id){
        //TO DO: definire come fare il flow per IA vs IA
        //DoMove(o,i,j,id);
    }

    private void DoMove(JButton o,int indBigBoard, int rowSmallBoard, int colSmallBoard){
        if(firstMove)
            firstMove = false;

        if (worldGame.getBigBoard().UpdateBoardStatus(rowSmallBoard, colSmallBoard, indBigBoard, UserToPlay)) {
            if (UserToPlay == 1) gameView.setIconX(o); else if (UserToPlay == -1) gameView.setIconO(o);
            UserToPlay *= -1;
        }

        List<JPanel> jpanels = GameView.getjPanels();
        for(int i = 0; i < 9; i++) {

            // Sets the border green if it is the next board or if any board can be played when the game has no winner
            if ((worldGame.getBigBoard().getBigBoardWinner() == InfoGame.Winner.NOWINNER
                    && (worldGame.getBigBoard().getNextBoard() == i || worldGame.getBigBoard().getNextBoard() == -1))) {
                jpanels.get(i).setBorder(BorderFactory.createLineBorder(Settings.State.CURRENT_PLAYING.getColor(), 5));
            } else {
                jpanels.get(i).setBorder(BorderFactory.createLineBorder(Settings.State.BIG_LINES_COLOR.getColor(), 5));
            }
        }
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
        GameView.setJPanel(panel, id);
    }

    @Override
    public void onGameWinner(InfoGame.Winner winner) {
        gameView.disableAll();
    }
}
