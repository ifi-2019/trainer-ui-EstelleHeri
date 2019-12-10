package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.trainers.bo.Trainer;
import com.ifi.trainer_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class TrainerController {

    private TrainerService trainerService;

    @GetMapping("/trainers")
    public ModelAndView trainers(){
        var modelAndView = new ModelAndView("trainers");
        modelAndView.addObject("trainers", trainerService.listTrainers());
        return modelAndView;
    }

    @GetMapping("/trainers/{name}")
    public ModelAndView trainer(@PathVariable String name){
        var modelAndView = new ModelAndView("trainer");
        modelAndView.addObject("trainer", trainerService.getTrainer(name));
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profile(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = (UserDetails) auth.getPrincipal();
        var modelAndView = new ModelAndView("profile");
        modelAndView.addObject("profile", trainerService.getTrainer(principal.getUsername()));
        return modelAndView;

    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService= trainerService;
    }
}
