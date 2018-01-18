package cucumberProject.steps.serenity;

import cucumberProject.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    DictionaryPage dictionaryPage;
private final String DICTIONARY_PATH="./src/test/java/cucumberProject/dictionaries/dictionary.json";

//    private String BASE_URL = System.getProperty("base.url");
    private String BASE_LANGUAGE = System.getProperty("base.language");
    private String BASE_URL = "https://staging.ict4apps.aimprosoft.com";


    @Step
    public void openBaseURL() {
        dictionaryPage.openBaseUrl(BASE_URL);
    }
    @Step
    public void selectLanguage() {
        dictionaryPage.selectLanguage(BASE_LANGUAGE);
    }

    @Step
    public void test() throws IOException {
        Assert.assertEquals("Gelieve de foutmeldingen hieronder te corrigeren.", dictionaryPage.getTranslatedMessage("Please correct the errors below","nl_BE",DICTIONARY_PATH));
    }

}