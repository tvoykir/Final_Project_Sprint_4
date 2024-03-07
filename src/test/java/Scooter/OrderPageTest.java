package Scooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderPageTest {

    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phone;
    private final String date;
    private final String rentDuration;
    private final String browserType;



    public OrderPageTest(String browserType, String name, String surname, String address, String subwayStation, String phone, String date, String rentDuration ) {
        this.browserType = browserType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phone = phone;
        this.date = date;
        this.rentDuration = rentDuration;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"chrome", "Кирилл", "Ковалев", "Улица Льва Толстого", "Библиотека имени Ленина", "89999999999", "10.03.2024", "сутки"},
                {"firefox", "Леня", "Голубков", "Морская, дом 1", "Сокол", "89999994546", "11.03.2024", "семеро суток"},
        });
    }

    @Before
    public void openPage() {
        if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Неподдерживаемый тип браузера: " + browserType);
        }
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }


    @Test
    public void placeOrderHeaderButton() {

        OrderPage objFAQPage = new OrderPage(driver);
        objFAQPage.clickOrderButtonHeader();
        objFAQPage.waitForLoad(objFAQPage.nameField);
        objFAQPage.fillFirstPage(name,surname,address,subwayStation,phone);
        objFAQPage.clickNextButtonForm();
        objFAQPage.waitForLoad(objFAQPage.rentDropdown);
        objFAQPage.fillSecondPage(date, rentDuration);
        objFAQPage.clickOrderButtonForm();
        objFAQPage.waitForLoad(objFAQPage.confirmButtonForm);
        objFAQPage.clickConfirmButtonForm();
        objFAQPage.checkConfirmOrderHeader();
    }

    @Test
    public void placeOrderHowToButton() {

        OrderPage objFAQPage = new OrderPage(driver);
        objFAQPage.clickOrderButtonHowTo();
        objFAQPage.waitForLoad(objFAQPage.nameField);
        objFAQPage.fillFirstPage(name,surname,address,subwayStation,phone);
        objFAQPage.clickNextButtonForm();
        objFAQPage.waitForLoad(objFAQPage.rentDropdown);
        objFAQPage.fillSecondPage(date, rentDuration);
        objFAQPage.clickOrderButtonForm();
        objFAQPage.waitForLoad(objFAQPage.confirmButtonForm);
        objFAQPage.clickConfirmButtonForm();
        objFAQPage.checkConfirmOrderHeader();
    }

    @After
    public void teardown() {
        driver.quit();
    }

}