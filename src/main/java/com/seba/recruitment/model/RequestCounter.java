package com.seba.recruitment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REQUEST_COUNTER")
@Data
@NoArgsConstructor
public class RequestCounter {

	public RequestCounter(String login) {
		this.login = login;
	}

	@Id
	@Column(name = "LOGIN", nullable = false)
	private String login;

	@Column(name = "REQUEST_COUNT", nullable = false)
	private long requestCount;

	public void addRequestCount() {
		requestCount++;
	}

}
