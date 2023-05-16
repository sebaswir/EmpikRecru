package com.seba.recruitment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.seba.recruitment.exception.ExternalSystemException;
import com.seba.recruitment.model.UserData;
import com.seba.recruitment.model.UserResponse;
import com.seba.recruitment.repository.RequestCounterRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private DataService dataService;

	@Mock
	private RequestCounterRepository requestCounterRepository;

	@ParameterizedTest
	@CsvSource({ "0, 1, Followers is 0", "1, 1, 18", "6, 1, 3", "12, 1, 1.5", "11, 1, 1.64" })
	void getUserResponseCalculationTest(int followers, int publicRepos, String result)
			throws InterruptedException, ExternalSystemException {
		UserData testData = new UserData();
		testData.setFollowers(followers);
		testData.setPublicRepos(publicRepos);
		Mockito.when(dataService.getUserData("test")).thenReturn(testData);
		assertEquals(result, userService.getUserResponse("test").getCalculations());
	}

	@Test
	void getUserResponseDataTest() throws InterruptedException, ExternalSystemException {
		UserData testData = new UserData();
		testData.setFollowers(1);
		testData.setPublicRepos(1);
		String avatarUrlValue = "avatarUrl";
		testData.setAvatarUrl(avatarUrlValue);
		String createdAtValue = "createdAt";
		testData.setCreatedAt(createdAtValue);
		int idValue = 10;
		testData.setId(idValue);
		String loginValue = "login";
		testData.setLogin(loginValue);
		String nameValue = "name";
		testData.setName(nameValue);
		String typeValue = "type";
		testData.setType(typeValue);
		Mockito.when(dataService.getUserData("test")).thenReturn(testData);
		UserResponse userResponse = userService.getUserResponse("test");
		assertEquals(avatarUrlValue, userResponse.getAvatarUrl());
		assertEquals(Integer.toString(idValue), userResponse.getId());
		assertEquals(loginValue, userResponse.getLogin());
		assertEquals(nameValue, userResponse.getName());
		assertEquals(typeValue, userResponse.getType());
	}

	@BeforeEach
	void prepareRepositoryMock() {
		Mockito.when(requestCounterRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
	}

}
