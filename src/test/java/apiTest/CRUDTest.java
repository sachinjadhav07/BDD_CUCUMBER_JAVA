package apiTest;


import org.testng.annotations.Test;

import io.restassured.response.Response;
import utilities.ApiBase;
import utilities.AssertUtils;

public class CRUDTest extends ApiBase {

	
	@Test
	public void getAllUsers() {
	        String[] userOne = {"addPet"};
	        Response respUserOne = ApiBase.makeMultipleApiCallsExcel("petStore", userOne);
	        AssertUtils.assertEquals(respUserOne.statusCode(), "200");
	        log.info("Successfully added a new Pet to the Pet Store");

	}
	
	
}
