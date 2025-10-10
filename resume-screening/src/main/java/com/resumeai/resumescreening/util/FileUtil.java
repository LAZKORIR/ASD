package com.resumeai.resumescreening.util;

import org.apache.tika.Tika;

import java.io.File;

public class FileUtil {

    private static final Tika tika = new Tika();

    public static String extractText(String filePath) {
        try {
            File file = new File(filePath);
            return tika.parseToString(file);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
