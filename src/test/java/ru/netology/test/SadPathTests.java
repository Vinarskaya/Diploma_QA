package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.JDBC.getCreditStatus;
import static ru.netology.data.JDBC.getPaymentStatus;

public class SadPathTests {

    @BeforeEach
    public void openChrome() {
        open("http://localhost:8080/");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("Unsuccessful purchase with invalid debit card.")
    @Test
    public void shouldConfirmFailWithInvalidCard() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getDeclinedCardInfo();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.failedCardPayment();

        Assertions.assertEquals("DECLINED", getPaymentStatus());
    }

    @DisplayName("Unsuccessful purchase with invalid debit card. Card has expiration date more than five years.")
    @Test
    public void shouldConfirmFailWithInvalidCardWhereExpirationDateMoreThanFiveYears() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithExpirationDateMoreThanFiveYears();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.invalidDate();
    }

    @DisplayName("Unsuccessful purchase with invalid debit card. Card has expired year.")
    @Test
    public void shouldConfirmFailWithInvalidCardWithExpiredYear() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithExpiredYear();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.expiredCardYear();
    }

    @DisplayName("Unsuccessful purchase with invalid debit card. Card has expired month.")
    @Test
    public void shouldConfirmFailWithInvalidCardWithExpiredMonth() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithExpiredMonth();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.expiredCardYear();
    }

    @DisplayName("Unsuccessful purchase with invalid debit card. Card has invalid month.")
    @Test
    public void shouldConfirmFailWithInvalidCardWithInvalidMonth() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithInvalidMonth();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.invalidDate();
    }

    @DisplayName("Unsuccessful purchase with all empty fields.")
    @Test
    public void shouldConfirmFailWithAllEmptyFields() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithEmptyData();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.fillAllField();
    }

    @DisplayName("Unsuccessful purchase with empty card number field.")
    @Test
    public void shouldConfirmFailWithEmptyCardNumberField() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithEmptyCardNumber();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.fillCardNumberRequest();
    }

    @DisplayName("Unsuccessful purchase with empty month field.")
    @Test
    public void shouldConfirmFailWithEmptyMonthField() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithEmptyMonth();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.fillRequest();
    }

    @DisplayName("Unsuccessful purchase with empty year field.")
    @Test
    public void shouldConfirmFailWithEmptyYearField() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithEmptyYear();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.fillRequest();
    }

    @DisplayName("Unsuccessful purchase with empty holder name field.")
    @Test
    public void shouldConfirmFailWithEmptyHolderNameField() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithEmptyOwnerName();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.fillRequest();
    }

    @DisplayName("Unsuccessful purchase with empty cvv field.")
    @Test
    public void shouldConfirmFailWithEmptyCVVField() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithEmptyCVV();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.fillCVCRequest();
    }

    @DisplayName("Unsuccessful purchase with invalid cvv. CVV has only two numbers.")
    @Test
    public void shouldConfirmFailWithInvalidTwoNumbersCVV() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithInvalidCVVWithTwoNumbers();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.invalidCVCFormat();
    }

    @DisplayName("Unsuccessful purchase with invalid cvv. CVV has only one number.")
    @Test
    public void shouldConfirmFailWithInvalidOneNumberCVV() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithInvalidCVVWithSingleNumber();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.invalidCVCFormat();
    }

    @DisplayName("Unsuccessful purchase with invalid cvv. CVV has three zeros.")
    @Test
    public void shouldConfirmFailWithInvalidCVVWithZeros() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithInvalidCVVWithZeros();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.invalidCVCFormat();
    }

    @DisplayName("Unsuccessful purchase with invalid debit card. Card number too small.")
    @Test
    public void shouldConfirmFailWithInvalidCardWithSmallCardNumber() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithSmallCardNumber();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.invalidCardNumberFormat();
    }

    @DisplayName("Unsuccessful purchase with invalid debit card. Holder name in Cyrillic.")
    @Test
    public void shouldConfirmFailWithInvalidCardWithHolderNameInCyrillic() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithInvalidHolderNameInCyrillic();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.invalidFormat();
    }

    @DisplayName("Unsuccessful purchase with invalid debit card. Holder name with numbers.")
    @Test
    public void shouldConfirmFailWithInvalidCardWhereHolderNameHasNumbers() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithInvalidHolderNameWithNumbers();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.invalidFormat();
    }

    @DisplayName("Unsuccessful purchase with invalid debit card. Holder name with symbols.")
    @Test
    public void shouldConfirmFailWithInvalidCardWhereHolderNameHasSymbols() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var invalidCardInfo = getCardInfoWithInvalidHolderNameWithSymbols();
        orderPage.fillTheForm(invalidCardInfo);
        orderPage.invalidFormat();
    }

    @DisplayName("Unsuccessful credit with declined card.")
    @Test
    public void shouldConfirmCreditFailWithDeclinedCard() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getDeclinedCardInfo();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.failedCreditCardPayment();

        Assertions.assertEquals("DECLINED", getCreditStatus());
    }

    @DisplayName("Unsuccessful credit with invalid card. Card has expiration date more than five years.")
    @Test
    public void shouldConfirmCreditFailWithInvalidCardWhereExpirationDateMoreThanFiveYears() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithExpirationDateMoreThanFiveYears();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.invalidDate();
    }

    @DisplayName("Unsuccessful credit with invalid card. Card has expired year.")
    @Test
    public void shouldConfirmCreditFailWithInvalidCardWithExpiredYear() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithExpiredYear();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.expiredCardYear();
    }

    @DisplayName("Unsuccessful credit with invalid card. Card has expired month.")
    @Test
    public void shouldConfirmCreditFailWithInvalidCardWithExpiredMonth() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithExpiredMonth();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.expiredCardYear();
    }

    @DisplayName("Unsuccessful credit with invalid debit card. Card has invalid month.")
    @Test
    public void shouldConfirmCreditFailWithInvalidCardWithInvalidMonth() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithInvalidMonth();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage. invalidDate();
    }

    @DisplayName("Unsuccessful credit with all empty fields.")
    @Test
    public void shouldConfirmCreditFailWithAllEmptyFields() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithEmptyData();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.fillAllField();
    }

    @DisplayName("Unsuccessful credit with empty card number field.")
    @Test
    public void shouldConfirmCreditFailWithEmptyCardNumberField() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithEmptyCardNumber();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.fillCardNumberRequest();
    }

    @DisplayName("Unsuccessful credit with empty month field.")
    @Test
    public void shouldConfirmCreditFailWithEmptyMonthField() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithEmptyMonth();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.fillRequest();
    }

    @DisplayName("Unsuccessful credit with empty year field.")
    @Test
    public void shouldConfirmCreditFailWithEmptyYearField() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithEmptyYear();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.fillRequest();
    }

    @DisplayName("Unsuccessful credit with empty holder name field.")
    @Test
    public void shouldConfirmCreditFailWithEmptyHolderNameField() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithEmptyOwnerName();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.fillRequest();
    }

    @DisplayName("Unsuccessful credit with empty cvv field.")
    @Test
    public void shouldConfirmCreditFailWithEmptyCVVField() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithEmptyCVV();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.fillCVCRequest();
    }

    @DisplayName("Unsuccessful credit with invalid cvv. CVV has only two numbers.")
    @Test
    public void shouldConfirmCreditFailWithInvalidTwoNumbersCVV() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithInvalidCVVWithTwoNumbers();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.invalidCVCFormat();
    }

    @DisplayName("Unsuccessful credit with invalid cvv. CVV has only one number.")
    @Test
    public void shouldConfirmCreditFailWithInvalidOneNumberCVV() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithInvalidCVVWithSingleNumber();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.invalidCVCFormat();
    }

    @DisplayName("Unsuccessful credit with invalid cvv. CVV has three zeros.")
    @Test
    public void shouldConfirmCreditFailWithInvalidCVVWithZeros() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithInvalidCVVWithZeros();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.invalidCVCFormat();
    }

    @DisplayName("Unsuccessful credit with invalid card. Card number too small.")
    @Test
    public void shouldConfirmCreditFailWithInvalidCardWithSmallCardNumber() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithSmallCardNumber();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.invalidCardNumberFormat();
    }

    @DisplayName("Unsuccessful credit with invalid card. Holder name in Cyrillic.")
    @Test
    public void shouldConfirmCreditFailWithInvalidCardWithHolderNameInCyrillic() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithInvalidHolderNameInCyrillic();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.invalidFormat();
    }

    @DisplayName("Unsuccessful credit with invalid card. Holder name with numbers.")
    @Test
    public void shouldConfirmCreditFailWithInvalidCardWhereHolderNameHasNumbers() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithInvalidHolderNameWithNumbers();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.invalidFormat();
    }

    @DisplayName("Unsuccessful credit with invalid card. Holder name with symbols.")
    @Test
    public void shouldConfirmCreditFailWithInvalidCardWhereHolderNameHasSymbols() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var invalidCardInfo = getCardInfoWithInvalidHolderNameWithSymbols();
        creditPage.fillTheForm(invalidCardInfo);
        creditPage.invalidFormat();
    }
}