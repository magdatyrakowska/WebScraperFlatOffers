package webscraper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ErrorController {


    @GetMapping("/error")
    public String handleError() {
        log.error("Error happened");
        return "error";
    }

}
