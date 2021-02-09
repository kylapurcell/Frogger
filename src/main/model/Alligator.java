package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// represents an alligator enemy
public class Alligator extends Enemy {
    private boolean visible;


    // EFFECTS: sets x and y coordinate to the parameter, sets visible to false and type to Alligator
    public Alligator(int x, int y) {
        super(x, y);
        this.visible = false;
        this.type = "Alligator";

    }

    // EFFECTS: returns visible
    public boolean isVisible() {
        return visible;
    }

    // MODIFIES: this
    // EFFECTS: if xcoord is <= 400 sets visible to true, if xcoord is divisible by 13 sets visible to false
    // moves enemy to the right by the movement speed
    @Override
    public void move() {
        if (xcoord <= 400) {
            this.visible = true;
        } else if (xcoord % 13 == 0) {
            this.visible = false;
        }
        super.move();
    }

    // MODIFIES: g
    // EFFECTS: draws an alligator image onto g is visible is true and water image onto g if visible is false
    // method inspired by https://stackoverflow.com/questions/17865465/how-do-i-draw-an-image-to-a-jpanel-or-jframe
    // Throws IOException if images cannot be found or is of incompatible type
    @Override
    public void draw(Graphics g) throws IOException {
        Image img = ImageIO.read(new File("./data/water.png"));
        if (visible) {
            img = ImageIO.read(new File("./data/aligator.gif"));

        }

        g.drawImage(img,xcoord,ycoord,SIZE_X,SIZE_Y,null);


    }
}
