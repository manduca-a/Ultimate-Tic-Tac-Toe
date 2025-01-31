package it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("GridToPlay")
public class GridToPlay {

    @Param(0)
    private int idGrid;

    public GridToPlay(){}

    public GridToPlay(int id) {
        idGrid = id;
    }

    public void setIdGrid(int idGrid) {
        this.idGrid = idGrid;
    }

    public int getIdGrid() {
        return idGrid;
    }

    @Override
    public String toString() {
        return "GridToPlay("+idGrid+").";
    }
}
