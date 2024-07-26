package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;


public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinition m = new StepDefinition();
		if(StepDefinition.placeId==null) {
		m.add_place_payload_with_and("Mayanka", "French", "Asia");
		m.user_calls_with_post_http_payload("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Mayanka", "getPlaceAPI");
		}
		
		
	}

}
