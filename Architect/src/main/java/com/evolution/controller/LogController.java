package com.evolution.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.evolution.entity.FileInfo;
import com.evolution.entity.LoginInfo;
import com.evolution.entity.User;
import com.evolution.service.LogService;
import com.evolution.util.SshUtil;

@Controller
public class LogController {
	@Autowired
	private LogService logService;
	
	@RequestMapping(value = "/listFiles", method = RequestMethod.POST)
	public ModelAndView listFiles(@RequestParam("remoteFolder") String remoteFolder,
			HttpServletRequest httpServletRequest) {
		User user = logService.getUser(httpServletRequest);
		Map<String, FileInfo> fileInfos = user.getSshUtil().listFiles(remoteFolder);
		List<String> filenames = new LinkedList<>();
		for (String filename : fileInfos.keySet()) {
			filenames.add(filename);
		}
		return logService.createModelAndView("listFiles", "filenames", filenames);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(
			@RequestParam("userId") String userId,
			@RequestParam("host") String host,
			@RequestParam("port") Integer port,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest httpServletRequest) throws IOException {
		// Prepare for the login entity.
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setHost(host);
		loginInfo.setPort(port);
		loginInfo.setUsername(username);
		loginInfo.setPassword(password);
		// Create a SSH utility.
		SshUtil sshUtil = new SshUtil();
		sshUtil = new SshUtil();
		sshUtil.createSession(loginInfo);
		// Create an user.
		User user = new User();
		user.setUserId(userId);
		user.setSshUtil(sshUtil);
		// Save user.
		logService.saveUser(httpServletRequest, user);
		return logService.createModelAndView("index");
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest httpServletRequest) {
		User user = logService.getUser(httpServletRequest);
		user.getSshUtil().closeSession();
		logService.removeUser(httpServletRequest);
		return logService.createModelAndView("login");
	}
	
	@RequestMapping(value = "/printLog", method = RequestMethod.POST)
	public ModelAndView printLog(@RequestParam("remoteFile") String remoteFile,
			HttpServletRequest httpServletRequest) throws IOException {
		if (remoteFile == null) {
			return logService.createModelAndView("error", "info", "Please provide the remote file.");
		}
		User user = logService.getUser(httpServletRequest);
		InputStream inputStream = user.getSshUtil().download(remoteFile);
		String line;
		List<String> logs = new LinkedList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			logs.add(line);
		}
		return logService.createModelAndView("printLog", "logs", logs);
	}
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	public ModelAndView run(@RequestParam("command") String command,
			@RequestParam("minWaitingTime") Integer minWaitingTime, 
			HttpServletRequest httpServletRequest) {
		User user = logService.getUser(httpServletRequest);
		String echo = user.getSshUtil().run(command, minWaitingTime);
		return logService.createModelAndView("run", "echo", echo);
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(@RequestParam("localBasePath") String localBasePath,
			@RequestParam("remoteBasePath") String remoteBasePath,
			HttpServletRequest httpServletRequest) {
		User user = logService.getUser(httpServletRequest);
		Boolean status = user.getSshUtil().upload(localBasePath, remoteBasePath);
		if (status == true) {
			return logService.createModelAndView("index");
		} else {
			return logService.createModelAndView("error");
		}
	}
}
