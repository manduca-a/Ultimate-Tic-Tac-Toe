package it.unical.informatica.studenti.Model;

public class SmallBoard {

    private int[][] matrix =new int[3][3];

    private InfoGame.Winner winner;

    public InfoGame.Winner isWinner() {
        return winner;
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
        if(matrix[i][j] == 0){
            System.out.println("Ciao" + i +j);
            matrix[i][j] = value;
            return  true;
        }
        return  false;
    }

    public void checkWinner(){
        winner = InfoGame.checkWinner(matrix);
    }


}
