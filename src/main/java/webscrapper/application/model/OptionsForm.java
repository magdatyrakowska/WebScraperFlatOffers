package webscrapper.application.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class OptionsForm {

    private FlatSize flatSize;

    @Pattern(regexp = "([^\\d])+")
    private String city;


}
