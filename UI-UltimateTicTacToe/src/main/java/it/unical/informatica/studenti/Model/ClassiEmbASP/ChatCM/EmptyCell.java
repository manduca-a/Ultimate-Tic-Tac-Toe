package it.unical.informatica.studenti.Model.ClassiEmbASP.ChatCM;


import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("EmptyCell")
public class EmptyCell {

        @Param(0)
        private int i;
        @Param(1)
        private int j;
        @Param(2)
        private int id;


        public EmptyCell(int _i, int _j, int _id){
            i =_i;
            j = _j;
            id= _id;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public int getId() {
            return id;
        }
}
