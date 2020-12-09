package com.upb.snowden.impln.models;

public class Fact {

    private String id;
    private String fact;
    private double value;

    public Fact(String id, String fact, double value) {
        this.id = id;
        this.fact = fact;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getFact() {
        return fact;
    }

    public double getValue() {
        return value;
    }
}
