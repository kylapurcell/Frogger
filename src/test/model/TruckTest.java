package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TruckTest {
    Truck enemy1;
    Truck enemy2;

    @BeforeEach
    void runBefore() {
        enemy1 = new Truck(7,8);
        enemy2 = new Truck(Frog.STARTING_X_POS,Frog.STARTING_Y_POS);
    }


    @Test
    void testConstructor(){
        assertEquals(7,enemy1.getXCoord());
        assertEquals(8,enemy1.getYCoord());

    }



    @Test
    void testMovement(){
        enemy2.move();
        assertEquals(Frog.STARTING_X_POS - (Enemy.MOVE+2),enemy2.getXCoord());
        enemy1.move();
        assertEquals(7 - (Enemy.MOVE+2), enemy1.getXCoord());
    }
}
