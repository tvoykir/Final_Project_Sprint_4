package Scooter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage {

    private WebDriver driver;
    MainPage(WebDriver driver){
        this.driver = driver;

    }
    //Кнопка заказа из заголовка
    static final By orderButtonHeader= By.xpath("//button[@class='Button_Button__ra12g']");

    //Кнопка заказа из раздела Как это работает
    static final By orderButtonHowTo= By.cssSelector(".Button_Middle__1CSJM");


    //Описание локаторов для раздела "Вопросы о важном"

    //Раздел "Вопросы о важном"
    final By pathFAQList = By.cssSelector(".Home_FourPart__1uthg");

    //Вопросы из FAQ
    final By pathAccordionItems = By.className("accordion__item");

    //Ответ на выбраннй вопрос
    final By pathVisibleItemDesc = By.xpath("//*[@class='accordion__panel' and not(@hidden)]/p");

    public void scrollToFAQ() {

        WebElement element = driver.findElement(pathFAQList);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}