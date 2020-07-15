package webscraper.application.model;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class Form {

    private String flatSizeId;
    private String buildingId;
    private String levelId;
    private String furnishingsId;
    private String areaFromId;
    private String areaToId;

    @Pattern(regexp = "([\\D])+")
    private String city;

}