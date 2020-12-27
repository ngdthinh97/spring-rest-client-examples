package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

	private RestTemplate restTemplate;

	public ApiServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<User> getUsers(Integer limit) {
		UserData userData = new UserData();
		userData = restTemplate.getForObject("http://private-anon-93a2b28ffa-apifaketory.apiary-mock.com/api/user?limit=" + limit, UserData.class);
		System.out.println("");
		return userData.getData();
	}
}