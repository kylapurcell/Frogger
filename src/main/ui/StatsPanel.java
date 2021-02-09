package ui;

import model.FroggerGame;
import model.Item;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

// The panel in which the score and inventory are displayed
// inspiration from this class taken from  https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// and JPanel menu tutorials
// https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html


public class StatsPanel extends JPanel {
    private static final String TXT = "Score: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private JLabel scoreLbl;
    JMenuBar menuBar;
    JMenu menu;
    ArrayList<Item> seen;
    ArrayList<JMenu> subs;
    int counter;


    // EFFECTS: sets the background colour and draws the initial menu bar, and score label;
    //          updates this with the game whose score, inventory is to be displayed
    public StatsPanel(FroggerGame game) {
        seen = new ArrayList<>();
        subs = new ArrayList<>();
        counter = 0;
        menuBar = new JMenuBar();
        menu = new JMenu("Inventory");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
        menu.addSeparator();
        setBackground(new Color(100, 200, 100));
        scoreLbl = new JLabel(TXT + game.getPlayer().getScore());
        scoreLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(Box.createHorizontalStrut(10));
        add(scoreLbl);
        add(menuBar);
    }


    // MODIFIES: this
    // EFFECTS:  updates the inventory items collected to the menu and updates the score panel
    // to reflect the current score
    public void update(FroggerGame game, Frogger parent) {
        scoreLbl.setText(TXT + game.getPlayer().getScore());
        for (Item item : game.getPlayer().getItems().list) {
            if (!seen.contains(item)) {
                seen.add(item);
                counter++;
                JMenu submenu = new JMenu(item.getName());
                submenu.setName(item.getName());
                submenu.setMnemonic(KeyEvent.VK_S);
                JMenuItem menuItem = new JMenuItem("Use");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_2, InputEvent.ALT_MASK));
                submenu.add(menuItem);
                menuItem.addActionListener(parent);
                menuItem.setActionCommand("Use" + counter);
                subs.add(submenu);
                menu.add(submenu);
            }

        }
        repaint();
    }

    // MODIFIES: this
   // EFFECTS: uses a menu item in the inventory that adds to the player's score, then
   // removes that item from the inventory.
    public void updateMenu(FroggerGame game, JMenu sub) {
        game.getPlayer().useItem(sub.getName());
        menu.remove(sub);

    }
}






