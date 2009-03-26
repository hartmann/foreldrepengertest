package no.nav.foreldrepenger.webtest.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.internal.matchers.StringContains.containsString;
import static org.junit.Assert.assertThat;
import no.nav.foreldrepenger.webtest.webdriver.pages.PageMother;
import no.nav.foreldrepenger.webtest.webdriver.pages.ForeldreOgBarnPage;

/**
 * Created by IntelliJ IDEA.
 * User: vegard
 * Date: Mar 26, 2009
 * Time: 2:35:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForeldrepengerJavascriptTest {

     WebDriver driver;

    @Before
    public void setup() {
        HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
        htmlUnitDriver.setJavascriptEnabled(true);
        driver = htmlUnitDriver;

        PageMother.setDriver(driver);
    }

    @Test
    public void visHjelpForKunMorSkalTaUtForeldrepenger() {
        driver.get("http://tjenester.nav.no/foreldrepengeveilederen/fpenger/fpengerWizard.do");
        ForeldreOgBarnPage foreldreOgBarnPage = PageMother.createForeldreOgBarnPage();
        foreldreOgBarnPage.visHjelpForKunMorHarRett();
        assertThat(foreldreOgBarnPage.getHjelpetekst(), containsString("kun er mor som har rett til foreldrepenger"));

    }

    @After
    public void teardown() {
        driver.close();
    }
}
