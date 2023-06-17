package tests.user;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.*;

import org.junit.jupiter.api.*;
import specs.Specs;
import testdata.TestData;
import tests.TestBase;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.Specs.responseSpec;
import static steps.UserSteps.createUsers;

@Epic("Pet store")
@Feature("User")
public class PetStoreUserTests extends TestBase {

    TestData testData = new TestData();

    @Test
    @DisplayName("Create user")
    void createUser() {

        UserData user = createUsers(testData.userid, testData.username, testData.firstname, testData.lastname, testData.email, testData.password, testData.phone, testData.userStatus);

        UserResponse response = given()
                .filter(withCustomTemplates())
                .spec(Specs.request)
                .body(user)
                .when()
                .post("/user")
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
    @DisplayName("Login")
    void checkLogin() {

        Login loginUser = new Login();
        loginUser.setUsername(testData.username);
        loginUser.setPassword(testData.password);

        UserResponse response = given()
                .spec(Specs.request)
                .body(loginUser)
                .when()
                .get("/user/login")
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
        step("Check that Message field contains not only letters and digits", () ->
                assertFalse(response.getMessage().matches("[a-zA-Z0-9]+")));
    }

    @Test
    @DisplayName("Get user by name")
    void checkUserByName() {

        UserDataResponse response = given()
                .spec(Specs.request)
                .when()
                .get("/user/" + testData.username)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(UserDataResponse.class);

        step("Check that username is 'Charlie'", () ->
                assertEquals(testData.username, response.getUsername()));
        step("Check that lastname is 'Mitchel'", () ->
                assertEquals(testData.lastname, response.getLastName()));
        step("Check that email is 'cherise888@gmail.com'", () ->
                assertEquals(testData.email, response.getEmail()));
        step("Check that password is '92xu9bbg9bpa'", () ->
                assertEquals(testData.password, response.getPassword()));
        step("Check that phone is '+79603253232'", () ->
                assertEquals(testData.phone, response.getPhone()));
        step("Check that userCode is '0'", () ->
                assertEquals(testData.userStatus, response.getUserStatus()));

    }

}


