package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

// represents a sound player observer

public class SoundPlayer implements Observer {
    private AudioInputStream audio;

    // EFFECTS: Plays a sound at observable object event
    @Override
    public void update(Observable subject, Object event) {
        try {
            playSound("GemChest1-1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // MODIFIES: this
    //EFFECTS: If file exists creates a new AudioInputStream and plays the sound
    // Throws IOException if file not found, UnsupportedAudioFileException if audio not supported, and
    // LineUnavailableException if line cannot be opened or is already in use
    // taken from  https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
    void playSound(String name) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File f = new File("./data/" + name + ".wav");
        audio = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }
}
