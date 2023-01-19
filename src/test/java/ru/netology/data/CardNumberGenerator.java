package ru.netology.data;

import static ru.netology.data.DataHelper.faker;

public class CardNumberGenerator {
    private CardNumberGenerator() {
    }
    public static String ApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String DeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String InvalidCardNumber() {
        String cardNumber = "4444 4444 4444 4441";
        String invalidCardNumber = cardNumber.substring(0, faker.number().numberBetween(1, cardNumber.length() - 1));
        return invalidCardNumber;
    }
}