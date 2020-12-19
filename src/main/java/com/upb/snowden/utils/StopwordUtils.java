package com.upb.snowden.utils;

import com.upb.snowden.Constants;
import com.upb.snowden.models.Fact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StopwordUtils {

    public static List<String> readStopwords() {
        String contents = "";
        List<String> list = new ArrayList<>();
        try {
            File f = new File(Constants.STOPWORDS_PATH);
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                br.readLine(); //ignore first line
                while ((contents = br.readLine()) != null) {
                    list.add(contents);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
