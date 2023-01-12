package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class CVVGenerator {
    private CVVGenerator() {
    }

    public static Faker faker = new Faker(new Locale("en"));

    public static CVVNumber getValidCVVNumber() {
        String code = faker.numerify("###");
        if (code == "000") {
            code = "001";
        }
        return new CVVNumber(code);
    }

    public static CVVNumber getInvalidCVVNumberWithTwoNumbers() {
        return new CVVNumber(faker.numerify("##"));
    }

    public static CVVNumber getInvalidCVVNumberWithSingleNumber() {
        return new CVVNumber(faker.numerify("#"));
    }

    public static CVVNumber getInvalidCVVNumberWithZeros() {
        return new CVVNumber("000");
    }

    @Value
    public static class CVVNumber {
        private String CVVNumber;
    }

}