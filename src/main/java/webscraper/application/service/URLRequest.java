package webscraper.application.service;

import lombok.Data;
import webscraper.application.model.Form;

@Data
public class URLRequest {

    private Form form;
    private final String base = "https://www.olx.pl/nieruchomosci/mieszkania/wynajem/torun/";


    public String getURL() {
        return base;
    }

}
