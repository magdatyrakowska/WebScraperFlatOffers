package webscrapper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webscrapper.application.model.FlatSize;
import webscrapper.application.model.OptionsForm;
import webscrapper.application.service.OptionsService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("options")
public class OptionsController {

    OptionsService optionsService;

    public OptionsController(OptionsService optionsService) {
        this.optionsService = optionsService;
    }

    @ModelAttribute("flatSizes")
    public List<FlatSize> addOptionsToModel(Model model) {
        List<FlatSize> flatSizes = Arrays.asList(
                FlatSize.ALL_SIZES,
                FlatSize.ONE_ROOM,
                FlatSize.TWO_ROOMS,
                FlatSize.THREE_ROOMS,
                FlatSize.FOUR_ROOMS_AND_MORE);
        return flatSizes;
    }

    @GetMapping
    public String showRequestForm(Model model) {
        log.info("showrequestform działa");
        model.addAttribute("optionsmodel", new OptionsForm());
        return "options";
    }

    @PostMapping
    public String processRequest(/*@Valid */@ModelAttribute OptionsForm optionsmodel, Errors errors) {
        String size = optionsmodel.getFlatSize().getDisplayValuePl();
        log.info("Przetwarzanie obiektu " + size);
        if (errors.hasErrors()) {
            return "options";
        } else {
            log.info("Options without error, redirection");
            optionsService.setOptions(optionsmodel);
            return "redirect:/result";
        }
    }






}
