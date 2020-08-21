package webscraper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webscraper.application.model.FlatOfferDisplay;
import webscraper.application.service.*;

import java.net.URISyntaxException;

@Slf4j
@Controller
@RequestMapping("/result")
public class ResultController {

    WebScraperService webScraperService;
    FormService formService;
    SearchOptionsService searchOptionsService;
    FlatOffersService flatOffersService;

    public ResultController(WebScraperService webScraperService, FormService formService, SearchOptionsService searchOptionsService, FlatOffersService flatOffersService) {
        this.webScraperService = webScraperService;
        this.formService = formService;
        this.searchOptionsService = searchOptionsService;
        this.flatOffersService = flatOffersService;
    }

    @GetMapping
    public String showResult(Model model) {
        model.addAttribute("formDisplay", formService.getFormDisplay(searchOptionsService));

        try {
            webScraperService.scrapURL();
            FlatOfferDisplay flatOfferDisplay = flatOffersService.getFlatOfferDisplay();
            model.addAttribute("flatOfferDisplay", flatOfferDisplay);

            model.addAttribute("data", flatOffersService.getHistogramData());


        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.info("web scraper error");
        }

        log.info("GET poszło do result");
        return "result";
    }


}
