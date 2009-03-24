package no.nav.foreldrepenger.webtest.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.How;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;

import java.util.List;

/**
 * User: Vegard Hartmann
 * Date: 13.mar.2009
 * Time: 14:17:45
 */
public class ForeldreOgBarnPage {

    public enum ForeldrepengerUttak {
        MOR_OG_FAR("baademorogfar"),
        MOR("Kunmor"),
        FAR("Kunfar"),
        ALENEMOR("Alenemor"),
        ALENEFAR("Alenefar");

        private final String id;

        ForeldrepengerUttak(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
    }

    public enum TypeFodsel {
        FODSEL("fodsel"),
        ADOPSJON("adopsjon");

        private final String id;

        TypeFodsel(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
    }

    private WebDriver driver;

    @FindBy(how = How.NAME, using = "antallBarn")
    @CacheLookup
    private WebElement antallBarnSelect;

    @FindBy(how = How.XPATH, using = "//input[contains(@value,'videre')]")
    @CacheLookup
    private WebElement gaaVidereButton;

    public ForeldreOgBarnPage(WebDriver driver) {
        this.driver = driver;
    }

    public void velgHvemSomSkalTaUtForeldrepenger(ForeldrepengerUttak hvem) {
        WebElement radiobutton = driver.findElement(By.xpath("//input[@id='" + hvem + "']"));
        radiobutton.setSelected();
    }

    public void velgFodselEllerAdopsjon(TypeFodsel typeFodsel) {
        WebElement radiobutton = driver.findElement(By.xpath("//input[@id='" + typeFodsel + "']"));
        radiobutton.setSelected();
    }

    public void velgAntallBarn(int antallBarn) {
        List<WebElement> options = antallBarnSelect.findElements(By.tagName("option"));
        boolean found = false;
        for (WebElement option : options) {
            if (option.getValue().equals(String.valueOf(antallBarn))) {
                found = true;
                option.setSelected();
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The given number of childs was not in the select list: " + antallBarn);
        }
    }

    public InntektForFodselPage gaaVidere() {
        gaaVidereButton.click();
        return PageMother.createInntektForFodselPage();
    }
}
