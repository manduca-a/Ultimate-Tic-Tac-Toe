package it.unical.informatica.studenti.Model.ClassiEmbASP.QueryQueens;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("occupiedCell")
public class occupiedCell {

    @Param(0)
    private String mark;
    @Param(1)
    private int i;
    @Param(2)
    private int j;

    public occupiedCell(){}

    public occupiedCell(String _mark, int _i, int _j) {
        mark = _mark;
        i = _i;
        j = _j;
    }

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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "occupiedCell(" +
                "mark='" + mark + '\'' +
                ", i=" + i +
                ", j=" + j +
                ')';
    }
}
