package no.nav.foreldrepenger.webtest.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * User: Vegard Hartmann
 * Date: 13.mar.2009
 * Time: 15:01:00
 */
public class PageMother {

    private static WebDriver driver;

	public static void setDriver(WebDriver driver) {
		PageMother.driver = driver;
	}

    public static ForeldreOgBarnPage createForeldreOgBarnPage() {
        ForeldreOgBarnPage page = new ForeldreOgBarnPage(driver);
        PageFactory.initElements(driver, page);
        return page;
    }

    public static InntektForFodselPage createInntektForFodselPage() {
        InntektForFodselPage inntektForFodselPage = new InntektForFodselPage(driver);
        PageFactory.initElements(driver, inntektForFodselPage);
        return inntektForFodselPage;
    }

    public static DekningsgradPage createDekningsgradPage() {
        DekningsgradPage dekningsgradPage = new DekningsgradPage(driver);
        PageFactory.initElements(driver, dekningsgradPage);
        return dekningsgradPage;
    }

    public static ResultatPage createResultatPage() {
        return PageFactory.initElements(driver, ResultatPage.class);
    }
}
