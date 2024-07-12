package it.unical.informatica.studenti.Model.ClassiEmbASP.QueryQueens;


import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

import java.util.ArrayList;
import java.util.List;

@Id("mossa_definitiva")
public class Mossa_definitiva {

    @Param(0)
    private int i;

    @Param(1)
    private int j;

    @Param(2)
    private int id;

    public Mossa_definitiva(int _i,int _j, int _id){
        i = _i;
        j = _j;
        id = _id;
    }

    public Mossa_definitiva(){}

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getInsertData(){
        return new ArrayList<>(List.of(i,j,id));
    }
}
