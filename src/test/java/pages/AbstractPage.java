package pages;

import org.openqa.selenium.WebDriver;

public class AbstractPage {

    public WebDriver driver;

    public AbstractPage(WebDriver driver){
        this.driver = driver;
    }
}
