package model;

import java.util.ArrayList;
import java.util.List;

// an abstract class representing an enemy
public abstract class Enemy extends Sprite {
    public static final int SIZE_X = 75; // width of enemy
    public static final int SIZE_Y = 65; // height of enemy
    public static final int MOVE = 3; // movement speed of enemy



    // EFFECTS: sets x and y coordinate to the parameter
    public Enemy(int x, int y) {
        super(x, y, SIZE_X, SIZE_Y);
        this.type = "Enemy";




    }

    public int getYCoord() {
        return ycoord;
    }

    public int getXCoord() {
        return xcoord;
    }

    // MODIFIES: this
    // EFFECTS: moves enemy to the right by the movement speed
    @Override
    public void move() {
        xcoord = xcoord + MOVE;
    }
}
