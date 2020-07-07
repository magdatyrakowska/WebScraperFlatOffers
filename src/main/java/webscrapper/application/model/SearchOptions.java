package webscrapper.application.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SearchOptions {

    private static List<SearchOption> searchOptions = new ArrayList<>();

    public SearchOptions() {
        super();
    }

    public void add(SearchOption searchOption) {
        searchOptions.add(searchOption);
    }

    public List<SearchOption> findAll() {
        return searchOptions;
    }

    public List<SearchOption> findByCategory(Category category) {
        return searchOptions.stream()
                .filter(searchOption -> searchOption.getCategory() == category)
                .collect(Collectors.toList());
    }

    public SearchOption findById(String id) {
        return searchOptions.stream()
                .filter(searchOption -> searchOption.getId().equals(id))
                .findFirst()
                .get();
    }

}
