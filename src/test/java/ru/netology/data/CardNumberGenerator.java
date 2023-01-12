package ru.netology.data;

import static ru.netology.data.DataHelper.faker;

public class CardNumberGenerator {
    private CardNumberGenerator() {
    }
    public static String ApprovedCardNumber() {
        return "4444444444444441";
    }

    public static String DeclinedCardNumber() {
        return "4444444444444442";
    }

    public static String InvalidCardNumber() {
        String cardNumber = "4444444444444441";
        cardNumber.substring(0, faker.number().numberBetween(0, cardNumber.length() - 2));
        return cardNumber;
    }
}