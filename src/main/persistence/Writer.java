package persistence;

import model.*;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


// Represents a json file writer/writes a FroggerGame object to a json file
// methods/inspiration for this class taken from this GSON tutorial https://mkyong.com/java/how-to-parse-json-with-gson/

public class Writer {
    private Gson gson;


    // EFFECTS: constructs a writer that will write data to a json file
    public Writer()  {

        // this line is copied from https://mkyong.com/java/how-to-parse-json-with-gson/
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // MODIFIES: this
    // EFFECTS: writes a FroggerGame object to a json file
    // IOException if an exception is raised when writing to a json file
    public void write(FroggerGame fg, String name) throws IOException {
        FileWriter writer = new FileWriter(name);
        gson.toJson(fg, writer);
        writer.close();
    }




}
