package webscrapper.application.model;

import lombok.Data;

@Data
public class FormDisplay {

    private SearchOption flatSize;
    private SearchOption building;
    private SearchOption level;
    private SearchOption furnishings;
    private SearchOption areaFrom;
    private SearchOption areaTo;
    private String city;

}