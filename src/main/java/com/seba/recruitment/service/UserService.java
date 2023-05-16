package com.seba.recruitment.service;

import com.seba.recruitment.exception.ExternalSystemException;
import com.seba.recruitment.model.UserResponse;

public interface UserService {

	UserResponse getUserResponse(String login) throws InterruptedException, ExternalSystemException;

}
