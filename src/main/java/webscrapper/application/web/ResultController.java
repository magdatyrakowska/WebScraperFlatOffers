package webscrapper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webscrapper.application.model.OptionsForm;
import webscrapper.application.service.FlatOffersService;
import webscrapper.application.service.OptionsService;

@Slf4j
@Controller
@RequestMapping("/result")
public class ResultController {

    FlatOffersService flatOffersService;
    OptionsService optionsService;

    public ResultController(FlatOffersService flatOffersService, OptionsService optionsService) {
        this.flatOffersService = flatOffersService;
        this.optionsService = optionsService;
    }

    @GetMapping
    public String showResult(Model model) {
        model.addAttribute("options", optionsService.getOptions());
        log.info("GET poszło do result");
        return "result";
    }

}
