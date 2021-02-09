package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// represents a car enemy
public class Car extends Enemy {


    // EFFECTS: creates a car object, sets x and y to parameter
    public Car(int x, int y) {
        super(x, y);
        this.type = "Car";
    }

    // MODIFIES: g
    // EFFECTS: draw car image onto g
    // method inspired by https://stackoverflow.com/questions/17865465/how-do-i-draw-an-image-to-a-jpanel-or-jframe
    // Throws IOException if image cannot be found or is of incompatible type
    @Override
    public void draw(Graphics g) throws IOException {
        Image img = ImageIO.read(new File("./data/car.png"));
        g.drawImage(img,xcoord,ycoord,SIZE_X,SIZE_Y,null);

    }
}




