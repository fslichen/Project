package com.evolution.entity;

import lombok.Data;

@Data
public class FileInfo {
	private String filename;
	private String longFilename;
	private Integer modifiedTime;
	private Long size;
}
