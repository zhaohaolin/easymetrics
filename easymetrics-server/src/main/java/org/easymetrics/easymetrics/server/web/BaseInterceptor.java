package org.easymetrics.easymetrics.server.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BaseInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		BaseController controller = (BaseController) handler;
		controller.setRequest(request);
		controller.setResponse(response);
		controller.setSession(request.getSession());
		controller.setCookie(request.getCookies());
		return true;
	}
}
