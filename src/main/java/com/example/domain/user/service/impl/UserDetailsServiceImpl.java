package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // ********************** UserDetails là interface, thể hiện user ******************

        // user情報取得
        MUser loginUser = userService.getLoginUser(username);

        // userが存在しない場合
        if (loginUser == null) {
            throw new UsernameNotFoundException("user not found");
        }

        //権限List作成
        GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        // UserDetails生成
        UserDetails userDetails = (UserDetails) new User(loginUser.getUserId(), loginUser.getPassword(), authorities);
        // ********************** User là class extending UserDetails **********************

        return userDetails;
    };

}
