package webscraper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Provides custom error page
 */
@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorController {

    /**
     * Mapping /error GET request
     *
     * @return view name of universal error page
     */
    @GetMapping
    public String handleError() {
        log.error("Error page loaded");
        return "error";
    }

}
