package webscraper.application.service;

import lombok.Data;
import webscraper.application.model.Form;

@Data
public class URLRequest {

    private Form form;
    private final String base = "https://www.olx.pl/nieruchomosci/mieszkania/wynajem/torun/";


    public String getURL() {
        String url = base;
/*

        switch(form.getFlatSize().getId()) {
            case ONE_ROOM:
                url += "?search%5Bfilter_enum_rooms%5D%5B0%5D=one";
                break;
            case TWO_ROOMS:
                url += "?search%5Bfilter_enum_rooms%5D%5B0%5D=two";
                break;
            case THREE_ROOMS:
                url += "?search%5Bfilter_enum_rooms%5D%5B0%5D=three";
                break;
            case FOUR_ROOMS_AND_MORE:
                url += "?search%5Bfilter_enum_rooms%5D%5B0%5D=four";
                break;
            case ALL_SIZES:
            default:
        }
*/

        return url;
    }

}
