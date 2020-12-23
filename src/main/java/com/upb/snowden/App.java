package com.upb.snowden;

import com.upb.snowden.models.Triplet;
import com.upb.snowden.parsers.WikiParseUtils;
import com.upb.snowden.utils.NetworkUtils;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.simple.Sentence;
import org.jsoup.nodes.Document;


import java.util.List;
import java.util.function.Consumer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        /*List<Fact> factsList = FileOperation.readFile(Constants.TRAINING_DATASET_INPUT_PATH);
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
        }*/

        String fact = "Einstein was born in Ulm";
        String subj = "Einstein";
        String obj = "Ulm";
        String pred = "born";



        Triplet t = new Triplet(subj,pred,obj);
        List<String> listUrls = WikiParseUtils.getAlternativeUrls(t.getSubject());
        System.out.println("All Urls");
        listUrls.stream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        boolean isFoundinInfobox = false;
        if (!isFoundinInfobox) {
            for (String url: listUrls) {
                System.out.println(url);
                Document document = NetworkUtils.getResponseForSubject(t.getSubject());
                String infobox = WikiParseUtils.getInfobox(document,true);
                String infoboxHtml = WikiParseUtils.getInfobox(document,false);
                System.out.println(infoboxHtml);
                if (infobox.contains(t.getPredicate()) && infobox.contains(t.getObject())) {
                    isFoundinInfobox = true;
                    System.out.println("Infobox True");
                    break;
                } else {
                    System.out.println("Infobox False");
                }
            }
        } else {
            Document document = NetworkUtils.getResponseForSubject(t.getSubject());
            String webpage = WikiParseUtils.getWebpage(document, true);
            if (webpage.contains(t.getPredicate()) && webpage.contains(t.getObject())) {
                System.out.println("True");
            } else {
                isFoundinInfobox = false;
                System.out.println("False");
            }
        }

        /*Document doc = new Document("A Hard Day's Night (film) stars Vanessa Hudgens");
        for (Sentence sent : doc.sentences()) {
            // Iterate over the triples in the sentence
            for (RelationTriple triple : sent.openieTriples()) {
                // Print the triple
                System.out.println(triple.confidence + "\t" +
                        triple.subjectLemmaGloss() + "\t" +
                        triple.relationLemmaGloss() + "\t" +
                        triple.objectLemmaGloss());
            }
        }*/
    }
}
