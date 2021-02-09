package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Item item1;
    Item item2;
    Inventory inventory;


    @BeforeEach
    void runBefore() {
        item1 = new Item("Lily Pad", 2, Frog.STARTING_X_POS, Frog.STARTING_Y_POS);
        item2 = new Item("Fly", 1, 2, 3);
        inventory = new Inventory();
    }

    @Test
    void testConstructor(){
        assertEquals(0,inventory.list.size());
    }

    @Test
    void testAddItem(){
        inventory.addItem(item1);
        assertTrue(inventory.list.contains(item1));
        assertEquals(1,inventory.list.size());
        inventory.addItem(item2);
        assertTrue(inventory.list.contains(item2));
        assertEquals(2,inventory.list.size());
    }

    @Test
    void testTotalBonus(){
        inventory.addItem(item1);
        assertEquals(2,inventory.totalBonus());
        inventory.addItem(item2);
        assertEquals(3,inventory.totalBonus());
    }

    @Test
    void testListNames(){
        inventory.addItem(item1);
        assertEquals("Your Inventory: Lily Pad, ",inventory.listNames());
        inventory.addItem(item2);
        assertEquals("Your Inventory: Lily Pad, Fly, ",inventory.listNames());
    }

    @Test
    void testClear(){
        inventory.addItem(item1);
        inventory.addItem(item2);
        assertEquals(2,inventory.list.size());
        inventory.clear();
        assertEquals(0,inventory.list.size());
    }

}
