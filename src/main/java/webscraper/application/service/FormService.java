package webscraper.application.service;

import org.springframework.stereotype.Service;
import webscraper.application.model.Form;
import webscraper.application.model.FormDisplay;

@Service
public class FormService {

    private Form form;
    private boolean formReady = false;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
        formReady = true;
    }

    public boolean isFormReady() {
        return formReady;
    }

    public FormDisplay getFormDisplay(SearchOptionsService searchOptionsService) {
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
