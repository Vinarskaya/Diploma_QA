package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.API.*;
import static ru.netology.data.DataHelper.getDeclinedCardInfo;
import static ru.netology.data.DataHelper.getValidCardInfo;

public class APITests {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("Get status for purchase with valid debit card.")
    @Test
    void shouldGetStatusForValidCardPayment() {
        val validCard = getValidCardInfo();
        val status = getPaymentStatus(validCard);
        assertEquals("APPROVED", status.getStatus());
    }

    @DisplayName("Get status for purchase with declined debit card.")
    @Test
    void shouldGetStatusForDeclinedCardPayment() {
        val declinedCard = getDeclinedCardInfo();
        val status = getPaymentStatus(declinedCard);
        assertEquals("DECLINED", status.getStatus());
    }

    @DisplayName("Get status for credit with valid debit card.")
    @Test
    void shouldGetStatusForValidCreditRequest() {
        val validCard = getValidCardInfo();
        val status = getCreditStatus(validCard);
        assertEquals("APPROVED", status.getStatus());
    }

    @DisplayName("Get status for credit with declined debit card.")
    @Test
    void shouldGetStatusValidDeclinedCardCreditRequest() {
        val declinedCard = getDeclinedCardInfo();
        val status = getCreditStatus(declinedCard);
        assertEquals("DECLINED", status.getStatus());
    }
}
