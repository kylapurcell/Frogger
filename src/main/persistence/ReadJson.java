package persistence;

import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import ui.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Observer;

// represents a reader for Json files/ converts Json files into FroggerGame objects
// methods and class inspired by this GSON tutorial https://mkyong.com/java/how-to-parse-json-with-gson/
// deserialization method learned from https://www.baeldung.com/gson-list
public class ReadJson {

    // EFFECTS: returns a FroggerGame object parsed from a json file
    // IOException if an exception is raised when opening / reading from file
    public static FroggerGame read(String name) throws IOException {
        FroggerGame frogger = new FroggerGame();

        Gson gson = new Gson();

        try (Reader reader = new FileReader(name)) {

            // this line is a modified line copied from https://mkyong.com/java/how-to-parse-json-with-gson/
            RuntimeTypeAdapterFactory<Sprite> adapter = RuntimeTypeAdapterFactory.of(Sprite.class, "type")
                    .registerSubtype(Frog.class)
                    .registerSubtype(Enemy.class)
                    .registerSubtype(Car.class)
                    .registerSubtype(Truck.class)
                    .registerSubtype(Alligator.class)
                    .registerSubtype(Item.class);
            gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
            frogger = gson.fromJson(reader,FroggerGame.class);


        } catch (IOException e) {
            System.out.print("We could not find that save file \n We will start you a new game \n");
        }

        return frogger;
    }

}
