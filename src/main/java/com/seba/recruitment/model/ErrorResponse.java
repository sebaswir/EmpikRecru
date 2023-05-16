package com.seba.recruitment.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class ErrorResponse {

	@NonNull
	private int errorCode;

	@NonNull
	private String errorMessage;

}
