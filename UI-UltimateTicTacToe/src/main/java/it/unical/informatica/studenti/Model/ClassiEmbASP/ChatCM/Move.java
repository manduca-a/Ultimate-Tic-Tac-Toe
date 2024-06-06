package it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("Move")
public class Move {

    @Param(0)
    int TurnNumber;

    public Move(int turn){
        TurnNumber=turn;
    }

    public Move(){}

    public int getTurnNumber() {
        return TurnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        TurnNumber = turnNumber;
    }
}
