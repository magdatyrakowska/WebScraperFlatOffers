package webscraper.application.model;

import lombok.Data;

/**
 * Stores data of one search option belonging to certain {@code Category}.
 * Each {@code SearchOption} has id and polish and english description to be displayed.
 */
@Data
public class SearchOption {
    /**
     * id should be unique String that enables to link id and that particular search option.
     * Used in matching data from form in {@code FormService}
     */
    private final String id;
    /**
     * Category of that search option
     */
    private final Category category;
    /**
     * Name of the search option in polish language
     */
    private final String namePl;
    /**
     * Name of the search option in english language
     */
    private final String nameENG;

    /**
     * Creates new search option. Requires data to all fields
     */
    public SearchOption(String id, Category category, String namePl, String nameENG) {
        this.id = id;
        this.category = category;
        this.namePl = namePl;
        this.nameENG = nameENG;
    }

}