package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** security対象外を設定 */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //securityを適用しない
        web.ignoring()
                .antMatchers("/webjars/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/h2-console/**");
    }

    /** securityの各種設定 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //ログイン不要ページの設定
        http.authorizeRequests()
                .antMatchers("/login").permitAll() //直リンクOK
                .antMatchers("/user/signup").permitAll() //直リンクOK
                .anyRequest().authenticated(); //それ以外は直リンクNG

        http.formLogin()
                .loginProcessingUrl("/login") //login処理のpath, đồng nhất với th:action="@{/login}" trong html
                .loginPage("/login") //loginページの指定, đồng nhất với @GetMapping("/login") trong login ctl
                .failureUrl("/login?error") //login失敗時の遷移先
                .usernameParameter("userId") //loginページのuserID, đồng nhất với name="userId" trong form
                .passwordParameter("password") //loginページのpassword, đồng nhất với name="password" trong form
                .defaultSuccessUrl("/user/list", true); // 成功後の遷移先, param t2 set true để bắt phải dùng path ở param t1

        // logout処理
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // để khi GET /logout thì sẽ thêm method này
                .logoutUrl("/logout") // logoutのrequest先パス, đông nhất với th:action="@{/logout}"
                .logoutSuccessUrl("/login?logout");
        // ********** với implementation trên, ko cần logout controller nữa ************

        // CSRF対策を無効に設定（一時的）
        http.csrf().disable();
    }

    /** 認証の設定 */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = passwordEncoder();

        //インメモリ認証
        /*
        auth.inMemoryAuthentication() // để tạo in-memory 認証
                .withUser("user") // userを追加
                    .password(encoder.encode("user"))
                    .roles("GENERAL")
                .and()
                .withUser("admin") // adminを追加
                    .password(encoder.encode("admin"))
                    .roles("ADMIN");
         */

        // user dataで認証
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder);
    }
}
