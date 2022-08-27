package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.user.model.MUser;

public interface UserRepository extends JpaRepository<MUser, String> {
	// JpaRepositoryを継承する際には、@Repositoryアノテーションを省略することができます
	
	/** ログインユーザー検索 */
	@Query("select user"             // this is JPQLクエリー言語
		+ " from MUser user"         // nativeQuery属性にtrueすれば、純粋なSQLできる
		+ " where userId = :userId") // :変数 lấy từ @Param ở dưới
	public MUser findLoginUser(@Param("userId") String userId);
	
	@Modifying // insert, update, delete (DDL)の場合
	@Query("update MUser"
		+ " set"
		+ " password = :password"
		+ " , userName = :userName"
		+ " where"
		+ " userId = :userId")
	public Integer updateUser(@Param("userId") String userId,
			@Param("password") String password,
			@Param("userName") String userName);
	
}