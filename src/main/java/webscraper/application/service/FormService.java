package webscraper.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraper.application.model.Form;
import webscraper.application.model.FormDisplay;

/**
 * Manages {@code FormService} instance. Stores information from form with selected search options.
 * {@code Form} object has only ids of possible option, and to display full descriptions they need to be taken
 * from {@code SearchOptionsService}. That data can be obtained from service as {@code FormDisplay} object,
 * and is used to display in the result.html view.
 */
@Service
public class FormService {

    /**
     * Stores {@code Form} object with data from form
     */
    private Form form;
    /**
     * Describes is {@code form} field already initialized and possible to process.
     */
    private boolean formReady;
    /**
     * {@code SearchOptionService} is injected to get data necessary to prepare {@code FormDisplay} object
     */
    SearchOptionsService searchOptionsService;

    /**
     * Initialize {@code searchOptionService} field and sets {@code formReady} field to false,
     * because on the start of application there is no form get yet and there is no data to be processed.
     *
     * @param searchOptionsService instance of {@code SearchOptionsService} to be assigned
     */
    @Autowired
    public FormService(SearchOptionsService searchOptionsService) {
        this.searchOptionsService = searchOptionsService;
        formReady = false;
    }

    /**
     * Returns form stored in the service
     *
     * @return instance of {@code Form} object stored in the service
     */
    public Form getForm() {
        return form;
    }

    /**
     * Assigns given {@code Form} object to a {@code form} field
     * and sets the {@code formReady} flag to {@code true} to mark that
     * form is not empty and ready to be processed
     *
     * @param form is {@code Form} object to be assigned to a {@code form} field
     */
    public void setForm(Form form) {
        this.form = form;
        formReady = true;
    }

    /**
     * Checks, whether stored form is ready (not empty) to process or not
     *
     * @return {@code true} when {@code form} field is initialized and ready to process
     */
    public boolean isFormReady() {
        return formReady;
    }

    /**
     * Takes data from {@code form} field and using injected {@code SearchOptionsService}
     * creates {@code FormDisplay} object with all data necessary to display search conditions
     *
     * @return {@code FormDisplay} object based on data from {@code Form} object, with all data
     * needed to display search conditions on a result.html view
     */
    public FormDisplay getFormDisplay() {
        FormDisplay formDisplay = new FormDisplay();

        formDisplay.setFlatSize(searchOptionsService.findById(form.getFlatSizeId()));
        formDisplay.setBuilding(searchOptionsService.findById(form.getBuildingId()));
        formDisplay.setLevel(searchOptionsService.findById(form.getLevelId()));
        formDisplay.setFurnishings(searchOptionsService.findById(form.getFurnishingsId()));
        formDisplay.setAreaFrom(searchOptionsService.findById(form.getAreaFromId()));
        formDisplay.setAreaTo(searchOptionsService.findById(form.getAreaToId()));
        formDisplay.setCity(form.getCity());

        return formDisplay;
    }
}
