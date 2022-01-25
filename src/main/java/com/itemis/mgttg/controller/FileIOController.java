package com.itemis.mgttg.controller;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileIOController {

    /**
     * Gets content from a File as Array of lines
     * @param filename the file to read
     * @return String array of lines, null if the file doesn't exist
     */
    public static String[] getLines(String filename) {
        List<String> allLines;
        try {
            allLines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            return null;
        }
        return allLines.toArray(new String[0]);
    }
}
