package com.evolution.entity;

import lombok.Data;

@Data
public class AnyEntity {
	String name;
	String gender;
	AnotherEntity anotherEntity;
}
