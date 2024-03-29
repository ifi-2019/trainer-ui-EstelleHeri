package com.ifi.trainer_ui.config;


import com.ifi.trainer_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TrainerService trainerService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> Optional.ofNullable(trainerService.getTrainer(username))
                .map(trainer ->
                        User.withUsername(trainer.getName()).password(trainer.getPassword()).authorities("ROLE_USER").build())
                .orElseThrow(() -> new BadCredentialsException("No such user"));
    }

    public void setTrainerService( TrainerService trainerService) {
        this.trainerService = trainerService;
    }
}
