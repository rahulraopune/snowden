package com.upb.snowden.utils;

import com.upb.snowden.models.Triplet;

public class ComparisonsUtils {

    public static double checkPredicateObject(Triplet triplet, String row){
        double result = 0.0;
        if (ComparisonsUtils.isAlternatePredicatePresent(triplet, row)) {
            return ComparisonsUtils.isObjectPresent(triplet,row) ? 1.0 : -1.0;
        } else if (ComparisonsUtils.isPredicatePresent(triplet, row)) {
            return ComparisonsUtils.isObjectPresent(triplet,row) ? 1.0 : -1.0;
        }
        return result;
    }

    public static boolean isAlternatePredicatePresent(Triplet triplet, String row) {
        if (triplet.getAlternatePredicate() != null && !triplet.getAlternatePredicate().isEmpty())
            return row.toLowerCase().contains(triplet.getPredicate()) || row.toLowerCase().contains(triplet.getAlternatePredicate());
        return false;
    }

    public static boolean isPredicatePresent(Triplet triplet, String row){
        return row.toLowerCase().contains(triplet.getPredicate());
    }

    public static boolean isObjectPresent(Triplet triplet, String row){
        return row.contains(triplet.getObject());
    }
}
