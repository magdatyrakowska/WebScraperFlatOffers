package webscraper.application.service;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraper.application.model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages repository with flat offers.
 * Allows to add new offers, get list with all offers and remove them (clean).
 * Checks, if the offers are correct. Counts simple statistic data (i.e. average, median).
 * Can repeat processed data to be send to model layer: histogram and statistic data.
 */
@Service
public class FlatOffersService {

    /**
     * Repository storing {@code FlatOffer} objects
     */
    FlatOfferRepository flatOfferRepository;

    /**
     * Assigns an instance of {@code FlatOfferRepository}
     *
     * @param flatOfferRepository instance of {@code FlatOfferRepository} object to be assigned
     */
    @Autowired
    public FlatOffersService(FlatOfferRepository flatOfferRepository) {
        this.flatOfferRepository = flatOfferRepository;
    }

    /**
     * Cleans the list of flat offers in the repository to enable writing result from a new search
     */
    public void clean() {
        flatOfferRepository.clean();
    }

    /**
     * Returns sorted list with all offers in {@code FlatOfferRepostory}.
     * List is sorted in ascending order according to the price.
     *
     * @return sorted list with all flat offers
     */
    public List<FlatOffer> findAll() {
        return flatOfferRepository.all()
                .stream()
                .sorted(Comparator.comparingInt(FlatOffer::getPrice))
                .collect(Collectors.toList());
    }

    /**
     * Adds given flat offer to the repository after checking,
     * whether offer is correct and is not a duplicate of any offer from repository,
     *
     * @param flatOffer is offer checked and added to the repository
     */
    public void add(FlatOffer flatOffer) {
        if (isNotRepeatedAndCorrect(flatOffer)) {
            flatOfferRepository.add(flatOffer);
        }
    }

    /**
     * Checks whether offer is correct and is not a duplicate of any offer from repository.
     *
     * @param flatOffer is flat offer to be checked
     * @return {@code true} if offer meets requirements of long term offer
     * and is not a duplicate; {@code false} in case one of these requirements are not fulfilled
     */
    private boolean isNotRepeatedAndCorrect(FlatOffer flatOffer) {
        return flatOffer.checkLongTerm()
                && flatOfferRepository.all().stream().noneMatch(
                fo -> fo.getPrice() == flatOffer.getPrice()
                        && fo.getDescription().equals(flatOffer.getDescription()));
    }

    /**
     * Calculates average price from all offers in the repository and returns it as formatted {@code String}
     *
     * @return average price from all offers in a formatted String with precision to 0.01
     */
    private String getAveragePrice() {
        double average = flatOfferRepository.all()
                .stream()
                .mapToInt(FlatOffer::getPrice)
                .average()
                .getAsDouble();

        return String.format("%.2f", average);
    }

    /**
     * Finds minimum price from all flat offers in the repository.
     *
     * @return minimum price from all offers
     */
    private int getMinPrice() {
        return flatOfferRepository.all()
                .stream()
                .mapToInt(FlatOffer::getPrice)
                .min()
                .getAsInt();
    }

    /**
     * Finds maximum price from all flat offers in the repository.
     *
     * @return maximum price from all offers
     */
    private int getMaxPrice() {
        return flatOfferRepository.all()
                .stream()
                .mapToInt(FlatOffer::getPrice)
                .max()
                .getAsInt();
    }

    /**
     * Calculates the most frequent price (mode) and its frequency.
     *
     * @return {@code Pair} of two Integers: first number is the most frequent price (mode),
     * and second number is its frequency.
     */
    private Pair<Integer, Integer> getModePrice() {
        List<FlatOffer> sorted = flatOfferRepository.all()
                .stream()
                .sorted(Comparator.comparingInt(FlatOffer::getPrice))
                .collect(Collectors.toList());

        int repetition = 0;
        int repetitionMax = 0;
        int priceMode = 0;
        int priceCurrent = sorted.get(0).getPrice();

        for (FlatOffer offer : sorted) {
            if (offer.getPrice() == priceCurrent) {
                repetition++;
            } else {
                if (repetition > repetitionMax) {
                    repetitionMax = repetition;
                    priceMode = priceCurrent;
                }
                repetition = 1;
                priceCurrent = offer.getPrice();
            }
        }
        return new Pair<>(priceMode, repetitionMax);
    }

    /**
     * Finds the median - price standing precisely in the middle of sorted list of prices.
     * Median divides this list in two equal parts.
     *
     * @return price in the middle of sorted offers
     */
    private int getMedian() {
        int size = flatOfferRepository.size();

        return flatOfferRepository.all().stream()
                .map(FlatOffer::getPrice)
                .sorted()
                .skip(size / 2)
                .findFirst()
                .get();
    }

    /**
     * Returns object {@code FlatOfferDisplay} containing all statistical data.
     * Used to send those information to model layer and display on result.html
     *
     * @return object {@code FlatOfferDisplay} containing statistical data:
     * number of offers, minimum and maximum price, median and mode.
     */
    public FlatOffersDisplay getFlatOfferDisplay() {
        FlatOffersDisplay flatOffersDisplay = new FlatOffersDisplay();

        flatOffersDisplay.setNumberOfOffers(flatOfferRepository.size());
        flatOffersDisplay.setAveragePrice(getAveragePrice());
        flatOffersDisplay.setMinPrice(getMinPrice());
        flatOffersDisplay.setMaxPrice(getMaxPrice());

        Pair<Integer, Integer> mode = getModePrice();
        flatOffersDisplay.setModePrice(mode.getKey());
        flatOffersDisplay.setModeRepetition(mode.getValue());

        flatOffersDisplay.setMedian(getMedian());

        return flatOffersDisplay;
    }

    /**
     * Returns two-dimensional array containing data to be send to model layer
     * and display on result.html in a histogram graph (prices and descriptions)
     *
     * @return two-dimensional array with data for histogram, containing prices
     * and descriptions
     */
    public String[][] getHistogramData() {
        String[][] data = new String[flatOfferRepository.size() + 1][2];
        data[0] = new String[]{"Description", "Price"};
        int i = 1;
        for (FlatOffer offer : flatOfferRepository.all()) {
            String description = offer.getDescription();
            String price = Integer.toString(offer.getPrice());
            data[i] = new String[]{description, price};
            i++;
        }
        return data;
    }

}
