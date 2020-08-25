package com.animana.assignment.Service;

import com.animana.assignment.Model.Resources;
import com.animana.assignment.Model.AlbumsData;
import com.animana.assignment.Model.BooksData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SearchBusinessService {
    @Autowired
    AlbumsData albumsData;
    @Autowired
    BooksData booksData;

    private static int limitToDisplay = 5;
    private static String searchField;

    public ArrayList<Resources> getResources(String searchField) {
        this.searchField = searchField;

        ArrayList<Resources> bookResource;
        ArrayList<Resources> albumResource;
        List<ArrayList<Resources>> bookAndAlbumResourcesList = new ArrayList<>();

        try {
            bookResource = booksData.getBooks(searchField);
        } catch (NullPointerException e) {
            return null;
        }

        try {
            albumResource = albumsData.getAlbums(searchField, "nl");
        } catch (NullPointerException e) {
            return null;
        }

        bookAndAlbumResourcesList.add(0, albumResource);
        bookAndAlbumResourcesList.add(1, bookResource);

        return this.getRestrictedCombinedResources(bookAndAlbumResourcesList);
    }

    public ArrayList<Resources> getRestrictedCombinedResources(List<ArrayList<Resources>> bookAndAlbumResourcesArrayList) {
        int counter = 0;
        ArrayList<Resources> restrictedCombinedResource = new ArrayList<>();
        for (int i = 0; i < bookAndAlbumResourcesArrayList.size(); i++) {
            ArrayList<Resources> individualResource = bookAndAlbumResourcesArrayList.get(i);
            if (individualResource.size() < limitToDisplay) {
                limitToDisplay = individualResource.size();
            }
            for (int j = counter; j < limitToDisplay + counter; j++) {
                restrictedCombinedResource.add(individualResource.get(j));
            }
            counter = limitToDisplay;
        }
        Collections.sort(restrictedCombinedResource, new Comparator<Resources>() {
            @Override
            public int compare(Resources o1, Resources o2) {
                return o1.title.toLowerCase().compareTo(o2.title.toLowerCase());
            }
        });
        return restrictedCombinedResource;
    }
}
