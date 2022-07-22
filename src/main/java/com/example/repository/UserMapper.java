package com.example.repository;

import com.example.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** MyBatisでrepositoryを作成するために、Javaのinterfaceに@Mapperをつける */
@Mapper
public interface UserMapper {
	/** user登録 */
	public int insertOne(MUser user);

	/** user取得 */
	public List<MUser> findMany(MUser user);

	/** user取得（１件） */
	public MUser findOne(String userId);

	/** user更新（１件） */
	public void updateOne(@Param("userId") String userId,
						  @Param("password") String password,
						  @Param("userName") String userName);
					/* 複数のparametersを使用する場合、@Paramをつけます */

	/** user削除（１件） */
	public int deleteOne(@Param("userId") String userId);

	/** login user情報取得 */
	public MUser findLoginUser(String userId);
}
