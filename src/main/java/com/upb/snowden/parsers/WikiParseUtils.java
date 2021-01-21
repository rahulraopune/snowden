package com.upb.snowden.parsers;

import com.upb.snowden.Constants;
import com.upb.snowden.utils.NetworkUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class WikiParseUtils {
    //get wiki's infobox
    public static String getInfobox(Document document, boolean asText) {
        String infobox = "";
        Element body = document.body();
        Elements wikitables = body.getElementsByTag("table");

        for (Element table : wikitables) {
            if (table.className().contains("infobox")) {
                infobox = asText ? table.text() : table.html();
                break;
            }
        }
        return infobox;
    }

    public static List<String> getInfoboxrows(Document document) {
        List<String> list = new ArrayList<>();
        String infobox = "";
        Element body = document.body();
        Elements wikitables = body.getElementsByTag("table");
        Elements infoboxrows = null;

        for (Element table : wikitables) {
            if (table.className().contains("infobox")) {
                infoboxrows = table.select("tbody").select("tr");
                break;
            }
        }

        if (infoboxrows != null && !infoboxrows.isEmpty()) {
            for(Element e: infoboxrows) {
                if (e != null) {
                    list.add(e.text());
                }
            }
        }

        return list;
    }

    //get wiki's infobox
    public static String getWebpage(Document document, boolean asText) {
        Element body = document.body();
        return asText ? body.text() : body.html();
    }

    //construction of URLs
    public static List<String> getAlternativeUrls(String subject) {
        subject = StringUtils.stripAccents(subject).replaceAll("ø","o").replaceAll("ł","l");
        subject = subject.replaceAll(" ","%20");
        List<String> list = new ArrayList<>();
        try {
            String data = IOUtils.toString(URI.create(Constants.ALTERNATIVE_URLS + subject), StandardCharsets.UTF_8);
            JSONArray jsonarray = new JSONArray(data);
            JSONArray array = jsonarray.getJSONArray(3);
            for (int i = 0; i < array.length() ; i++) {
                list.add(array.get(i).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> fetchParagraph(Document document) {
        Elements elements = document.select("p");
        List<String> list = new ArrayList<>();
        for (Element e: elements) {
            list.add(e.text());
        }
        return list;
    }
}
