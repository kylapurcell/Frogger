/*
package ui;

import model.FroggerGame;
import persistence.*;

import java.io.IOException;
import java.util.Scanner;

// Frogger Game application
// this UI is heavily inspired by the Teller Application
// found here:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/master/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
public class Frogger {
    private FroggerGame game;
    private boolean keepGoing;
    private Scanner input;

    // EFFECTS: runs the frogger application
    // IOException if an exception is raised when writing to a json file or reading a json file
    public Frogger() throws IOException {
        runFrogger();

    }
    // MODIFIES: this
    // EFFECTS: loads game, processes user input, updates the game after input and prints out map/instructions
    // saves game when game is over
    // IOException if an exception is raised when writing to a json file or reading a json file

    public void runFrogger() throws IOException {
        input = new Scanner(System.in);
        this.game = loadGame();
        keepGoing = true;
        while (keepGoing) {
            game.update();
            displayMenu();

            if (!game.getGameOver()) {
                String command = input.next();
                command = command.toUpperCase();
                if (quitGame(command)) {
                    keepGoing = false;
                }
                game.movePlayer(command);

            } else {
                System.out.print("You hit a car, continue (Y/N)?");
                String choice = input.next();
                choice = choice.toUpperCase();
                if (!gameOverMessage(choice)) {
                    keepGoing = false;

                }


            }


        }
        saveGame();
    }


    // EFFECTS: displays menu options to user
    public void displayMenu() {
        System.out.print("Welcome to Frogger \n");
        System.out.print("Controls: W: Up, S: Down, A: Left, D: Right Q: Quit \n");
        System.out.print(game.getPlayer().items.listNames() + "\n");
        System.out.print("Score: " + game.getPlayer().getScore() + " High Score: " + game.getHighScore());
        printMap();
        System.out.print("\n");

    }

    // EFFECTS: if user inputs "Q" returns true, else returns false
    public boolean quitGame(String choice) {
        boolean stop = false;
        if (choice.equals("Q")) {
            stop = true;
        }
        return stop;
    }

    // MODIFIES: this
    // EFFECTS:  sets high score and saves game to a json file
    // IOException if an exception is raised when writing to a json file
    public void saveGame() throws IOException {
        game.setHighestScore();
        System.out.print("What would you like your file to be called? ");
        String name = input.next();
        Writer writer = new Writer(game,"./data/" + name + ".json");
        writer.write();
        System.out.print("You saved your game");

    }


    // EFFECTS: loads game from json file or starts new game if desired file not found
    // IOException if an exception is raised when reading a json file
    public FroggerGame loadGame() throws IOException {
        System.out.print("What is the name of the file you wish to load? ");
        return ReadJson.read("./data/" + input.next() + ".json");
    }

    // MODIFIES: this
    // EFFECTS: if user inputs "Y", resets the froggerGame,
    // else sets keepGoing to false
    public boolean gameOverMessage(String choice) {
        boolean keepGoing = false;
        game.resetGame();
        if (choice.equals("Y")) {
            keepGoing = true;

        }
        return keepGoing;
    }


    // EFFECTS: prints a map for the user to visualize movement with F to represent player,
    // E to represent the enemies and I to represent an item to collect
    public void printMap() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (j % 7 == 0) {
                    System.out.print("\n");
                } else if (j == game.getPlayer().getXCoord() && i == game.getPlayer().getYCoord()) {
                    System.out.print("F");
                } else if (j == game.getEnemy().getXCoord() && i == game.getEnemy().getYCoord()) {
                    System.out.print("E");
                } else if (j == game.getBonus().getXCoord() && i == game.getBonus().getYCoord()) {
                    System.out.print("I");
                }
                System.out.print("***");
            }
        }
    }
}



*/
