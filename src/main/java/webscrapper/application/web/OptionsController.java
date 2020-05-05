package webscrapper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webscrapper.application.model.Options;

@Slf4j
@Controller
@RequestMapping("options")
public class OptionsController {

    @GetMapping
    public String showRequestForm() {
        log.info("showrequestform działa");
        return "options";
    }


    @PostMapping
    public String processRequest() {
        log.info("Przetwarzanie obiektu ");
        return "redirect:/result";
    }



}
