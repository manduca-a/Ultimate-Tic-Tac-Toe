package it.unical.informatica.studenti.Model.ClassiEmbASP.GM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("moveICanWin")
public class MoveICanWin {

    @Param(0)
    private int B;
    @Param(1)
    private int X;
    @Param(2)
    private int Y;

    public MoveICanWin(int B, int X, int Y) {
        this.B = B;
        this.X = X;
        this.Y = Y;
    }

    public MoveICanWin() {}

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "moveICanWin("+B+","+X+","+Y+").";
    }
}