package no.nav.foreldrepenger.webtest.pages;

import org.openqa.selenium.How;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
