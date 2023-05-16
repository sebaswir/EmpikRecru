package com.seba.recruitment.service;

import com.seba.recruitment.exception.ExternalSystemException;
import com.seba.recruitment.model.UserData;

public interface DataService {

	UserData getUserData(String login) throws InterruptedException, ExternalSystemException;

}
