package com.animana.assignment.Service;

import com.animana.assignment.Model.Resources;
import com.animana.assignment.Model.AlbumsData;
import com.animana.assignment.Model.BooksData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class SearchBusinessService {
    @Autowired
    AlbumsData albumsData;
    @Autowired
    BooksData booksData;

    private static int limitToDisplay = 5;

    public ArrayList<Resources> getResources(String searchField) {

        //Object albumObject = albumsData.getAlbums(searchField, "nl");
        ArrayList<Resources> bookResources;
        try {
            bookResources = booksData.getBooks(searchField);
        } catch (NullPointerException e) {
            return null;
        }
        ArrayList<Resources> restrictedBookAlbumResources = new ArrayList<>();

        if (bookResources.size() < limitToDisplay) {
            limitToDisplay = bookResources.size();
        }
        for (int i = 0; i < limitToDisplay; i++) {
            restrictedBookAlbumResources.add(bookResources.get(i));
        }
        return restrictedBookAlbumResources;
    }
}
