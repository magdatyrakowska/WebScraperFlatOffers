package webscraper.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraper.application.model.FlatOffer;
import webscraper.application.model.FlatOffers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class FlatOffersService {

    FlatOffers flatOffers;

    @Autowired
    public FlatOffersService(FlatOffers flatOffers) {
        this.flatOffers = flatOffers;
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

    public String getAveragePrice() {
        flatOffers.all()
                .stream()
                .sorted(Comparator.comparingInt(FlatOffer::getPrice))
                .forEach(System.out::println);

        double average = flatOffers.all()
                .stream()
                .mapToInt(FlatOffer::getPrice)
                .average()
                .getAsDouble();


        System.out.printf("Liczba mieszkań: %d\nśredni koszt: %.2f", flatOffers.size(), average);
        return String.format("%.2f", average);
    }


}
