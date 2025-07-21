package com.example.quickpoll.model;

import java.util.ArrayList;
import java.util.List;

public class Poll {
    private String question;
    private List<Option> options;

    public Poll() {
        this.options = new ArrayList<>();
        // Initialize with at least two options for the form
        this.options.add(new Option("Option 1"));
        this.options.add(new Option("Option 2"));
    }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public List<Option> getOptions() { return options; }
    public void setOptions(List<Option> options) { this.options = options; }

    // Helper method to add an option
    public void addOption(Option option) {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }
        this.options.add(option);
    }
}