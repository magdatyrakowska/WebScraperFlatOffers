package webscrapper.application.service;

import org.springframework.stereotype.Service;
import webscrapper.application.model.Category;
import webscrapper.application.model.FlatOffer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FlatOffersService {

    private static Document document;
    private static List<FlatOffer> flats;

    public void scrapURL(URLRequest urlRequest) {

        flats = new ArrayList<>();
        int pageIndex = 1;
        int pageIndexMax;
        String urlPages = "&page=";

        try {
            String modURL = urlRequest.getURL() + urlPages + pageIndex;
            document = Jsoup.connect(modURL).get();
            pageIndexMax = Integer.parseInt(
                    document.getElementsByAttributeValue("data-cy", "page-link-last").first().text());

            while (isURLValid(modURL) && pageIndex <= pageIndexMax) {

                if (pageIndex != 1) {
                    document = Jsoup.connect(modURL).get();
                }
                Elements repository = document.getElementsByClass("offer-wrapper");
                for (Element e : repository) {
                    FlatOffer flatOffer = new FlatOffer();
                  //  flatOffer.setSize(null); //searchOptionService.get....

                    flatOffer.setDescription(e.getElementsByClass("title-cell").select("strong").text());
                    flatOffer.setPrice(e.getElementsByClass("price").text());

                    if (isNotRepeatedAndCorrect(flatOffer)) {
                        flats.add(flatOffer);
                    }
                }

                pageIndex++;
                modURL = urlRequest.getURL() + urlPages + pageIndex;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getAveragePrice() {
        flats.stream()
                .sorted(Comparator.comparingInt(FlatOffer::getPrice))
                .forEach(System.out::println);

        double average = flats.stream()
                .mapToInt(FlatOffer::getPrice)
                .average()
                .getAsDouble();


        System.out.printf("Liczba mieszkań: %d\nśredni koszt: %.2f", flats.size(), average);
        return String.format("%.2f", average);
    }

    private static boolean isNotRepeatedAndCorrect(FlatOffer flatOffer) {
        return flatOffer.checkLongTerm()
                && flats.stream().noneMatch(
                fo -> fo.getPrice() == flatOffer.getPrice()
                        && fo.getDescription().equals(flatOffer.getDescription()));
    }

    private static boolean isURLValid(String urlS) {
        try {
            URL url = new URL(urlS);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

