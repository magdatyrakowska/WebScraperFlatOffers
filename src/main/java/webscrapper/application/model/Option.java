package webscrapper.application.model;

import javax.validation.constraints.Pattern;

public class Option {


    private FlatSize flatSize;

    @Pattern(regexp = "([^\\d])+")
    private String city;
}
