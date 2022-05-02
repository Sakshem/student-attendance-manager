package com.sakshem.studentmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("securityDataSource")
    private DataSource securityDataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource);
        // add our users for in memory authentication
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication().withUser(users.username("john").password("test123").roles("EMPLOYEE"));
//        auth.inMemoryAuthentication().withUser(users.username("mary").password("test123").roles("MANAGER"));
//        auth.inMemoryAuthentication().withUser(users.username("susan").password("test123").roles("ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/students-attendance/showForm*").hasAnyRole("TEACHER", "ADMIN")
                .antMatchers("/students-attendance/save*").hasAnyRole("TEACHER", "ADMIN")
                .antMatchers("/student-attendance/mark-attendance*").hasAnyRole("TEACHER", "ADMIN")
                .antMatchers("/students-attendance/delete").hasRole("ADMIN")
                .antMatchers("/students-attendance/**").hasRole("STUDENT")
                .antMatchers("/resources/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
