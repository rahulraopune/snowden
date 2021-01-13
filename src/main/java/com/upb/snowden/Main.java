package com.upb.snowden;

import com.upb.snowden.models.Fact;
import com.upb.snowden.models.Triplet;
import com.upb.snowden.parsers.InputParse;
import com.upb.snowden.parsers.WikiParseUtils;
import com.upb.snowden.utils.ComparisonsUtils;
import com.upb.snowden.utils.FileOperation;
import com.upb.snowden.utils.Logger;
import com.upb.snowden.utils.NetworkUtils;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import static com.upb.snowden.Constants.*;

public class Main {
    public static void main(String[] args) {
        processTrainData();
        processTestData();

        //Logger.log(preprocessFact("1","Johannes Stark's award is Nobel Prize in Literature.").toString());
    }

    private static void processTestData() {
        List<Fact> factsList = FileOperation.readTestFile(Constants.TESTING_DATASET_INPUT_PATH_2020);
        List<Fact> resultFact = new ArrayList<>();
        for (Fact fact: factsList) {
            Fact resultfact = preprocessFact(fact.getId(), fact.getFact());
            Logger.log(resultfact.toString());
            resultFact.add(resultfact);
        }
        FileOperation.writeFile(resultFact,TESTING_DATASET_OUTPUT_PATH_2020);
    }

    private static void processTrainData() {
        List<Fact> factsList = FileOperation.readTestFile(Constants.TRAINING_DATASET_INPUT_PATH_2020);
        List<Fact> resultFact = new ArrayList<>();
        for (Fact fact: factsList) {
            Fact resultfact = preprocessFact(fact.getId(), fact.getFact());
            Logger.log(resultfact.toString());
            resultFact.add(resultfact);
        }
        FileOperation.writeFile(resultFact,TRAINING_DATASET_OUTPUT_PATH_2020);
    }


    public static Fact preprocessFact(String id, String fact) {
        double result = 0.0;
        try {
            InputParse inputParse = new InputParse();
            Triplet triplet = inputParse.getTriplet(fact);
            if (!triplet.getSubject().isEmpty()) {
                List<String> alternativeUrls = WikiParseUtils.getAlternativeUrls(triplet.getSubject());
                for (String url : alternativeUrls) {
                    Logger.log(url);
                    Document document = NetworkUtils.getResponse(url);
                    List<String> infoboxrows = WikiParseUtils.getInfoboxrows(document);
                    if (!infoboxrows.isEmpty()) {
                        for (String row : infoboxrows) {
                            result = ComparisonsUtils.checkPredicateObject(triplet, row);
                            if (result != 0.0)
                                return new Fact(id, fact, result);
                        }

                        List<String> paragraphList = WikiParseUtils.fetchParagraph(document);
                        for (String paragraph: paragraphList) {
                             result = ComparisonsUtils.checkPredicateObject(triplet, paragraph);
                            if (result != 0.0)
                                return new Fact(id, fact, result);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Fact(id, fact, result);
    }
}
