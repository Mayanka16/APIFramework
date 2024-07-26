Feature: Validating Place API's 

@AddPlace
Scenario Outline: Verify if Place has been successfully added using AddPlaceAPI. 
	Given Add place Payload with "<name>" "<language>" and "<address>" 
	When user calls "AddPlaceAPI" with "POST" Http Payload 
	Then the API call got success with success code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify place_id created maps to "<name>" using "getPlaceAPI" 
	
	
	Examples: 
			| name | language | address |
		|Mayanka | English | PSOTS |
		#| Saurabh | English1 | PSOTS1 |
		
		
@DeletePlace	
Scenario: Verify if Delete Place functionality is working 
	Given DeletePlace Payload 
	When user calls "deletePlaceAPI" with "POST" Http Payload 
	Then the API call got success with success code 200 
	And "status" in response body is "OK"
		