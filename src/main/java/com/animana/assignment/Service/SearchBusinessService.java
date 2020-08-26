package com.animana.assignment.Service;

import com.animana.assignment.Model.Resources;
import com.animana.assignment.Model.AlbumsData;
import com.animana.assignment.Model.BooksData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/*
 * This class acts as the service interface which receives the keyword to be searched in albums/music api
 * Sends the request to Data layer and recieves the resources individually
 * The recieved resources is combined and limited to the number specified in the properties
 *
 * */


@Service
public class SearchBusinessService {
    @Autowired
    AlbumsData albumsData;
    @Autowired
    BooksData booksData;

    private static int limitToDisplay = 5;

    public ArrayList<Resources> getResources(String searchField) {
        ArrayList<Resources> bookResource = null;
        ArrayList<Resources> albumResource = null;
        List<ArrayList<Resources>> bookAndAlbumResourcesList = new ArrayList<>();
        boolean bookResults = true, albumResults = true;

        try {
            albumResource = albumsData.getAlbums(searchField, "nl");
            if(albumResource.size()!=0)
            bookAndAlbumResourcesList.add(0, albumResource);
        } catch (NullPointerException e) {
            bookResults=false;
        }

        try {
            bookResource = booksData.getBooks(searchField);
            if(bookResource.size()!=0)
            bookAndAlbumResourcesList.add(1, bookResource);
        } catch (NullPointerException e) {
            bookResults=false;
        }

        if (bookAndAlbumResourcesList.size()==0){return null;}
        if(bookResults==false & albumResults==false){return null;}

        return this.getRestrictedCombinedResources(bookAndAlbumResourcesList);
    }

    /*
     * This method does restricts the result to the numbers mentioed in properties file
     * */

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
