package no.nav.foreldrepenger.webtest.webdriver.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.How;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

/**
 * User: Vegard Hartmann
 * Date: 14.mar.2009
 * Time: 18:03:01
 */
public class ResultatPage {

    @FindBy(how = How.XPATH, using = "//div[@class='NAVstepSelectedBody']/div/div")
    WebElement foreldrepengerPerUkeMor;

    public int getForeldrepengerPerUkeMor() {
        return Integer.parseInt(foreldrepengerPerUkeMor.getText());
    }
}
