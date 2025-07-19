package com.swaglabs.steps;

import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.HomePage;
import com.swaglabs.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginSteps {

    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

//    @Before
//    public void setUp(Scenario scenario) {
//
//    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("Finishing Scenario: '{}'", scenario.getName());
        WebDriverFactory.quitDriver();
        driver = null;
        loginPage = null;
        homePage = null;
    }

    @Given("I open the {string} browser and go to the login page")
    public void i_open_the_browser_and_go_to_the_login_page(String browser) {
        logger.info("Setting up test for browser: {}", browser);
        WebDriverFactory.createInstanceDriver(browser);
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @When("I enter the username {string}")
    public void i_enter_the_username(String username) {
        if(username != null && !username.isEmpty()) {
            loginPage.enterUsername(username);
        }
    }

    @When("I enter the password {string}")
    public void i_enter_the_password(String password) {
        if(password != null && !password.isEmpty()) {
            loginPage.enterPassword(password);
        }
    }

    @When("I click the login button")
    public void i_click_the_login_button(){
        loginPage.clickLoginButton();
    }

    @Then("I must see the error message {string}")
    public void i_must_see_the_error_message(String errorMessage){
        String actualErrorMessage = loginPage.getErrorMessage();
        assertThat(actualErrorMessage, is(equalTo(errorMessage)));
    }

    @When("I log in with the user {string} and the password {string}")
    public void i_log_in_with_the_user_and_the_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I must be redirected to the products page")
    public void i_must_be_redirected_to_the_products_page(){
        String expectedTitle = "Swag Labs";
        String actualTitle = homePage.getTitle();
        assertThat(actualTitle, is(equalTo(expectedTitle)));
    }

    @And("I add {int} products to the cart")
    public void i_add_products_to_the_cart(int numberOfProducts){
        homePage.addProductsToCart(numberOfProducts);
    }

    @Then("The cart Counter must show {int}")
    public void i_check_the_cart_counter(int count){
        String expectedCartCount = String.valueOf(count);
        String currentCartCount = homePage.getCartItemCount();
        assertThat(currentCartCount, is(equalTo(expectedCartCount)));
    }

}
