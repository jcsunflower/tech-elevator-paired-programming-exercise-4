package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component
public class RestCatPicService implements CatPicService {

	private static final String API_BASE_URL = "https://cat-data.netlify.app/api/pictures/random";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatPic getPic() {
		CatPic catPic = null;

		try {
			catPic = restTemplate.getForObject(API_BASE_URL, CatPic.class);
		} catch (RestClientResponseException e) {
			System.out.println(e.getRawStatusCode());
		} catch (ResourceAccessException e) {
			System.out.println(e.getMessage());
		}
		return catPic;
	}

}	
