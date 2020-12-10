package com.upb.snowden.impln.utils;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class NetworkUtils {

	// add this to constants.java file
    public static final String BASE_URL = "https://en.wikipedia.org/wiki/";

    public static Document getResponse(String url) {
        Response res = null;
        Document document = null;
        try {
            res = Jsoup.connect(url).execute();
            if (res.statusCode() == 200) {
                String body = res.body();
                document = Jsoup.parseBodyFragment(body);
            }  else {
                System.out.println(url+" not working");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Document getResponseForSubject(String subject) {
        String url = BASE_URL+ subject;
        return getResponse(url);
    }
}
