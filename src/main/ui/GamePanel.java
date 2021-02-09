package ui;

import model.FroggerGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

// The panel in which the game is rendered.
// // inspiration from this class taken from  https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

public class GamePanel extends JPanel {
    protected FroggerGame game;


    // EFFECTS:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    // method from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    public GamePanel(FroggerGame g) {
        setPreferredSize(new Dimension(FroggerGame.HORIZONTAL_BOUNDARY, FroggerGame.VERTICAL_BOUNDARY));
        setBackground(Color.GRAY);
        this.game = g;
    }


    // MODIFIES: g
    // EFFECTS:  paints components onto g and draws game onto g
    // method from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            drawGame(g);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (game.getGameOver()) {
            try {
                new GameOverScreen(g);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // MODIFIES: g
    // EFFECTS:  the game is drawn onto the Graphics object g
    // throws IOException if background image cannot be found or is an incompatible type
    private void drawGame(Graphics g) throws IOException {
        Image img = ImageIO.read(new File("./data/frogger_background.gif"));
        g.drawImage(img, 0, 0, FroggerGame.HORIZONTAL_BOUNDARY, FroggerGame.VERTICAL_BOUNDARY, null);
        game.draw(g);
    }

}