package com.ifi.trainer_ui.trainers.bo;

import java.util.List;

public class Trainer implements Comparable {

    private String name;

    private List<Pokemon> team;

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

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    @Override
    public int compareTo(Object o) {
        Trainer t = (Trainer) o;
        return getName().compareTo(t.getName());
    }
}