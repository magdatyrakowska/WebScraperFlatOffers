package webscrapper.application.service;

import org.springframework.stereotype.Service;
import webscrapper.application.model.Form;

@Service
public class OptionsService {

    private Form options;

    public Form getOptions() {
        return options;
    }

    public void setOptions(Form options) {
        this.options = options;
    }
}
