package com.upb.snowden.parsers;

import com.upb.snowden.Constants;
import org.apache.commons.io.IOUtils;
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

    //construction of URLs
    public static List<String> getAlternativeUrls(String subject) {
        subject = subject.replaceAll(" ","%20");
        List<String> list = new ArrayList<>();
        try {
            String data = IOUtils.toString(URI.create(Constants.ALTERNATIVE_URLS+subject), StandardCharsets.UTF_8);
            //System.out.println(data);
            JSONArray jsonarray = new JSONArray(data);
            String sUrlList = jsonarray.getJSONArray(3).toString();
            String[] array = sUrlList.replace("]","").replace("[","").replaceAll("\"","").split(",");
            list.addAll(Arrays.asList(array));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
