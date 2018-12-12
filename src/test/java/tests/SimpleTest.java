package tests;

import org.junit.Assert;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

@Title("My simple test suite")
@Description("This suite is to verify reporting with Allure.")
@Features("My test feature")
@Stories({"Story 1", "Story 2"})
public class SimpleTest {

    @Title("My positive test case")
    @Description("This test case to verify positive case")
    @Test
    public void positiveSimpleTest(){
        Assert.assertTrue("Result should be equals 4", 2*2==4);
    }

    @Title("My negative test case")
    @Description("This test case to verify negative case")
    @Test
    public void negativeSimpleTest(){
        Assert.assertTrue("Result should be equals 4", 2*2==5);
    }
}
