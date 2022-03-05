package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	private static final String API_BASE_URL = "https://cat-data.netlify.app/api/facts/random";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatFact getFact() {
		CatFact catFact = null;

		try {
			catFact = restTemplate.getForObject(API_BASE_URL, CatFact.class);
		} catch (RestClientResponseException e) {
			System.out.println(e.getRawStatusCode());
		} catch (ResourceAccessException e) {
			System.out.println(e.getMessage());
		}
		return catFact;
	}

}
