package guru.springframework.springrestclientexamples.services;

import java.util.List;

import guru.springframework.api.domain.User;
import guru.springframework.api.model.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExpApiService {

	public List<Example> getExp(int id);

	public Flux<Example> getExp(Mono<Integer> id);
	
}
