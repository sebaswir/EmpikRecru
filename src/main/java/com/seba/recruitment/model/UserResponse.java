package com.seba.recruitment.model;

import lombok.Data;

@Data
public class UserResponse {

	private String id;
	private String login;
	private String name;
	private String type;
	private String avatarUrl;
	private String createdAt;
	private String calculations;

}
