package it.unical.informatica.studenti.Model;

import it.unical.informatica.studenti.Teams;
import it.unical.informatica.studenti.WorldGame;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.platforms.desktop.DesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

import java.util.ArrayList;

public class EmbaspManager {

    public static ArrayList<Integer> avviaASP(Teams team){
        DesktopService service = new DLV2DesktopService("lib/dlv2.exe");
        Handler handler = new DesktopHandler(service);
        OptionDescriptor option = new OptionDescriptor("-n 1");
        handler.addOption(option);
        InputProgram program = new ASPInputProgram();
        program.addFilesPath("encodings/encoding");
        switch(team){
            case Team1 -> {return AddProgramTeamName(handler,program);}
            case Team2 -> {return AddProgramTeamName(handler,program);}
            case Team3 -> {return AddProgramTeamName(handler,program);}
            //Ogni team implementa il proprio metodo EmbAsp
        }
        return null;
    }

    private static ArrayList<Integer> AddProgramTeamName(Handler handler, InputProgram program){

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

}
