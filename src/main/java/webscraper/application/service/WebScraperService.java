package webscraper.application.service;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraper.application.model.FlatOffer;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class WebScraperService {

    FlatOffersService flatOffersService;
    URLService urlService;
    private Document document;

    @Autowired
    public WebScraperService(FlatOffersService flatOffersService, URLService urlService) {
        this.flatOffersService = flatOffersService;
        this.urlService = urlService;
    }

    public void scrapURL() throws URISyntaxException {
        flatOffersService.clean();
        urlService.buildURL();

        int pageIndex = 1;
        int pageIndexMax;

        try {
            String url = urlService.getStringURL();
            document = Jsoup.connect(url).get();
            try {
                pageIndexMax = Integer.parseInt(document.getElementsByAttributeValue("data-cy", "page-link-last").first().text());
            } catch (NullPointerException e) {
                pageIndexMax = 1;
            }

            while (pageIndex <= pageIndexMax) {

                if (pageIndex != 1) {
                    document = Jsoup.connect(url).get();
                }
                Elements repository = document.getElementsByClass("offer-wrapper");
                for (Element e : repository) {
                    FlatOffer flatOffer = new FlatOffer();

                    flatOffer.setDescription(e.getElementsByClass("title-cell").select("strong").text());
                    flatOffer.setPrice(e.getElementsByClass("price").text());

                    if (flatOffersService.isNotRepeatedAndCorrect(flatOffer)) {
                        flatOffersService.add(flatOffer);
                    }
                }

                pageIndex++;
                url = urlService.getStringURLWithPages(pageIndex);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

