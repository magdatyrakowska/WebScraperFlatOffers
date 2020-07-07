package webscrapper.application.model;

import lombok.Data;

@Data
public class SearchOption {
    private final String id;
    private final Category category;
    private final String namePl;
    private final String nameENG;

    public SearchOption(String id, Category category, String namePl, String nameENG) {
        this.id = id;
        this.category = category;
        this.namePl = namePl;
        this.nameENG = nameENG;
    }

}