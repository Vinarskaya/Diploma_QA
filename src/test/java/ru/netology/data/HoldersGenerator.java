package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class HoldersGenerator {
    private HoldersGenerator() {
    }

    public static Faker faker = new Faker(new Locale("en"));
    public static Faker ruOption = new Faker(new Locale("ru"));

    public static Holder getValidName() {
        return new Holder(faker.name().fullName());
    }

    public static Holder getValidNameWithTwoWordsThroughSpace() {
        return new Holder((faker.name().lastName() + " " + faker.name().fullName()));
    }

    public static Holder getValidNameWithTwoWordsThroughHyphen() {
        return new Holder((faker.name().lastName() + "-" + faker.name().fullName()));
    }

    public static Holder getInvalidNameInCyrillic() {
        return new Holder(ruOption.name().fullName());
    }

    public static Holder getInvalidNameWithNumbers() {
        return new Holder(faker.name().fullName() + faker.numerify("#"));
    }

    public static Holder getInvalidNameWithSymbols() {
        return new Holder(faker.name().fullName() + "!");
    }


    @Value
    public static class Holder {
        private String holderName;
    }
}