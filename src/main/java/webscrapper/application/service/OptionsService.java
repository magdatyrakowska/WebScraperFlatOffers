package webscrapper.application.service;

import org.springframework.stereotype.Service;
import webscrapper.application.model.OptionsForm;

@Service
public class OptionsService {

    private OptionsForm options;

    public OptionsForm getOptions() {
        return options;
    }

    public void setOptions(OptionsForm options) {
        this.options = options;
    }
}
