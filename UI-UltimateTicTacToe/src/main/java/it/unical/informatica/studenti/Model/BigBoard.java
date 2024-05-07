package it.unical.informatica.studenti.Model;

import java.util.ArrayList;

public class BigBoard {

    private ArrayList<SmallBoard> matrix=new ArrayList<>();

    private InfoGame.Winner BigBoardWinner = InfoGame.Winner.NOWINNER;

    private int[][] BigBoardMatrix = new int[3][3];

    private int nextBoard = -1;


    public InfoGame.Winner getBigBoardWinner() {
        return BigBoardWinner;
    }

    public BigBoard(){
        for(int i =0; i<9; i++){
            matrix.add(new SmallBoard());
        }
        for(int i =0; i <3; i++){
            for (int j =0; j<3; j++){
                BigBoardMatrix[i][j]=0;
            }
        }
    }

    public void UpdateBigBoard(){
        for(int i =0 ; i< 9; i++){
            if( BigBoardMatrix[i / 3][i % 3] == 0 ) {
                if (InfoGame.checkWinner(matrix.get(i).getMatrix()) == InfoGame.Winner.CROSS) {
                    BigBoardMatrix[i / 3][i % 3] = 1;
                } else if (InfoGame.checkWinner(matrix.get(i).getMatrix()) == InfoGame.Winner.CIRCLE) {
                    BigBoardMatrix[i / 3][i % 3] = -1;
                }
            }
        }
    }

    public void PrintMatrix() {
        for( int k=0; k<9; k++){
            System.out.println("Matrix di:"+k);
            for(int i =0; i<3; i++){
                for(int j=0; j<3; j++){
                    System.out.print(matrix.get(k).getMatrix()[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public void UpdateBoardStatus(int i, int j, int boardIndex, int value){

        if(nextBoard == -1 || boardIndex == nextBoard)
            matrix.get(boardIndex).SetCell(i,j,value);

        if(InfoGame.checkWinner(matrix.get(3*i+j).getMatrix()) == InfoGame.Winner.NOWINNER)
            nextBoard = 3*i+j;
        else
            nextBoard = -1;
    }

}
