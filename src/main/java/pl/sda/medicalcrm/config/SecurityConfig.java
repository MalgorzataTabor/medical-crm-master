package pl.sda.medicalcrm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.medicalcrm.service.UserDetailsServiceImpl;

import javax.sql.DataSource;
import java.security.AuthProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   /* private final DataSource dataSource;

    SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/
    @Autowired
   AuthenticationProvider authenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/patients").permitAll()
                .antMatchers("/doctors").permitAll()
                .antMatchers("/crmspecialists").permitAll()
                .antMatchers("/admins").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/clinics").permitAll()
                .antMatchers("/appointments").hasRole("PATIENT")
                .antMatchers("/admins/**").hasRole("ADMIN")
                .antMatchers("/patients/**").hasRole("PATIENT")//.hasAnyRole()
                .antMatchers("/doctors/**").hasRole("DOCTOR")//.hasAnyRole()
                .antMatchers("/crmspecialists/**").hasRole("CRMSPECIALIST")//.hasAnyRole()
                //.antMatchers("/doctors/**").hasAnyRole("ADMIN", "DOCTOR")
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.jdbcAuthentication().dataSource(dataSource);

        auth.authenticationProvider(authenticationProvider);

    }



}
