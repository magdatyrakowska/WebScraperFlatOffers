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

@Slf4j
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

    public void scrapURL() throws IOException{
        flatOffersService.clean();

        try {
            urlService.buildURL();
            document = Jsoup.connect(urlService.getStringURL()).get();

            String maxPage = document.getElementsByAttributeValue("data-cy", "page-link-last").first().text();
            urlService.setPageIndexMax(maxPage == null ? 1 : Integer.parseInt(maxPage));

            while (urlService.hasNextPage()) {

                if (urlService.getPageIndex() != 1) {
                    document = Jsoup.connect(urlService.getStringURL()).get();
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
                urlService.increasePageIndex();
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}

