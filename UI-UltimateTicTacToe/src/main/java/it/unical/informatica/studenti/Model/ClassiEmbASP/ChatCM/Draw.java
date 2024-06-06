package it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("Draw")
public class Draw {

    @Param(0)
    int Id;

    public Draw(int id){
        Id=id;
    }

    public Draw(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
