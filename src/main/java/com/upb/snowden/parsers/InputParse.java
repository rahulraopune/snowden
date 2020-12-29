package com.upb.snowden.parsers;

import com.upb.snowden.Constants;
import com.upb.snowden.models.Triplet;

import java.util.ArrayList;
import java.util.List;

public class InputParse {

    public static List<Triplet> getAllTriplets() {
        //generate all Triplets
        //make this global variable List<Triplet> list
        List<Triplet> list = new ArrayList<>();
        System.out.println("Noch nicht entwickelt");
        return null;
    }

    public Triplet getTriplet(String sentence) {
        String relation = null;
        Triplet triplet = null;
        if (sentence.contains("award is")) {
            triplet =  subjPredObj(sentence, "award is", Constants.AWARD);
        } else if (sentence.contains("honour")) {
            triplet =  ObjPredSubj(sentence, "honour", Constants.AWARD);
            triplet.setAlternatePredicate("honour");
        } else if (sentence.contains("nascence place")) {
            triplet =  ObjPredSubj(sentence, "nascence place", Constants.BORN_IN);
        } else if (sentence.contains("birth place is")) {
            triplet =  subjPredObj(sentence, "birth place is", Constants.BORN_IN);
        } else if (sentence.contains("death place is")) {
            triplet =  subjPredObj(sentence, "death place is", Constants.DIE_IN);
        } else if (sentence.contains("last place")) {
            triplet =  ObjPredSubj(sentence, "last place", Constants.DIE_IN);
        } else if (sentence.contains("spouse is")) {
            triplet =  subjPredObj(sentence, "spouse is", Constants.SPOUSE);
        } else if (sentence.contains("better half")) {
            triplet =  ObjPredSubj(sentence, "better half", Constants.SPOUSE);
        } else if (sentence.contains(" role.")) {
            triplet =  ObjPredSubj(sentence, "role", Constants.LEADER);
        } else if (sentence.contains("office is")) {
            triplet =  subjPredObj(sentence, "office is", Constants.LEADER);
        } else if (sentence.contains("team is")) {
            triplet =  subjPredObj(sentence, "team is", Constants.TEAM);
        } else if (sentence.contains(" squad.")) {
            triplet =  ObjPredSubj(sentence, "squad", Constants.TEAM);
        } else if (sentence.contains("foundation place is")) {
            triplet =  subjPredObj(sentence, "foundation place is", Constants.FOUND);
        } else if (sentence.contains("innovation place")) {
            triplet =  ObjPredSubj(sentence, "innovation place", Constants.FOUND);
        } else if (sentence.startsWith("Stars")) {
            String subject = sentence.substring("Stars ".length(), sentence.indexOf(" has been"));
            String object = sentence.substring(sentence.lastIndexOf(' ') + 1);
            object = preprocessSentence(object);
            triplet = new Triplet(subject, Constants.STARS, object);
        } else if (sentence.contains("stars")) {
            int predIndex = sentence.indexOf("stars");
            String subject = sentence.substring(0, predIndex);
            String object = sentence.substring(predIndex + "stars".length() + 1, sentence.length());
            subject = preprocessSentence(subject);
            object = preprocessSentence(object);
            triplet = new Triplet(subject, Constants.STARS, object);
        } else if (sentence.contains("author is")) {
            triplet =  subjPredObj(sentence, "author is", Constants.AUTHOR);
        } else if (sentence.contains(" generator.")) {
            triplet =  ObjPredSubj(sentence, "generator", Constants.AUTHOR);
            triplet.setAlternatePredicate("writer");
        } else if (sentence.contains("subordinate")) {
            triplet =  exchangeSubjectObject(ObjPredSubj(sentence, "subordinate", Constants.SUBSIDIARY));
        } else if (sentence.contains("subsidiary")) {
//            if (sentence.contains("subsidiary is")) {
                triplet =  exchangeSubjectObject(subjPredObj(sentence, "subsidiary is", Constants.SUBSIDIARY));
                triplet.setAlternatePredicate("owner");
//            } else {
//                triplet =  exchangeSubjectObject(subjPredObj(sentence, "subsidiary", Constants.SUBSIDIARY));
//            }

        } else {
            triplet = new Triplet("","","");
        }
        return triplet;
    }

