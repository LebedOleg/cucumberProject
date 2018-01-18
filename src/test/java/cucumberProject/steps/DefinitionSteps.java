package cucumberProject.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import net.thucydides.core.annotations.Steps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import cucumberProject.steps.serenity.EndUserSteps;

public class DefinitionSteps {

    @Steps
    EndUserSteps shag;




    @And("^test$")
    public void test() throws Throwable {
        shag.test();
    }

    @Given("^Open base URL$")
    public void openBaseURL() throws Throwable {
        shag.openBaseURL();
    }
}
