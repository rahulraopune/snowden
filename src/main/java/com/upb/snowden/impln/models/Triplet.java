package com.upb.snowden.impln.models;


// why do you need two similar classes triplet and fact???
//class used for extraction of triplet
public class Triplet {

    private String subject;
    private String predicate;
    private String object;

    public Triplet(String subject, String predicate, String object) {
        this.subject = subject;
        this.predicate = predicate;
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
                ", object='" + object + '\'' +
                '}';
    }
}
