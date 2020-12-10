package com.upb.snowden.impln.parsers;

import com.upb.snowden.impln.models.Triplet;

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
        System.out.println("Not Yet Implemented");
        //rule1 subjPredObj
        //rule2 ObjPredSubj
        return null;
    }

    public Triplet subjPredObj(String sentence) {
        System.out.println("Not Yet Implemented");
        return null;
    }

    public Triplet ObjPredSubj(String sentence) {
        System.out.println("Not Yet Implemented");
        return null;
    }

    public void specificRules() {
        //born in
        //died in and stuff.....
    }
}
