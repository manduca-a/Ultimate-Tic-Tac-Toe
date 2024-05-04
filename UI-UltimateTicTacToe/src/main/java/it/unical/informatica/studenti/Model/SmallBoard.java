package it.unical.informatica.studenti.Model;

public class SmallBoard {

    private int[][] matrix =new int[3][3];

    private int nextBoard = -1;

    public enum Winner {CROSS,CIRCLE,DRAW};
    private Winner winner;

    public Winner isWinner() {
        return winner;
    }

    public int getNextBoard() {
        return nextBoard;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public SmallBoard(){
        for( int i =0; i < 3; i++){
            for (int j =0 ; j<3 ; j++){
                matrix[i][j] = 0;
            }
        }
    }

    public boolean SetCell(int i, int j, int value){
        if(matrix[i][j] != 0){
            matrix[i][j] = value;
            return  true;
        }
        return  false;
    }

    public Winner checkWinner(){
        int counterRow = 0;
        int counterCols = 0;
        int counterMajorD = 0;
        int counterMinorD = matrix[0][2]+matrix[1][1]+matrix[2][0];

        if(counterMinorD == 3){
            winner = Winner.CROSS;
            return  winner;
        }
        else if(counterMinorD == -3) {
            winner = Winner.CIRCLE;
            return winner;
        }

        for( int i =0; i < 3; i++){
            for (int j =0 ; j<3 && winner != null ; j++){
                counterRow += matrix[i][j];
                counterCols += matrix[j][i];
                counterMajorD += matrix[j][j];
            }
            if( counterRow == 3 || counterCols == 3 || counterMajorD == 3){
                winner = Winner.CROSS;
                return winner;
            }
            else if (counterRow == -3 || counterCols ==-3 || counterMajorD == -3 ){
                winner = Winner.CIRCLE;
                return  winner;
            }
            counterRow = 0;
            counterCols = 0;
            counterMajorD = 0;
        }

        return Winner.DRAW;

    }


}
