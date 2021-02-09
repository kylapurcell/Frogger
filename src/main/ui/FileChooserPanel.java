package ui;

import model.FroggerGame;
import persistence.ReadJson;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// the panel in which the save and load buttons are displayed
// this class is inspired by this JFileChooser tutorial
// https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html#JFileChooser--
// as well as SpaceInvaders  https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// and UBC cs RobustTrafficLights starter *link unavailable*

public class FileChooserPanel extends JPanel {
    private Writer writer;
    final JFileChooser fc = new JFileChooser();

    // EFFECTS: creates the load and save buttons, sets the background color and sets the width of the panel
    public FileChooserPanel(ActionListener parent) {
        this.writer = new Writer();
        createButton(this, "Save Your Game",parent);
        createButton(this, "Load Your Game",parent);
        setBackground(new Color(200, 100, 250));
        add(Box.createHorizontalStrut(10));
    }


    // EFFECTS: customizes a button and then returns it
    protected JButton customizeButton(JButton button, String name, ActionListener parent) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setFocusable(false);
        button.setContentAreaFilled(true);
        button.setActionCommand(name);
        button.addActionListener(parent);
        return button;
    }


    // EFFECTS: constructs a button and adds it to the parent panel
    protected void createButton(JComponent parent, String name, ActionListener grandparent) {
        JButton button = new JButton(name);
        button = customizeButton(button, name, grandparent);
        addToParent(parent, button);
    }


    // EFFECTS: adds a button  to the parent panel
    public void addToParent(JComponent parent, JButton button) {
        parent.add(button);
    }


    // EFFECTS: uses file chooser to open a json file and then load a FroggerGame from the file
    public void loadGame() {
        int returnVal = fc.showOpenDialog((this));
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                new Frogger(ReadJson.read(file.getPath()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    // EFFECTS: uses file chooser to save a FroggerGame to a json file
    public void saveGame(FroggerGame game) {
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                game.deleteObserver(Frogger.cw);
                writer.write(game, file + ".json");
                game.addObserver(Frogger.cw);
            } catch (Exception ie) {
                ie.printStackTrace();
            }
        }

    }


}
