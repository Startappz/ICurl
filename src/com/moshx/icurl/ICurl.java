package com.moshx.icurl;

import java.util.HashMap;
import java.util.Locale;

public class ICurl {

	@SuppressWarnings("unused")
	private static void main(String[] args) {

		ICurl ac = new ICurl("www.google.com");
		ac.setHeader("mail", "mos@aaa.com");
		ac.setHeader("userId", "7DDDgfsdf");
		ac.setParameter("mm", "add&");
		ac.setParameter("u", 12444);
		ac.setParameter("m", 2002);

		ac.setCookie("cookie1", "value1");
		ac.setCookie("cookie2", "val2");
		ac.enableResponseLogging().enableVerbose();
		ac.setTimeOut(100);
		ac.setType(Type.POST);

		System.out.println(ac.toCurl());
	}

	public enum Type {

		GET("GET"), POST("POST"), DELETE("DELETE"), PUT("PUT");

		public final String name;

		private Type(String name) {
			this.name = name;
		}
	}

	private final String mUrl;
	private final HashMap<String, String> mHeaders = new HashMap<>();
	private final HashMap<String, Object> mParameters = new HashMap<>();
	private final HashMap<String, Object> mCookies = new HashMap<>();

	private static final String FORMATTED_HEADER = "-H \"%s: %s\" ";
	private static final String FORMATTED_URL = "\"%s\" ";
	private static final String FORMATTED_PARM = "%s=%s&";

	private boolean responseLogging = false;
	private boolean verbose = false;
	private String basicAuthentication = null;

	private Type mType = Type.GET;

	private long mTimeOut = 30000l;

	public ICurl(String mUrl) {
		super();
		this.mUrl = mUrl;
	}

	public ICurl setType(Type t) {
		mType = t;
		return this;
	}

	public ICurl setHeader(String k, String v) {
		mHeaders.put(k, v);
		return this;
	}

	public ICurl setHeaders(HashMap<String, String> hs) {
		if (hs != null && hs.size() > 0) {
			mHeaders.putAll(hs);
		}
		return this;
	}

	public ICurl setParameter(String k, Object v) {
		mParameters.put(k, v);
		return this;
	}

	public ICurl setParameters(HashMap<String, Object> ps) {
		if (ps != null && ps.size() > 0) {
			mParameters.putAll(ps);
		}
		return this;
	}

	public ICurl setCookie(String k, Object v) {
		mCookies.put(k, v);
		return this;
	}

	public ICurl setCookies(HashMap<String, Object> cs) {
		if (cs != null && cs.size() > 0) {
			mCookies.putAll(cs);
		}
		return this;
	}

	public ICurl setTimeOut(long mTimeOut) {
		this.mTimeOut = mTimeOut;
		return this;
	}

	public ICurl enableResponseLogging() {
		responseLogging = true;
		return this;
	}

	public ICurl enableVerbose() {
		verbose = true;
		return this;
	}

	public ICurl setBasicAuthentication(String username, String password) {
		basicAuthentication = username + ":" + password;
		return this;
	}

	public ICurl setBasicAuthentication(String auth) {
		basicAuthentication = auth;
		return this;
	}

	public String toCurl() {
		StringBuilder builder = new StringBuilder();
		builder.append("curl ");
		addTimeOut(builder);
		addHeaders(builder);
		addParameters(builder);
		addCookies(builder);
		addExtraParms(builder);
		addType(builder);
		addUrl(builder);
		return builder.toString();
	}

	// ##############################################################
	// INTERNAL METHODS
	// ##############################################################

	private void addHeaders(StringBuilder builder) {
		if (!mHeaders.isEmpty()) {

			for (String key : mHeaders.keySet()) {
				builder.append(String.format(Locale.US, FORMATTED_HEADER, key,
						mHeaders.get(key)));
			}
		}
	}

	private void addParameters(StringBuilder builder) {
		if (!mParameters.isEmpty()) {
			builder.append("-d \"");
			for (String key : mParameters.keySet()) {
				builder.append(String.format(Locale.US, FORMATTED_PARM, key,
						mParameters.get(key).toString()));
			}
			builder.deleteCharAt(builder.length() - 1).append("\" ");
		}
	}

	private void addCookies(StringBuilder builder) {
		if (mCookies.size() > 0) {
			builder.append("-c \"");
			for (String key : mCookies.keySet()) {
				builder.append(String.format(Locale.US, FORMATTED_PARM, key,
						mCookies.get(key).toString()));
			}
			builder.deleteCharAt(builder.length() - 1).append("\" ");
		}

	}

	private void addExtraParms(StringBuilder builder) {
		if (responseLogging) {
			builder.append("-i ");
		}
		if (verbose) {
			builder.append("-v ");
		}
		if (basicAuthentication != null) {
			builder.append("-u \"").append(basicAuthentication).append(" ");
		}
	}

	private void addType(StringBuilder builder) {
		builder.append("-X ").append(mType.name).append(' ');

	}

	private void addUrl(StringBuilder builder) {
		builder.append(String.format(Locale.US, FORMATTED_URL, mUrl));
	}

	private void addTimeOut(StringBuilder builder) {
		if (mTimeOut != 30000l) {
			builder.append("--max-time ").append(mTimeOut).append(" ");
		}
	}
}
