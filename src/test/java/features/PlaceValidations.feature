Feature: Validating Place API's 

Scenario Outline: Verify if Place has been successfully added using AddPlaceAPI. 
	Given Add place Payload with "<name>" "<language>" and "<address>"
	When user calls "AddPlaceAPI" with POST Http Payload 
	Then the API call got sucess with sucess code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	
	
	
	Examples: 
	
		| name | language | address |
		|Mayanka | English | PSOTS |
		| Saurabh | English1 | PSOTS1 |
