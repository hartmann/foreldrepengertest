package no.nav.foreldrepenger.webtest.webdriver;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import static org.junit.internal.matchers.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.not;
import no.nav.foreldrepenger.webtest.webdriver.pages.*;
import static no.nav.foreldrepenger.webtest.webdriver.pages.DekningsgradPage.Dekningsgrad.HUNDRE;
import static no.nav.foreldrepenger.webtest.webdriver.pages.InntektForFodselPage.Arbeidskategori.FAST_STILLING;
import static no.nav.foreldrepenger.webtest.webdriver.pages.ForeldreOgBarnPage.ForeldrepengerUttak.MOR;
import static no.nav.foreldrepenger.webtest.webdriver.pages.ForeldreOgBarnPage.TypeFodsel.FODSEL;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.WebClient;

import java.net.URL;


/**
 * User: Vegard Hartmann
 * Date: 13.mar.2009
 */
public class ForeldrepengerHovedTest {

    WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        //HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
        //driver = htmlUnitDriver;
    
        PageMother.setDriver(driver);
    }

    @Test
    public void beregnForeldrepengerKunForMor() {
        driver.get("http://tjenester.nav.no/foreldrepengeveilederen/fpenger/fpengerWizard.do");
        ForeldreOgBarnPage foreldreOgBarnPage = PageMother.createForeldreOgBarnPage();
        foreldreOgBarnPage.velgHvemSomSkalTaUtForeldrepenger(MOR);
        foreldreOgBarnPage.velgFodselEllerAdopsjon(FODSEL);
        foreldreOgBarnPage.velgAntallBarn(2);
        InntektForFodselPage inntektForFodselPage = foreldreOgBarnPage.gaaVidere();
        inntektForFodselPage.velgArbeidskategoriForMor(FAST_STILLING);
        inntektForFodselPage.registrerInntektSiste3AarForMor(200000, 250000, 300000);
        inntektForFodselPage.registrerManedsInntektForMor(25000);
        DekningsgradPage dekningsgradPage = inntektForFodselPage.gaaVidere();
        ResultatPage resultatPage = dekningsgradPage.velgDekningsgradOgGaaVidere(HUNDRE);
        System.out.println(driver.getPageSource());
        assertThat(resultatPage.getForeldrepengerPerUkeMor(), not(0));

   }

    @Test
    public void skalKunneGaTilTidligereSteg(){
        driver.get("http://tjenester.nav.no/foreldrepengeveilederen/fpenger/fpengerWizard.do");
        ForeldreOgBarnPage foreldreOgBarnPage = PageMother.createForeldreOgBarnPage();
        foreldreOgBarnPage.velgHvemSomSkalTaUtForeldrepenger(MOR);
        InntektForFodselPage inntektForFodselPage = foreldreOgBarnPage.gaaVidere();
        foreldreOgBarnPage = inntektForFodselPage.endreForeldreOgBarnPage();
        assertTrue(foreldreOgBarnPage.isCurrentPage());
    }


    @After
    public void teardown() {
        driver.close();
    }
}
