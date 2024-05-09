package it.unical.informatica.studenti.Model;

public class InputManager {
    private static InputManager instance = null;
    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    private InputManager() {}

    public void inputPlayer(){
        System.out.println("inputPlayer");
    }

    public void inputEmbasp(){
        System.out.println("inputEmbasp");
    }

}
