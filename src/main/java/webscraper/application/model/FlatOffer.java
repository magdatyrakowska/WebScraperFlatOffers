package webscraper.application.model;

import lombok.Data;

/**
 * Stores data of a flat offer.
 * Method {@code checkLongTerm} allows to check, if flat offer is not an offer for short term - days, weeks etc.
 */
@Data
public class FlatOffer {

    /**
     * Price of an offer
     */
    private int price;
    /**
     * Description of an offer - title from olx page
     */
    private String description;
    /**
     * URL address of that offers individual page
     */
    private String url;


    /**
     * Takes two fields (price and description) and concatenates them to one {@code String}.
     *
     * @return {@code} containing price, tabulator and description of current offer.
     */
    @Override
    public String toString() {
        return price + "\t" + description;
    }

    /**
     * Changes price expressed in {@code String} with currency to {@code int}
     * and assigns it to the price field of {@code FlatOffer} object.
     *
     * @param price String with price and currency
     */
    public void setPrice(String price) {
        price = price.replaceAll(" ", "").replaceAll("zł", "");
        this.price = Integer.parseInt(price);
    }

    /**
     * Checks, if the offer is not for a short time - day, week etc.
     * Compare {@code description} to a set of keywords.
     *
     * @return {@code true} if offer matches restriction for long-term offer,
     * {@code false} in case it does not match any of them.
     */
    public boolean checkLongTerm() {
        return //(this.price > 600) &&
                !(this.description.toLowerCase().contains("nocleg"))
                && !(this.description.toLowerCase().contains("krótkoterminow"))
                && !(this.description.toLowerCase().contains("godziny"))
                && !(this.description.toLowerCase().contains("doby"))
                && !(this.description.toLowerCase().contains("kwarantann"))
                && !(this.description.toLowerCase().contains("dzień"))
                && !(this.description.toLowerCase().contains("dni"))
                && !(this.description.toLowerCase().contains("tydzień"))
                && !(this.description.toLowerCase().contains("tygodnie"));
    }

}
