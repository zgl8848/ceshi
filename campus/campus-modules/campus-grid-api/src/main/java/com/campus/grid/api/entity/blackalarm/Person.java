package com.campus.grid.api.entity.blackalarm;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@TableName("grid_blacklist_person")
public class Person implements Serializable {
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	private String age;
	private String personID;
	private String personName;
	private String personType;
	private String sex;
	private String modalType;
	private String alarmMsg;

	public Person(String id, String age, String personID, String personName, String personType, String sex, String modalType) {
		this.id = id;
		this.age = age;
		this.personID = personID;
		this.personName = personName;
		this.personType = personType;
		this.sex = sex;
		this.modalType = modalType;
	}
}
