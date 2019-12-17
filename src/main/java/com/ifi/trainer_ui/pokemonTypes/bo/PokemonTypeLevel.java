package com.ifi.trainer_ui.pokemonTypes.bo;

public class PokemonTypeLevel extends PokemonType {

    private PokemonType pokemonType;
    private int level;

    public PokemonTypeLevel(PokemonType pokemonType, int level) {
        this.pokemonType = pokemonType;
        this.level = level;
    }

    public PokemonType getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(PokemonType pokemonType) {
        this.pokemonType = pokemonType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }




}
