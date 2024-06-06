package it.unical.informatica.studenti.Model.ClassiEmbASP.GM;


import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("boardCanWin")
public class BoardCanWin {
    @Param(0)
    private int B;

    public BoardCanWin() {}
    public BoardCanWin(int B) {
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
        return "boardCanWin("+B+").";
    }
}
