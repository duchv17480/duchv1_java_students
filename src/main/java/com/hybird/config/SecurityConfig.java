package com.hybird.config;

import com.hybird.entities.User;
import com.hybird.repositories.UserRepository;
import com.hybird.security.jwt.JwtEntryPoint;
import com.hybird.security.jwt.JwtProvider;
import com.hybird.security.jwt.JwtTokenFilter;
import com.hybird.security.userpincal.UserDetailService;
import com.hybird.services.IUserService;
import com.hybird.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.NoSuchElementException;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailService userDetailService;

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    @Autowired
    private IUserService userService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(username -> {
//            try {
//                User user = userService.findByUsername(username);
//                String password = getPasswordEncoder().encode(user.getPassword());
//                Integer role = user.getRoles().getId().ordinal();
//                return org.springframework.security.core.userdetails.User.withUsername(username).password(password).roles(String.valueOf(role)).build();
//            } catch (NoSuchElementException e) {
//                throw new UsernameNotFoundException("username not found" + username);
//            }
//        });
        auth.userDetailsService(userDetailService).passwordEncoder(getPasswordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager Bean
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .anyRequest().authenticated();
        // Thêm một lớp Filter kiểm tra jwt
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.csrf().disable().cors().disable();
//        http.authorizeRequests()
//                .antMatchers("/admin/users").hasRole(String.valueOf(0))
//                .antMatchers("/admin/**").authenticated()
//                .anyRequest().permitAll();
//        http.formLogin()
//                .loginPage("/security/login/form")
//                .loginProcessingUrl("/security/login")
//                .defaultSuccessUrl("/security/login/success", false)
//                .failureUrl("/security/login/error");
//        http.exceptionHandling()
//                .accessDeniedPage("/security/unauthorized");
//        http.rememberMe()
//                .tokenValiditySeconds(120);
//        http.logout()
//                .logoutUrl("/security/logoff")
//                .logoutSuccessUrl("/security/logoff/success");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
