package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	/** user登録 */
	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		userMapper.insertOne(user);
	}

	/** users取得 */
	@Override
	public List<MUser> getUsers() {
		return userMapper.findMany();
	}

	/** users取得 */
	@Override
	public MUser getOneUser(String userId) {
		return userMapper.findOne(userId);
	}
}
