package model;

// from space invaders

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

// Represents a sprite
// This class was taken from Space Invaders Sprite Class https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

public abstract class Sprite {
    protected String type;
    protected int xcoord;
    protected int ycoord;
    protected int width;
    protected int height;


    //Effects: sprite is at the specified location with given width and height and type.
    public Sprite(int x, int y, int width, int height) {
        this.xcoord = x;
        this.ycoord = y;
        this.width = width;
        this.height = height;
        this.type = "Sprite";
    }

    public int getXcoord() {
        return xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    // MODIFIES: this
    // EFFECTS:  sprite has been moved
    public void move() {
        handleBoundary();
    }


    // MODIFIES: g
    // EFFECTS: draws the sprite on the Graphics object g
    public abstract void draw(Graphics g) throws IOException;


    // MODIFIES: this
    // EFFECTS: sprite is constrained to remain within horizontal bounds of game
    protected void handleBoundary() {
        if (xcoord < 0) {
            xcoord = 0;
        } else if (xcoord > FroggerGame.HORIZONTAL_BOUNDARY) {
            xcoord = FroggerGame.HORIZONTAL_BOUNDARY;
        }
    }

}
