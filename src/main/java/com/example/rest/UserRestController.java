package com.example.rest;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;
import com.example.form.UserDetailForm;
import com.example.form.UserListForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    /** userを検索 */
    @GetMapping("/get/list")
    public List<MUser> getUserList(UserListForm form) {
        log.error(form.toString());
        MUser user = modelMapper.map(form, MUser.class);
        log.error(user.toString());
        List<MUser> userList = userService.getUsers(user);
        log.error(userList.toString());
        /** List<MUser> sẽ được đổi thành JSON */
        return userList;
    }

    /** userを登録 */
    @PostMapping("/signup/rest")
    public RestResult postSignUp(@Validated(GroupOrder.class)SignupForm form,
                                 BindingResult bindingResult, Locale locale) {
        // 入力チェック結果
        if (bindingResult.hasErrors()) {
            // check結果:NG
            Map<String, String> errors = new HashMap<>();

            // error message取得
            // **** khi validation bị NG, có thể lấy tên field từ FieldError ****
            for (FieldError error : bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, locale);
                errors.put(error.getField(), message);
            }

            // error結果の返却, **** 90:validation result is NG ****
            return new RestResult(90, errors);
        }

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        // user登録
        userService.signup(user);

        //　結果の返却, **** 0:正常完了 ****
        return new RestResult(0, null);
    }

    /** user更新 */
    @PutMapping("/update")
    public int updateUser(UserDetailForm form) {

        String userId = form.getUserId();
        String userName = form.getUserName();
        String password = form.getPassword();

        //MUser user = userService.getOneUser(userId);
        //
        //if (password.isEmpty()) password = user.getPassword();
        //else password = encoder.encode(password);

        userService.updateOneUser(userId, password, userName);

        return 0;
    }

    /** user削除 */
    @DeleteMapping("/delete")
    public int deleteUser(UserDetailForm form) {

        userService.deleteOneUser(form.getUserId());

        return 0;
    }
}
