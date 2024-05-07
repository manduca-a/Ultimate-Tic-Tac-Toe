package it.unical.informatica.studenti.Model;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
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

    public void avvia(){
        DesktopService service = new DLV2DesktopService("lib/dlv2.exe");
        Handler handler = new DesktopHandler(service);
        OptionDescriptor option = new OptionDescriptor("-n 0");
        handler.addOption(option);
        InputProgram program = new ASPInputProgram();
        program.addFilesPath("encodings/encoding");
        /*for (int i =0;i<3;i++)
            for (int j =5;j<7;j++)
                program.addObjectInput(new Arco(i, j));
        program.addObjectInput(new Arco(8, 9));
        program.addObjectInput(new Arco(9, 10));
        program.addObjectInput(new Arco(10, 8));*/
        handler.addProgram(program);
        System.out.println(program.getPrograms());
        Output output = handler.startSync();
        //ASPMapper.getInstance().registerClass(InCricca.class);

        AnswerSets answersets = (AnswerSets) output;

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
            for (String e : elements) {
                System.out.println(e);

            }
        }
    }

}
