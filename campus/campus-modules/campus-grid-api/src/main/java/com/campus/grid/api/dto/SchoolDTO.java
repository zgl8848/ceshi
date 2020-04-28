package com.campus.grid.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SchoolDTO implements Serializable {

	private String schoolType;
	private Integer schoolNumber;

}