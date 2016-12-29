package com.evolution.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoginInfos {
	private static Map<Name, LoginInfo> loginInfos;
	
	static {
		loginInfos = new LinkedHashMap<>();
		LoginInfo loginInfo = new LoginInfo("120.76.190.46", 22, "root", "5gQXgsRRRc87");
		loginInfos.put(Name.xxx, loginInfo);
		loginInfo = new LoginInfo("120.77.50.202", 22, "root", "b9EASteMXaGJ");
		loginInfos.put(Name.doctorApi, loginInfo);
		loginInfo = new LoginInfo("120.77.50.217", 22, "root", "mSXpVZP70moR");
		loginInfos.put(Name.easemobPostOffice, loginInfo);
	}
	
	public static LoginInfo getLoginInfo(Name name) {
		return loginInfos.get(name);
	}
}
