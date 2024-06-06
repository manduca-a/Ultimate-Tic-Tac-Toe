package it.unical.informatica.studenti.Model;

import it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM.*;
import it.unical.informatica.studenti.Model.ClassiEmbASP.GM.*;
import it.unical.informatica.studenti.Model.ClassiEmbASP.GM.Common.Move;
import it.unical.informatica.studenti.Model.ClassiEmbASP.GM.Utils.GMUtils;
import it.unical.informatica.studenti.OsCheck;
import it.unical.informatica.studenti.Teams;
import it.unical.informatica.studenti.WorldGame;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.platforms.desktop.DesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EmbaspManager {

    public static ArrayList<Integer> avviaASP(Teams team){
        try {
            DesktopService service = switch (OsCheck.getOperatingSystemType()) {
                case Windows -> new DLV2DesktopService("src/main/resources/EmbASP/dlv2_win64.exe");
                case MacOS -> new DLV2DesktopService("src/main/resources/EmbASP/dlv-2.1.2-arm64");
                case Linux -> new DLV2DesktopService("src/main/resources/EmbASP/dlv2_linux64");
                default -> throw new Exception("No DLV for this OS.");
            };

            Handler handler = new DesktopHandler(service);
            InputProgram program = new ASPInputProgram();
            String path = "src/main/resources/EmbASP/encodings/" + team;
            program.addFilesPath(path);
            //Attenzione i file dentro encodings devono assumere il medesimo valore dell'enum per essere presi
            //(o almeno dovrebbe essere così, da testare)

            switch (team) {
                case CHATCM -> {
                    return ASPChatCM(handler, program);
                }
                case GM -> {
                    return ASPGM(handler, program);
                }
                case QueryQueens -> {
                    return ASPQueryQueens(handler, program);
                }
                //Ogni team implementa il proprio metodo EmbAsp
            }
            return null;
        }
        catch (Exception ignored){
            return null;
        }
    }

    private static ArrayList<Integer> ASPGM(Handler handler, InputProgram program) throws Exception {
        for (SmallBoard b : WorldGame.getInstance().getBigBoard().getSmallBoards()) {
            if(b.GetWinner() == InfoGame.Winner.NOWINNER) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (b.getSubBoard()[i][j] == WorldGame.getInstance().getUserToPlay())
                            program.addObjectInput(new MyMove(b.getId(),i, j));
                        else if (b.getSubBoard()[i][j] != 0)
                            program.addObjectInput(new EnemyMove(b.getId(),i, j));
                    }
                }
            }
            else{
                switch(b.GetWinner()){
                    case CROSS -> {
                        if(WorldGame.getInstance().getUserToPlay() == 1)
                            program.addObjectInput(new BoardWinByMe(b.getId()));
                        else
                            program.addObjectInput(new BoardWinByEnemy(b.getId()));
                    }
                    case CIRCLE -> {
                        if (WorldGame.getInstance().getUserToPlay() == -1)
                            program.addObjectInput(new BoardWinByMe(b.getId()));
                        else
                            program.addObjectInput(new BoardWinByEnemy(b.getId()));
                    }
                }
            }
        }
        int currentBoard = GMUtils.calculateCurrentBoard();
        program.addObjectInput(new CurrentBoard(currentBoard));

        List<Move> movesICanWin = GMUtils.getWinnableMoves(WorldGame.getInstance().getUserToPlay());
        List<Move> movesEnemyCanWin = GMUtils.getWinnableMoves(WorldGame.getInstance().getUserToPlay()*-1);
        for(Move m : movesICanWin) {
            program.addObjectInput(new MoveICanWin(m.getB(), m.getX(), m.getY()));
            program.addObjectInput(new BoardCanWin(m.getB()));
        }
        for(Move m : movesEnemyCanWin)
            program.addObjectInput(new MoveEnemyCanWin(m.getB(), m.getX(), m.getY()));

        handler.addProgram(program);
        System.out.println(program.getPrograms());
        Output output = handler.startSync();
        ASPMapper.getInstance().registerClass(InPossibleMove.class);


        AnswerSets answersets = (AnswerSets) output;
        System.out.println(output.getOutput());

        InPossibleMove move;


        try{
            for (AnswerSet a : answersets.getOptimalAnswerSets()) {
                try {
                    for (Object obj : a.getAtoms()) {
                        if (!(obj instanceof InPossibleMove)) continue;
                        move = (InPossibleMove) obj;
                        return GMUtils.generateData(move);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (NoSuchElementException ex){
            for (AnswerSet a : answersets.getAnswersets()) {
                try {
                    for (Object obj : a.getAtoms()) {
                        if (!(obj instanceof InPossibleMove)) continue;
                        move = (InPossibleMove) obj;
                        return GMUtils.generateData(move);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private static ArrayList<Integer> ASPQueryQueens(Handler handler, InputProgram program) {
        handler.removeAll();

        String nextBoard = transformCoordinates(WorldGame.getInstance().getBigBoard().getNextBoard());

        String fact = null;
        if (nextBoard != null) /*   Se si gioca in una sola board creo il fatto playingSmallBoard   */ {
            String[] splitted = nextBoard.split("_");
            fact = "playingSmallBoard(" + splitted[0] + ", " + splitted[1] + ").";
            program.addProgram(fact);
        }
//        else System.out.println("no playing small board");
////        devo cercare la board di gioco e passare tutti i dati ad ASP
        int i=0;
        for (SmallBoard b : WorldGame.getInstance().getBigBoard().getSmallBoards()) {
            String tosplit = transformCoordinates(i);
            i++;
            String[] coords = tosplit.split("_");
            if(b.GetWinner() == InfoGame.Winner.CIRCLE) /*   Creo il fatto won(A, B, -1)   */ {
                fact = "won(" + coords[0] + ", " + coords[1] + ", -1).";
                program.addProgram(fact);
            }
            else if(b.GetWinner() == InfoGame.Winner.CROSS) /*   Creo il fatto won(A, B, -1)   */ {
                fact = "won(" + coords[0] + ", " + coords[1] + ", 1).";
                program.addProgram(fact);
            }
            int[][] subBoard = b.getSubBoard();
            for (int a=0; a<3; a++){
                for (int c=0; c<3; c++) {
                    if (subBoard[a][c] == 1) {
                        fact = "\n\tsmallBoard_State(" + coords[0] + ", " + coords[1] + ", " + a + ", " + c + ", 1).";
                        program.addProgram(fact);
                    }
                    else if (subBoard[a][c] == -1) {
                        fact = "\n\tsmallBoard_State(" + coords[0] + ", " + coords[1] + ", " + a + ", " + c + ", -1).";
                        program.addProgram(fact);
                    }
                }
            }
        }

        handler.addProgram(program);

        Output output = handler.startSync();

        AnswerSets answersets = (AnswerSets) output;

        String result = answersets.getAnswerSetsString();

        result = result.replaceAll("\\DLV 2.1.1|\\{mossa_definitiva\\(|\\)\\}", "");

        result = result.replace("\n", "");

        String[] parts = result.split(",");


        String p = parts[0] + "_" + parts[1];
        int l = retransformCoordinates(p);

        ArrayList<Integer> coords = new ArrayList<Integer>();
        coords.add(Integer.valueOf(parts[2]));
        coords.add(Integer.valueOf(parts[3]));
        coords.add(l);

        return coords;
    }

    private static String transformCoordinates(int number) {
        switch (number){
            case 0 -> {
                return "0_0";
            }
            case 1 -> {
                return"0_1";
            }
            case 2 -> {
                return"0_2";
            }
            case 3 -> {
                return"1_0";
            }
            case 4 -> {
                return"1_1";
            }
            case 5 -> {
                return"1_2";
            }
            case 6 -> {
                return"2_0";
            }
            case 7 -> {
                return"2_1";
            }
            case 8 -> {
                return"2_2";
            }
        }    //nextBoard può anche essere -1 quando si gioca in più celle
        return null;
    }
    private static int retransformCoordinates(String coords) {
        int smallBoard = -1;

        switch (coords){
            case "0_0" -> {
                return 0;
            }
            case "0_1" -> {
                return 1;
            }
            case "0_2" -> {
                return 2;
            }
            case "1_0" -> {
                return 3;
            }
            case "1_1" -> {
                return 4;
            }
            case "1_2" -> {
                return 5;
            }
            case "2_0" -> {
                return 6;
            }
            case "2_1" -> {
                return 7;
            }
            case "2_2" -> {
                return 8;
            }
        }    // trasformo le coordinate da EmbASP a java
        return smallBoard;
    }

    //Metodo ChatCM ancora da testare
    private static ArrayList<Integer> ASPChatCM(Handler handler, InputProgram program) throws Exception {

        for (SmallBoard b : WorldGame.getInstance().getBigBoard().getSmallBoards()) {
            if(b.GetWinner() == InfoGame.Winner.NOWINNER) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (b.getSubBoard()[i][j] == 0)
                            program.addObjectInput(new EmptyCell(i, j, b.getId()));
                        else
                            program.addObjectInput(new FullCell(i, j, b.getId(), b.getSubBoard()[i][j]));
                    }
                }
            }
            else if(b.GetWinner() == InfoGame.Winner.CROSS)
                program.addObjectInput(new SubTris(b.getId(),1));
            else if(b.GetWinner() == InfoGame.Winner.CIRCLE)
                program.addObjectInput(new SubTris(b.getId(),-1));
            else
                program.addObjectInput(new Draw(b.getId()));
        }

        int turnCounter = 0;
        for(SmallBoard b : WorldGame.getInstance().getBigBoard().getSmallBoards()){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(b.getSubBoard()[i][j] != 0)
                        turnCounter++;
                }
            }
        }

        program.addObjectInput(new Marker(WorldGame.getInstance().getUserToPlay()));
        program.addObjectInput(new EnemyMarker(WorldGame.getInstance().getUserToPlay()*-1));
        program.addObjectInput(new GridToPlay(WorldGame.getInstance().getBigBoard().getNextBoard()));
        program.addObjectInput(new it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM.Move(turnCounter));

        System.out.println(program.getPrograms());
        //eventuali altri predicati da aggiungere da input

        handler.addProgram(program);
        Output output = handler.startSync();
        ASPMapper.getInstance().registerClass(InsertMarker.class);

        AnswerSets answersets = (AnswerSets) output;
        System.out.println(output.getOutput());

        InsertMarker marker;

        for (AnswerSet a : answersets.getOptimalAnswerSets()) {
            try {
                for (Object obj : a.getAtoms()) {
                    if (!(obj instanceof InsertMarker)) continue;
                    marker = (InsertMarker) obj;
                    return marker.getInsertData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
