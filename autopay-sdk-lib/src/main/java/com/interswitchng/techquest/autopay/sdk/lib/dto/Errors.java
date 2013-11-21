package com.interswitchng.techquest.autopay.sdk.lib.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class Errors extends RestResponse{

	public Errors()
	{
		successful = false;
	}
	
	private Error[] errors;
	

	public Error[] getErrors() {
		return errors;
	}

	public void setErrors(Error[] errors) {
		this.errors = errors;
	}

	
	
	

	
}
