package com.ifi.trainer_ui.trainers.bo;

import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;
import com.ifi.trainer_ui.pokemonTypes.bo.PokemonTypeLevel;

import java.util.List;

public class Trainer implements Comparable {

    private String name;

    private String password;

    private List<Pokemon> team;

    private List<PokemonTypeLevel> pokemonTypes;

    public Trainer() {

    }

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public List<PokemonTypeLevel> getPokemonTypes() {
        return this.pokemonTypes;
    }

    public void setPokemonTypes(List<PokemonTypeLevel> pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
    }

    @Override
    public int compareTo(Object o) {
        Trainer t = (Trainer) o;
        return getName().compareTo(t.getName());
    }
}