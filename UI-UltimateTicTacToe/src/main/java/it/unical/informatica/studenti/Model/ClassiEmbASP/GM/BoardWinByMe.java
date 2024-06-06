package it.unical.informatica.studenti.Model.ClassiEmbASP.GM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("boardWinByMe")
public class BoardWinByMe {

    @Param(0)
    private int B;

    public BoardWinByMe() {}
    public BoardWinByMe(int B) {
        this.B = B;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {B = b;}
    @Override
    public String toString() {
        return "boardWinByMe("+B+").";
    }
}
