package tests.user;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.UserData;
import models.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;
import testdata.TestData;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.Specs.responseDeleteSpec;
import static specs.Specs.responseSpec;
import static steps.UserSteps.updateUser;


@Epic("Pet store")
@Feature("User")
public class PetStoreUserChangeTests extends TestBase {
    TestData testData = new TestData();

    @Test
    @DisplayName("Update user")
    void checkUpdateUser() {

        UserData user = updateUser(testData.userid, testData.username, testData.newFirstName, testData.lastname, testData.email, testData.password, testData.phone, testData.userStatus);

        UserResponse response = given()
                .spec(Specs.request)
                .body(user)
                .when()
                .put("/user/" + testData.username)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(UserResponse.class);

        step("Check that code is '200'", () ->
                assertEquals(200, response.getCode()));
        step("Check that Type field is not empty", () ->
                assertTrue(response.getType().length() >= 1));
        step("Check that Type field contains only letters and digits", () ->
                assertTrue(response.getType().matches("[a-zA-Z0-9]+")));
        step("Check that Message field is not empty", () ->
                assertTrue(response.getMessage().length() >= 1));
        step("Check that Message field contains only letters and digits", () ->
                assertTrue(response.getMessage().matches("[a-zA-Z0-9]+")));

    }

    @Test
    @DisplayName("Try to delete user, who is not exist")
    void deleteUserTest() {

        given()
                .spec(Specs.request)
                .when()
                .delete("/user/" + testData.noneExistUser)
                .then()
                .spec(responseDeleteSpec);

    }
}
