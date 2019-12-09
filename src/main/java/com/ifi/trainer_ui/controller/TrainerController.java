package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.trainers.bo.Trainer;
import com.ifi.trainer_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Trainer profile(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = (Principal) auth.getPrincipal();
        var modelAndView = new ModelAndView("profile");
        return trainerService.getTrainer(principal.getName());

    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService= trainerService;
    }
}
