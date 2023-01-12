package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

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
    private final SelenideElement invalidFormatNotification = $(withText("Неверный формат"));
    private final SelenideElement fillInTheFieldNotification = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement invalidDateNotification = $(withText("Неверно указан срок действия карты"));
    private final SelenideElement expiredYearNotification = $(withText("Истёк срок действия карты"));

    public void fillTheForm (DataHelper.CardInfo cardInformation) {
        cardNumberField.setValue(cardInformation.getCardNumber());
        expirationMonthField.setValue(cardInformation.getMonth());
        expirationYearField.setValue(cardInformation.getYear());
        cardHolderNameField.setValue(cardInformation.getOwnerName());
        cardSecurityCodeField.setValue(cardInformation.getCVC());
        continueButton.click();
    }

    public void successfulCreditCardPayment() {
        successfulNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void failedCreditCardPayment() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void invalidCreditCardFormat() {
        invalidFormatNotification.shouldBe(visible);
    }

    public void fillInTheFieldRequest() {
        fillInTheFieldNotification.shouldBe(visible);
    }

    public void invalidCreditCardDate() {
        invalidDateNotification.shouldBe(visible);
    }

    public void expiredCreditCardDate() {
        expiredYearNotification.shouldBe(visible);
    }
}
