package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class FrogTest {
    Frog player;
    Item item1;
    Item item2;
    Car enemy1;
    Car enemy2;

    @BeforeEach
    void runBefore() {
       player = new Frog(Frog.STARTING_X_POS,Frog.STARTING_Y_POS );
       item1 = new Item("Lily Pad",2,Frog.STARTING_X_POS,Frog.STARTING_Y_POS);
       item2 = new Item("Fly", 1,2,3);
       enemy1 = new Car(7,8);
       enemy2 = new Car(Frog.STARTING_X_POS,Frog.STARTING_Y_POS);
    }

    @Test
    void testConstructor() {
    assertEquals(0,player.getScore());
    assertEquals(Frog.STARTING_X_POS,player.getXCoord());
    assertEquals(Frog.STARTING_Y_POS,player.getYCoord());
    assertEquals(0,player.items.list.size());

    }

    @Test
    void testDraw() throws IOException {
        Graphics g = null;
        try {
            player.draw(g);
            fail("Cannot properly test draw methods");
        } catch (NullPointerException e) {
            // expected
        }
    }
    @Test
    void testHandleBoundary() {
        player.setXCoord(-5);
        player.moveUp();
        assertEquals(0,player.getXCoord());
        player.setXCoord(2);
        player.moveDown();
        assertEquals(2,player.getXCoord());
        player.setXCoord(FroggerGame.HORIZONTAL_BOUNDARY + 1);
        player.moveDown();
        assertEquals(FroggerGame.HORIZONTAL_BOUNDARY ,player.getXCoord());
    }
    @Test
    void testUseItem(){
        player.addItem(item1);
        player.addItem(item2);
        assertEquals(2,player.getItems().list.size());
        player.useItem("Fly");
        assertFalse(player.items.list.contains(item2));
        assertEquals(1,player.getScore());
        assertEquals(1,player.getItems().list.size());
    }

    @Test
    void testMovement(){
        player.moveUp();
        assertEquals(Frog.STARTING_Y_POS - Frog.SPEED,player.getYCoord());
        player.moveDown();
        assertEquals(Frog.STARTING_Y_POS,player.getYCoord());
        player.moveRight();
        assertEquals(Frog.STARTING_X_POS + Frog.SPEED,player.getXCoord());
        player.moveLeft();
        assertEquals(Frog.STARTING_X_POS,player.getXCoord());
    }

    @Test
    void testCollideWithEnemy() {
        assertTrue(player.collidedWith(enemy2));
        assertFalse(player.collidedWith(enemy1));
        player.moveUp();
        assertTrue(player.collidedWith(enemy2));
    }

    @Test
    void testCollectedItem() {
        assertTrue(player.collidedWith(item1));
        assertFalse(player.collidedWith(item2));
        player.moveUp();
        assertTrue(player.collidedWith(item1));
    }

    @Test
    void testAddItem(){
        player.addItem(item1);
        assertTrue(player.items.list.contains(item1));
        assertFalse(player.items.list.contains(item2));
        player.addItem(item2);
        assertEquals(2,player.items.list.size());
    }

    @Test
    void testReset(){
        player.moveUp();
        player.moveRight();
        assertEquals(Frog.STARTING_Y_POS - Frog.SPEED,player.getYCoord());
        assertEquals(Frog.STARTING_X_POS + Frog.SPEED,player.getXCoord());
        player.reset();
        assertEquals(Frog.STARTING_Y_POS,player.getYCoord());
        assertEquals(Frog.STARTING_X_POS,player.getXCoord());
    }

}