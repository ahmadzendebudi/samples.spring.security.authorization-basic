package com.zendebudi.s5e1.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
    return security
        .httpBasic().and()
        .authorizeHttpRequests(r -> r.requestMatchers("/demo").hasAuthority("READ"))
        .authorizeHttpRequests(r -> r.anyRequest().permitAll())
        .build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetailsManager udm = new InMemoryUserDetailsManager();

    udm.createUser(new User("user",
     passwordEncoder().encode("secret"),
     Arrays.asList(() -> "ROLE_ADMIN", () -> "READ")));

    return udm;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
