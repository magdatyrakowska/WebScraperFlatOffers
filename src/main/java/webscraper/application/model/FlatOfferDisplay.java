package webscraper.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlatOfferDisplay {

    private int numberOfOffers;
    private String averagePrice;
    private int minPrice;
    private int maxPrice;
    private int modePrice;
    private int modeRepetition;
    private int median;
}
