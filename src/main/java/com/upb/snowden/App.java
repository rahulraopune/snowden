package com.upb.snowden;

import com.upb.snowden.models.Fact;
import com.upb.snowden.utils.FileOperation;
import com.upb.snowden.utils.StopwordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Fact> factsList = FileOperation.readFile(Constants.TRAINING_DATASET_INPUT_PATH);
        List<String> stopwords = StopwordUtils.readStopwords();

        System.out.println(stopwords);

        for (Fact fact: factsList) {
            String factStr = fact.getFact();
            factStr = factStr.replaceAll("'s|'","").replaceAll(",","").replaceAll("\\?","");
            factStr = factStr.substring(0, factStr.length() - 1);
            String[] words = factStr.split(" ");
            List<String> wordsList = new ArrayList<>();
            Arrays.stream(words).forEach(s -> wordsList.add(s));
            //System.out.println(fact.getFact());
            for(int i = 0; i < wordsList.size() ; i++){
                String word = wordsList.get(i);
                if (stopwords.contains(word)) {
                    wordsList.remove(word);
                }
            }
            System.out.println(wordsList);
        }
    }
}
