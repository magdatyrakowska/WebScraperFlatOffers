package webscraper.application.model;

import lombok.Data;

/**
 * Stores information from form with search options, like number of offers etc.
 * Used to send information to model and display them on the result.html view as searching parameters.
 * Fields are {@code SearchOption} objects and allow to display information in polish or english language.
 */
@Data
public class FormDisplay {
    /**
     * Information about size of flat
     */
    private SearchOption flatSize;
    /**
     * Information about type of building
     */
    private SearchOption building;
    /**
     * Information about building level
     */
    private SearchOption level;
    /**
     * Information telling whether flat is furnished or not
     */
    private SearchOption furnishings;
    /**
     * Information about down limit of flat area
     */
    private SearchOption areaFrom;
    /**
     * Information about up limit of flat area
     */
    private SearchOption areaTo;
    /**
     * Name of the city
     */
    private String city;

}