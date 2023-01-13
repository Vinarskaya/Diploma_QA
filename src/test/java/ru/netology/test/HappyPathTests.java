package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.JDBC.getCreditStatus;
import static ru.netology.data.JDBC.getPaymentStatus;

public class HappyPathTests {

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

    @DisplayName("Successful purchase with valid debit card.")
    @Test
    public void shouldConfirmPaymentWithValidCard() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var validCardInfo = getValidCardInfo();
        orderPage.fillTheForm(validCardInfo);
        orderPage.successfulCardPayment();

        Assertions.assertEquals("APPROVED", getPaymentStatus());
    }

    @DisplayName("Successful purchase with valid debit card. Holder name has two words through space.")
    @Test
    public void shouldConfirmPaymentWithValidCardWithTwoWordsNameThroughSpace() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var validCardInfo = getValidCardInfoWithTwoWordsNameThroughSpace();
        orderPage.fillTheForm(validCardInfo);
        orderPage.successfulCardPayment();

        Assertions.assertEquals("APPROVED", getPaymentStatus());
    }

    @DisplayName("Successful purchase with valid debit card. Holder name has two words through hyphen.")
    @Test
    public void shouldConfirmPaymentWithValidCardWithTwoWordsNameThroughHyphen() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var validCardInfo = getValidCardInfoWithTwoWordsNameThroughHyphen();
        orderPage.fillTheForm(validCardInfo);
        orderPage.successfulCardPayment();

        Assertions.assertEquals("APPROVED", getPaymentStatus());
    }

    @DisplayName("Successful purchase with valid debit card. Card has current month and year.")
    @Test
    public void shouldConfirmPaymentWithValidCardWithCurrentMonthAndYear() {
        var mainPage = new MainPage();
        var orderPage = mainPage.payWithCard();
        var validCardInfo = getValidCardInfoWithCurrentMonthAndYear();
        orderPage.fillTheForm(validCardInfo);
        orderPage.successfulCardPayment();

        Assertions.assertEquals("APPROVED", getPaymentStatus());
    }

    @DisplayName("Successful purchase with valid credit card.")
    @Test
    public void shouldConfirmCreditWithValidCard() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var validCardInfo = getValidCardInfo();
        creditPage.fillTheForm(validCardInfo);
        creditPage.successfulCreditCardPayment();

        Assertions.assertEquals("APPROVED", getCreditStatus());
    }

    @DisplayName("Successful purchase with valid credit card. Holder name has two words through space.")
    @Test
    public void shouldConfirmCreditWithValidCardWithTwoWordsNameThroughSpace() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var validCardInfo = getValidCardInfoWithTwoWordsNameThroughSpace();
        creditPage.fillTheForm(validCardInfo);
        creditPage.successfulCreditCardPayment();

        Assertions.assertEquals("APPROVED", getCreditStatus());
    }

    @DisplayName("Successful purchase with valid credit card. Holder name has two words through hyphen.")
    @Test
    public void shouldConfirmCreditWithValidCardWithTwoWordsNameThroughHyphen() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var validCardInfo = getValidCardInfoWithTwoWordsNameThroughHyphen();
        creditPage.fillTheForm(validCardInfo);
        creditPage.successfulCreditCardPayment();

        Assertions.assertEquals("APPROVED", getCreditStatus());
    }

    @DisplayName("Successful purchase with valid credit card. Card has current month and year.")
    @Test
    public void shouldConfirmCreditWithValidCardWithCurrentMonthAndYear() {
        var mainPage = new MainPage();
        var creditPage = mainPage.buyByCredit();
        var validCardInfo = getValidCardInfoWithCurrentMonthAndYear();
        creditPage.fillTheForm(validCardInfo);
        creditPage.successfulCreditCardPayment();

        Assertions.assertEquals("APPROVED", getCreditStatus());
    }
}