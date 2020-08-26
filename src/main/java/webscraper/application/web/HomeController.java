package webscraper.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping(value = {"/", "/home"})
    public String redirectHome() {
        return "home";
    }

}
