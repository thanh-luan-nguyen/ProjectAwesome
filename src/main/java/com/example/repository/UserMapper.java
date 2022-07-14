package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.user.model.MUser;

/** MyBatisでrepositoryを作成するために、Javaのinterfaceに@Mapperをつける */
@Mapper
public interface UserMapper {
	/** user登録 */
	public int insertOne(MUser user);

	/** user取得 */
	public List<MUser> findMany();

	/** user取得（１件） */
	public MUser findOne(String userId);
}
