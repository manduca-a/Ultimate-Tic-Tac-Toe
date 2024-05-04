package it.unical.informatica.studenti.Model;

import java.util.ArrayList;

public class BigBoard {

    private ArrayList<SmallBoard> matrix=new ArrayList<>();

    private int[][] BigBoardMatrix = new int[3][3];

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

    public void PrintMatrix() {
        for( int k =0; k<9; k++){
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

    public void UpdateBoard(int i, int j, int boardIndex, int value){
        matrix.get(boardIndex).SetCell(i,j,value);
    }

}
