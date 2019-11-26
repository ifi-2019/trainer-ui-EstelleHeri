package com.ifi.trainer_ui.pokemonTypes.service;

import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    public List<PokemonType> listPokemonsTypes() {
        var pokemonTypes = this.restTemplate.getForObject(this.pokemonServiceUrl+"/pokemon-types/",PokemonType[].class);
        return Arrays.asList(pokemonTypes);
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate= restTemplate;
    }

    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl= pokemonServiceUrl;
    }
}

