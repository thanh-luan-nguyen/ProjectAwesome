package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	public List<MUser> getUsers(MUser user) {
		return userMapper.findMany(user);
	}

	/** users取得 */
	@Override
	public MUser getOneUser(String userId) {
		return userMapper.findOne(userId);
	}

	/** user更新（１件） */
	@Transactional /** khi bị exception, thì sẽ rollback, có thể gán ở class level */
	@Override
	public void updateOneUser(String userId, String password, String userName){
		userMapper.updateOne(userId, password, userName);

		//　例外を発生させる
		//int i = 1 / 0;
	}

	/** user削除（１件） */
	@Override
	public void deleteOneUser(String userId){
		int count = userMapper.deleteOne(userId);
	}
}
