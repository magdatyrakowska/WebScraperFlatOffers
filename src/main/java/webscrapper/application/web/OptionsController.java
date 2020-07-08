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
import webscrapper.application.model.Category;
import webscrapper.application.model.Form;
import webscrapper.application.model.SearchOption;
import webscrapper.application.service.FormService;
import webscrapper.application.service.SearchOptionsService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("options")
public class OptionsController {

    FormService formService;
    SearchOptionsService searchOptionsService;

    @Autowired
    public OptionsController(FormService formService, SearchOptionsService searchOptionsService) {
        this.formService = formService;
        this.searchOptionsService = searchOptionsService;
        this.searchOptionsService.populate();
    }

    /*
        @ModelAttribute("flatSizes")
        public List<FlatSize> addOptionsToModel(Model model) {
            List<FlatSize> flatSizes = Arrays.asList(
                    FlatSize.ALL_SIZES,
                    FlatSize.ONE_ROOM,
                    FlatSize.TWO_ROOMS,
                    FlatSize.THREE_ROOMS,
                    FlatSize.FOUR_ROOMS_AND_MORE);
            return flatSizes;
        }*/
    @ModelAttribute
    public void populateFlatSize(Model model) {
        model.addAttribute("flatSizes", searchOptionsService.findByCategory(Category.FLAT_SIZE));
        model.addAttribute("buildings", searchOptionsService.findByCategory(Category.BUILDING));
    }


    @GetMapping
    public String showRequestForm(Model model) {
        log.info("showrequestform działa");
        model.addAttribute("form", new Form());
        return "options";
    }

    @PostMapping
    public String processRequest(@Valid @ModelAttribute Form form, Errors errors) {
        log.info("Walidacja obiektu");
        if (errors.hasErrors()) {
            log.info("jest error");
            return "options";
        } else {
            log.info("Options without error, redirection");
            formService.setForm(form);
            return "redirect:/result";
        }
    }


}
