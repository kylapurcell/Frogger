package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

// Represents a game of Frogger
// Inspiration for the structure of this class taken from Space Invaders SI Game class
// Space Invaders: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
public class FroggerGame extends Observable {
    public static final int HORIZONTAL_BOUNDARY = 1200; // provides the horizontal boundary/width of the game
    public static final int VERTICAL_BOUNDARY = 1000; // provides the vertical boundary/height of the game
    public static final int SCORE_INCREASE_BOUNDARY = 0; // determines the y-coordinate the score will increase at
    public static final int CAR_X_POSITION = 0; // determines the car's initial x-position
    public static final int CAR_Y_POSITION = VERTICAL_BOUNDARY / 2; // determines the car's y-position
    public static final int ITEM_X_POSITION = 450; // determines the item's x-position
    public static final int ITEM_Y_POSITION = 350; // determines the item's y-position
    public static final Random RND = new Random();


    private List<Sprite> sprites;
    private Frog player;
    private boolean gameOver;
    private int highScore;


    // EFFECTS: creates a car enemy object, a frog player and several items to be placed throughout the map
    // gameOver is also set to false and sets high score
    public FroggerGame() {
        this.player = new Frog(Frog.STARTING_X_POS, Frog.STARTING_Y_POS);
        this.sprites = new ArrayList<>();
        sprites.add(new Item("Fly", 2, ITEM_X_POSITION, ITEM_Y_POSITION));
        sprites.add(new Car(CAR_X_POSITION, CAR_Y_POSITION + 30));
        this.gameOver = false;
        this.highScore = 0;


    }

    public Frog getPlayer() {
        return player;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }


    // MODIFIES: g
    // EFFECTS: draws player and the sprites in the sprite list onto g
    // method inspired by https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // // Throws IOException if images cannot be found or is of incompatible type
    public void draw(Graphics g) throws IOException {
        player.draw(g);
        for (Sprite sprite : sprites) {
            sprite.draw(g);
        }
    }

    // MODIFIES: this
    // EFFECTS: at random interval, adds various enemy type objects to sprite list
    // method inspired by https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    public void addEnemy(int number) {
        if (number < 1) {
            Car car = new Car(CAR_X_POSITION, CAR_Y_POSITION + 170);
            Car car2 = new Car(CAR_X_POSITION, CAR_Y_POSITION + 100);
            Truck truck1 = new Truck(HORIZONTAL_BOUNDARY, CAR_Y_POSITION + 20);
            Alligator ali = new Alligator(CAR_X_POSITION,CAR_Y_POSITION - 200);
            Alligator ali2 = new Alligator(CAR_X_POSITION,CAR_Y_POSITION - 100);
            sprites.add(car);
            sprites.add(car2);
            sprites.add(truck1);
            sprites.add(ali);
            sprites.add(ali2);
        }
    }

    // MODIFIES: this
    // EFFECTS: at random interval, adds item to sprites list at random location in the game map
    // method inspired by https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    public void addItem(int number) {
        if (number < 1) {
            Item i1 = new Item("LilyPad", 2,
                    RND.nextInt(HORIZONTAL_BOUNDARY), RND.nextInt(VERTICAL_BOUNDARY));
            sprites.add(i1);
        }
    }

    // MODIFIES: this
    // EFFECTS: moves enemies in the sprite list
    // method inspired by https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    private void moveEnemies() {
        for (Sprite next : sprites) {
            if (next instanceof Enemy) {
                next.move();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: reacts to key presses and moves player accordingly
    // method inspired by https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {
            player.moveLeft();
        } else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {
            player.moveRight();
        } else if (keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_UP) {
            player.moveUp();
        } else if (keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_DOWN) {
            player.moveDown();
        } else if (keyCode == KeyEvent.VK_R && gameOver) {
            resetGame();
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS:  updates enemy position, checks if score has increased or item collected, adds enemies and items to
    // sprite list
    public void update() {
        checkGameOver();
        checkCollection();
        checkEnemyCollision();
        checkGameOver();
        addScore();
        moveEnemies();
        addEnemy(RND.nextInt(250));
        addItem(RND.nextInt(250));

    }


    // MODIFIES: this
    // EFFECTS:  if player has collided with enemy, gameOver is set to true unless Enemy is Alligator and visible
    public void checkEnemyCollision() {
        for (Sprite next : sprites) {
            if (next instanceof Enemy) {
                if (player.collidedWith(next)) {
                    this.gameOver = !(next instanceof Alligator) || ((Alligator) next).isVisible();
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS:  if the current high score is lower then the player's score sets high score to score
    public void setHighestScore() {
        if (highScore < player.getScore()) {
            this.highScore = player.getScore();
        }
    }

    // MODIFIES: this
    // EFFECTS:  resets the enemy position and sets gameOver to false
    public void resetGame() {
        setHighestScore();
        startGame();
        this.gameOver = false;
    }

    // MODIFIES: this
    // EFFECTS:  starts a new game
    public void startGame() {
        player = new Frog(Frog.STARTING_X_POS, Frog.STARTING_Y_POS);
        sprites.clear();
        sprites.add(new Item("Fly", 2, ITEM_X_POSITION, ITEM_Y_POSITION));
        sprites.add(new Car(CAR_X_POSITION, CAR_Y_POSITION));
    }

    // MODIFIES: this
    // EFFECTS:  if player has collided with an item, item is collected into player inventory
    public void checkCollection() {
        List<Item> remove = new ArrayList<>();
        for (Sprite next : sprites) {
            if (next instanceof Item) {
                if (player.collidedWith(next)) {
                    player.addItem((Item) next);
                    remove.add((Item) next);
                    setChanged();
                    notifyObservers();
                }
            }
        }
        sprites.removeAll(remove);

    }

    // MODIFIES: this
    // EFFECTS:  if the game is over, player position is reset and score is reset to zero
    public void checkGameOver() {
        if (gameOver) {
            startGame();

        }

    }

    // MODIFIES: this
    // EFFECTS:  if player's y coordinate equals or surpasses the score increase boundary,
    // the player's score increases and player's position is reset
    public void addScore() {
        int score = player.getScore();
        if (player.getYCoord() <= SCORE_INCREASE_BOUNDARY) {
            score++;
            player.reset();
            player.setScore(score);
        }

    }
}
