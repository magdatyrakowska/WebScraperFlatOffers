package webscraper.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Provides start page with basic information about application
 */
@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    /**
     * Mapping / and /home GET requests
     *
     * @return view name of start page
     */
    @GetMapping
    public String redirectHome() {
        return "home";
    }

}
