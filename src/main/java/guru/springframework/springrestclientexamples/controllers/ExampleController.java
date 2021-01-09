package guru.springframework.springrestclientexamples.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import guru.springframework.springrestclientexamples.services.ExpApiService;
import reactor.core.publisher.Mono;

@Controller
public class ExampleController {

	ExpApiService expApiService;

	public ExampleController(ExpApiService expApiService) {
		super();
		this.expApiService = expApiService;
	}

	@GetMapping("/example")
	public String exmapleForm() {

		return "index.html";
	}

	@PostMapping("/example/find")
	public String exmapleProcess(Model model, ServerWebExchange serverWebExchange,
			@RequestParam(required = false) String id) {

		if (id == null) {
			model.addAttribute("examples", expApiService
					.getExp(serverWebExchange.getFormData().map(mp -> Integer.valueOf(mp.getFirst("id")))));

		}

		else {

			Mono<Integer> key = Mono.just(Integer.valueOf(id));

			model.addAttribute("examples", expApiService.getExp(key));
		}

		return "findbyid.html";

	}

	@GetMapping("/example/find/{id}")
	public String exmapleGetProcess(Model model, ServerWebExchange serverWebExchange, @PathVariable int id) {

		if (id == 0) {
			id = 10;
		}

		model.addAttribute("examples", expApiService.getExp(id));

		return "findbyid.html";

	}
}