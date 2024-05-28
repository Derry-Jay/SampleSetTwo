package com.set.two.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandleXML {
    public volatile boolean parsingComplete = true;
    private String title = "title";
    private String link = "link";
    private String description = "description";
    private String urlString;
    private XmlPullParserFactory xmlFactoryObject;

    public HandleXML(String url) {
        this.urlString = url;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        try {
            int event;
            String text = null;
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                label:
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "title":
                                title = text;
                                break;
                            case "link":
                                link = text;
                                break;
                            case "description":
                                description = text;
                                break;
                            default:
                                break label;
                        }
                }
                event = myParser.next();
            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchXML() {
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
// Starts the query
                conn.connect();
                InputStream stream = conn.getInputStream();
                xmlFactoryObject = XmlPullParserFactory.newInstance();
                XmlPullParser myParser = xmlFactoryObject.newPullParser();
                myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                myParser.setInput(stream, null);
                parseXMLAndStoreIt(myParser);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}