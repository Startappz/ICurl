package com.moshx.icurl;

import java.util.HashMap;

public abstract class ICurlWrapper {

	public abstract String getUrl();

	public abstract ICurl.Type getType();

	public abstract HashMap<String, String> getHeaders();

	public abstract HashMap<String, Object> getParameters();

	public abstract HashMap<String, Object> getCookies();

	/**
	 * Default value is 30 seconds
	 * 
	 * @return the timeout duration for the connection
	 */
	public int getTimeOut() {
		return 30000;
	}

	/**
	 * will add -i to curl
	 * 
	 * @return
	 */
	public boolean enableResponseLogging() {
		return false;
	}

	/**
	 * will add -v to curl
	 * 
	 * @return
	 */
	public boolean enableVerbose() {
		return false;
	}

	public String getBasicAuthentication() {
		return null;
	}

	public ICurl buildIcurl() {
		ICurl c = new ICurl(getUrl());
		c.setType(getType());
		c.setCookies(getCookies());
		c.setParameters(getParameters());
		c.setHeaders(getHeaders());
		c.setTimeOut(getTimeOut());
		if (enableResponseLogging()) {
			c.enableResponseLogging();
		}
		if (enableVerbose()) {
			c.enableVerbose();
		}
		return c;
	}
}
