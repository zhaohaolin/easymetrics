package org.easymetrics.easymetrics.server.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected Cookie[] cookie;

	protected void redirect(HttpServletResponse _response, String url) {
		try {
			_response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setRequest(HttpServletRequest _request) {
		this.request = _request;
	}

	public void setResponse(HttpServletResponse _response) {
		this.response = _response;
	}

	public void setSession(HttpSession _session) {
		this.session = _session;
	}

	public void setCookie(Cookie[] _cookie) {
		this.cookie = _cookie;
	}

	protected String request(String name) {
		return this.request.getParameter(name);
	}

	protected String get(String name) {
		return this.request.getParameter(name);
	}

	protected void set(String name, Object obj) {
		this.request.setAttribute(name, obj);
	}

	protected void setAttribute(String name, Object obj) {
		this.request.setAttribute(name, obj);
	}

	protected Object getAttribute(String name) {
		return this.request.getAttribute(name);
	}

	protected Object session(String name) {
		return this.session.getAttribute(name);
	}

	protected Object getSession(String name) {
		return this.session.getAttribute(name);
	}

	protected String getWebRoot(HttpServletRequest _request) {
		return _request.getContextPath();
	}

	protected String getWebRoot() {
		return getWebRoot(this.request);
	}

	protected String getEncoding(HttpServletRequest _request) {
		return _request.getCharacterEncoding();
	}

	protected String getEncoding() {
		return getEncoding(this.request);
	}

	protected boolean hasParameter(String _name) {
		return this.request.getParameter(_name) != null;
	}

	protected String[] getParameterValues(String _name) {
		return this.request.getParameterValues(_name);
	}

	protected Enumeration<String> getParameterNames() {
		return this.request.getParameterNames();
	}

	protected Map<String, String[]> getParameterMap() {
		return this.request.getParameterMap();
	}

	protected String forward(String url) {
		return url;
	}

	protected String redirect(String url) {
		return "redirect:" + url;
	}

	protected String getServerUrl() {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		return basePath;
	}

	protected void echoString(HttpServletResponse _response, String str)
			throws IOException {
		_response.setContentType("application/json;charset=utf-8");
		_response.setCharacterEncoding("utf-8");
		_response.getWriter().write(str);
		_response.flushBuffer();
	}

}