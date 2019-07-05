package com.build.manager.buildmanager.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StatusModal {
	@JsonInclude(Include.NON_NULL)
	String statusMessage;
	@JsonInclude(Include.NON_NULL)
	Integer statusCode;
	@JsonInclude(Include.NON_NULL)
	String sessionId;
	public StatusModal() {}
	
	public StatusModal(String statusMessage, Integer statusCode, String sessionId) {
		super();
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
		this.sessionId = sessionId;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
