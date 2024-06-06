package it.unical.informatica.studenti.Model.ClassiEmbASP.GM.Common;

import it.unical.mat.embasp.languages.Param;

public class Board {
    @Param(0)
    protected int B;

    public Board(int B){
        this.B = B;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    @Override
    public String toString() {
        return "board("+B+").";
    }
}
