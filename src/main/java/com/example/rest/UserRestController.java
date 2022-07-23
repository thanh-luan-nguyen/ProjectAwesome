package com.example.rest;

import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    //@Autowired
    //private PasswordEncoder encoder;

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
