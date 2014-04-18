package com.timexautoweb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AndroidRequestInterceptor extends HandlerInterceptorAdapter {

	/**
	 * The name of the mobile view that the viewer is re-directed to in the
	 * event that a mobile device is detected.
	 */
	private static final String MOBILE_VIEWER_VIEW_NAME = "mobile";

	/**
	 * The User-Agent Http header.
	 */
	private static final String USER_AGENT_HEADER = "User-Agent";

	private List<String> mobileAgents_;
	private List<Pattern> uaPatterns_;

	public void init() {
		uaPatterns_ = new ArrayList<Pattern>();
		// Pre-compile the user-agent patterns as specified in mvc.xml
		for (final String ua : mobileAgents_) {
			try {
				uaPatterns_.add(Pattern.compile(ua, Pattern.CASE_INSENSITIVE));
			} catch (PatternSyntaxException e) {
				// Ignore the pattern, if it failed to compile
				// for whatever reason.
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		final String userAgent = request.getHeader(USER_AGENT_HEADER);
		System.out.println("User Agent = " + userAgent);
		final String accept = request.getHeader("Accept");
		System.out.println("Accept = " + accept);
		if (userAgent != null) {
			// If the User-Agent matches a mobile device, then we set view name to a mobile view
			if (isMobile(userAgent)) {
				final String view = mav.getViewName();
				// If the incoming view was "timesheetlist" then this interceptor
				// changes the view name to "timesheetlist-mobile" which will resolve to
				// "timesheetlist-mobile.xsl" to render a mobile version of this view.
				mav.setViewName(view + "-" + MOBILE_VIEWER_VIEW_NAME);
			}
		}
	}

	/**
	 * Returns true of the given User-Agent string matches a suspected mobile
	 * device.
	 * 
	 * @param agent
	 * @return
	 */
	private final boolean isMobile(final String agent) {
		boolean mobile = false;
		for (final Pattern p : uaPatterns_) {
			final Matcher m = p.matcher(agent);
			if (m.find()) {
				mobile = true;
				break;
			}
		}
		return mobile;
	}

	public void setMobileUserAgents(List<String> agents) {
		mobileAgents_ = agents;
	}

}
