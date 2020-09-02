package webscraper.application.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraper.application.model.FlatOffer;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Service responsible for scrapping information about flat offers from olx.pl portal.
 * Uses JSoup library to scrap data from html elements.
 * All data is saved using {@code FlatOfferService}.
 */
@Slf4j
@Service
public class WebScraperService {

    /**
     * Service for storing data scrapped from web page
     */
    FlatOffersService flatOffersService;
    /**
     * Service providing URL address to connect via JSoup
     */
    URLService urlService;
    /**
     * HTML document downloaded and then searched for information about flat offers.
     */
    private Document document;

    /**
     * Assigns instances of {@code FlatOfferService} and {@code URL Service}
     *
     * @param flatOffersService instance of {@code FlatOffersService} to be assigned
     * @param urlService instance of {@code URLService} to be assigned
     */
    @Autowired
    public WebScraperService(FlatOffersService flatOffersService, URLService urlService) {
        this.flatOffersService = flatOffersService;
        this.urlService = urlService;
    }

    /**
     * Performs connection with URL address provided by {@code urlService},
     * downloads {@code Document} object with HTML code of page,
     * searches it and save data using {@code FlatOfferService}.
     * All Result HTML pages are searched using while loop and modification
     * of page index in {@code URLService}
     *
     * @throws IOException
     */
    public void scrapURL() throws IOException {
        flatOffersService.clean();

        try {
            urlService.buildURL();
            log.info("connecting url: " + urlService.getStringURL());
            document = Jsoup.connect(urlService.getStringURL()).get();

            String maxPage = document.getElementsByAttributeValue("data-cy", "page-link-last").first().text();
            urlService.setPageIndexMax(maxPage == null ? 1 : Integer.parseInt(maxPage));

            while (urlService.hasNextPage()) {

                if (urlService.getPageIndex() != 1) {
                    log.info("connecting url: " + urlService.getStringURL());
                    document = Jsoup.connect(urlService.getStringURL()).get();
                }
                Elements repository = document.getElementsByClass("offer-wrapper");
                for (Element e : repository) {
                    FlatOffer flatOffer = new FlatOffer();

                    flatOffer.setDescription(e.getElementsByClass("title-cell").select("strong").text());
                    flatOffer.setPrice(e.getElementsByClass("price").text());
                    flatOffer.setUrl(e.getElementsByClass("title-cell").select("a").attr("href"));

                    flatOffersService.add(flatOffer);

                }
                urlService.increasePageIndex();
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}

