package webscraper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webscraper.application.service.FlatOffersService;
import webscraper.application.service.FormService;
import webscraper.application.service.SearchOptionsService;
import webscraper.application.service.WebScraperService;

import java.io.IOException;

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
        if (formService.isFormReady()) {
            model.addAttribute("formDisplay", formService.getFormDisplay(searchOptionsService));

            try {
                webScraperService.scrapURL();

                model.addAttribute("flatOfferDisplay", flatOffersService.getFlatOfferDisplay());
                model.addAttribute("data", flatOffersService.getHistogramData());

                log.info("GET poszło do result");
                return "result";
            } catch (IOException e) {
                return "empty_result";
            }

        } else {
            return "redirect:/home";
        }
    }


}
