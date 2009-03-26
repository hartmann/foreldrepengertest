package no.nav.foreldrepenger.webtest.webdriver;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import static org.junit.internal.matchers.StringContains.containsString;
import static org.junit.Assert.assertThat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import no.nav.foreldrepenger.webtest.webdriver.pages.*;
import static no.nav.foreldrepenger.webtest.webdriver.pages.DekningsgradPage.Dekningsgrad.HUNDRE;
import static no.nav.foreldrepenger.webtest.webdriver.pages.InntektForFodselPage.Arbeidskategori.FAST_STILLING;
import static no.nav.foreldrepenger.webtest.webdriver.pages.ForeldreOgBarnPage.ForeldrepengerUttak.MOR;
import static no.nav.foreldrepenger.webtest.webdriver.pages.ForeldreOgBarnPage.TypeFodsel.FODSEL;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.WebClient;

import java.net.MalformedURLException;
import java.io.IOException;

/**
 * User: Vegard Hartmann
 * Date: 13.mar.2009
 */
public class ForeldrepengerHovedTest {

    WebDriver driver;

    @Before
    public void setup() {
        //driver = new FirefoxDriver();
        HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
        driver = htmlUnitDriver;

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
        //sjekk at mor far foreldrepenger
        //sjekk at far ikke far noen uker
    }


    @After
    public void teardown() {
        driver.close();
    }
}
