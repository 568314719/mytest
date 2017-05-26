package com.itheima.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户登录处理
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class UserController {

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping(value="/dologin", method=RequestMethod.POST)
	public String doLogin(String username, String password, HttpSession session) {
		if ("admin".equals(username) && "123".equals(password)) {
			//登录成功
			session.setAttribute("user", username);
			//跳转到商品列表
			return "redirect:list.action";
		}
		//登录不成功展示登录页面
		return "redirect:login.action";
	}
}
