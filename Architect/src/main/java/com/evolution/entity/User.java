package com.evolution.entity;

import com.evolution.util.SshUtil;

import lombok.Data;

@Data
public class User {
	private String userId;
	private SshUtil sshUtil;
}
