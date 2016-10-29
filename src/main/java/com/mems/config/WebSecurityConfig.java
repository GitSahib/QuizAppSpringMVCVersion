package com.mems.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean(name="AuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/admin**").hasAuthority("Admin")
        .antMatchers("/admin/**").hasAuthority("Admin")
        .antMatchers("/teacher**").hasAuthority("Teacher")
        .antMatchers("/teacher/**").hasAuthority("Teacher")
        .antMatchers("/student**").hasAuthority("Student")
        .antMatchers("/student/**").hasAuthority("Student")
        .antMatchers("/profile**").authenticated()
        .antMatchers("profile/uploadPhoto").authenticated()		
		.and()
		    .formLogin().loginPage("/login").failureUrl("/login?error")
		    .usernameParameter("username").passwordParameter("password")
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        http.rememberMe(). 
        key("loginkeeping").
        rememberMeParameter("loginkeeping").
        rememberMeCookieName("loginkeeping").
        tokenValiditySeconds(86400);
       
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}