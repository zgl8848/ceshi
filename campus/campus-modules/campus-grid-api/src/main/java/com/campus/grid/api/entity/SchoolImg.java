package com.campus.grid.api.entity;

import com.campus.grid.api.entity.equipmententity.EquipmentImg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author eatheryu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolImg implements Serializable {

	private static final long serialVersionUID = 1L;
	private String day;
	private String total;
	private List<EquipmentImg> equipmentImg;
}
