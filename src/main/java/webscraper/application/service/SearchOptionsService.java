package webscraper.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraper.application.model.Category;
import webscraper.application.model.SearchOption;
import webscraper.application.model.SearchOptionRepository;

import java.util.List;

/**
 * Manages search options available in the options.html view, which are then assigned
 * to {@code Form} object using ids.
 * Populates {@code SearchOptionsRepository} with all available search options.
 * Allows to get a list of search options filtered by category or one search option
 * identified by unique id.
 */
@Service
public class SearchOptionsService {

    /**
     * Repository storing {@code SearchOption} objects
     */
    SearchOptionRepository searchOptionRepository;

    /**
     * Assigns an instance of {@code SearchOptionRepository}
     *
     * @param searchOptionRepository instance of {@code SearchOptionRepository} object to be assigned
     */
    @Autowired
    public SearchOptionsService(SearchOptionRepository searchOptionRepository) {
        this.searchOptionRepository = searchOptionRepository;
    }

    /**
     * Initializes {@code SearchOptionRepository} with all available search options.
     * Options are added in blocks according to category. Each one is created
     * with all necessary data: id, category, polish and english description.
     * Included options matches those available on the olx.pl portal in flats rent section.
     * Unique id {@code String} is used also to build query segments in URL.
     */
    public void populate() {
        searchOptionRepository.add(new SearchOption("all_sizes", Category.FLAT_SIZE, "wszystkie", "all"));
        searchOptionRepository.add(new SearchOption("one", Category.FLAT_SIZE, "kawalerka", "studio"));
        searchOptionRepository.add(new SearchOption("two", Category.FLAT_SIZE, "2 pokoje", "2 rooms"));
        searchOptionRepository.add(new SearchOption("three", Category.FLAT_SIZE, "3 pokoje", "3 rooms"));
        searchOptionRepository.add(new SearchOption("four", Category.FLAT_SIZE, "4 i więcej pokojów", "4 and more rooms"));

        searchOptionRepository.add(new SearchOption("all_buildings", Category.BUILDING, "wszystkie", "all"));
        searchOptionRepository.add(new SearchOption("blok", Category.BUILDING, "blok", "flat"));
        searchOptionRepository.add(new SearchOption("kamienica", Category.BUILDING, "kamienica", "tenement"));
        searchOptionRepository.add(new SearchOption("wolnostojacy", Category.BUILDING, "dom wolnostojący", "house"));
        searchOptionRepository.add(new SearchOption("szeregowiec", Category.BUILDING, "szeregowiec", "row house"));
        searchOptionRepository.add(new SearchOption("apartamentowiec", Category.BUILDING, "apartamentowiec", "apartment building"));
        searchOptionRepository.add(new SearchOption("loft", Category.BUILDING, "loft", "loft"));
        searchOptionRepository.add(new SearchOption("pozostale", Category.BUILDING, "pozostałe", "other"));

        searchOptionRepository.add(new SearchOption("all_levels", Category.LEVEL, "wszystkie", "all"));
        searchOptionRepository.add(new SearchOption("floor_-1", Category.LEVEL, "suterena", "basement"));
        searchOptionRepository.add(new SearchOption("floor_0", Category.LEVEL, "parter", "ground floor"));
        searchOptionRepository.add(new SearchOption("floor_1", Category.LEVEL, "1 piętro", "1st floor"));
        searchOptionRepository.add(new SearchOption("floor_2", Category.LEVEL, "2 piętro", "2nd floor"));
        searchOptionRepository.add(new SearchOption("floor_3", Category.LEVEL, "3 piętro", "3rd floor"));
        searchOptionRepository.add(new SearchOption("floor_4", Category.LEVEL, "4 piętro", "4th floor"));
        searchOptionRepository.add(new SearchOption("floor_5", Category.LEVEL, "5 piętro", "5th floor"));
        searchOptionRepository.add(new SearchOption("floor_6", Category.LEVEL, "6 piętro", "6th floor"));
        searchOptionRepository.add(new SearchOption("floor_7", Category.LEVEL, "7 piętro", "7th floor"));
        searchOptionRepository.add(new SearchOption("floor_8", Category.LEVEL, "8 piętro", "8th floor"));
        searchOptionRepository.add(new SearchOption("floor_9", Category.LEVEL, "9 piętro", "9th floor"));
        searchOptionRepository.add(new SearchOption("floor_10", Category.LEVEL, "10 piętro", "10th floor"));
        searchOptionRepository.add(new SearchOption("floor_11", Category.LEVEL, "powyżej 10 piętra", "above 10th floor"));
        searchOptionRepository.add(new SearchOption("floor_17", Category.LEVEL, "poddasze", "attic"));

        searchOptionRepository.add(new SearchOption("all_furnishings", Category.FURNISHINGS, "wszystkie", "all"));
        searchOptionRepository.add(new SearchOption("yes", Category.FURNISHINGS, "tak", "yes"));
        searchOptionRepository.add(new SearchOption("no", Category.FURNISHINGS, "nie", "no"));

        searchOptionRepository.add(new SearchOption("any_areas", Category.AREA, "dowolne", "any"));
        searchOptionRepository.add(new SearchOption("25", Category.AREA, "25m\u00B2", "25m\u00B2"));
        searchOptionRepository.add(new SearchOption("40", Category.AREA, "40m\u00B2", "40m\u00B2"));
        searchOptionRepository.add(new SearchOption("50", Category.AREA, "50m\u00B2", "50m\u00B2"));
        searchOptionRepository.add(new SearchOption("60", Category.AREA, "60m\u00B2", "60m\u00B2"));
        searchOptionRepository.add(new SearchOption("70", Category.AREA, "70m\u00B2", "70m\u00B2"));
        searchOptionRepository.add(new SearchOption("80", Category.AREA, "80m\u00B2", "80m\u00B2"));
        searchOptionRepository.add(new SearchOption("100", Category.AREA, "100m\u00B2", "100m\u00B2"));
        searchOptionRepository.add(new SearchOption("120", Category.AREA, "120m\u00B2", "120m\u00B2"));
        searchOptionRepository.add(new SearchOption("150", Category.AREA, "150m\u00B2", "150m\u00B2"));

    }

    /**
     * Returns list of search options from the {@code SearchOptionRepository}
     * filtered by the given category.
     *
     * @param category used to filter only search options from the same category
     * @return List of {@code SearchOption} objects from repository matching
     * category condition
     */
    public List<SearchOption> findByCategory(Category category) {
        return searchOptionRepository.findByCategory(category);
    }

    /**
     * Returns one search option from the {@code SearchOptionRepository}
     * matching given id
     *
     * @param id used to find search option with the same id
     * @return {@code SearchOption} object with matching id
     */
    public SearchOption findById(String id) {
        return searchOptionRepository.findById(id);
    }

}