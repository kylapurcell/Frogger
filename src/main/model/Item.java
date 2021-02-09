package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Represents an item that can be collected for bonus points or other effects
public class Item extends Sprite {
    public static final int SIZE_X = 60; // the width of the item
    public static final int SIZE_Y = 60; // the height of the item
    private String name; // the name of the item
    private int points; // represents how much extra points will add to the score if player makes it across the lake


    // REQUIRES: points must be 1 =<= 5
    // EFFECTS: sets name, points, x coordinate, and y coordinate to the parameter
    public Item(String n, int p, int x, int y) {
        super(x, y, SIZE_X, SIZE_Y);
        this.name = n;
        this.points = p;
        this.type = "Item";
    }

    public int getXCoord() {
        return xcoord;
    }

    public int getYCoord() {
        return ycoord;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    // MODIFIES: g
    // EFFECTS: if item.name == Fly draws fly image onto g, else draws lilypad image onto g
    // method inspired by https://stackoverflow.com/questions/17865465/how-do-i-draw-an-image-to-a-jpanel-or-jframe
    // Throws IOException if images cannot be found or is of incompatible type
    @Override
    public void draw(Graphics g) throws IOException {
        if (this.name.equals("Fly")) {
            Image img = ImageIO.read(new File("./data/fly.png"));
            g.drawImage(img, xcoord, ycoord, SIZE_X, SIZE_Y, null);
        } else {
            Image img = ImageIO.read(new File("./data/lilypad.png"));
            g.drawImage(img, xcoord, ycoord, SIZE_X, SIZE_Y, null);
        }

    }
}
