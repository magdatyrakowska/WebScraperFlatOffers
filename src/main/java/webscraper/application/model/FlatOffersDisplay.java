package webscraper.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Stores statistic information about all flat offers in {@code FlatOffersRepository}, like number of offers etc.
 * Used to send information to model and display them on the result.html view.
 */
@Getter
@Setter
@NoArgsConstructor
public class FlatOffersDisplay {

    /**
     * Number of offers in the repository
     */
    private int numberOfOffers;
    /**
     * Arithmetic average of all prices
     */
    private String averagePrice;
    /**
     * Minimum price from all offers
     */
    private int minPrice;
    /**
     * Maximum price from all offers
     */
    private int maxPrice;
    /**
     * The most frequent price
     */
    private int modePrice;
    /**
     * How many times the most frequent price occurred
     */
    private int modeRepetition;
    /**
     * Price in the middle of sorted offers
     */
    private int median;
}
