package duorourou.micro.services.demo.gateway.cache;

import duorourou.micro.services.demo.gateway.BaseCucumberTest;

public class CacheRetrieveStepsDefinitions extends BaseCucumberTest {

//    @When("^the client calls /caches$")
//    public void retrieve_cards_from_cache() {
//        System.out.println("cards count : ");
//    }
//
//    @Then("^finish$")
//    public void finish_cache_of_cards() throws Throwable {
//        System.out.println("finish");
//    }

    public CacheRetrieveStepsDefinitions() {
        When("^the client calls /caches$", () -> {
            get("/caches", String.class);
        });

        Then("^finish$", () -> {
        });
    }
}
