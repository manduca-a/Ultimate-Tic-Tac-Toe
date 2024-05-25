package it.unical.informatica.studenti.Model;

import it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM.*;
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

public class EmbaspManager {

    public static ArrayList<Integer> avviaASP(Teams team){
        try {
            DesktopService service = switch (OsCheck.getOperatingSystemType()) {
                case Windows -> new DLV2DesktopService("src/main/resources/EmbASP/dlv2_win64.exe");
                case MacOS -> new DLV2DesktopService("src/main/resources/EmbASP/dlv-2.1.2-arm64");
                case Linux -> new DLV2DesktopService("src/main/resources/EmbASP/dlv2_macOS.exe");
                default -> throw new Exception("No DLV for this OS.");
            };

            Handler handler = new DesktopHandler(service);
            InputProgram program = new ASPInputProgram();
            program.addFilesPath("src/main/resources/EmbASP/encodings/" + team);
            //Attenzione i file dentro encodings devono assumere il medesimo valore dell'enum per essere presi
            //(o almeno dovrebbe essere così, da testare)

            switch (team) {
                case CHATCM -> {
                    return ASPChatCM(handler, program);
                }
                case GM -> {
                    return AddProgramTeamName(handler, program);
                }
                case QueryQueens -> {
                    return AddProgramTeamName(handler, program);
                }
                //Ogni team implementa il proprio metodo EmbAsp
            }
            return null;
        }
        catch (Exception ignored){
            return null;
        }
    }

    private static ArrayList<Integer> AddProgramTeamName(Handler handler, InputProgram program) throws Exception{

        //esempio di come aggiungere gli Input
        /*for (int i =0;i<3;i++)
            for (int j =5;j<7;j++)
                program.addObjectInput(new Arco(i, j));
        program.addObjectInput(new Arco(8, 9));
        program.addObjectInput(new Arco(9, 10));
        program.addObjectInput(new Arco(10, 8));*/
        //

        for ( SmallBoard b: WorldGame.getInstance().getBigBoard().getSmallBoards()){
            for (int i = 0; i<3; i++){
                for(int j= 0; j<3; j++){
                    //aggiungi classe per elemento della board
                }
            }
        }

        //eventuali altri predicati da aggiungere da input

        handler.addProgram(program);
        System.out.println(program.getPrograms());
        Output output = handler.startSync();
        //ASPMapper.getInstance().registerClass(InCricca.class); //Esempio registrare classe per EmbAsp

        AnswerSets answersets = (AnswerSets) output;

        ArrayList<Integer> indici = new ArrayList<>();

        for(AnswerSet a: answersets.getOptimalAnswerSets()){
            ArrayList<String> elements = new ArrayList<String>();
            System.out.println(a.toString());
            try {
                for(Object obj:a.getAtoms()){
                    //Scartiamo tutto ci� che non � un oggetto della classe Cell
                    //if(!(obj instanceof InCricca)) continue;
                    //Convertiamo in un oggetto della classe Cell e impostiamo il valore di ogni cella
                    //nella matrice rappresentante la griglia del Sudoku
                    //InCricca c= (InCricca) obj;
                    //elements.add(c.toString());
                    //System.out.println("cricca dim "+ elements.size() );
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return indici;
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
        }

        program.addObjectInput(new Marker(WorldGame.getInstance().getUserToPlay()));
        program.addObjectInput(new EnemyMarker(WorldGame.getInstance().getUserToPlay()*-1));
        program.addObjectInput(new GridToPlay(WorldGame.getInstance().getBigBoard().getNextBoard()));
        //eventuali altri predicati da aggiungere da input

        handler.addProgram(program);
        Output output = handler.startSync();
        ASPMapper.getInstance().registerClass(InsertMarker.class);

        AnswerSets answersets = (AnswerSets) output;
        System.out.println(output.getOutput());

        InsertMarker marker;

        for (AnswerSet a : answersets.getOptimalAnswerSets()) { //getOptimalAnswerSet da usare
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
