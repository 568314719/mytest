package com.itheima.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//在handler执行之前先执行此方法
		//返回值决定是否拦截，返回true：放行 false：拦截
		// 4）如果判断是展示登录页面或者登录处理url直接放行。
		//取请求的url
		String url = request.getRequestURL().toString();
		if (url.contains("login")) {
			//放行
			return true;
		}
		// 1）判断登录状态，判断Session中是否有用户信息
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");
		if (user == null) {
			// 3）如果没有拦截，跳转到登录页面。
			response.sendRedirect(request.getContextPath() + "/login.action");
			return false;
		}
		// 2）如果有放行
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// 在handler执行之后，在返回ModelAndView之前
		System.out.println("Interceptor2 postHandle....");

	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		// 完成处理，在饭ModelAndView之后。只能做异常处理
		System.out.println("Interceptor2 afterCompletion....");
	}

	

}
