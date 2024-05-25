package it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("SubTris")
public class SubTris {

    @Param(0)
    private int IdBoard;

    @Param(1)
    private int Winner;

    public SubTris(){}

    public SubTris(int id, int win){
        IdBoard=id;
        Winner=win;
    }

    public int getIdBoard() {
        return IdBoard;
    }

    public int getWinner() {
        return Winner;
    }

    public void setIdBoard(int idBoard) {
        IdBoard = idBoard;
    }

    public void setWinner(int winner) {
        Winner = winner;
    }
}
