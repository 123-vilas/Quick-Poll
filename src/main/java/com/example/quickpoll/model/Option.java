package com.example.quickpoll.model;

public class Option {
    private String name;
    private int votes;

    public Option() {
        // Default constructor for Spring/Thymeleaf binding
    }

    public Option(String name) {
        this.name = name;
        this.votes = 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getVotes() { return votes; }
    public void setVotes(int votes) { this.votes = votes; }

    public void incrementVotes() {
        this.votes++;
    }
}