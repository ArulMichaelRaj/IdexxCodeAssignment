package com.animana.assignment.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Repository
public class AlbumsData {
    private static String albumBaseUrl = "https://itunes.apple.com/search?";

    @Autowired
    private RestTemplate restTemplate;

    public Object getAlbums(String searchField, String countryCode) {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        String albumUrl = albumBaseUrl + "term=" + searchField + "&country=" + countryCode;
        Object albums = restTemplate.getForObject(albumUrl, Object.class);
        return albums;
    }


}
