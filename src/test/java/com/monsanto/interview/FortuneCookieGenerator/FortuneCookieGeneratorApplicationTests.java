package com.monsanto.interview.FortuneCookieGenerator;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FortuneCookieGeneratorApplicationTests {
	
	@LocalServerPort
	private int port = 8080;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	
	@Test
	public void testRetrieveSarah() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/generateFortuneCookie?client=Sarah&company=MegaMarket"),
				HttpMethod.GET, entity, String.class);

		String expected = "Hi Sarah! Thanks for buying at MegaMarket :) ";

		String returnResponse = response.getBody();
		String substringReturnResponse = returnResponse.substring(0, returnResponse.indexOf(":) "));

		JSONAssert.assertEquals(expected, substringReturnResponse, false);
	}
	
	@Test
	public void testRetrieveBarney() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/generateFortuneCookie?client=Barney&company=SuperStore"),
				HttpMethod.GET, entity, String.class);

		String expected = "Hi Barney! Thanks for buying at SuperStore :) ";
		
		String returnResponse = response.getBody();
		String substringReturnResponse = returnResponse.substring(1, returnResponse.indexOf(":) "));
		
		System.out.println(substringReturnResponse);

		JSONAssert.assertEquals(expected, substringReturnResponse, false);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
