package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.pokemonTypes.bo.PokemonTypeLevel;
import com.ifi.trainer_ui.pokemonTypes.service.PokemonTypeService;
import com.ifi.trainer_ui.trainers.bo.Pokemon;
import com.ifi.trainer_ui.trainers.bo.Trainer;
import com.ifi.trainer_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainerController {

    private TrainerService trainerService;

    private PokemonTypeService pokemonTypeService;

    @GetMapping("/trainers")
    public ModelAndView trainers(@ModelAttribute("user") Object user){
        var modelAndView = new ModelAndView("trainers");
        var trainers = trainerService.listTrainers();

        for (Trainer t : trainers) {

            List<PokemonTypeLevel> listPokemon = new ArrayList<>();
            for (Pokemon p : t.getTeam()) {
                var pT = pokemonTypeService.getPokemonType(p.getPokemonType());
                var pTL = new PokemonTypeLevel(pT, p.getLevel());
                listPokemon.add(pTL);
            }
            t.setPokemonTypes(listPokemon);

        }
        modelAndView.addObject("trainers", trainers);
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
        var trainer = trainerService.getTrainer(principal.getUsername());

        List<PokemonTypeLevel> listPokemon = new ArrayList<>();
        for (Pokemon p : trainer.getTeam()) {
            var pT = pokemonTypeService.getPokemonType(p.getPokemonType());
            var pTL = new PokemonTypeLevel(pT,p.getLevel());
            listPokemon.add(pTL);
        }
        trainer.setPokemonTypes(listPokemon);

        modelAndView.addObject("profile", trainer);
        return modelAndView;

    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService= trainerService;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService= pokemonTypeService;
    }
}
