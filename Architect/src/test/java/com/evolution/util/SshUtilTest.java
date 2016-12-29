package com.evolution.util;

import com.evolution.entity.LoginInfo;
import com.evolution.entity.LoginInfos;
import com.evolution.entity.Name;

public class SshUtilTest {
//	@Test
	public void testUpload() {
		SshUtil sshUtil = new SshUtil();
		sshUtil.createSession("120.76.190.46", 22, "root", "5gQXgsRRRc87");
		sshUtil.upload("D:\\Data", "/home/data/log/Data");
	}
	
//	@Test
	public void testListFiles() {
		SshUtil sshUtil = new SshUtil();
		sshUtil.createSession("120.76.190.46", 22, "root", "5gQXgsRRRc87");
		System.out.println(sshUtil.listFiles("/home/data/log/jiankang-api"));
	}
	
//	@Test
	public void testGetLocalFilesAndRelativePaths() {
		System.out.println(SshUtil.getLocalFilesAndRelativePaths("D:\\Data"));
	}
	
//	@Test
	public void test() {
		LoginInfo loginInfo = LoginInfos.getLoginInfo(Name.xxx);
		SshUtil sshUtil = new SshUtil();
		sshUtil.createSession(loginInfo);
		System.out.println(sshUtil.run("cd /home/data/log; ls", 5));
		sshUtil.closeSession();
	}
}
