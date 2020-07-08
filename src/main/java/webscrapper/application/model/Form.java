package webscrapper.application.model;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class Form {

    private String flatSizeId;
    private String buildingId;

    @Pattern(regexp = "([\\D])+")
    private String city;

}
