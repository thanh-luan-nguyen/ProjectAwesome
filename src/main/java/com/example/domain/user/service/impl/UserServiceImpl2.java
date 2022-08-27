package com.example.domain.user.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserRepository;

@Service
@Primary
public class UserServiceImpl2 implements UserService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	@Override
	public void signup(MUser user) {
		// 存在チェック
		boolean exists = repository.existsById(user.getUserId());
		if (exists) {
			throw new DataAccessException("ユーザーがすでに存在") {}; 
			// phải thêm {} vì là abstract class
		}
		
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		
		// パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		// insert
		repository.save(user);
	}
	
	@Override
	public List<MUser> getUsers(MUser user) {
		
		// 検索条件
		ExampleMatcher matcher = ExampleMatcher
				.matching() // and条件
				.withStringMatcher(StringMatcher.CONTAINING) // Like句
				.withIgnoreCase(); // 大文字・小文字の両方
		return repository.findAll(Example.of(user, matcher)); 
		// cho entity class (class có ＠Entity) và ExampleMatcher class vào
	}
	
	@Override
	public MUser getOneUser(String userId) {
		Optional<MUser> option = repository.findById(userId);
		MUser user = option.orElse(null);
		return user;				
	}
	
	@Transactional // @Modifyingをつけたmethodを実行するため, kể cả ko có @Modi.. phải gán @Tran.. cho insert/update/delete
	@Override
	public void updateOneUser(String userId, String password, String userName) {
		
		String encryptPassword = encoder.encode(password);
		
		repository.updateUser(userId, encryptPassword, userName);
	}
	
	@Transactional // @Modifyingをつけたmethodを実行するため
	@Override
	public void deleteOneUser(String userId) {
		repository.deleteById(userId);
	}
	
	@Override
	public MUser getLoginUser(String userId) {
//		Optional<MUser> option = repository.findById(userId);
//		MUser user = option.orElse(null);
//		return user;
		return repository.findLoginUser(userId);
	}
}
