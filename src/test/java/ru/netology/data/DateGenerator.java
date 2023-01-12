package ru.netology.data;

import lombok.Value;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static ru.netology.data.DataHelper.faker;

public class DateGenerator {
    private DateGenerator() {
    }
    private static final LocalDate actualDate = LocalDate.now();
    static DateTimeFormatter monthTimeFormatter = DateTimeFormatter.ofPattern("MM");
    static DateTimeFormatter yearTimeFormatter = DateTimeFormatter.ofPattern("yy");

    public static Date getCurrentDate() {
        return new Date(actualDate.format(yearTimeFormatter),
                actualDate.format(monthTimeFormatter));
    }

    public static Date getValidDate() {
        int numberOfYeatsAdded = faker.number().numberBetween(0, 5);
        Month month = actualDate.getMonth();
        if (numberOfYeatsAdded == 5) {
            month = actualDate.minusMonths(faker.number()
                    .numberBetween(0, actualDate.getMonth().getValue()-1)).getMonth();
        } else if (numberOfYeatsAdded == 0) {
            month = actualDate.plusMonths(faker.number()
                    .numberBetween(1,12 - actualDate.getMonth().getValue())).getMonth();
        }
        LocalDate newDate = LocalDate.of(actualDate.plusYears(numberOfYeatsAdded).getYear(),
                month, actualDate.getDayOfYear());
        return new Date(newDate.format(yearTimeFormatter),
                newDate.format(monthTimeFormatter));
    }

    public static Date getDateWithExpirationMoreThatFiveYears() {
        int numberOfYeatsAdded = faker.number().numberBetween(5, 99 - actualDate.getYear());
        Month month = actualDate.getMonth();
        if (numberOfYeatsAdded == 5) {
            month = actualDate.plusMonths(faker.number().
                    numberBetween(1, 12 - actualDate.getMonth().getValue())).getMonth();
        }
        LocalDate newDate = LocalDate.of(actualDate.plusYears(numberOfYeatsAdded).getYear(),
                month, actualDate.getDayOfYear());
        return new Date(newDate.format(yearTimeFormatter),
                newDate.format(monthTimeFormatter));
    }

    public static Date getDateWithPreviousYears() {
        int numberOfYeatsTaken = faker.number().numberBetween(1, actualDate.getYear());
        return new Date(actualDate.minusYears(numberOfYeatsTaken).format(yearTimeFormatter),
                actualDate.format(monthTimeFormatter));
    }

    public static Date getDateWithPreviousMonths() {
        int numberOfMonthsTaken = faker.number().numberBetween(1, actualDate.getMonth().getValue() - 1);
        return new Date(actualDate.format(yearTimeFormatter),
                actualDate.minusMonths(numberOfMonthsTaken).format(monthTimeFormatter));
    }

    public static Date getDateWithInvalidMonthInfo() {
        int number = faker.number().numberBetween(0, 99);
        String month = String.valueOf(number);
        if (number >= 0 && number < 13) {
            month = "00";
        }
        return new Date(actualDate.format(yearTimeFormatter), month);
    }

    @Value
    public static class Date {
        private String year;
        private String month;
    }
}