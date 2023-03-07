package com.example.weeklyperiodical.config;

import com.example.weeklyperiodical.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security的配置类
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance();//NoOpPasswordEncoder是“不加密”的密码编码器
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http); //父类方法配置要求所有请求都是认证过的

        //白名单URL
        //注意:所有路径使用/作为第一个字符
        //可以使用*通配符,例如/admins/*可以匹配/admins/add-new,但是,不能匹配多级,例如不能匹配到/admins/9527/delete
        //可以使用*通配符,例如/admins/**可以匹配/admins/add-new,但是,不能匹配多级,例如不能匹配到/admins/9527/delete
        String[] urls = {
                "/admins/login",
                "/doc.html",
                "/**/*.css",
                "/**/*.js",
                "/favicon.ico",
                "/swagger-resources",
                "/v2/api-docs"
        };

        //启用Security框架自带的CorsFilter过滤器,可以对OPTIONS请求放行
        http.cors();

        //配置请求是否需要认证
        http.authorizeRequests() //配置请求的认证授权
                // .mvcMatchers(HttpMethod.OPTIONS,"/**")
                // .permitAll()
                .mvcMatchers(urls) //匹配某些请求路径
                .permitAll() //允许直接访问,既不需要通过认证
                .anyRequest() //其他任何请求
                .authenticated(); //需要时通过认证的

        //禁用"防止伪造的跨域攻击"这种防御机制
        http.csrf().disable();

        // 将JWT过滤器添加在Spring Security的“用户名密码认证信息过滤器”之前
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        //http.formLogin();//开启登录表单

        //设置Session创建策略，从不创建
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }
}
