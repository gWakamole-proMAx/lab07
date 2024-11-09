package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDays();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByOrder();
    }

    private enum Month {
        JANUARY(31), FEBRUARY(28),
        MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31), 
        AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

        private final int days;

        Month(final int number_of_days) {
            this.days = number_of_days;
        }

        public static Month fromString(final String name) {
            Objects.requireNonNull(name);
            String nameToFind = name.toUpperCase();
            boolean found = false;
            Month returnMonth = null;

            for (final Month month : Month.values()) {
                if(month.toString().contains(nameToFind)) {
                    if(!found) {
                        found = true;
                        returnMonth = month;
                    }
                    else {
                        throw new IllegalArgumentException(name + " is an ambiguous month name");
                    }
                }
            }

            if(!found) {
                throw new IllegalArgumentException(name + " is not a month name");
            }
            else {
                return returnMonth;
            }
        }
    }
    private final static class SortByDays implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            final var m1 = Month.fromString(o1); 
            final var m2 = Month.fromString(o2); 
            return Integer.compare(m1.days, m2.days);
        }
    }

    private final static class SortByOrder implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            final var m1 = Month.fromString(o1); 
            final var m2 = Month.fromString(o2);
            return m1.compareTo(m2);
        }
    }
}