package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import static io.restassured.RestAssured.given;


public class API {

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static APIResponse getPaymentStatus (DataHelper.CardInfo cardInfo) {
        return given()
                .spec(requestSpec)
                .body(cardInfo)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract().response().as(APIResponse.class);
    }

    public static APIResponse getCreditStatus (DataHelper.CardInfo cardInfo) {
        return given()
                .spec(requestSpec)
                .body(cardInfo)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .extract().response().as(APIResponse.class);
    }

    @Value
    public static class APIResponse {
        private String status;
    }
}