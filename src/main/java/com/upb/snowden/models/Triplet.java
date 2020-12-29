package com.upb.snowden.models;

public class Triplet {

    private String subject;
    private String predicate;
    private String alternatePredicate;
    private String object;

    public Triplet(String subject, String predicate, String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    public String getAlternatePredicate() {
        return alternatePredicate;
    }

    public void setAlternatePredicate(String alternatePredicate) {
        this.alternatePredicate = alternatePredicate;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getSubject() {
        return subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "Triplet{" +
                "subject='" + subject + '\'' +
                ", predicate='" + predicate + '\'' +
                ", alternate predicate='" + alternatePredicate + '\'' +
                ", object='" + object + '\'' +
                '}';
    }
}
