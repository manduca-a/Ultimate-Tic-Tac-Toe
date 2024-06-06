package it.unical.informatica.studenti.Model.ClassiEmbASP.GM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("currentBoard")
public class CurrentBoard {

    @Param(0)
    private int B;

    public CurrentBoard() {}
    public CurrentBoard(int B) {
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
        return "currentBoard("+B+").";
    }
}
