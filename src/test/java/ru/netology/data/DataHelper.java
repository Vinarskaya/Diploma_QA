package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

import static ru.netology.data.CVVGenerator.*;
import static ru.netology.data.CardNumberGenerator.*;
import static ru.netology.data.DateGenerator.*;
import static ru.netology.data.HoldersGenerator.*;

public class DataHelper {
    private DataHelper() {
    }
    public static Faker faker = new Faker(new Locale("en"));

    public static CardInfo getValidCardInfo() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getValidCardInfoWithTwoWordsNameThroughSpace() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidNameWithTwoWordsThroughSpace().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getValidCardInfoWithTwoWordsNameThroughHyphen() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidNameWithTwoWordsThroughHyphen().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getValidCardInfoWithCurrentMonthAndYear() {
        return new CardInfo(ApprovedCardNumber(),
                getCurrentDate().getMonth(),
                getCurrentDate().getYear(),
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(DeclinedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithExpirationDateMoreThanFiveYears() {
        return new CardInfo(ApprovedCardNumber(),
                getDateWithExpirationMoreThatFiveYears().getMonth(),
                getDateWithExpirationMoreThatFiveYears().getYear(),
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithExpiredYear() {
        return new CardInfo(ApprovedCardNumber(),
                getDateWithPreviousYears().getMonth(),
                getDateWithPreviousYears().getYear(),
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithExpiredMonth() {
        return new CardInfo(ApprovedCardNumber(),
                getDateWithPreviousMonths().getMonth(),
                getDateWithPreviousMonths().getYear(),
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithEmptyData() {
        return new CardInfo("",
                "",
                "",
                "",
                "");
    }

    public static CardInfo getCardInfoWithEmptyCardNumber() {
        return new CardInfo("",
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithEmptyMonth() {
        return new CardInfo(ApprovedCardNumber(),
                "",
                getValidDate().getYear(),
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithEmptyYear() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                "",
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithEmptyOwnerName() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                "",
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithEmptyCVV() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidName().getHolderName(),
                "");
    }

    public static CardInfo getCardInfoWithInvalidCVVWithTwoNumbers() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidName().getHolderName(),
                getInvalidCVVNumberWithTwoNumbers().getCVVNumber());
    }

    public static CardInfo getCardInfoWithInvalidCVVWithSingleNumber() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidName().getHolderName(),
                getInvalidCVVNumberWithSingleNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithInvalidCVVWithZeros() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidName().getHolderName(),
                getInvalidCVVNumberWithZeros().getCVVNumber());
    }

    public static CardInfo getCardInfoWithSmallCardNumber() {
        return new CardInfo(InvalidCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getValidName().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithInvalidHolderNameInCyrillic() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getInvalidNameInCyrillic().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithInvalidHolderNameWithNumbers() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getInvalidNameWithNumbers().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    public static CardInfo getCardInfoWithInvalidHolderNameWithSymbols() {
        return new CardInfo(ApprovedCardNumber(),
                getValidDate().getMonth(),
                getValidDate().getYear(),
                getInvalidNameWithSymbols().getHolderName(),
                getValidCVVNumber().getCVVNumber());
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String ownerName;
        private String CVC;
    }
}