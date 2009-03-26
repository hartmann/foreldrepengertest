package no.nav.foreldrepenger.webtest.webdriver.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.How;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;

/**
 * User: Vegard Hartmann
 * Date: 14.mar.2009
 * Time: 17:44:14
 */
public class DekningsgradPage {

    public enum Dekningsgrad {
        HUNDRE("hundre"),
        AATTI("aatti");

        private final String id;

        Dekningsgrad(String id) {
            this.id = id;
        }

       public String id() {
           return id;
       }
    }

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//input[contains(@value,'videre')]")
    @CacheLookup
    private WebElement gaaVidereButton;

    public DekningsgradPage(WebDriver driver) {
        this.driver = driver;
    }


    public ResultatPage velgDekningsgradOgGaaVidere(Dekningsgrad dekningsgrad) {
        WebElement radioButton = driver.findElement(By.id(dekningsgrad.id()));
        radioButton.setSelected();
        gaaVidereButton.click();
        return PageMother.createResultatPage();
    }
}
