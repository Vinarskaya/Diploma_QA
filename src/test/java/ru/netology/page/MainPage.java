package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement buyButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));

    public OrderPage payWithCard() {
        buyButton.click();
        return new OrderPage();
    }

    public CreditPage buyByCredit() {
        creditButton.click();
        return new CreditPage();
    }
}