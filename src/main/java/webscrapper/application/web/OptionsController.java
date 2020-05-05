package webscrapper.application.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webscrapper.application.model.FlatSize;
import webscrapper.application.model.Options;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("options")
public class OptionsController {

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
        model.addAttribute("optionsmodel", new Options());
        return "options";
    }

    @PostMapping
    public String processRequest(Options options) {
        //String size = options.getFlatSize().getDisplayValue();
        //log.info("Przetwarzanie obiektu " + size);
        log.info("Przetwarzanie obiektu ");
        return "redirect:/result";
    }






}
