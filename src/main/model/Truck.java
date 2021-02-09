package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

// represents a truck enemy
public class Truck extends Enemy {

    // EFFECTS: sets x and y coordinates and type to "Truck"
    public Truck(int x, int y) {
        super(x, y);
        this.type = "Truck";
    }


    // MODIFIES: this
    // EFFECTS: moves enemy to the left by the movement speed plus 2
    @Override
    public void move() {
        xcoord = xcoord - (MOVE + 2);
    }

    // MODIFIES: g
    // EFFECTS: draw truck image onto g
    // method inspired by https://stackoverflow.com/questions/17865465/how-do-i-draw-an-image-to-a-jpanel-or-jframe
    // Throws IOException if image cannot be found or is of incompatible type
    @Override
    public void draw(Graphics g) throws IOException {
        Image img = ImageIO.read(new File("./data/firetruck.png"));
        g.drawImage(img, xcoord, ycoord, SIZE_X, SIZE_Y, null);
    }
}
