package com.example.domain.user.service;

import com.example.domain.user.model.MUser;

import java.util.List;

public interface UserService {
	/** user登録 */
	public void signup(MUser user);

	/** users取得 */
	public List<MUser> getUsers();

	/** users取得(１件） */
	public MUser getOneUser(String userId);

	/** user更新（１件） */
	public void updateOneUser(String userId,
							  String password,
						      String userName);

	/** user削除（１件） */
	public void deleteOneUser(String userId);
}
