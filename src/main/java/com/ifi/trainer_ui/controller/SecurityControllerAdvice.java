package com.ifi.trainer_ui.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SecurityControllerAdvice {

    @ModelAttribute("user")
    Object principal(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = (User) auth.getPrincipal();
        return principal;
    }

}
