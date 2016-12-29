package com.evolution.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.evolution.entity.User;

@Component
public class LogService {
	private Map<String, User> users;// The user buffer.
	
	public ModelAndView createModelAndView(String path, String key, Object value) {
		ModelAndView modelAndView = new ModelAndView(path);
		if (key != null && value != null) {
			modelAndView.addObject(key, value);
		}
		return modelAndView;
	}

	public ModelAndView createModelAndView(String path) {
		return createModelAndView(path, null, null);
	}

	public User getUser(HttpServletRequest httpServletRequest) {
		String userId = (String) getAttribute(httpServletRequest, "userId");
		return this.users.get(userId);
	}
	
	public void saveUser(HttpServletRequest httpServletRequest, User user) {
		if (users == null) {
			users = new LinkedHashMap<String, User>();
		}
		users.put(user.getUserId(), user);
		setAttribute(httpServletRequest, "userId", user.getUserId());
	}
	
	public void removeUser(HttpServletRequest httpServletRequest) {
		String userId = (String) getAttribute(httpServletRequest, "userId");
		users.remove(userId);
		removeAttribute(httpServletRequest, "userId");
	}
	
	public void setAttribute(HttpServletRequest httpServletRequest, String key, Object value) {
		httpServletRequest.getSession().setAttribute(key, value);
	}
	
	public void removeAttribute(HttpServletRequest httpServletRequest, String key) {
		httpServletRequest.getSession().removeAttribute(key);
	}
	
	public Object getAttribute(HttpServletRequest httpServletRequest, String key) {
		return httpServletRequest.getSession().getAttribute(key);
	}
}
