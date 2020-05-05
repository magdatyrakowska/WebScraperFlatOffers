package webscrapper.application.model;


public enum FlatSize {
    ALL_SIZES("wszystkie"),
    ONE_ROOM("kawalerka"),
    TWO_ROOMS("2 pokoje"),
    THREE_ROOMS("3 pokoje"),
    FOUR_ROOMS_AND_MORE( "4 i więcej pokoi");

    private final String displayValue;


    FlatSize(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
