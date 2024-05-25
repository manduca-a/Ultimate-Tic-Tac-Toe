package it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("EnemyMarker")
public class EnemyMarker {

    @Param(0)
    private int marker;
    public int getMarker() {
        return marker;
    }

    public void setMarker(int marker) {
        this.marker = marker;
    }

    public EnemyMarker(int mark) {
        marker = mark;
    }

    public EnemyMarker(){}

    @Override
    public String toString() {
        return "Marker("+marker+").";
    }
}