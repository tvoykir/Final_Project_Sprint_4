package Scooter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Parameterized.class)
public class MainPageTest {
    private WebDriver driver;
    private final String browserType;
    private final Integer index;
    private final String expected;

    public MainPageTest(String browserType, Integer index, String expected) {
        this.browserType = browserType;
        this.index = index;
        this.expected = expected;

    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][]{
                {"chrome", 0 ,"Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"firefox", 0 ,"Сутки — 400 рублей. Оплата курьеру — наличными или картой."},

                {"chrome", 1,"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"firefox", 1,"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},

                {"chrome", 2,"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"firefox", 2,"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},

                {"chrome", 3,"Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"firefox", 3,"Только начиная с завтрашнего дня. Но скоро станем расторопнее."},

                {"chrome", 4,"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"firefox", 4,"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},

                {"chrome", 5,"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"firefox", 5,"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},

                {"chrome", 6,"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"firefox", 6,"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},

                {"chrome", 7,"Да, обязательно. Всем самокатов! И Москве, и Московской области."},
                {"firefox", 7,"Да, обязательно. Всем самокатов! И Москве, и Московской области."},

        };
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
    public void testFaqItemContent() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.scrollToFAQ();
        List<WebElement> elements = driver.findElements(objMainPage.pathAccordionItems);
        elements.get(index).click();
        Assert.assertEquals(expected, driver.findElement(objMainPage.pathVisibleItemDesc).getText());
    }
    @After
    public void teardown() {
            driver.quit();
        }
    }


