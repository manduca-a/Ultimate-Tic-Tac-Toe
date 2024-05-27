package it.unical.informatica.studenti.Model.ClassiEmbASP.QueryQueens;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("symbol")
public class symbol {

    @Param(0)
    private String mark;
    @Param(1)
    private int i;
    @Param(2)
    private int j;

    public symbol(String mark, int i, int j) {
        this.mark = mark;
        this.i = i;
        this.j = j;
    }

    public symbol(){}

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    @Override
    public String toString() {
        return "symbol(" +
                "mark='" + mark + '\'' +
                ", i=" + i +
                ", j=" + j +
                ')';
    }
}
