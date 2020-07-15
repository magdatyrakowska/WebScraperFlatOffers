package webscraper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webscraper.application.service.FlatOffersService;
import webscraper.application.service.FormService;
import webscraper.application.service.SearchOptionsService;
import webscraper.application.service.URLService;

import java.net.URISyntaxException;

@Slf4j
@Controller
@RequestMapping("/result")
public class ResultController {

    FlatOffersService flatOffersService;
    FormService formService;
    SearchOptionsService searchOptionsService;
    URLService urlService;

    public ResultController(FlatOffersService flatOffersService, FormService formService, SearchOptionsService searchOptionsService, URLService urlService) {
        this.flatOffersService = flatOffersService;
        this.formService = formService;
        this.searchOptionsService = searchOptionsService;
        this.urlService = urlService;
    }

    @GetMapping
    public String showResult(Model model) {
        model.addAttribute("formDisplay", formService.getFormDisplay(searchOptionsService));
        //flatOfferService.search(); // tutaj wywołanie serwisu który dokona przeszukania ofert i wrzuci je do serwisu z wynikiem przeszukiwania
        //model.addAttribute(searchResults);
        try {
            urlService.buildURL();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info("GET poszło do result");
        return "result";
    }

}
