package com.upb.snowden;

public class Constants {
    public static final String BASE_URL = "https://en.wikipedia.org/wiki/";
    public static final String ALTERNATIVE_URLS = "https://en.wikipedia.org/w/api.php?&origin=*&action=opensearch&search=";

    public static final String STOPWORDS_PATH = "src/main/resources/stopwords.txt";

    public static final String TESTING_DATASET_INPUT_PATH_2020 = "src/main/resources/SNLP2020_test.tsv";
    public static final String TESTING_DATASET_OUTPUT_PATH_2020 = "src/main/resources/SNLP2020_test_output.ttl";

    public static final String TRAINING_DATASET_INPUT_PATH_2020 = "src/main/resources/SNLP2020_training.tsv";
    public static final String TRAINING_DATASET_OUTPUT_PATH_2020 = "src/main/resources/SNLP2020_training_output.ttl";

    public static String URI_FACT = "http://swc2017.aksw.org/task2/dataset/";
    public static String URI_TRUTH_VAL = "<http://swc2017.aksw.org/hasTruthValue>";
    public static String DATA_TYPE = "<http://www.w3.org/2001/XMLSchema#double>";

    public static final String AWARD = "award";
    public static final String BORN_IN = "born";
    public static final String DIE_IN = "died";
    public static final String SPOUSE = "spouse";
    public static final String LEADER = "leader";
    public static final String TEAM = "team";
    public static final String FOUND = "found";
    public static final String STARS = "stars";
    public static final String AUTHOR = "author";
    public static final String SUBSIDIARY = "subsidiary";
}
