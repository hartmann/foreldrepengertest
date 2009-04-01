package no.nav.foreldrepenger.webtest.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.How;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;

import java.util.List;

/**
 * User: Vegard Hartmann
 * Date: 13.mar.2009
 * Time: 15:42:06
 */
public class InntektForFodselPage {
    public enum Arbeidskategori {
        FAST_STILLING("1"),
        KORTE_ARBEIDSFORHOLD("2"),
        SELVSTENDIG_NAERINGSDRIVENDE("3"),
        FRILANSER("5");

        private final String id;

        Arbeidskategori(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }

    }

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//input[contains(@value,'videre')]")
    @CacheLookup
    private WebElement gaaVidereButton;

    @FindBy(how = How.NAME, using = "valgtKategoriMor")
    @CacheLookup
    private WebElement morArbeidskategoriSelect;

    @FindBy(how = How.NAME, using = "pensjInntektMor1")
    @CacheLookup
    private WebElement inntektMor1;

    @FindBy(how = How.NAME, using = "pensjInntektMor2")
    @CacheLookup
    private WebElement inntektMor2;

    @FindBy(how = How.NAME, using = "pensjInntektMor3")
    @CacheLookup
    private WebElement inntektMor3;

    @FindBy(how = How.NAME, using = "mndInntektMor")
    @CacheLookup
    private WebElement manedsInntektForMor;

    @FindBy(how = How.XPATH, using = "//div[@id='NAVtrekkspill']/form/div/a")
    @CacheLookup
    private WebElement endreForeldreOgBarnPageLink;

    public InntektForFodselPage(WebDriver driver) {
        this.driver = driver;
    }

    public void registrerInntektSiste3AarForMor(int inntekt1, int inntekt2, int inntekt3) {
        inntektMor1.sendKeys(String.valueOf(inntekt1));
        inntektMor2.sendKeys(String.valueOf(inntekt2));
        inntektMor3.sendKeys(String.valueOf(inntekt3));
    }


    public void registrerManedsInntektForMor(int manedsInntekt) {
        manedsInntektForMor.sendKeys(String.valueOf(manedsInntekt));
    }

    public void velgArbeidskategoriForMor(Arbeidskategori arbeidskategori) {
        List<WebElement> options = morArbeidskategoriSelect.findElements(By.tagName("option"));
        boolean found = false;
        for (WebElement option : options) {
            if (option.getValue().equals(arbeidskategori.toString())) {
                found = true;
                option.setSelected();
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The specified arbeidskategori was not in the select list: " + arbeidskategori);
        }
    }

    public DekningsgradPage gaaVidere() {
        gaaVidereButton.click();
        return PageMother.createDekningsgradPage();
    }

    public ForeldreOgBarnPage endreForeldreOgBarnPage() {
        endreForeldreOgBarnPageLink.click();
        return PageMother.createForeldreOgBarnPage();
    }
}
