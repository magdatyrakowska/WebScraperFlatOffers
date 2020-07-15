package webscraper.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraper.application.model.Category;
import webscraper.application.model.SearchOption;
import webscraper.application.model.SearchOptions;

import java.util.List;

@Service
public class SearchOptionsService {

    SearchOptions searchOptions;

    @Autowired
    public SearchOptionsService(SearchOptions searchOptions) {
        this.searchOptions = searchOptions;
    }

    public void populate(){
        searchOptions.add(new SearchOption("ALL_SIZES", Category.FLAT_SIZE, "wszystkie", "all"));
        searchOptions.add(new SearchOption("ONE_ROOM", Category.FLAT_SIZE, "kawalerka", "studio"));
        searchOptions.add(new SearchOption("TWO_ROOMS", Category.FLAT_SIZE, "2 pokoje", "2 rooms"));
        searchOptions.add(new SearchOption("THREE_ROOMS", Category.FLAT_SIZE, "3 pokoje", "3 rooms"));
        searchOptions.add(new SearchOption("FOUR_ROOMS_AND_MORE", Category.FLAT_SIZE, "4 i więcej pokojów", "4 and more rooms"));

        searchOptions.add(new SearchOption("all_buildings", Category.BUILDING, "wszystkie", "all"));
        searchOptions.add(new SearchOption("blok", Category.BUILDING, "blok", "flat"));
        searchOptions.add(new SearchOption("kamienica", Category.BUILDING, "kamienica", "tenement"));
        searchOptions.add(new SearchOption("wolnostojacy", Category.BUILDING, "dom wolnostojący", "house"));
        searchOptions.add(new SearchOption("szeregowiec", Category.BUILDING, "szeregowiec", "row house"));
        searchOptions.add(new SearchOption("apartamentowiec", Category.BUILDING, "apartamentowiec", "apartment building"));
        searchOptions.add(new SearchOption("loft", Category.BUILDING, "loft", "loft"));
        searchOptions.add(new SearchOption("pozostale", Category.BUILDING, "pozostałe", "other"));

        searchOptions.add(new SearchOption("ALL_LEVELS", Category.LEVEL, "wszystkie", "all"));
        searchOptions.add(new SearchOption("BASEMENT", Category.LEVEL, "suterena", "basement"));
        searchOptions.add(new SearchOption("GROUND_FLOOR", Category.LEVEL, "parter", "ground floor"));
        searchOptions.add(new SearchOption("1ST_FLOOR", Category.LEVEL, "1 piętro", "1st floor"));
        searchOptions.add(new SearchOption("2ND_FLOOR", Category.LEVEL, "2 piętro", "2nd floor"));
        searchOptions.add(new SearchOption("3RD_FLOOR", Category.LEVEL, "3 piętro", "3rd floor"));
        searchOptions.add(new SearchOption("4TH_FLOOR", Category.LEVEL, "4 piętro", "4th floor"));
        searchOptions.add(new SearchOption("5TH_FLOOR", Category.LEVEL, "5 piętro", "5th floor"));
        searchOptions.add(new SearchOption("6TH_FLOOR", Category.LEVEL, "6 piętro", "6th floor"));
        searchOptions.add(new SearchOption("7TH_FLOOR", Category.LEVEL, "7 piętro", "7th floor"));
        searchOptions.add(new SearchOption("8TH_FLOOR", Category.LEVEL, "8 piętro", "8th floor"));
        searchOptions.add(new SearchOption("9TH_FLOOR", Category.LEVEL, "9 piętro", "9th floor"));
        searchOptions.add(new SearchOption("10TH_FLOOR", Category.LEVEL, "10 piętro", "10th floor"));
        searchOptions.add(new SearchOption("ABOVE_10", Category.LEVEL, "powyżej 10 piętra", "above 10th floor"));
        searchOptions.add(new SearchOption("ATTIC", Category.LEVEL, "poddasze", "attic"));

        searchOptions.add(new SearchOption("ALL_FURNISHINGS", Category.FURNISHINGS, "wszystkie", "all"));
        searchOptions.add(new SearchOption("YES_FURNISHED", Category.FURNISHINGS, "tak", "yes"));
        searchOptions.add(new SearchOption("NO_FURNISHED", Category.FURNISHINGS, "nie", "no"));

        searchOptions.add(new SearchOption("ANY_AREAS", Category.AREA, "dowolne", "any"));
        searchOptions.add(new SearchOption("25M2", Category.AREA, "25m\u00B2", "25m\u00B2"));
        searchOptions.add(new SearchOption("40M2", Category.AREA, "40m\u00B2", "40m\u00B2"));
        searchOptions.add(new SearchOption("50M2", Category.AREA, "50m\u00B2", "50m\u00B2"));
        searchOptions.add(new SearchOption("60M2", Category.AREA, "60m\u00B2", "60m\u00B2"));
        searchOptions.add(new SearchOption("70M2", Category.AREA, "70m\u00B2", "70m\u00B2"));
        searchOptions.add(new SearchOption("80M2", Category.AREA, "80m\u00B2", "80m\u00B2"));
        searchOptions.add(new SearchOption("100M2", Category.AREA, "100m\u00B2", "100m\u00B2"));
        searchOptions.add(new SearchOption("120M2", Category.AREA, "120m\u00B2", "120m\u00B2"));
        searchOptions.add(new SearchOption("150M2", Category.AREA, "150m\u00B2", "150m\u00B2"));

    }

    public List<SearchOption> findAll() {
        return searchOptions.findAll();
    }

    public List<SearchOption> findByCategory(Category category) {
        return searchOptions.findByCategory(category);
    }

    public SearchOption findById(String id) {
        return searchOptions.findById(id);
    }

}