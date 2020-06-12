package webscrapper.application.model;


public enum FlatSize {
    ALL_SIZES("wszystkie", "all"),
    ONE_ROOM("kawalerka", "studio"),
    TWO_ROOMS("2 pokoje", "2 rooms"),
    THREE_ROOMS("3 pokoje", "3 rooms"),
    FOUR_ROOMS_AND_MORE( "4 i więcej pokoi", "4 and more rooms");

    private final String displayValuePl;
    private final String displayValueEng;


    FlatSize(String displayValuePl, String displayValueEng) {
        this.displayValuePl = displayValuePl;
        this.displayValueEng = displayValueEng;
    }

    public String getDisplayValuePl() {
        return displayValuePl;
    }

    public String getDisplayValueEng() {
        return displayValueEng;
    }
}
