package com.upb.snowden.utils;

import com.upb.snowden.models.Fact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.upb.snowden.Constants.*;

public class FileOperation {

    public static List<Fact> readTsvFile(String path) {
        String contents;
        List<Fact> list = new ArrayList<>();
        try {
            File f = new File(path);
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                while ((contents = br.readLine()) != null) {
                    String[] line_splits = contents.split("\t");
                    String id_fact = line_splits[0];
                    String sent = line_splits[1];
                    double value = 0.0;
                    list.add(new Fact(id_fact,sent,value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeFile(List<Fact> results, String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            int i = 1;
            if (file.exists()) {
                StringBuilder content = new StringBuilder();
                for (Fact f : results) {
                    String output_line = "<" + URI_FACT+ f.getId()+ ">" + URI_TRUTH_VAL + "\"" + f.getValue() + "\""+"^^" + DATA_TYPE + " .\n";
                    content.append(output_line);
                    i++;
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                writer.write(content.toString());
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
