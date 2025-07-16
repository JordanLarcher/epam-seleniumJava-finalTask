package com.swaglabs.tests;

import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.logging.Logger;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginTest extends BaseTest{

    private static final Logger logger = Logger.getLogger(LoginTest.class.getName());

    static Stream<String> browserProvider() {
        return Stream.of("firefox", "chrome");
    }


    @ParameterizedTest
    @MethodSource("browserProvider")
    public void testLoginWithEmptyCredentials(String browser){
        setupTest(browser);
        logger.info("Executing UC-1: Test Login with empty credentials");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("test");
        loginPage.enterPassword("test");
        loginPage.clearUsername();
        loginPage.clearPassword();
        loginPage.clickLoginButton();

        String expectedErrorMessage;
        if("firefox".equalsIgnoreCase(browser)){
            expectedErrorMessage = "Epic sadface: Username is required";
        }else {
            expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        }
        String actualErrorMessage = loginPage.getErrorMessage();
        assertThat(actualErrorMessage, is(equalTo(expectedErrorMessage)));
        logger.info("UC-1: Passed on {}");
    }


    @ParameterizedTest
    @MethodSource("browserProvider")
    public void testLoginWithEmptyPassword(String browser){
        setupTest(browser);
        logger.info("Executing UC-2: Test Login with empty password");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.clearPassword();
        loginPage.clickLoginButton();

        String expectedErrorMessage;
        if("firefox".equalsIgnoreCase(browser)){
            expectedErrorMessage = "Epic sadface: Password is required";
        }else {
            expectedErrorMessage = "Epic sadface: Password is required";
        }
        String actualErrorMessage = loginPage.getErrorMessage();
        assertThat(actualErrorMessage, is(equalTo(expectedErrorMessage)));
        logger.info("UC-2: Passed on {}");
    }

     @ParameterizedTest
    @MethodSource("browserProvider")
    public void testLoginWithLockedUser(String browser){
        setupTest(browser);
        logger.info("Executing UC-3: Test Login with locked user");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        String expectedErrorMessage;
        if("firefox".equalsIgnoreCase(browser)){
            expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        }else {
            expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        }
        String actualErrorMessage = loginPage.getErrorMessage();
        assertThat(actualErrorMessage, is(equalTo(expectedErrorMessage)));

     }

    @ParameterizedTest
    @MethodSource("browserProvider")
    void testSuccessFullLogin(String browser) {
        setupTest(browser);
        logger.info("Executing UC-4: Test Successful login");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login("standard_user", "secret_sauce");
        String expectedTitle = "Swag Labs";
        String actualTitle = homePage.getTitle();

        assertThat(actualTitle, is(equalTo(expectedTitle)));
        logger.info("UC-4: Passed on {}");
    }

}
