Feature: Validating Place APIs

Scenario: Verify if Place is being Successfully added using AddPlaceAPI 
	Given Add Place Payload
	When user calls "AddPlaceAPI" with Post http request
	Then the API is success with status code 200
	And status in response body is OK