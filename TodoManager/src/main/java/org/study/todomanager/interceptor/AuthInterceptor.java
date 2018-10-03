package org.study.todomanager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		logger.info("AuthInterceptor preHandle...");
		
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null) {
			logger.info(" login is required...");
			saveDest(request);
			response.sendRedirect(request.getContextPath() + "/login");
			
			return false;
		} else {
			return true;
		}
		
	}

	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		
		if (query == null) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		if (request.getMethod().equals("GET")) {
			request.getSession().setAttribute("dest", uri + query);
		}
	}
}