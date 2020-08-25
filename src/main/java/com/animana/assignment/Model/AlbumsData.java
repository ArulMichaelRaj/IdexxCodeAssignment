package com.animana.assignment.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class AlbumsData {
    private static String albumBaseUrl = "https://itunes.apple.com/search?";

    @Autowired
    private RestTemplate restTemplate;

    public ArrayList<Resources> getAlbums(String searchField, String countryCode) {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        String albumUrl = albumBaseUrl + "term=" + searchField + "&country=" + countryCode;
        LinkedHashMap albums = (LinkedHashMap) restTemplate.getForObject(albumUrl, Object.class);
        ArrayList<Resources> albumResources = transformToResource(albums);
        return albumResources;
    }

    private ArrayList<Resources> transformToResource(LinkedHashMap albums) {
        ArrayList<LinkedHashMap> results;
        try {
            results = (ArrayList<LinkedHashMap>) albums.get("results");
        } catch (NullPointerException e) {
            return null;
        }
        ArrayList<Resources> albumResult = new ArrayList<>();
        for (LinkedHashMap result : results) {
            Resources resources = new Resources();
            resources.setAuthor((String) result.get("artistName"));
            resources.setTitle((String) result.get("collectionName"));
            if (result.get("collectionName") == null) {
                resources.setTitle("Null value");
            }
            resources.setType("Music");
            albumResult.add(resources);
        }

        return albumResult;
    }

}
