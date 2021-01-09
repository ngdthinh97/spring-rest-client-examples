package guru.springframework.springrestclientexamples.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.api.domain.UserData;
import guru.springframework.api.model.Example;
import guru.springframework.api.model.ExampleData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExpApiServiceimpl implements ExpApiService{
	
	private RestTemplate restTemplate;

	private final String api_url_exp;
	
	public ExpApiServiceimpl(RestTemplate restTemplate, @Value("${api.url.exp}") String api_url_exp) {
		this.restTemplate = restTemplate;
		this.api_url_exp = api_url_exp;
	}

	@Override
	public List<Example> getExp(int id) {

		ExampleData exampleData = new ExampleData();
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromUriString(api_url_exp)
				.queryParam("id", id); 
		
		exampleData.setData(Arrays.asList(restTemplate.getForObject(uriBuilder.toUriString(), Example[].class))); 
		
		return exampleData.getData();
	}

	@Override
	public Flux<Example> getExp(Mono<Integer> id) {
		// TODO Auto-generated method stub
        return WebClient
                .create(api_url_exp)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("id", id.block()).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(Example.class));
        
	}
}
