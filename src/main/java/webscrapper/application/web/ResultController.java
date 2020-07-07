package webscrapper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webscrapper.application.model.SearchOption;
import webscrapper.application.service.FlatOffersService;
import webscrapper.application.service.FormService;
import webscrapper.application.service.SearchOptionsService;

@Slf4j
@Controller
@RequestMapping("/result")
public class ResultController {

    FlatOffersService flatOffersService;
    FormService formService;
    SearchOptionsService searchOptionsService;

    public ResultController(FlatOffersService flatOffersService, FormService formService, SearchOptionsService searchOptionsService) {
        this.flatOffersService = flatOffersService;
        this.formService = formService;
        this.searchOptionsService = searchOptionsService;
    }

    @GetMapping
    public String showResult(Model model) {
        model.addAttribute("formDisplay", formService.getFormDisplay(searchOptionsService));
        log.info("GET poszło do result");
        return "result";
    }

}
