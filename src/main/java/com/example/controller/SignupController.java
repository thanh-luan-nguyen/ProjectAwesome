package com.example.controller;

import com.example.application.service.UserApplicationService;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

    @Autowired /* thay vì dùng new keyword để tạo instance ta sài @Autowired, hiểu v là được rồi, DI này nọ nhức đầu :)) */
    private UserApplicationService userApplicationService; /** để đổi giới tính thành Integer */

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

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

        // formをMUserクラスに変換
        /** copy hết fields từ SignupForm vào MUser, cho nên khi đặt
         * tên fields của hai class này phải trùng nhau 一致する
         * tuy nhiên trong tr.hợp này MUser có nhiều hơn 2 fields (deparmentId và role)*/
        MUser user = modelMapper.map(form, MUser.class);

        // user登録
        /** thay vì cho form trực tiếp vào method của service dưới đây, ta map nó
         * sang user như ở trên r mới cho vào (xem trang 133 & 134 for details) */
        userService.signup(user);

        // ログイン画面にリダイレクト
        return "redirect:/login";
    }
}
