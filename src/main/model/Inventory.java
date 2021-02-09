package model;

import java.util.ArrayList;

// Represents an inventory to fill with items
public class Inventory {
    public ArrayList<Item> list;

    public Inventory() {
        list = new ArrayList<>();
    } // the list to store the inventory items

    // MODIFIES: this
    // EFFECTS: adds an item to the inventory list
    public void addItem(Item i) {
        list.add(i);
    }

    // EFFECTS: returns total sum of bonus points received from items in inventory
    public int totalBonus() {
        int bonus = 0;
        for (Item items: list) {
            bonus = bonus + items.getPoints();
        }
        return bonus;
    }


    // EFFECTS: returns a string of the names of inventory items using the format:
    // "Your Inventory: Item 1, Item 2, Etc"
    public String listNames() {
        String string = "";
        String intro = "Your Inventory: ";
        for (Item items: list) {
            string = string + items.getName() + ", ";
        }
        return intro + string;
    }

    // MODIFIES: this
    // EFFECTS: clears the inventory list to an empty list
    public void clear() {
        list = new ArrayList<>();
    }
}
