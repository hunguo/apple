package org.study.todomanager.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	/**
	 * 1. ���ǿ��� "login" Ű ���� ����
	 * 2. 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		if (request.getMethod().equals("POST")) {
			logger.info("LoginInterceptor: preHandle()");
		}
		
		return true;
	}
	
	/**
	 * ���ǿ� "login" Ű�� ����� ���� �߰�
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) {
		if (request.getMethod().equals("POST")) {
			logger.info("LoginInterceptor: postHandle()");
			
			ModelMap map = modelAndView.getModelMap();
			String user = (String) map.get("user");
			HttpSession session = request.getSession();
			
			if (user != null) {
				logger.info("로그인이 성공...");
				session.setAttribute("login", user);
				try {
					String dest = (String) session.getAttribute("dest");
					if (dest != null) {
						response.sendRedirect(dest);
					} else {
						response.sendRedirect(request.getContextPath() + "/");
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
