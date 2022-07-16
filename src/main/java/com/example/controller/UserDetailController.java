package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;

@Controller
@RequestMapping("/user")
public class UserDetailController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/detail/{userId:.+}") /* :.+ là vì dùng email để làm id */
	public String getUserDetail(UserDetailForm userDetailForm, Model model, @PathVariable("userId") String userId) {
		// user取得
		MUser user = userService.getOneUser(userId);
		user.setPassword(null);
		// convert MUser to UserDetailForm
		userDetailForm = modelMapper.map(user, UserDetailForm.class);
		// Modelに登録
		model.addAttribute("userDetailForm", userDetailForm);
		// user詳細画面を表示
		return "user/detail";
	}

	/** user更新処理 */
	@PostMapping(value = "/detail", params = "update") 	/* params attribute: cùng tên với name attribute của button tag trong html */
	public String updateUser(UserDetailForm userDetailForm, Model model) {

		String userId = userDetailForm.getUserId();
		String userName = userDetailForm.getUserName();
		String password = userDetailForm.getPassword();

		MUser user = userService.getOneUser(userId);

		if (userName.isEmpty()) userName = user.getUserName();
		if (password.isEmpty()) password = user.getPassword();

		// userを更新
		userService.updateOneUser(userId, password, userName);

		// user一覧画面にredirect
		return "redirect:/user/list";
	}

	/** user削除処理 */
	@PostMapping(value="/detail", params = "delete")
	public String deleteUser(UserDetailForm userDetailForm, Model model) {

		// userを削除
		userService.deleteOneUser(userDetailForm.getUserId());

		// user一覧画面にredirect
		return "redirect:/user/list";
	}

}