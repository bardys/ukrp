package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class UpBaseTest {

    public WebDriver driver;
    private static String BROWSER = System.getProperty("browser");

    @Before
    public void setUp(){
        if (BROWSER == null || BROWSER.equalsIgnoreCase("Chrome") || BROWSER.equalsIgnoreCase("")){
            this.driver = new ChromeDriver();
        }else if (BROWSER.equalsIgnoreCase("Firefox")) {
            this.driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

//    @After
//    public void tearDown(){
//        saveImageAttach("screenshot on failure");
//        driver.quit();
//    }



    private static byte[] toByteArray(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getPath()));
    }

    @Rule
    public TestWatcher screenshotOnFail = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            saveImageAttach("screenshot on failure");
        }

        @Override
        protected void finished(Description description) {
            driver.quit();
        }
    };

    @Attachment(value="{0}", type="image/png")
    public byte[] saveImageAttach(String attachName){
        try{
            File srcFile =
                    ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            return toByteArray(srcFile);
        }catch(Exception e){e.printStackTrace();}
        return new byte[0];
    }

}
