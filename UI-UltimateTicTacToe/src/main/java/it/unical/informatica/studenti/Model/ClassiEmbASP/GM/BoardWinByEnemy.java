package it.unical.informatica.studenti.Model.ClassiEmbASP.GM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("boardWinByEnemy")
public class BoardWinByEnemy {

    @Param(0)
    private int B;

    public BoardWinByEnemy() {}
    public BoardWinByEnemy(int B) {
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
        return "boardWinByEnemy("+B+").";
    }
}
