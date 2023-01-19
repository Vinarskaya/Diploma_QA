package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage extends MainPage {
    private final ElementsCollection fields = $$(".input__control");
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement expirationMonthField = $("[placeholder='08']");
    private final SelenideElement expirationYearField = $("[placeholder='22']");
    private final SelenideElement cardHolderNameField = fields.get(3);
    private final SelenideElement cardSecurityCodeField = $("[placeholder='999']");
    private final SelenideElement continueButton = $(byText("Продолжить"));

    private final SelenideElement successfulNotification = $(withText("Успешно"));
    private final SelenideElement errorNotification = $(withText("Ошибка"));
    private final SelenideElement invalidFormat = $(withText("Неверный формат"));
    private final SelenideElement fillTheFormRequest = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement invalidDateNotification = $(withText("Неверно указан срок действия карты"));
    private final SelenideElement expiredYearNotification = $(withText("Истёк срок действия карты"));

    private final ElementsCollection invalidNotifications = $$(".input__sub");

    public void fillTheForm (DataHelper.CardInfo cardInformation) {
        cardNumberField.setValue(cardInformation.getNumber());
        expirationMonthField.setValue(cardInformation.getMonth());
        expirationYearField.setValue(cardInformation.getYear());
        cardHolderNameField.setValue(cardInformation.getHolder());
        cardSecurityCodeField.setValue(cardInformation.getCvc());
        continueButton.click();
    }

    public void successfulCreditCardPayment() {
        successfulNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void failedCreditCardPayment() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void invalidCardNumberFormat() {
        invalidNotifications.first().shouldHave(exactText("Неверный формат"));
    }

    public void invalidFormat() {
        invalidFormat.shouldBe(visible);
    }

    public void invalidCVCFormat() {
        invalidNotifications.last().shouldHave(exactText("Неверный формат"));
    }

    public void fillAllField() {
        for (SelenideElement x: invalidNotifications) {
            x.shouldHave(exactText("Поле обязательно для заполнения"));
        }
    }

    public void fillCardNumberRequest() {
        invalidNotifications.first().shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public void fillRequest() {
        fillTheFormRequest.shouldBe(visible);
    }

    public void fillCVCRequest() {
        invalidNotifications.last().shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public void invalidDate() {
        invalidDateNotification.shouldBe(visible);
    }

    public void expiredCardYear() {
        expiredYearNotification.shouldBe(visible);
    }
}