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
        formDisplay.setCity(form.getCity());

        return formDisplay;
    }
}
