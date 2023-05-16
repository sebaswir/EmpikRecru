package com.seba.recruitment.service;

import java.util.concurrent.ExecutionException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.seba.recruitment.exception.ExternalSystemException;
import com.seba.recruitment.model.UserData;

@Component
public class GitHubDataServiceImpl implements DataService {

	private static final String LOGIN_PATH_VARIABLE = "{login}";
	private final WebClient client;

	public GitHubDataServiceImpl(WebClient.Builder builder) {
		this.client = builder.baseUrl("https://api.github.com/users/").build();
	}

	@Override
	public UserData getUserData(String login) throws InterruptedException, ExternalSystemException {
		try {
			return this.client.get().uri(LOGIN_PATH_VARIABLE, login).accept(MediaType.APPLICATION_JSON).retrieve()
					.bodyToMono(UserData.class).toFuture().get();
		} catch (ExecutionException e) {
			throw new ExternalSystemException(e.getCause());
		}
	}

}
