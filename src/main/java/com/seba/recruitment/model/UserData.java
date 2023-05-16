package com.seba.recruitment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserData {

	private long id;

	private String login;
	
	private String name;
	
	private String type;
	
	@JsonProperty("avatar_url")
	private String avatarUrl;
	
	@JsonProperty("created_at")
	private String createdAt;
	
	private long followers;
	
	@JsonProperty("public_repos")
	private long publicRepos;

}
