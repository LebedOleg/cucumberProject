package cucumberProject.pages;

import ch.lambdaj.function.convert.Converter;
import cucumberProject.Locs;
import net.thucydides.core.annotations.DefaultUrl;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static ch.lambdaj.Lambda.convert;


public class DictionaryPage extends PageObject {

    public void openBaseUrl(String base_url) {
        getDriver().navigate().to(base_url);
    }



    public String getTranslatedMessage(String message, String language, String dictionary) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, String>> messages;
        messages = mapper.readValue(new File(dictionary), new TypeReference<Map<String, Map<String, String>>>() {
        });
        return messages.get(message).get(language);
    }

    public void selectLanguage(String base_language) {
        $(Locs.LanguageDropDown).click();
        withTimeoutOf(6, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locs.LanguageElement.replace("$1",base_language))));
        $(Locs.LanguageElement.replace("$1", base_language)).click();
    }
}