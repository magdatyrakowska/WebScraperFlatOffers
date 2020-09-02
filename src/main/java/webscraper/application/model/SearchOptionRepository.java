package webscraper.application.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stores all search options and allows to make basic operation in repository:
 * adding a new search option and finding by different conditions.
 */
@Repository
public class SearchOptionRepository {

    /**
     * List of all search options, stored in a {@code List}
     */
    private List<SearchOption> searchOptions;

    /**
     * Assigns an empty {@code ArrayList} to a {@code searchOptions} field
     */
    public SearchOptionRepository() {
        this.searchOptions = new ArrayList<>();
    }

    /**
     * Adds given search option to list
     *
     * @param searchOption is added to a list of all search options
     */
    public void add(SearchOption searchOption) {
        searchOptions.add(searchOption);
    }

    /**
     * Returns list with all search options
     *
     * @return list with all search options
     */
    public List<SearchOption> findAll() {
        return searchOptions;
    }

    /**
     * Returns list with search options matching to given category
     *
     * @param category is condition used to filter all search options
     * @return list with search options matching to given category
     */
    public List<SearchOption> findByCategory(Category category) {
        return searchOptions.stream()
                .filter(searchOption -> searchOption.getCategory() == category)
                .collect(Collectors.toList());
    }

    /**
     * Return search option matching to given id
     *
     * @param id is condition used to filter all search options
     * @return search option matching to given id
     */
    public SearchOption findById(String id) {
        return searchOptions.stream()
                .filter(searchOption -> searchOption.getId().equals(id))
                .findFirst()
                .get();
    }

}
