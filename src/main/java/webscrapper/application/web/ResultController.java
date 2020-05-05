package webscrapper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webscrapper.application.model.FlatSize;
import webscrapper.application.service.FlatOffersService;

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
    public String processResult(@RequestParam("flatSize") FlatSize flatSize) {
        log.info("otrzymano flatsize " + flatSize.getDisplayValue());
        return "result";
    }
}
