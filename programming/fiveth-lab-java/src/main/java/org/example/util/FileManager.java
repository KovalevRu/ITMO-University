package org.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.StudyGroup;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Vector;

/**
 * Class for managing file operations (reading and writing collection)
 */
public class FileManager {
    private final String fileName;
    private final Gson gson;

    public FileManager(String fileName) {
        this.fileName = fileName;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    /**
     * Reads collection from file
     * @return Vector of StudyGroup objects
     * @throws IOException If file cannot be read
     */
    public Vector<StudyGroup> readCollection() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            return new Vector<>();
        }
        try (FileReader reader = new FileReader(file)) {
            Type collectionType = new TypeToken<Vector<StudyGroup>>(){}.getType();
            Vector<StudyGroup> collection = gson.fromJson(reader, collectionType);
            return collection != null ? collection : new Vector<>();
        }
    }

    /**
     * Writes collection to file
     * @param collection Collection to write
     * @throws IOException If file cannot be written
     */
    public void writeCollection(Vector<StudyGroup> collection) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
            String json = gson.toJson(collection);
            bos.write(json.getBytes());
        }
    }
} 