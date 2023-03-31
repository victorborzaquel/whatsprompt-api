package com.victorborzaquel.whatsprompt.enums;

import java.time.LocalDate;

public enum FilterDates {
    LAST_WEEK(LocalDate.now().minusDays(7)),
    LAST_MONTH(LocalDate.now().minusMonths(1)),
    LAST_YEAR(LocalDate.now().minusYears(1)),
    ALL_TIME(null);

    private final LocalDate date;

    FilterDates(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
