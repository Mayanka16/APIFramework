Feature: Validating Place API's 

Scenario: Verify if Place has been successfully added using AddPlaceAPI. 
	Given Add place Payload 
	When user calls "AddPlaceAPI" with POST HttP Payload
	Then the API call got sucess with sucess code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	
	
	
