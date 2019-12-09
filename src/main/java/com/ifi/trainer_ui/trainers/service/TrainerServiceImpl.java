package com.ifi.trainer_ui.trainers.service;

import com.ifi.trainer_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String trainerServiceUrl;

    public List<Trainer> listTrainers() {
        var trainers = this.restTemplate.getForObject(this.trainerServiceUrl+"/trainers/",Trainer[].class);
        Arrays.sort(trainers);
        return Arrays.asList(trainers);
    }

    public Trainer getTrainer(String name) {
        var trainer = this.restTemplate.getForObject(this.trainerServiceUrl+"/trainers/{name}",Trainer.class,name);
        return trainer;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate= restTemplate;
    }

    @Value("${trainer.service.url}")
    void setTrainerServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl= trainerServiceUrl;
    }
}
