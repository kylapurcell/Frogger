package ui;

import model.FroggerGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// represents a game over display screen
// inspiration for this class was taken from the Game Panel class of
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
public class GameOverScreen {
    private static final String OVER = "Game Over!";
    private static final String REPLAY = "Press R to replay";

    // MODIFIES: g
    // EFFECTS:  draws "game over" image and game over text and replay instructions onto g
    // constructor inspired by  https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // Throws IOException if game over image cannot be found or is of incompatible type
    public GameOverScreen(Graphics g) throws IOException {
        Image img = ImageIO.read(new File("./data/frogger_feature.jpg"));
        g.drawImage(img, 0, 0, FroggerGame.HORIZONTAL_BOUNDARY, FroggerGame.VERTICAL_BOUNDARY, null);
        Color saved = g.getColor();
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Monospaced", Font.BOLD, 40));

        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, FroggerGame.VERTICAL_BOUNDARY / 2 + 50);
        centreString(REPLAY, g, fm, FroggerGame.VERTICAL_BOUNDARY / 2 + 80);
        g.setColor(saved);

    }

    // MODIFIES: g
    // EFFECTS:  centres the string str horizontally onto g at vertical position yPosition
    // method from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    private void centreString(String str, Graphics g, FontMetrics fm, int position) {
        int width = fm.stringWidth(str);
        g.drawString(str, (FroggerGame.HORIZONTAL_BOUNDARY - width) / 2, position);
    }
}
