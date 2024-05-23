package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;

public class HTMLComparator {

    public static void main(String[] args) {
        String file1Path = "D:\\ABC\\sample1.html";
        String file2Path = "D:\\ABC\\sample2.html";

        try {
            Document doc1 = Jsoup.parse(new File(file1Path), "UTF-8");
            Document doc2 = Jsoup.parse(new File(file2Path), "UTF-8");

            if (compareDocuments(doc1, doc2)) {
                System.out.println("The HTML files are identical.");
            } else {
                System.out.println("The HTML files are different.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean compareDocuments(Document doc1, Document doc2) {
        return compareElements(doc1.getElementsByTag("html").first(), doc2.getElementsByTag("html").first());
    }

    private static boolean compareElements(Element elem1, Element elem2) {
        if (!elem1.tagName().equals(elem2.tagName())) {
            System.out.println("elem1"+elem1);
            System.out.println("elem2"+elem2);
            return false;
        }

        // Compare attributes
        if (!elem1.attributes().equals(elem2.attributes())) {
            return false;
        }

        // Compare text content
        if (!elem1.text().equals(elem2.text())) {
            return false;
        }

        // Compare child elements
        Elements children1 = elem1.children();
        Elements children2 = elem2.children();

        if (children1.size() != children2.size()) {
            return false;
        }

        for (int i = 0; i < children1.size(); i++) {
            Element child1 = children1.get(i);
            Element child2 = children2.get(i);
            if (!compareElements(child1, child2)) {
                return false;
            }
        }

        return true;
    }
}