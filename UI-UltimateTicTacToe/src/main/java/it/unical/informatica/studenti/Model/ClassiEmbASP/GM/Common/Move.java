package it.unical.informatica.studenti.Model.ClassiEmbASP.GM.Common;

import it.unical.mat.embasp.languages.Param;

public class Move {
    @Param(0)
    protected int B;

    @Param(1)
    protected int X;

    @Param(2)
    protected int Y;

    public Move(int B, int X, int Y){
        this.B = B;
        this.X = X;
        this.Y = Y;
    }

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
        return "move("+B+","+X+","+Y+").";
    }
}
