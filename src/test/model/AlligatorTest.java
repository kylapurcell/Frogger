package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlligatorTest {
    Alligator enemy1;
    Alligator enemy2;

    @BeforeEach
    void runBefore() {
        enemy1 = new Alligator(517,8);
        enemy2 = new Alligator(200,Frog.STARTING_Y_POS);
    }


    @Test
    void testConstructor(){
        assertEquals(517,enemy1.getXCoord());
        assertEquals(8,enemy1.getYCoord());
        assertFalse(enemy1.isVisible());
        assertFalse(enemy1.isVisible());


    }



    @Test
    void testMovement(){
        enemy2.move();
        assertEquals(200 + Enemy.MOVE,enemy2.getXCoord());
        assertTrue(enemy2.isVisible());
        enemy1.move();
        assertEquals(517 + Enemy.MOVE, enemy1.getXCoord());
        assertFalse(enemy1.isVisible());
    }
}
