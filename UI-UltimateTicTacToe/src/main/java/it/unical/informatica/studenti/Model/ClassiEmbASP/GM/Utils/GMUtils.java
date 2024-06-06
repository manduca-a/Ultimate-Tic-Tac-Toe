package it.unical.informatica.studenti.Model.ClassiEmbASP.GM.Utils;

import it.unical.informatica.studenti.Model.ClassiEmbASP.GM.Common.Move;
import it.unical.informatica.studenti.Model.ClassiEmbASP.GM.InPossibleMove;
import it.unical.informatica.studenti.Model.InfoGame;
import it.unical.informatica.studenti.WorldGame;

import java.util.*;

public class GMUtils {

    public static int calculateCurrentBoard(){
        int board = WorldGame.getInstance().getBigBoard().getNextBoard();
        if (board != -1) return board;
        else return findBestSmallBoard();
    }

    private static int findBestSmallBoard(){
        int bestBoard = -1;
        int bestValue = -1;
        List<Move> winnableMoves = getWinnableMoves(WorldGame.getInstance().getUserToPlay());
        List<Move> enemyWinnableMoves = getWinnableMoves(-WorldGame.getInstance().getUserToPlay());
        if(!winnableMoves.isEmpty() && !enemyWinnableMoves.isEmpty()){
            HashMap<Integer, Integer> boards = new HashMap<>();
            for(Move m: winnableMoves){
                if(!boards.containsKey(m.getB())){
                    boards.put(m.getB(), 1);
                }
                else{
                    boards.put(m.getB(), boards.get(m.getB()) + 1);
                }
            }
            for(Move m: enemyWinnableMoves){
                if(!boards.containsKey(m.getB())){
                    boards.put(m.getB(), 1);
                }
                else{
                    boards.put(m.getB(), boards.get(m.getB()) + 1);
                }
            }

           return Collections.max(boards.entrySet(), Map.Entry.comparingByValue()).getKey();

        }
        else{
            for (int i = 0; i < 9; i++){
                if (WorldGame.getInstance().getBigBoard().getSmallBoards().get(i).GetWinner() == InfoGame.Winner.NOWINNER){
                    int value = WorldGame.getInstance().getBigBoard().getSmallBoards().get(i).getValue(WorldGame.getInstance().getUserToPlay());
                    if (value > bestValue){
                        bestValue = value;
                        bestBoard = i;
                    }
                }
            }
            if(bestValue == 0 && bestBoard == 0)
                return 4;
            return bestBoard;
        }
    }

    public static List<Move> getWinnableMoves(int player){
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            if (WorldGame.getInstance().getBigBoard().getSmallBoards().get(i).GetWinner() == InfoGame.Winner.NOWINNER){
                for (int j = 0; j < 3; j++){
                    for (int k = 0; k < 3; k++){
                        if (WorldGame.getInstance().getBigBoard().getSmallBoards().get(i).getSubBoard()[j][k] == 0){
                            int[][] mat = WorldGame.getInstance().getBigBoard().getSmallBoards().get(i).getSubBoardCopy();
                            mat[j][k] = player;
                            switch (InfoGame.checkWinner(mat)){
                                case CROSS -> {
                                    if(player == 1)
                                        moves.add(new Move(i, j, k));
                                }
                                case CIRCLE -> {
                                    if(player == -1)
                                        moves.add(new Move(i, j, k));
                                }
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    public static ArrayList<Integer> generateData(InPossibleMove move){
        return new ArrayList<>(List.of(move.getX(), move.getY(), move.getB(), WorldGame.getInstance().getUserToPlay()));
    }
}
