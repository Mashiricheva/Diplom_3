import io.qameta.allure.Step;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.FactoryDriver;
import page.object.MainPage;

import static org.junit.Assert.assertTrue;

public class DesignerTest extends FactoryDriver {
    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
    //"Переход к разделу булки"
    public void goBunSection () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickBunsSection();
        assertTrue("Раздел «Булки» должен быть активным", mainPage.isBunsSectionActive());
    }
    @Test
    //"Переход к разделу соусы"
    public void goSaucesSection () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickSaucesSection();
        assertTrue("Раздел «Соусы» должен быть активным", mainPage.isSaucesSectionActive());
    }
    @Test
    //"Переход к разделу начинки"
    public void goFillingsSection () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickFillingsSection();
        assertTrue("Раздел «Соусы» должен быть активным", mainPage.isFillingsSectionActive());
    }

}
