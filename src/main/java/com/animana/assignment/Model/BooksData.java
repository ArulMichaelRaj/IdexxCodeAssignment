package com.animana.assignment.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * This class consumes the book api and stores only Title, Author & Type to the Resources model
 * */

@Repository
public class BooksData {

    private static String bookBaseUrl = "https://www.googleapis.com/books/v1/volumes?q=";

    @Autowired
    private RestTemplate restTemplate;

    public ArrayList<Resources> getBooks(String searchField) {
        String bookUrl = bookBaseUrl + searchField;
        LinkedHashMap books = restTemplate.getForObject(bookUrl, LinkedHashMap.class);
        ArrayList<Resources> bookResources;
        bookResources = transformToResource(books);
        return bookResources;
    }

    private ArrayList<Resources> transformToResource(LinkedHashMap books) {
        ArrayList<LinkedHashMap> items;
        try {
            items = (ArrayList<LinkedHashMap>) books.get("items");
        } catch (NullPointerException e) {
            return null;
        }
        ArrayList<Resources> result = new ArrayList<>();
        for (LinkedHashMap item : items) {
            LinkedHashMap volumeInfo = (LinkedHashMap) item.get("volumeInfo");
            Resources resources = new Resources();
            resources.setTitle((String) volumeInfo.get("title"));
            if (volumeInfo.get("title") == null) {
                resources.setTitle("Null value");
            }
            ArrayList<String> authors = (ArrayList<String>) volumeInfo.get("authors");
            resources.setAuthor(authors.get(0));
            resources.setType("Books");
            result.add(resources);
        }

        return result;
    }
}
