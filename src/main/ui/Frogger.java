package ui;

import model.FroggerGame;
import persistence.ReadJson;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

// Represents a game of frogger frame
// inspiration for this class taken from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// and JButton, FileChooser tutorials
// https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
// https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
// and UBC cs RobustTrafficLights starter *link unavailable*

public class Frogger extends JFrame implements ActionListener {

    private static final int INTERVAL = 20;
    private FroggerGame game;
    private GamePanel gp;
    private FileChooserPanel fcp;
    private Timer timer;
    private StatsPanel sp;
    public static SoundPlayer cw = new SoundPlayer();


    // EFFECTS: sets up window in which Frogger Game will be played
    public Frogger(FroggerGame g) {
        super("Frogger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        game = g;
        gp = new GamePanel(game);
        sp = new StatsPanel(game);
        fcp = new FileChooserPanel(this);
        add(gp);
        add(sp, BorderLayout.NORTH);
        add(fcp, BorderLayout.SOUTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        timer.start();
        setResizable(false);
        game.addObserver(cw);


    }


    // EFFECTS:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    // method from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    private void addTimer() {
        timer = new Timer(INTERVAL, ae -> {
            game.update();
            gp.repaint();
            sp.update(game, this);
            if (game.getGameOver()) {
                sp.menu.removeAll();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    // method from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // EFFECTS: responds to key events by moving the player, closing the game or resetting
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());

        }
    }

    // MODIFIES: this
    // EFFECTS: When save button is clicked, saves the game to json file. When load button is clicked starts a
    // game using data from the selected json file When menu is clicked,
    // uses an item in the inventory that adds to the player's score, then
    // removes that item from the inventory.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Load Your Game")) {
            fcp.loadGame();
        } else if (e.getActionCommand().equals("Save Your Game")) {
            fcp.saveGame(game);
        }
        int count = 0;
        for (JMenu sub : sp.subs) {
            count++;
            if (e.getActionCommand().equals("Use" + count)) {
                sp.updateMenu(game, sub);

            }
        }


    }
}
