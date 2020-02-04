package com.wildcodeschool.skillhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class JdbcSecurityConfiguration extends WebSecurityConfigurerAdapter {

    

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .jdbcAuthentication()
      .dataSource(dataSource)
//      .passwordEncoder(passwordEncoder())
      .usersByUsernameQuery(
        "SELECT username, password, admin from user WHERE username = ?")
      .authoritiesByUsernameQuery(
        "SELECT u.username, c.categoryname " +
        "FROM category c, user u " +
        "WHERE u.username = ?" +
        "AND u.userid = c.categoryid");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.formLogin();

      http.authorizeRequests()
              .antMatchers(
                  "/",
                  "/logout",
                  "/login").permitAll()
              .antMatchers("/admin/**").hasRole("admin")
              .antMatchers("/user/**").hasRole("user");
  }  
}