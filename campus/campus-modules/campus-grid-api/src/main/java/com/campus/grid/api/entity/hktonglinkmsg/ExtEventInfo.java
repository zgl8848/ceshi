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
public class ExtEventInfo implements Serializable {
	private DevInfo devInfo;
}
