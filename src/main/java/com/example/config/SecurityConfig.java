package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** security対象外を設定 */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //securityを適用しない
        web
          .ignoring()
            .antMatchers("/webjars/**")
            .antMatchers("/css/**")
            .antMatchers("/js/**")
            .antMatchers("/h2-console/**");
    }

    /** securityの各種設定 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //ログイン不要ページの設定
        http
          .authorizeRequests()
            .antMatchers("/login").permitAll() //直リンクOK
            .antMatchers("/user/signup").permitAll() //直リンクOK
            .anyRequest().authenticated(); //それ以外は直リンクNG

        // CSRF対策を無効に設定（一時的）
        http.csrf().disable();
    }
}
