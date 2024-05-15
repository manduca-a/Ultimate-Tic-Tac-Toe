package it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("FullCell")
public class FullCell {

    @Param(0)
    private int i;
    @Param(1)
    private int j;
    @Param(2)
    private int id;
    @Param(3)
    private int marker;

    public FullCell(int _i, int _j, int _id, int mark){
        i =_i;
        j = _j;
        id= _id;
        marker = mark;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getId() {
        return id;
    }

    public int getMarker() {
        return marker;
    }
}