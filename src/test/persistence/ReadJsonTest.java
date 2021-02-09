package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class ReadJsonTest {

    @Test
    void testDummyConstructor(){
        ReadJson dummy = new ReadJson();
    }

    @Test
    void testReadFile1() {
        try {
            FroggerGame game1 = ReadJson.read("./data/testGame1.json");
            assertEquals(0,game1.getHighScore());
            assertEquals(0,game1.getPlayer().getScore());
            assertEquals(3,game1.getPlayer().getXCoord());
            assertEquals(5,game1.getPlayer().getYCoord());
            assertEquals(2,game1.getPlayer().items.list.size());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testReadFile2() {
        try {
            FroggerGame game2 = ReadJson.read("./data/testGame2.json");
            assertEquals(6,game2.getHighScore());
            assertEquals(6,game2.getPlayer().getScore());
            assertEquals(4,game2.getPlayer().getXCoord());
            assertEquals(5,game2.getPlayer().getYCoord());
            assertEquals(1,game2.getPlayer().items.list.size());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


    @Test
    void testIOException() {
        try {
            ReadJson.read("./path/does/not/exist/testAccount.json");
        } catch (IOException e) {
            // expected
        }
    }
}