    public Triplet getTripletNew(String sentence) {
        boolean isInversionRequired = false;
        Triplet triplet = null;
        if (sentence.contains("award is")) {
            isInversionRequired = false;
            triplet =  subjPredObj(sentence, "award is", Constants.AWARD);
        } else if (sentence.contains("honour")) {
            isInversionRequired = true;
            triplet =  ObjPredSubj(sentence, "honour", Constants.AWARD);
            triplet.setAlternatePredicate("honour");
        } else if (sentence.contains("nascence place")) {
            isInversionRequired = true;
            triplet =  ObjPredSubj(sentence, "nascence place", Constants.BORN_IN);
        } else if (sentence.contains("birth place is")) {
            isInversionRequired = false;
            triplet =  subjPredObj(sentence, "birth place is", Constants.BORN_IN);
        } else if (sentence.contains("death place is")) {
            isInversionRequired = false;
            triplet =  subjPredObj(sentence, "death place is", Constants.DIE_IN);
        } else if (sentence.contains("last place")) {
            isInversionRequired = true;
            triplet =  ObjPredSubj(sentence, "last place", Constants.DIE_IN);
        } else if (sentence.contains("spouse is")) {
            isInversionRequired = false;
            triplet =  subjPredObj(sentence, "spouse is", Constants.SPOUSE);
        } else if (sentence.contains("better half")) {
            isInversionRequired = true;
            triplet =  ObjPredSubj(sentence, "better half", Constants.SPOUSE);
        } else if (sentence.contains(" role.")) {
            isInversionRequired = true;
            triplet =  ObjPredSubj(sentence, "role", Constants.LEADER);
        } else if (sentence.contains("office is")) {
            isInversionRequired = false;
            triplet =  subjPredObj(sentence, "office is", Constants.LEADER);
        } else if (sentence.contains("team is")) {
            triplet =  subjPredObj(sentence, "team is", Constants.TEAM);
        } else if (sentence.contains(" squad.")) {
            isInversionRequired = true;
            triplet =  ObjPredSubj(sentence, "squad", Constants.TEAM);
        } else if (sentence.contains("foundation place is")) {
            triplet =  subjPredObj(sentence, "foundation place is", Constants.FOUND);
        } else if (sentence.contains("innovation place")) {
            isInversionRequired = true;
            triplet =  ObjPredSubj(sentence, "innovation place", Constants.FOUND);
        } else if (sentence.startsWith("Stars")) {
            String subject = sentence.substring("Stars ".length(), sentence.indexOf(" has been"));
            String object = sentence.substring(sentence.lastIndexOf(' ') + 1);
            object = preprocessSentence(object);
            triplet = new Triplet(subject, Constants.STARS, object);
        } else if (sentence.contains("stars")) {
            int predIndex = sentence.indexOf("stars");
            String subject = sentence.substring(0, predIndex);
            String object = sentence.substring(predIndex + "stars".length() + 1, sentence.length());
            subject = preprocessSentence(subject);
            object = preprocessSentence(object);
            triplet = new Triplet(subject, Constants.STARS, object);
        } else if (sentence.contains("author is")) {
            triplet =  subjPredObj(sentence, "author is", Constants.AUTHOR);
        } else if (sentence.contains(" generator.")) {
            isInversionRequired = true;
            triplet =  ObjPredSubj(sentence, "generator", Constants.AUTHOR);
        } else if (sentence.contains("subordinate")) {
            isInversionRequired = true;
            triplet =  exchangeSubjectObject(ObjPredSubj(sentence, "subordinate", Constants.SUBSIDIARY));
        } else if (sentence.contains("subsidiary")) {
//            if (sentence.contains("subsidiary is")) {
            triplet =  exchangeSubjectObject(subjPredObj(sentence, "subsidiary is", Constants.SUBSIDIARY));
            triplet.setAlternatePredicate("owner");
//            } else {
//                triplet =  exchangeSubjectObject(subjPredObj(sentence, "subsidiary", Constants.SUBSIDIARY));
//            }

        } else {
            triplet = new Triplet("","","");
        }
        return triplet;
    }

    public Triplet subjPredObj(String sentence, String predicate, String wikiword) {
        int predIndex = sentence.indexOf(predicate);
        String subject = sentence.substring(0, predIndex);
        subject = preprocessSentence(subject);
        String lastword = sentence.substring(sentence.lastIndexOf(" ")) + 1;
        String object = "";
//        if (lastword.equals(predicate)) {
//            object = sentence.substring(sentence.indexOf("is") + "is".length(), predIndex);
//        } else {
            object = sentence.substring(predIndex + predicate.length(), sentence.length());
//        }

        object = preprocessSentence(object);
        return new Triplet(subject, wikiword, object);
    }

    public Triplet ObjPredSubj(String sentence, String predicate, String wikiword) {
        int predIndex = sentence.indexOf(" is");
        String object = sentence.substring(0, predIndex);
        int honIndex = sentence.indexOf(predicate);
        String subject = sentence.substring(predIndex + " is".length(), sentence.length());
        subject = preprocessSentence(subject);
        object = preprocessSentence(object);
        return new Triplet(subject, wikiword, object);
    }

    public String preprocessSentence(String sentence) {
        if (containsApostrophe(sentence)) {
            int apIndex = sentence.lastIndexOf("'");
            sentence = sentence.substring(0, apIndex);
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
    
    
}
