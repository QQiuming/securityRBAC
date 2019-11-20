package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.filter.JwtAuthorizationTokenFilter;
import com.example.filter.MyAuthenticationProvider;
import com.example.handler.GoAccessDeniedHandler;
import com.example.handler.GoAuthenticationEntryPoint;
import com.example.handler.GoAuthenticationFailureHandler;
import com.example.handler.GoAuthenticationSuccessHandler;
import com.example.handler.GoLogoutSuccessHandler;
import com.example.service.JwtUserDetailsService;

@Configuration
@EnableWebSecurity // 这个注解必须加，开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true) // 保证post之前的注解可以使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    GoAuthenticationEntryPoint authenticationEntryPoint; // 未登陆时返回 JSON 格式的数据给前端（否则为 html）

    @Autowired
    GoAuthenticationSuccessHandler authenticationSuccessHandler; // 登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    GoAuthenticationFailureHandler authenticationFailureHandler; // 登录失败返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    GoLogoutSuccessHandler logoutSuccessHandler; // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Autowired
    GoAccessDeniedHandler accessDeniedHandler; // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;

    // 先来这里认证一下
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    }

    // 拦截在这配
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 去掉 CSRF 关闭session

        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
            .antMatchers("/test/hello").permitAll()
            //swagger不拦截
            .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
                "/swagger-ui.html", "/webjars/**", 
                "/swagger-resources/configuration/ui", "/swagge‌​r-ui.html").permitAll().
            antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 校验用户有没有权限访问链接
            .anyRequest().access("@rbacauthorityservice.hasPermission(request,authentication)").and()
            // 用户登录
            .formLogin().loginProcessingUrl("/user/login").permitAll()
            // 认证成功
            .successHandler(authenticationSuccessHandler)
            // 认证失败
            .failureHandler(authenticationFailureHandler).permitAll()
            // 已经认证的用户访问自己没有权限的资源处理
            .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            // 未认证的用户访问自己没有权限的资源处理
            .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
            // 登出处理
            .and().logout().logoutSuccessHandler(logoutSuccessHandler).and()
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


    }

    /**
     * 配置自定义校验规则，密码编码，使用我们自定义的校验器
     * 
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
