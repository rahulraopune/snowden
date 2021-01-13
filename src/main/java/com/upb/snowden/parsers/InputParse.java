package com.upb.snowden.parsers;

import com.upb.snowden.Constants;
import com.upb.snowden.models.Triplet;
import com.upb.snowden.utils.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InputParse {

    public Triplet getTriplet(String sentence) {
        Triplet triplet;
        if (sentence.contains(Constants.AWARD_IS)) {
            triplet =  parseSOP(sentence, Constants.AWARD_IS, Constants.AWARD, false);
        } else if (sentence.contains(Constants.AUTHOR_IS)) {
            triplet =  parseSOP(sentence, Constants.AUTHOR_IS, Constants.AUTHOR, false);
        } else if (sentence.contains(Constants.BIRTH_PLACE_IS)) {
            triplet =  parseSOP(sentence, Constants.BIRTH_PLACE_IS, Constants.BORN_IN, false);
        } else if (sentence.contains(Constants.DEATH_PLACE_IS)) {
            triplet =  parseSOP(sentence, Constants.DEATH_PLACE_IS, Constants.DIE_IN, false);
        } else if (sentence.contains(Constants.FOUNDATION_PLACE_IS)) {
            triplet =  parseSOP(sentence, Constants.FOUNDATION_PLACE_IS, Constants.FOUND, false);
        } else if (sentence.contains(Constants.OFFICE_IS)) {
            triplet =  parseSOP(sentence, Constants.OFFICE_IS, Constants.LEADER, false);
        } else if (sentence.contains(Constants.SPOUSE_IS)) {
            triplet =  parseSOP(sentence, Constants.SPOUSE_IS, Constants.SPOUSE, false);
        } else if (sentence.contains(Constants.SUBSIDIARY)) {
            Triplet temp;
            if(isLastWord(sentence, Constants.SUBSIDIARY)){
                temp = parseSOP(sentence, Constants.SUBSIDIARY, Constants.SUBSIDIARY, true);
                triplet = exchangeSubjectObject(temp);
            }else {
                temp = parseSOP(sentence, Constants.SUBSIDIARY_IS, Constants.SUBSIDIARY, false);
                triplet = exchangeSubjectObject(temp);
            }
            triplet.setAlternatePredicate(Constants.OWNER);
        } else if (sentence.contains(Constants.STARS)) {
            triplet = parseStarSOP(sentence, false);
        } else if (sentence.contains(Constants.TEAM_IS)) {
            triplet =  parseSOP(sentence, Constants.TEAM_IS, Constants.TEAM, false);
        }

        else if (sentence.contains(Constants.BETTER_HALF)) {
            triplet =  parseSOP(sentence, Constants.BETTER_HALF, Constants.SPOUSE, true);
        } else if (sentence.contains(Constants.GENERATOR)) {
            triplet =  parseSOP(sentence, Constants.GENERATOR, Constants.AUTHOR, true);
        } else if (sentence.contains(Constants.HONOUR)) {
            triplet =  parseSOP(sentence, Constants.HONOUR, Constants.AWARD, true);
            triplet.setAlternatePredicate(Constants.HONOUR);
        } else if (sentence.contains(Constants.INNOVATION_PLACE)) {
            triplet =  parseSOP(sentence, Constants.INNOVATION_PLACE, Constants.FOUND, true);
        } else if (sentence.contains(Constants.LAST_PLACE)) {
            triplet =  parseSOP(sentence, Constants.LAST_PLACE, Constants.DIE_IN, true);
        } else if (sentence.contains(Constants.NASCENCE_PLACE)) {
            triplet =  parseSOP(sentence, Constants.NASCENCE_PLACE, Constants.BORN_IN, true);
        } else if (sentence.contains(Constants.SQUAD)) {
            triplet =  parseSOP(sentence, Constants.SQUAD, Constants.TEAM, true);
        } else if (sentence.startsWith(Constants.STARS_PASSIVE)) {
            triplet = parseStarSOP(sentence, true);
        } else if (sentence.contains(Constants.SUBORDINATE)) {
            Triplet temp = parseSOP(sentence, Constants.SUBORDINATE, Constants.SUBSIDIARY, true);
            triplet =  exchangeSubjectObject(temp);
        } else {
            triplet = new Triplet("","","");
        }
        return triplet;
    }

    public Triplet parseSOP(String sentence, String predicate, String wikiword, boolean inversionRequired){
        try {
            if (inversionRequired) {
                int predicateIndex = sentence.indexOf(" " + Constants.IS);
                String object = sentence.substring(0, predicateIndex);
                String subject = sentence.substring(predicateIndex + (" " + Constants.IS).length());
                subject = preprocessSentence(subject);
                object = preprocessSentence(object);
                return new Triplet(subject, wikiword, object);
            } else {
                int predicateIndex = sentence.indexOf(predicate);
                String subject = sentence.substring(0, predicateIndex);
                subject = preprocessSentence(subject);
                String object;
                object = sentence.substring(predicateIndex + predicate.length());
                object = preprocessSentence(object);
                return new Triplet(subject, wikiword, object);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Triplet("","","");
        }
    }

    private Triplet parseStarSOP(String sentence, boolean inversionRequired) {
        Triplet triplet;
        if (inversionRequired) {
            String subject = sentence.substring(Constants.STARS_PASSIVE.length(), sentence.indexOf(Constants.HAS_BEEN));
            String object = sentence.substring(sentence.lastIndexOf(' ') + 1);
            object = preprocessSentence(object);
            triplet = new Triplet(subject, Constants.STARS, object);
        }else {
            int predIndex = sentence.indexOf(Constants.STARS);
            String subject = sentence.substring(0, predIndex);
            String object = sentence.substring(predIndex + Constants.STARS.length() + 1);
            subject = preprocessSentence(subject);
            object = preprocessSentence(object);
            triplet = new Triplet(subject, Constants.STARS, object);
        }
        triplet.setAlternatePredicate(Constants.KNOWN_FOR);
        return triplet;
    }

    public String preprocessSentence(String sentence) {
        if (containsApostrophe(sentence)) {
            int index = sentence.lastIndexOf("'");
            sentence = sentence.substring(0, index);
        }
        sentence = sentence.split(",")[0];
        return sentence.replaceAll("\\.", "").trim();
    }
    
    public boolean containsApostrophe(String sentence) {
        return sentence.contains("'");
    }
    
    public Triplet exchangeSubjectObject(Triplet triplet) {
        String subject = triplet.getSubject();
        triplet.setSubject(triplet.getObject());
        triplet.setObject(subject);
        return triplet;
    }

    public static boolean isLastWord(String text, String word) {
        String[] words = text.replaceAll("\\.","").split(" ");
        List<String> strings = Arrays.asList(words.clone());
        int index = strings.indexOf(word);
        int lastWordindex = strings.size() - 1;
        return index == lastWordindex;
    }

    public static void main(String[] args) {
        System.out.println(isLastWord("Kiel is Johan Christian Fabricius' subsidiary.","subsidiary"));
    }
}
