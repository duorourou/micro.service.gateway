package duorourou.micro.services.demo.gateway.card;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import duorourou.micro.services.demo.gateway.BaseCucumberTest;
import duorourou.micro.services.demo.gateway.card.entity.Card;
import org.hamcrest.core.Is;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;

public class CardRetrieveStepsDefinition extends BaseCucumberTest {

    private ResponseEntity<Card> response;

    @When("^the client calls /cards/(.+)$")
    public void the_client_issues_GET_version(String cardId) throws Throwable {
        response = get("/cards/" + cardId, Card.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus status = response.getStatusCode();
        assertThat("status code is incorrect : " + response.getBody(),
                status.value(), Is.is(statusCode));
    }

    @And("^the client receives card with id (.+)$")
    public void the_client_receives_server_version_body(String id) throws Throwable {
        Card result = response.getBody();
        assertThat("card id is not same with " + id,
                result.getId(), Is.is(id));
    }
}
