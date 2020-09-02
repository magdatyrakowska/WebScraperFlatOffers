package webscraper.application.model;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * Stores search options send from form on the options.html view.
 * Options (except {@code city}) are stored as an id number,
 * which can be changed to {@code SearchOption} in{@code SearchOptionService}.
 */
@Data
public class Form {
    /**
     * id of the flat size option
     */
    private String flatSizeId;
    /**
     * id of the building type option
     */
    private String buildingId;
    /**
     * id of the level option
     */
    private String levelId;
    /**
     * id is the flat furnished
     */
    private String furnishingsId;
    /**
     * id of the area down limit option
     */
    private String areaFromId;
    /**
     * id of the area up limit option
     */
    private String areaToId;

    /**
     * City name, pattern used to check if name is proper (if do not have numbers)
     */
    @Pattern(regexp = "([\\D])+")
    private String city;

}
