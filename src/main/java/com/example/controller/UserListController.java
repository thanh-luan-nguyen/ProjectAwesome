package com.example.controller;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	/* user一覧画面を表示 */
	@GetMapping("/list")
	public String getUserList(@ModelAttribute UserListForm userListForm, Model model) {

		// formをMUserクラスに変換
		MUser userSearchResult = modelMapper.map(userListForm, MUser.class);

		// user一検索
		List<MUser> userList = userService.getUsers(userSearchResult);

		// Modelに登録
		model.addAttribute("userList", userList);

		// user一覧画面を表示
		return "user/list";
	}

	/** ユーザー検索処理 */
	@PostMapping("/list")
	public String postUserList(@ModelAttribute UserListForm userListForm, Model model) {

		// formをMUserクラスに変換
		MUser user = modelMapper.map(userListForm, MUser.class);

		// user一検索
		List<MUser> userList = userService.getUsers(user);

		// Modelに登録
		model.addAttribute("userList", userList);

		// user一覧画面を表示
		return "user/list";
	}
}
