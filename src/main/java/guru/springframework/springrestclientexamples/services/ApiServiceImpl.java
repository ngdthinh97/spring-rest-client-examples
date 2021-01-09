package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User; 
import guru.springframework.api.domain.UserData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

	private RestTemplate restTemplate;

	private final String api_url;
	
	public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
		this.restTemplate = restTemplate;
		this.api_url = api_url;
	}

	@Override
	public List<User> getUsers(Integer limit) {
		UserData userData = new UserData();
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromUriString(api_url)
				.queryParam("limit", limit);
		
		userData = restTemplate.getForObject(uriBuilder.toUriString() , UserData.class);
		System.out.println("");
		return userData.getData();
	}

	@Override
	public Flux<User> getUsers(Mono<Integer> limit) {
		// TODO Auto-generated method stub
        return WebClient
                .create(api_url)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("limit", limit.block()).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(resp -> resp.bodyToMono(UserData.class))
                .flatMapIterable(UserData::getData);
	}
	
}