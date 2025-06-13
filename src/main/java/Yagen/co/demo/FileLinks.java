package Yagen.co.demo;

import java.io.File;
import java.util.ArrayList;

public class FileLinks {
    public static final String CLASSICAL_DIRECTORY = "";//Link to .mp3
    public static final String COUNTRY_DIRECTORY = "";//Link to .mp3
    public static final String JAZZ_DIRECTORY = "";//Link to .mp3
    public static final String ROCK_DIRECTORY = "";//Link to .mp3

    private static ArrayList<String> tracks = new ArrayList<>();

    public static String getAudioLink(Integer numberOfTrack, String Genre) {
        return Genre + tracks.get(numberOfTrack);
    }

    public void scanDirectoryForTracks(String directoryPath) {
        tracks.clear();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    tracks.add(file.getName());
                }
            }
        }
    }
}
