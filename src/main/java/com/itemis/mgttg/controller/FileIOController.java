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

    public static String[] getLines(String filename) {
        List<String> allLines = new ArrayList<String>();
        System.out.println("filename " + filename);
        try {
            allLines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return allLines.toArray(new String[0]);
    }
}
