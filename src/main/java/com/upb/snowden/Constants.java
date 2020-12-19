package com.upb.snowden;

public class Constants {
    public static final String BASE_URL = "https://en.wikipedia.org/wiki/";
    public static final String ALTERNATIVE_URLS = "https://en.wikipedia.org/w/api.php?&origin=*&action=opensearch&search=";
    public static final String TRAINING_DATASET_INPUT_PATH = "src/main/resources/SNLP2019_training.tsv";
    public static final String TRAINING_DATASET_OUTPUT_PATH = "src/main/resources/SNLP2019_training_output.tsv";

    public static String URI_FACT = "http://swc2017.aksw.org/task2/dataset/";
    public static String URI_TRUTH_VAL = "<http://swc2017.aksw.org/hasTruthValue>";
    public static String DATA_TYPE = "<http://www.w3.org/2001/XMLSchema#double>";
}
