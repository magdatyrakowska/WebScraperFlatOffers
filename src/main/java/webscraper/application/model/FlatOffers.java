package webscraper.application.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlatOffers {

    private List<FlatOffer> flatOffers;

    public FlatOffers() {
        this.flatOffers = new ArrayList<>();
    }

}
