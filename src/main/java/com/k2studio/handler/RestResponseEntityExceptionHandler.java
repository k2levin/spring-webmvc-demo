package com.k2studio.handler;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ConstraintViolationException.class})
	protected ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = ex.getMessage();
		ProblemDetail body = createProblemDetail(ex, status, detail, request);
		body.setProperty("stackTrace", ex.getStackTrace());
		HttpHeaders headers = new HttpHeaders();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleRuntimeException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String detail = ex.getMessage();
		ProblemDetail body = createProblemDetail(ex, status, detail, request);
		body.setProperty("stackTrace", ex.getStackTrace());
		HttpHeaders headers = new HttpHeaders();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	protected ProblemDetail createProblemDetail(Exception ex, HttpStatusCode status, String detail, WebRequest request) {
		ErrorResponse.Builder builder = ErrorResponse.builder(ex, status, detail);
		return builder.build().updateAndGetBody(null, LocaleContextHolder.getLocale());
	}

}
