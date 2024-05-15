package it.unical.informatica.studenti.Model;

public class InfoGame {
    public enum Winner {CROSS,CIRCLE,DRAW, NOWINNER}
    public static Winner checkWinner(int[][] matrix){
        int counterRow = 0;
        int counterCols = 0;
        int counterMajorD = matrix[2][2]+matrix[1][1]+matrix[0][0];
        int counterMinorD = matrix[0][2]+matrix[1][1]+matrix[2][0];

        if(counterMinorD == 3 || counterMajorD == 3){
            return  Winner.CROSS;
        }
        else if(counterMinorD == -3 || counterMajorD == -3) {
            return Winner.CIRCLE;
        }

        int moveCounter = 9;

        for(int i =0; i < 3; i++){
            for (int j =0 ; j<3; j++){
                counterRow += matrix[i][j];
                counterCols += matrix[j][i];
                if(matrix[i][j] != 0){
                    moveCounter--;
                }
            }
            if( counterRow == 3 || counterCols == 3){
                return InfoGame.Winner.CROSS;
            }
            else if (counterRow == -3 || counterCols == -3){
                return  InfoGame.Winner.CIRCLE;
            }
            counterRow = 0;
            counterCols = 0;
        }

        if(moveCounter == 0)
            return InfoGame.Winner.DRAW;

        return Winner.NOWINNER;
    }
}
