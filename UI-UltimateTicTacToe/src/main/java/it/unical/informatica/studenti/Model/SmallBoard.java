package it.unical.informatica.studenti.Model;

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

    public void SetCell(int i, int j, int value){
        if (subBoard[i][j] == 0) {
            subBoard[i][j] = value;
            switch (InfoGame.checkWinner(subBoard)) {
                case CROSS -> {
                    bigBoard.UpdateBigBoard(1, id);
                    winner = InfoGame.Winner.CROSS;
                }
                case CIRCLE -> {
                    bigBoard.UpdateBigBoard(-1, id);
                    winner = InfoGame.Winner.CIRCLE;
                }
                case DRAW -> {
                    bigBoard.UpdateBigBoard(-10, id); // potremmo avere dei problemi, da capire come individuare la draw
                    winner = InfoGame.Winner.DRAW;
                }
            }
        }
        else
            throw new RuntimeException("invalid move");
    }
}
