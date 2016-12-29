package com.evolution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
	private String host;
	private Integer port;
	private String username;
	private String password;
}
