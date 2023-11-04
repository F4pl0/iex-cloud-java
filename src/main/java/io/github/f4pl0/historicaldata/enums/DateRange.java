package io.github.f4pl0.historicaldata.enums;

public enum DateRange {
    YEAR("y"),
    YEAR_TO_DATE("ytd"),
    MONTH("m"),
    DAY("d");

    private final String value;

    DateRange(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
