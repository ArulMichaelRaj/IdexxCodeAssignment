package com.animana.assignment.BooksAndAlbums;

import com.animana.assignment.Model.Resources;
import com.animana.assignment.Service.SearchBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class SearchPageController {

    @Autowired
    private SearchBusinessService searchBusinessService;

    @RequestMapping(value = "/welcomepage", method = RequestMethod.GET)
    public String launchPage() {
        return "welcomepage";
    }


    @RequestMapping(value = "/welcomepage", method = RequestMethod.POST)
    public String searchResult(ModelMap modelMap, @RequestParam String searchField) {
        ArrayList<Resources> resources = searchBusinessService.getResources(searchField);
        if (resources == null) {
            modelMap.put("errorMessage", "No values returned for the search criteria");
        } else {

            modelMap.put("resources", resources);
        }
        return "welcomepage";
    }

}
