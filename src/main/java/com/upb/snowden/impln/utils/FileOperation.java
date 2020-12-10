package com.upb.snowden.impln.utils;

import com.upb.snowden.impln.models.Fact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperation {

	// add this to constants.java
    public static String URI_FACT = "http://swc2017.aksw.org/task2/dataset/";
    public static String URI_TRUTH_VAL = "<http://swc2017.aksw.org/hasTruthValue>";
    public static String DATA_TYPE = "<http://www.w3.org/2001/XMLSchema#double>";

    public static List<Fact> readFile(String path) {
        String contents = "";
        List<Fact> list = new ArrayList<>();
        try {
            File f = new File(path);
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                br.readLine(); //ignore first line
                while ((contents = br.readLine()) != null) {
                    String[] line_splits = contents.split("\t");
                    String id_fact = line_splits[0];
                    String sent = line_splits[1];
                    double value = Double.parseDouble(line_splits[2]);
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
                String content = "";
                for (Fact f : results) {
                    String output_line = "<" + URI_FACT+ f.getId()+ ">" + URI_TRUTH_VAL + "\"" + f.getValue() + "\""+"^^" + DATA_TYPE + " .\n";
                    content+=output_line;
                    i++;
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                writer.write(content);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
