package webscraper.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webscraper.application.model.Category;
import webscraper.application.model.Form;
import webscraper.application.service.FormService;
import webscraper.application.service.SearchOptionsService;

import javax.validation.Valid;

/**
 * Provides options page where one can select search options
 */
@Controller
@RequestMapping("options")
public class OptionsController {

    /**
     * {@code FormService} is injected to save {@code Form} object,
     * containing all selected by user search options
     */
    FormService formService;
    /**
     * {@code SearchOptionService} is injected to fill form in the options.html view
     * with all available search options
     */
    SearchOptionsService searchOptionsService;


    /**
     * Assigns instances of {@code FormService} and {@code SearchOptionsService}
     *
     * @param formService instance of {@code FormService} object to be assigned
     * @param searchOptionsService instance of {@code SearchOptionService} object to be assigned
     */
    @Autowired
    public OptionsController(FormService formService, SearchOptionsService searchOptionsService) {
        this.formService = formService;
        this.searchOptionsService = searchOptionsService;
        this.searchOptionsService.populate();
    }

    /**
     * Gets lists of search options divided by categories and adds them to the model.
     * Search options are used to generate form with all possible options
     *
     * @param model transfers data to options.html view
     */
    @ModelAttribute
    public void populateFlatSize(Model model) {
        model.addAttribute("flatSizes", searchOptionsService.findByCategory(Category.FLAT_SIZE));
        model.addAttribute("buildings", searchOptionsService.findByCategory(Category.BUILDING));
        model.addAttribute("levels", searchOptionsService.findByCategory(Category.LEVEL));
        model.addAttribute("furnishings", searchOptionsService.findByCategory(Category.FURNISHINGS));
        model.addAttribute("areas", searchOptionsService.findByCategory(Category.AREA));
    }

    /**
     * Mapping /options GET requests.
     * Creates and adds to model {@code Form} object for storing selected search options
     *
     * @param model transfers data to options.html view
     * @return view name of options page
     */
    @GetMapping
    public String showRequestForm(Model model) {
        model.addAttribute("form", new Form());
        return "options";
    }

    /**
     * Mapping /options POST requests. Checks, if there are no errors in coming data.
     * If there are some errors, options view is loaded again. In other case, data from form
     * is saved to {@code FormService} and there is redirection to result.html view.
     *
     * @param form {@code Form} object containing all selected in form search options
     * @param errors {@code Errors} object with information about invalid inputs
     * @return view name of options page when there is some invalid input data
     * or redirection to /result request when all input data are correct
     */
    @PostMapping
    public String processRequest(@Valid @ModelAttribute Form form, Errors errors) {
        if (errors.hasErrors()) {
            return "options";
        } else {
            formService.setForm(form);
            return "redirect:/result";
        }
    }


}
