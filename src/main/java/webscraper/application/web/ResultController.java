package webscraper.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webscraper.application.service.FlatOffersService;
import webscraper.application.service.FormService;
import webscraper.application.service.WebScraperService;

import java.io.IOException;

/**
 * Provides result page with summarized results of offers search
 */
@Controller
@RequestMapping("/result")
public class ResultController {

    /**
     * {@code WebScraperService} is injected to perform search of offers
     */
    WebScraperService webScraperService;
    /**
     * {@code FormService} is injected to get search current search options
     */
    FormService formService;
    /**
     * {@code FlatOfferService} is injected to get processed data summarizing all offers
     */
    FlatOffersService flatOffersService;

    /**
     * Assigns instances of {@code WebScraperService}, {@code FormService} and {@code FlatOffersService}
     *
     * @param webScraperService instance of {@code WebScraperService} object to be assigned
     * @param formService       instance of {@code FormService} object to be assigned
     * @param flatOffersService instance of {@code FlatOffersService} object to be assigned
     */
    public ResultController(WebScraperService webScraperService,
                            FormService formService,
                            FlatOffersService flatOffersService) {
        this.webScraperService = webScraperService;
        this.formService = formService;
        this.flatOffersService = flatOffersService;
    }

    /**
     * Mapping /result GET requests.
     * Checks if the {@code FormService} was already initialized and scrapping can be performed.
     * If it is possible, performs scrapping for information about flat offers
     * and presents them with basic statistic data.
     * If generated URL address does not provide access to valid result page, empty result page occurs.
     *
     * @param model transfers data to result.html view
     * @return redirection to /home, if {@code FormService} was not initialized;
     * view name of result page if scrapping finished with success;
     * view nam of empty_result if generated URL does not provide access to valid result page
     */
    @GetMapping
    public String showResult(Model model) {
        if (formService.isFormReady()) {
            model.addAttribute("formDisplay", formService.getFormDisplay());

            try {
                webScraperService.scrapURL();

                model.addAttribute("flatOfferDisplay", flatOffersService.getFlatOfferDisplay());
                model.addAttribute("data", flatOffersService.getHistogramData());
                model.addAttribute("offers", flatOffersService.findAll());

                return "result";
            } catch (IOException e) {
                return "empty_result";
            }

        } else {
            return "redirect:/home";
        }
    }


}
