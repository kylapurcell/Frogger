package persistence;

import model.Car;
import model.Frog;
import model.FroggerGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {
    private static final String TEST_FILE = "./data/testGame.json";
    private Writer testWriter;
    private FroggerGame game1;
    private Car enemy1;


    @BeforeEach
    void runBefore() {
        game1 = new FroggerGame();
        enemy1 = new Car(1, 6);
        game1.setHighScore(5);
        testWriter = new Writer();

    }

    @Test
    void testWrite() throws IOException {
        testWriter.write(game1,TEST_FILE );
        FroggerGame game2 = ReadJson.read(TEST_FILE);
        assertEquals(5, game2.getHighScore());
        assertEquals(0, game2.getPlayer().getScore());
        assertEquals(Frog.STARTING_X_POS, game2.getPlayer().getXCoord());
        assertEquals(Frog.STARTING_Y_POS, game2.getPlayer().getYCoord());


    }

}