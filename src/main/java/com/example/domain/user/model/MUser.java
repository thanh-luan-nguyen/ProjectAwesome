package com.example.domain.user.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity // Jpa, tableにマッピング
@Table(name="m_user") // Jpa, để map với table khác tên
public class MUser {
	@Id // Jpa
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Integer departmentId;
    private String role;
    
    //@Transient // O/R mappingをしたくないfield    
    @ManyToOne(optional = true) // nhiều user có thể thuộc 1 department
    // optional = true là left join, false là inner join (ko cho null)
    @JoinColumn(insertable=false, updatable=false, name="departmentId") // name: 結合先のcolumn名を指定
    private Department department;
    
    //@Transient
    @OneToMany
    @JoinColumn(insertable=false, updatable=false, name="userId")
    private List<Salary> salaryList;
}