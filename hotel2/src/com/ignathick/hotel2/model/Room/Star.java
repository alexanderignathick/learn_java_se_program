package com.ignathick.hotel2.model.Room;

public enum Star {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final Integer starValue;

    Star (final Integer starValue) {
        this.starValue = starValue;
    }

    public Integer getStarValue() {
        return this.starValue;
    }

    public Star getStarByStr(String starStr){

        for (Star star:
             values()) {
            if (Integer.valueOf(starStr).equals(getStarValue())) {
                return star;
            }
        }

        return null;

    }

}
