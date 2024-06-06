package it.unical.informatica.studenti.Model;

import it.unical.informatica.studenti.WorldGame;

import java.io.IOException;

import static java.lang.Math.abs;

public class SmallBoard {

    private final int[][] subBoard =new int[3][3];

    private final BigBoard bigBoard;

    private final int id;

    private InfoGame.Winner winner = InfoGame.Winner.NOWINNER;

    public InfoGame.Winner GetWinner() {
        return winner;
    }

    public int[][] getSubBoard() {
        return subBoard;
    }

    public int getId() {
        return id;
    }

    public SmallBoard(BigBoard board, int boardId){
        bigBoard = board;
        id = boardId;

        for( int i =0; i < 3; i++){
            for (int j =0 ; j<3 ; j++){
                subBoard[i][j] = 0;
            }
        }
    }

    public void SetCell(int i, int j, int value) {
        if (subBoard[i][j] == 0) {
            subBoard[i][j] = value;
            switch (InfoGame.checkWinner(subBoard)) {
                case CROSS -> {
                    bigBoard.UpdateBigBoard(1, id);
                    winner = InfoGame.Winner.CROSS;
                    WorldGame.getInstance().getWinnerListener().onNewWinner(winner, id);
                }
                case CIRCLE -> {
                    bigBoard.UpdateBigBoard(-1, id);
                    winner = InfoGame.Winner.CIRCLE;
                    WorldGame.getInstance().getWinnerListener().onNewWinner(winner, id);
                }
                case DRAW -> {
                    bigBoard.UpdateBigBoard(-10, id); // potremmo avere dei problemi, da capire come individuare la draw
                    winner = InfoGame.Winner.DRAW;
                    WorldGame.getInstance().getWinnerListener().onNewWinner(winner, id);
                }
            }
        }
        else
            throw new RuntimeException("invalid move");
    }

    /**
     * Get the number of cells occupied by the player
     * @param player the player
     * @return the number of cells occupied by the player
     */
    public int getValue(int player){
        int value = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (subBoard[i][j] == player)
                    value += subBoard[i][j];
            }
        }
        return abs(value);
    }

    public int[][] getSubBoardCopy() {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(subBoard[i], 0, copy[i], 0, 3);
        }
        return copy;
    }
}
