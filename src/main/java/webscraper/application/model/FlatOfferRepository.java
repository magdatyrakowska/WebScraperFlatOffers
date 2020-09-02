package webscraper.application.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all flat offers and allows adding a new flat offer, to obtain all offers,
 * get number of them or clear the whole list.
 */
@Repository
public class FlatOfferRepository {

    /**
     * List of all flat offers, stored in a {@code List}
     */
    private List<FlatOffer> flatOffers;

    /**
     * Assigns an empty {@code ArrayList} to a {@code flatOffers} field
     */
    public FlatOfferRepository() {
        this.flatOffers = new ArrayList<>();
    }

    /**
     * Adds given flat offer to list
     *
     * @param flatOffer is added to a list of all offers
     */
    public void add(FlatOffer flatOffer) {
        flatOffers.add(flatOffer);
    }

    /**
     * Returns list with all flat offers
     *
     * @return list with all flat offers
     */
    public List<FlatOffer> all() {
        return flatOffers;
    }

    /**
     * Return a number of elements in flatOffers list
     *
     * @return number of flat offers
     */
    public int size() {
        return flatOffers.size();
    }

    /**
     * Assign e new empty list to flatOffers field
     */
    public void clean() {
        flatOffers = new ArrayList<>();
    }
}
