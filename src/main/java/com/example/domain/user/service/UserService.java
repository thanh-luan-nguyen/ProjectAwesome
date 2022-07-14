package com.example.domain.user.service;

import java.util.List;

import com.example.domain.user.model.MUser;

public interface UserService {
	/** user登録 */
	public void signup(MUser user);

	/** users取得 */
	public List<MUser> getUsers();

	/** users取得(１件） */
	public MUser getOneUser(String userId);
}
