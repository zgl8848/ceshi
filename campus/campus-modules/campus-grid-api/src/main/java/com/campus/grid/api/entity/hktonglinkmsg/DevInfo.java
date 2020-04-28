package com.campus.grid.api.entity.hktonglinkmsg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class DevInfo implements Serializable {
	private String devIp;

}

