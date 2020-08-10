package com.hcl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppMessages {

	@Autowired
	private Environment messges;

	public String getResponseMessage(String key) {
		return messges.getProperty("message."+key);
	}
	
	public String getLogMessage(String key) {
		return messges.getProperty("log."+key);
	}
	

}
