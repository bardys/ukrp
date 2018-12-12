package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class CommentPage extends AbstractPage{

    @FindBy(xpath = "//a[@href='/auth/']")
    public WebElement authLinkLoc;

    @FindBy(xpath = "//a[@class='fb']")
    public WebElement fbLoginLoc;

    @FindBy(id = "email")
    public WebElement emailLoc;

    @FindBy(id = "pass")
    public WebElement passlLoc;

    @FindBy(id = "u_0_0")
    public WebElement fbSubmitLoc;

    @FindBy(name = "comment_msg[bodytext]")
    public WebElement commentFieldLoc;

    public CommentPage(WebDriver driver, String url) {
        super(driver);
        driver.navigate().to(url);
    }

    @Step("Enter login {0} and password {1}")
    public void login(String login, String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(authLinkLoc));
        authLinkLoc.click();

        String winHandleBefore = driver.getWindowHandle();
        fbLoginLoc.click();

        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        emailLoc.sendKeys(login);
        passlLoc.sendKeys(password);
        fbSubmitLoc.click();

        driver.switchTo().window(winHandleBefore);
    }

    @Step("Write comment {0}")
    public void writeComment(String commentText){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(100, MILLISECONDS)
                .ignoring(ElementNotVisibleException.class);

        wait.until(ExpectedConditions.elementToBeClickable(commentFieldLoc));



        commentFieldLoc.click();
        commentFieldLoc.sendKeys(commentText);
    }

    public void assertCommentText(String expectedCommentText){

    }

}
