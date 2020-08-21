package webscraper.application.service;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraper.application.model.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlatOffersService {

    FlatOffers flatOffers;

    @Autowired
    public FlatOffersService(FlatOffers flatOffers) {
        this.flatOffers = flatOffers;
    }


    public void clean() {
        flatOffers.clean();

    }

    public void add(FlatOffer flatOffer) {
        flatOffers.add(flatOffer);
    }

    public List<FlatOffer> all() {
        return flatOffers.all();
    }

    public boolean isNotRepeatedAndCorrect(FlatOffer flatOffer) {
        return flatOffer.checkLongTerm()
                && flatOffers.all().stream().noneMatch(
                fo -> fo.getPrice() == flatOffer.getPrice()
                        && fo.getDescription().equals(flatOffer.getDescription()));
    }

    private String getAveragePrice() {
        printAllToTerminal();

        double average = flatOffers.all()
                .stream()
                .mapToInt(FlatOffer::getPrice)
                .average()
                .getAsDouble();

        System.out.printf("Liczba mieszkań: %d\nśredni koszt: %.2f", flatOffers.size(), average);
        return String.format("%.2f", average);
    }

    private int getMinPrice() {
        return flatOffers.all()
                .stream()
                .mapToInt(FlatOffer::getPrice)
                .min()
                .getAsInt();
    }

    private int getMaxPrice() {
        return flatOffers.all()
                .stream()
                .mapToInt(FlatOffer::getPrice)
                .max()
                .getAsInt();
    }

    private Pair<Integer, Integer> getModePrice() {
        List<FlatOffer> sorted = flatOffers.all()
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

    private int getMedian() {
        int size = flatOffers.size();

        return flatOffers.all().stream()
                .map(FlatOffer::getPrice)
                .sorted()
                .skip(size / 2)
                .findFirst()
                .get();
    }

    private void printAllToTerminal() {
        flatOffers.all()
                .stream()
                .sorted(Comparator.comparingInt(FlatOffer::getPrice))
                .forEach(System.out::println);
    }


    public FlatOfferDisplay getFlatOfferDisplay() {
        FlatOfferDisplay flatOfferDisplay = new FlatOfferDisplay();

        flatOfferDisplay.setNumberOfOffers(flatOffers.size());
        flatOfferDisplay.setAveragePrice(getAveragePrice());
        flatOfferDisplay.setMinPrice(getMinPrice());
        flatOfferDisplay.setMaxPrice(getMaxPrice());

        Pair<Integer, Integer> mode = getModePrice();
        flatOfferDisplay.setModePrice(mode.getKey());
        flatOfferDisplay.setModeRepetition(mode.getValue());

        flatOfferDisplay.setMedian(getMedian());


        return flatOfferDisplay;
    }

    public String[][] getHistogramData() {
        String[][] data = new String[flatOffers.size() + 1][2];
        data[0] = new String[] {"Description", "Price"};
        int i = 1;
        for (FlatOffer offer : flatOffers.all()) {
            String description = offer.getDescription();
            String price = Integer.toString(offer.getPrice());
            data[i] = new String[] {description, price};
            i++;
        }
        return data;
    }


}
