package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.service.UserApplicationService;

@Controller
@RequestMapping("/user") //prefixing URLs, ngoài ra có thể thay thế @GetMapping, @PostMapping...
public class SignupController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	//ユーザー登録画面を表示
	@GetMapping("/signup")
	public String getSignUp(Model model) {
		//性別を取得
		Map<String,Integer> genderMap = userApplicationService.getGenderMap();
		model.addAttribute("genderMap", genderMap);
		
		//ユーザー登録画面に繊維
		return "user/signup";
	}
	
	//ユーザー登録処理
	@PostMapping("/signup")
	public String postSignUp() {
		//ログイン画面にリダイレクト
		return "redirect:/login";
	}
}