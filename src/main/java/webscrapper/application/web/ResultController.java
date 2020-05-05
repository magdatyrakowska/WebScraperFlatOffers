package webscrapper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webscrapper.application.model.FlatSize;
import webscrapper.application.model.Options;
import webscrapper.application.service.FlatOffersService;

import javax.swing.text.html.Option;

@Slf4j
@Controller
@RequestMapping("/result")
public class ResultController {

    @Autowired
    FlatOffersService flatOffersService;

    @GetMapping
    public String showResult() {

        return "result";
    }

    @PostMapping
    public String getResult() {
        log.info("POST poszło do result");
        return "result";
    }


}
