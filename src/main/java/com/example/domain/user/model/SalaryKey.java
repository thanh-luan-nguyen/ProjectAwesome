package com.example.domain.user.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

//khi có 2 PK, trong JPA phải tạo ra 1 class PK
@Embeddable // PKのクラスにつけます
@Data
public class SalaryKey implements Serializable{
	private String userId;
	private String yearMonth;
}