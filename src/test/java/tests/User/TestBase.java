package tests.User;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import tests.Pet.PetsTestData;

public class TestBase {
    TestData testData = new TestData();
    protected PetsTestData petData = new PetsTestData();

    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}
