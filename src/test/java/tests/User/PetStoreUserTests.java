package tests.User;

import models.*;

import org.junit.jupiter.api.*;
import specs.Specs;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.Specs.responseSpec;

public class PetStoreUserTests extends TestBase {

    @Test
    void createUser() {

        UserData user = new UserData();
        user.setId(testData.id);
        user.setUsername(testData.username);
        user.setFirstName(testData.firstname);
        user.setLastName(testData.lastname);
        user.setEmail(testData.email);
        user.setPassword(testData.password);
        user.setPhone(testData.phone);
        user.setUserStatus(testData.userStatus);

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

        assertEquals(response.getCode(), 200);
        assertNotNull(response.getType());
        assertNotNull(response.getMessage());

    }

    @Test
    @DisplayName("Login")
    void checkLogin() {

        Login loginUser = new Login();
        loginUser.setUsername("Vicente");
        loginUser.setPassword("7hrvwk5pdvk1u");

        UserResponse response = given()
                .filter(withCustomTemplates())
                .spec(Specs.request)
                .body(loginUser)
                .when()
                .get("/user/login")
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(UserResponse.class);

        assertEquals(response.getCode(), 200);
        assertNotNull(response.getType());
        assertNotNull(response.getMessage());
    }

    @Test
    @DisplayName("Get user by user name")
    void checkUserByName() {

        UserDataResponse response = given()
                .filter(withCustomTemplates())
                .spec(Specs.request)
                .when()
                .get("/user/" + testData.username)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(UserDataResponse.class);

        assertEquals(response.getUsername(), testData.username);
        assertEquals(response.getFirstName(), testData.firstname);
        assertEquals(response.getLastName(), testData.lastname);
        assertEquals(response.getEmail(), testData.email);
        assertEquals(response.getPassword(), testData.password);
        assertEquals(response.getPhone(), testData.phone);
        assertEquals(response.getUserStatus(), testData.userStatus);

    }

}


