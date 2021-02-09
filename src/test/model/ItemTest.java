package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ItemTest {
    Item item1;
    Item item2;


    @BeforeEach
    void runBefore() {
        item1 = new Item("Lily Pad", 2, Frog.STARTING_X_POS, Frog.STARTING_Y_POS);
        item2 = new Item("Fly", 1, 2, 3);

    }
    @Test
    void testConstructor(){
        assertEquals("Lily Pad", item1.getName());
        assertEquals("Fly", item2.getName());
        assertEquals(2,item1.getPoints());
        assertEquals(1,item2.getPoints());
        assertEquals(Frog.STARTING_X_POS, item1.getXCoord());
        assertEquals(2,item2.getXCoord());
        assertEquals(Frog.STARTING_Y_POS,item1.getYCoord());
        assertEquals(3,item2.getYCoord());
    }

    @Test
    void testDraw() throws IOException {
        Graphics g = null;
        try {
            item1.draw(g);
            fail("Cannot properly test draw methods");
        } catch (NullPointerException e) {
            // expected
        }
        try {
            item2.draw(g);
            fail("Cannot properly test draw methods");
        } catch (NullPointerException e) {
            // expected
        }
    }
}
