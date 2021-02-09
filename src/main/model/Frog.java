package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Represents the frog character, having a score, x-coordinate, y-coordinate, and inventory of bonus items collected
public class Frog extends Sprite {
    public static final int SIZE_X = 60; // size of the player character width
    public static final int SIZE_Y = 60; // size of player character height
    public static final int STARTING_Y_POS = FroggerGame.VERTICAL_BOUNDARY - SIZE_Y; // players starting position
    public static final int STARTING_X_POS = FroggerGame.HORIZONTAL_BOUNDARY / 2; // players starting position
    public static final int SPEED = 5; // determines how fast the player moves
    private int score;  // tracks user's score (goes up when user makes it across the map)
    public Inventory items; // the items in the player's possession


    /*
     * EFFECTS: score is set to 0, x and y coordinates are set to starting positions,
     * items are set to an empty list of items.
     */
    public Frog(int x, int y) {
        super(STARTING_X_POS,STARTING_Y_POS,SIZE_X,SIZE_Y);
        this.score = 0;
        this.items = new Inventory();
        this.type = "Frog";
    }

    public int getScore() {
        return score;
    }

    public int getXCoord() {
        return xcoord;
    }

    public int getYCoord() {
        return ycoord;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setYCoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public void setXCoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public Inventory getItems() {
        return items;
    }

    // MODIFIES: this
    // EFFECTS: moves player up one space
    public void moveUp() {
        ycoord = ycoord - SPEED;
        move();
    }

    // MODIFIES: this
    // EFFECTS: moves character left one space
    public void moveLeft() {
        xcoord = xcoord - SPEED;
        move();

    }

    // MODIFIES: this
    // EFFECTS: uses specified item in the inventory, adds bonus points to score, then removes specified item
    public void useItem(String item) {
        Item remove = null;
        for (Item use: items.list) {
            if (use.getName().equals(item)) {
                setScore(score + use.getPoints());
                remove = use;
            }
        }
        items.list.remove(remove);
    }

    // MODIFIES: this
    // EFFECTS: moves character right one space
    public void moveRight() {
        xcoord = xcoord + SPEED;
        move();


    }

    // MODIFIES: this
    // EFFECTS: moves character down one space
    public void moveDown() {
        ycoord = ycoord + SPEED;
        move();

    }

    // this method was inspired by/taken from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // from the model/Invader the collidedWith(Missile m) method
    // EFFECTS: returns true if frog has collided with an enemy
    // false otherwise
    public boolean collidedWith(Sprite e) {
        Rectangle frogBoundingRect = new Rectangle(getXCoord() - SIZE_X / 2, getYCoord()
                - SIZE_Y / 2, SIZE_X, SIZE_Y);
        Rectangle collisionBoundingRect = new Rectangle(e.getXcoord()
                - e.getWidth() / 2, e.getYcoord() - e.getHeight() / 2,
                e.getWidth(), e.getHeight());
        return frogBoundingRect.intersects(collisionBoundingRect);
    }


    // MODIFIES: this
    // EFFECTS: adds an item to the frog's inventory
    public void addItem(Item i) {
        items.addItem(i);
    }



    // MODIFIES: this
    // EFFECTS: frog's position is reset to starting
    public void reset() {
        xcoord = STARTING_X_POS;
        ycoord = STARTING_Y_POS;

    }

    // MODIFIES: g
    // EFFECTS: draw frogger image onto g
    // method inspired by https://stackoverflow.com/questions/17865465/how-do-i-draw-an-image-to-a-jpanel-or-jframe
    // Throws IOException if image cannot be found or is of incompatible type
    @Override
    public void draw(Graphics g) throws IOException {
        Image img = ImageIO.read(new File("./data/frogger2.png"));
        g.drawImage(img,xcoord,ycoord,SIZE_X,SIZE_Y,null);


    }
}



