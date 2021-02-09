package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class FroggerGameTest {
        FroggerGame game1;

        @BeforeEach
        void runBefore() {
           game1 = new FroggerGame();
        }

        @Test
    void testConstructor(){
            assertFalse(game1.getGameOver());
        }

        @Test
        void testKeyPressed()  {
            game1.keyPressed(KeyEvent.VK_KP_LEFT);
            assertEquals(Frog.STARTING_X_POS - 5, game1.getPlayer().getXCoord());
            game1.keyPressed(KeyEvent.VK_KP_UP);
            assertEquals(Frog.STARTING_Y_POS - 5,game1.getPlayer().getYCoord());
            game1.keyPressed(KeyEvent.VK_KP_RIGHT);
            assertEquals(Frog.STARTING_X_POS, game1.getPlayer().getXCoord());
            game1.keyPressed(KeyEvent.VK_KP_DOWN);
            assertEquals(Frog.STARTING_Y_POS,game1.getPlayer().getYCoord());
            game1.getPlayer().setScore(5);
            game1.setGameOver(true);
            game1.keyPressed(KeyEvent.VK_R);
            assertEquals(0,game1.getPlayer().getScore());
        }

        @Test
        void testAddCar() {
            game1.addEnemy(5);
            assertEquals(2,game1.getSprites().size());
            game1.addEnemy(0);
            assertEquals(7,game1.getSprites().size());

        }

        @Test
        void testAddItem(){
            game1.addItem(5);
            assertEquals(2,game1.getSprites().size());
            game1.addItem(0);
            assertEquals(3,game1.getSprites().size());

        }

        @Test
        void testDraw() throws IOException {
            Graphics g = null;
            try {
                game1.draw(g);
                fail("Cannot properly test draw methods");
            } catch (NullPointerException e) {
                // expected
            }
        }


        @Test
    void testCheckEnemyCollision() {
            Car enemy3 = new Car(Frog.STARTING_X_POS,Frog.STARTING_Y_POS);
            Car enemy4 = new Car(Frog.STARTING_X_POS - 1, Frog.STARTING_Y_POS - 2);
            game1.getSprites().add(enemy3);
            game1.getSprites().add(enemy4);
            game1.checkEnemyCollision();
            assertTrue(game1.getGameOver());
            game1.setGameOver(false);
            game1.getSprites().remove(enemy4);
            game1.getSprites().remove(enemy3);
            game1.checkEnemyCollision();
            assertFalse(game1.getGameOver());

        }

        @Test
    void testCheckCollection(){
           Item item1 = new Item("Lily Pad",2,Frog.STARTING_X_POS,Frog.STARTING_Y_POS);
           game1.getSprites().add(item1);
           game1.checkCollection();
           assertTrue(game1.getPlayer().items.list.contains(item1));
           assertEquals(1,game1.getPlayer().items.list.size());
        }

        @Test
    void testCheckGameOver(){
            assertEquals(Frog.STARTING_Y_POS,game1.getPlayer().getYCoord());
            assertEquals(Frog.STARTING_X_POS, game1.getPlayer().getXCoord());
            game1.getPlayer().setScore(3);
            assertEquals(3,game1.getPlayer().getScore());
            game1.setGameOver(true);
            game1.checkGameOver();
            assertEquals(Frog.STARTING_X_POS,game1.getPlayer().getXCoord());
            assertEquals(Frog.STARTING_Y_POS, game1.getPlayer().getYCoord());
            assertEquals( 0, game1.getPlayer().getScore());
        }

        @Test
    void testAddScore(){
            Item item1 = new Item("Lily Pad",2,Frog.STARTING_X_POS,Frog.STARTING_Y_POS);
            game1.getPlayer().setYCoord(FroggerGame.SCORE_INCREASE_BOUNDARY);
            game1.addScore();
            assertEquals(1,game1.getPlayer().getScore());
            assertEquals(Frog.STARTING_Y_POS,game1.getPlayer().getYCoord());
            assertEquals(Frog.STARTING_X_POS, game1.getPlayer().getXCoord());
            game1.getPlayer().addItem(item1);
            game1.getPlayer().setYCoord(FroggerGame.SCORE_INCREASE_BOUNDARY);
            game1.addScore();
            assertEquals(2,game1.getPlayer().getScore());
        }
        @Test
    void testUpdate(){
            game1.update();
            game1.getPlayer().setScore(2);
            game1.setGameOver(true);
            game1.update();
            assertEquals(0,game1.getPlayer().getScore());

        }

        @Test
    void testResetGame() {
            Car enemy1 = new Car(7,8);
            game1.resetGame();
            assertFalse(game1.getGameOver());
        }

        @Test
    void testSetHighestScore() {
            game1.getPlayer().setScore(6);
            game1.setHighScore(2);
            game1.setHighestScore();
            assertEquals(6,game1.getHighScore());
            game1.getPlayer().setScore(4);
            game1.setHighestScore();
            assertEquals(6,game1.getHighScore());
        }
}
