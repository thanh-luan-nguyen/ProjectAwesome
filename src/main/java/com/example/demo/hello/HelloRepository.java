package com.example.demo.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // DB操作を表すrepository
public class HelloRepository {
	
	@Autowired // Dependency Injectionを使うため
	private JdbcTemplate jdbcTemplate;
	
	public Map<String,Object> findById(String id) {
		
		//SELECT文	
		String query = "SELECT *"
				+ " FROM employee"
				+ " WHERE id=?";
		
		//検索実行
		Map<String,Object> employee = jdbcTemplate.queryForMap(query, id);
		
		return employee;
	}
}