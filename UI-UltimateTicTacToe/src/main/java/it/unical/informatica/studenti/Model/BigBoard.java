package it.unical.informatica.studenti.Model;

import java.util.ArrayList;

import static it.unical.informatica.studenti.Model.InfoGame.Winner.*;

public class BigBoard {

    private ArrayList<SmallBoard> smallBoards =new ArrayList<>();

    private InfoGame.Winner BigBoardWinner = InfoGame.Winner.NOWINNER;

    private int[][] gameBoard = new int[3][3];

    private int nextBoard = -1;

    public InfoGame.Winner getBigBoardWinner() {
        return BigBoardWinner;
    }

    public BigBoard(){
        for(int i =0; i<9; i++){
            smallBoards.add(new SmallBoard(this,i));
        }
        for(int i =0; i <3; i++){
            for (int j =0; j<3; j++){
                gameBoard[i][j]=0;
            }
        }
    }

    public void UpdateBigBoard(int value, int id){
        if(gameBoard[id/3][id%3] == 0){
            gameBoard[id/3][id%3] = value;
            switch (InfoGame.checkWinner(gameBoard)){
                case CROSS -> {BigBoardWinner = CROSS;}
                case CIRCLE -> {BigBoardWinner = CIRCLE;}
                case DRAW -> {BigBoardWinner = DRAW;}
            }
        }
        else
            throw new RuntimeException("Invalid board");
    }

    public void PrintGameBoard() {
        for( int k=0; k<9; k++){
            System.out.println("Matrix di:"+k);
            for(int i =0; i<3; i++){
                for(int j=0; j<3; j++){
                    System.out.print(smallBoards.get(k).getSubBoard()[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public void UpdateBoardStatus(int i, int j, int boardIndex, int value){

        if(nextBoard == -1 || nextBoard == boardIndex){
            smallBoards.get(boardIndex).SetCell(i, j, value);
            if(smallBoards.get(3*i+j).isWinner() == InfoGame.Winner.NOWINNER)
                nextBoard = 3*i+j;
            else
                nextBoard = -1;
        }
        else
            throw new RuntimeException("Invalid Move");

    }

    public int getNextBoard() {
        return nextBoard;
    }

}
