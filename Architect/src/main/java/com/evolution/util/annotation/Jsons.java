package com.evolution.util.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Jsons {
	Class<? extends Object>[] classes();
	String[] paths();
}
