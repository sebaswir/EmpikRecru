package com.seba.recruitment.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seba.recruitment.exception.ExternalSystemException;
import com.seba.recruitment.model.RequestCounter;
import com.seba.recruitment.model.UserData;
import com.seba.recruitment.model.UserResponse;
import com.seba.recruitment.repository.RequestCounterRepository;

@Component
public class UserServiceImpl implements UserService {

	private static final DecimalFormat df = new DecimalFormat("#.##");

	private static final String FOLLOWERS_IS_0 = "Followers is 0";

	@Autowired
	private DataService dataService;

	@Autowired
	private RequestCounterRepository requestCounterRepository;

	@Override
	public UserResponse getUserResponse(String login) throws InterruptedException, ExternalSystemException {
		UserData userData = dataService.getUserData(login);
		updateRequestCounter(login);
		return createUserResponse(userData);
	}

	private void updateRequestCounter(String login) {
		RequestCounter requestCounter = requestCounterRepository.findById(login).orElse(new RequestCounter(login));
		requestCounter.addRequestCount();
		requestCounterRepository.save(requestCounter);
	}

	private UserResponse createUserResponse(UserData userData) {
		UserResponse result = new UserResponse();
		result.setId(Long.toString(userData.getId()));
		result.setLogin(userData.getLogin());
		result.setName(userData.getName());
		result.setType(userData.getType());
		result.setAvatarUrl(userData.getAvatarUrl());
		result.setCreatedAt(userData.getCreatedAt());
		result.setCalculations(calculate(userData));
		return result;
	}

	private String calculate(UserData userData) {
		if (userData.getFollowers() == 0) {
			return FOLLOWERS_IS_0;
		}

		Double result = 6 / (double) userData.getFollowers() * (2 + userData.getPublicRepos());
		return df.format(result);
	}

}
