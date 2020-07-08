package webscrapper.application.service;

import org.springframework.stereotype.Service;
import webscrapper.application.model.Form;
import webscrapper.application.model.FormDisplay;

@Service
public class FormService {

    private Form form;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
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
