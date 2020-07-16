package webscraper.application.model;

import lombok.Data;

@Data
public class FlatOffer {

    private int price;
    private String description;

    @Override
    public String toString() {
        return price + "\t" + description;
    }

    public void setPrice(String price) {
        price = price.replaceAll(" ", "").replaceAll("zł", "");
        this.price = Integer.parseInt(price);
    }

    public boolean checkLongTerm() {
        return (this.price > 600)
                && !(this.description.toLowerCase().contains("nocleg"))
                && !(this.description.toLowerCase().contains("dzień"))
                && !(this.description.toLowerCase().contains("dni"))
                && !(this.description.toLowerCase().contains("tydzień"))
                && !(this.description.toLowerCase().contains("tygodnie"));
    }

}
