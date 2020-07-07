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
        searchOptions.add(new SearchOption("ALL_SIZES", Category.FLATSIZE, "wszystkie", "all"));
        searchOptions.add(new SearchOption("ONE_ROOM", Category.FLATSIZE, "kawalerka", "studio"));
        searchOptions.add(new SearchOption("TWO_ROOMS", Category.FLATSIZE, "2 pokoje", "2 rooms"));
        searchOptions.add(new SearchOption("THREE_ROOMS", Category.FLATSIZE, "3 pokoje", "3 rooms"));
        searchOptions.add(new SearchOption("FOUR_ROOMS_AND_MORE", Category.FLATSIZE, "4 i więcej pokojów", "4 and more rooms"));
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