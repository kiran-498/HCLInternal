package com.hcl.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

public class GateWayResponse<T> {

	private Boolean status;
	private HttpStatus httpStatus;
	private String message;
	private T data;
	private List<String> errors;
	private List<FieldError> fieldErrors;
	
	public GateWayResponse() {
		super();
	}

	public GateWayResponse(boolean status, final HttpStatus httpStatus, final T result) {
		super();
		this.data = result;
		this.status=status;
		this.httpStatus = httpStatus;
	}
	public GateWayResponse(final T result) {
		this.data = result;
		this.status=Boolean.TRUE;
		this.httpStatus = HttpStatus.OK;
	}


	public GateWayResponse(boolean status, final HttpStatus httpStatus, final String message, final List<String> errors) {
		super();
		this.status=status;
		this.httpStatus = httpStatus;
		this.message = message;
		this.errors = errors;
	}

	public GateWayResponse(String message, final List<FieldError> fieldErrors) {
		super();
		this.status=Boolean.FALSE;
		this.httpStatus = HttpStatus.BAD_REQUEST;
		this.message = message;
		this.fieldErrors = fieldErrors;
	}

	public GateWayResponse(boolean status, final HttpStatus httpStatus, final String message, final String error) {
		super();
		this.status=status;
		this.httpStatus = httpStatus;
		this.message = message;
		if (error!=null && !error.isEmpty()) {
			errors = Arrays.asList(error);
		}
	}
	public GateWayResponse(HttpStatus httpStatus,final String message) {
		super();
		this.status=Boolean.FALSE;
		this.httpStatus = httpStatus;
		this.message = message;
	}
	
	public GateWayResponse(final HttpStatus httpStatus, final T result, String message) {
		super();
		this.status = true;
		this.data = result;
		this.httpStatus = httpStatus;
		this.message = message;
	}
	public GateWayResponse(boolean status, final HttpStatus httpStatus,  final List<String> errors) {
		super();
		this.status=status;
		this.httpStatus = httpStatus;
		this.message = errors.toString();
		this.errors = errors;
	}
	public T getResult() {
		return data;
	}

	public void setResult(T result) {
		this.data = result;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	/**
	 * @return the fieldErrors
	 */
	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	/**
	 * @param fieldErrors
	 *            the fieldErrors to set
	 */
	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}


}
