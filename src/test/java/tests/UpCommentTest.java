package tests;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import pages.CommentPage;
import ru.yandex.qatools.allure.annotations.Title;

@RunWith(JUnitParamsRunner.class)
public class UpCommentTest extends UpBaseTest {

    @Title("Add comment")
    @FileParameters("src/test/java/resources/creds.csv")
    @Test
    public void addComment(String login, String password, String url, String commentText) throws InterruptedException {
        CommentPage commentPage = new CommentPage(driver, url);
        PageFactory.initElements(driver, commentPage);
        commentPage.login(login, password);
        commentPage.writeComment(commentText);
        commentPage.assertCommentText(commentText);

    }

}
