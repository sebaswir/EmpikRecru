package com.seba.recruitment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.seba.recruitment.exception.ExternalSystemException;
import com.seba.recruitment.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String DATA_NOT_FOUND = "Data not found";
	private static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = { ExternalSystemException.class })
	protected ResponseEntity<Object> handleConflict(ExternalSystemException ex, WebRequest request) {
		LOG.error("Error from external system", ex);
		if (ex.getCause() instanceof NotFound) {
			return handleExceptionInternal(ex, new ErrorResponse(404, DATA_NOT_FOUND), new HttpHeaders(),
					HttpStatus.NOT_FOUND, request);
		} else {
			return handleExceptionInternal(ex, new ErrorResponse(500, INTERNAL_SERVER_ERROR), new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR, request);
		}
	}

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		LOG.error("Unhandled error", ex);
		return handleExceptionInternal(ex, new ErrorResponse(500, INTERNAL_SERVER_ERROR), new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
