package it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

import java.util.ArrayList;
import java.util.List;

@Id("InsertMarker")
public class InsertMarker {

    @Param(0)
    private int i;

    @Param(1)
    private int j;

    @Param(2)
    private int id;

    @Param(3)
    private int marker;

    public InsertMarker(int _i,int _j, int _id, int _marker){
        i = _i;
        j = _j;
        id = _id;
        marker = _marker;
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

    public ArrayList<Integer> getInsertData(){
        return new ArrayList<>(List.of(i,j,id,marker));
    }
}