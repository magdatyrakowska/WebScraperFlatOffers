package webscrapper.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscrapper.application.model.Category;
import webscrapper.application.model.SearchOption;
import webscrapper.application.model.SearchOptions;

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

        searchOptions.add(new SearchOption("ALL_BUILDINGS", Category.BUILDING, "wszystkie", "all"));
        searchOptions.add(new SearchOption("FLAT", Category.BUILDING, "blok", "flat"));
        searchOptions.add(new SearchOption("TENEMENT", Category.BUILDING, "kamienica", "tenement"));
        searchOptions.add(new SearchOption("HOUSE", Category.BUILDING, "dom wolnostojący", "house"));
        searchOptions.add(new SearchOption("ROW_HOUSE", Category.BUILDING, "szeregowiec", "row house"));
        searchOptions.add(new SearchOption("APARTMENT", Category.BUILDING, "apartamentowiec", "apartment building"));
        searchOptions.add(new SearchOption("LOFT", Category.BUILDING, "loft", "loft"));
        searchOptions.add(new SearchOption("OTHER_BUILDINGS", Category.BUILDING, "pozostałe", "other"));

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