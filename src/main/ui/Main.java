package ui;

import model.FroggerGame;

import java.io.IOException;

// IOException if an exception is raised when writing to a json file or reading a json file
public class Main {
    public static void main(String[] args) throws IOException {
        new Frogger(new FroggerGame());

    }
}
