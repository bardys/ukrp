package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void login(String login, String password) throws InterruptedException {
        Thread.sleep(2000);
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

    public void writeComment(String commentText){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(commentFieldLoc));

        commentFieldLoc.click();
        commentFieldLoc.sendKeys(commentText);
    }

    public void assertCommentText(String expectedCommentText){

    }

}
