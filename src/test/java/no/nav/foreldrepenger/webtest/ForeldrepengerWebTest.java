package no.nav.foreldrepenger.webtest;

import no.nav.foreldrepenger.webtest.pages.DekningsgradPage;
import static no.nav.foreldrepenger.webtest.pages.DekningsgradPage.Dekningsgrad.HUNDRE;
import no.nav.foreldrepenger.webtest.pages.ForeldreOgBarnPage;
import static no.nav.foreldrepenger.webtest.pages.ForeldreOgBarnPage.ForeldrepengerUttak.MOR;
import static no.nav.foreldrepenger.webtest.pages.ForeldreOgBarnPage.TypeFodsel.FODSEL;
import no.nav.foreldrepenger.webtest.pages.InntektForFodselPage;
import static no.nav.foreldrepenger.webtest.pages.InntektForFodselPage.Arbeidskategori.FAST_STILLING;
import no.nav.foreldrepenger.webtest.pages.PageMother;
import no.nav.foreldrepenger.webtest.pages.ResultatPage;
import no.nav.foreldrepenger.webtest.pages.PageMother;
import no.nav.foreldrepenger.webtest.pages.ForeldreOgBarnPage;
import no.nav.foreldrepenger.webtest.pages.InntektForFodselPage;
import static org.hamcrest.core.IsNot.not;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.internal.matchers.StringContains.containsString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


/**
 * User: Vegard Hartmann
 * Date: 13.mar.2009
 */
public class ForeldrepengerWebTest {

   WebDriver driver;
   private static final String HOST_AND_PORT = "tjenester.nav.no";
   private static final String START_PAGE = "http://"+ HOST_AND_PORT +"/foreldrepengeveilederen/fpenger/fpengerWizard.do";

   @Before
   public void setup() {
      driver = new FirefoxDriver();
//      HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
//      htmlUnitDriver.setJavascriptEnabled(true);
//      driver = htmlUnitDriver;
      PageMother.setDriver(driver);
      driver.get(START_PAGE);
   }

   @Test
   public void beregnForeldrepengerKunForMor() {
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
      assertThat(resultatPage.getForeldrepengerPerUkeMor(), not(0));
   }

   @Test
   public void skalKunneGaTilTidligereSteg() {
      ForeldreOgBarnPage foreldreOgBarnPage = PageMother.createForeldreOgBarnPage();
      foreldreOgBarnPage.velgHvemSomSkalTaUtForeldrepenger(MOR);
      InntektForFodselPage inntektForFodselPage = foreldreOgBarnPage.gaaVidere();
      foreldreOgBarnPage = inntektForFodselPage.endreForeldreOgBarnPage();
      assertTrue(foreldreOgBarnPage.isCurrentPage());
   }

   @Test
    public void visHjelpForKunMorSkalTaUtForeldrepenger() {
        ForeldreOgBarnPage foreldreOgBarnPage = PageMother.createForeldreOgBarnPage();
        foreldreOgBarnPage.visHjelpForKunMorHarRett();
        assertThat(foreldreOgBarnPage.getHjelpetekst(), containsString("kun er mor som har rett til foreldrepenger"));
    }

   @After
   public void teardown() {
      driver.close();
   }
}
