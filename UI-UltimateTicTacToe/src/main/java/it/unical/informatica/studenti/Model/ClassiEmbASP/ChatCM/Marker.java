package it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("Marker")
public class Marker {

    @Param(0)
    private int marker;

    public int getMarker() {
        return marker;
    }

    public void setMarker(int marker) {
        this.marker = marker;
    }

    public Marker(int mark) {
        marker = mark;
    }

    public Marker(){}

}
