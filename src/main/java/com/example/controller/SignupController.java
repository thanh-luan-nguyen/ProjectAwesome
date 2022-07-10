package com.example.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

    @Autowired /* thay vì dùng new keyword để tạo instance ta sài @Autowired, hiểu v là được rồi, DI này nọ nhức đầu :)) */
    private UserApplicationService userApplicationService;

    /** ユーザー登録画面を表示 */
    @GetMapping("/signup")
    public String getSignup(Model model, Locale locale,
            @ModelAttribute SignupForm form) {
        // lấy gender từ map từ UserApplicationService xong rồi cho vào model để có thể access từ signup.html
        Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);

        // ユーザー登録画面に遷移
        return "user/signup";
    }

    /** ユーザー登録処理 */
    @PostMapping("/signup")
    public String postSignup(Model model, Locale locale,
            @ModelAttribute @Validated(GroupOrder.class) SignupForm form,
            BindingResult bindingResult) {

        // 入力チェック結果
        if (bindingResult.hasErrors()) {
            // NG:ユーザー登録画面に戻ります
            return getSignup(model, locale, form);
        }

        log.info(form.toString());

        // ログイン画面にリダイレクト
        return "redirect:/login";
    }
}
