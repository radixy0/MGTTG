package com.itemis.mgttg.controller;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileIOControllerTest {

    @Test
    void getLines_nonExistingFileShouldReturnNull() {
        assertNull(FileIOController.getLines("thisFileDoesNotExist.txt"));
    }

    @Test
    void getLines_existingFileShouldReturnContent(){
        String[] lines = {"test_line_1", "test_line_2"};
        String filename = "src/test/resources/testFile.txt";
        String[] lines_from_file = FileIOController.getLines(filename);
        assertEquals(lines.length, lines_from_file.length);
        for(int i=0; i<lines.length; i++){
            assertEquals(lines[i], lines_from_file[i]);
        }
    }

}