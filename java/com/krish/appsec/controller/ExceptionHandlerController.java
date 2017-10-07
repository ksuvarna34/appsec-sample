package com.krish.appsec.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.krish.appsec.model.ErrorMessage;

@ControllerAdvice
public class ExceptionHandlerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler(HttpServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage handleHttpServerException(HttpServerErrorException e) {
		LOGGER.warn("Handling HttpServerErrorException: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage handleException(Exception e) {
		LOGGER.warn("Handling Exception: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public ErrorMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		LOGGER.warn("Handling HttpMessageNotReadableException: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		LOGGER.warn("Handling MissingServletRequestParameterException: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage handleHttpClientErrorException(HttpClientErrorException e) {
		LOGGER.warn("Handling HttpClientErrorException: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleConstraintValidationException(ConstraintViolationException e) {
		LOGGER.warn("Handling ConstraintViolationException: {}", e.getClass().getSimpleName(), e);
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		StringBuilder messages = new StringBuilder();
		violations.stream().forEach(constraint -> messages.append(constraint.getMessage()));
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), messages.toString());
	}
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleBindException(BindException e) {
		LOGGER.warn("Handling HttpClientErrorException: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
}
