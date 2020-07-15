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

    public void add(FlatOffer flatOffer) {
        flatOffers.add(flatOffer);
    }

    public List<FlatOffer> all() {
        return flatOffers;
    }

    public int size() {
        return flatOffers.size();
    }
}
