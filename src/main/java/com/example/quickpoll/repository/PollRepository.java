package com.example.quickpoll.repository;

import com.example.quickpoll.model.Poll;
import com.example.quickpoll.model.Option;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PollRepository {
    // Changed to a list to store multiple polls
    private List<Poll> pollHistory = new ArrayList<>();
    private Poll currentPoll; // Still keep a reference to the active poll

    public void save(Poll poll) {
        // Ensure votes are reset or initialized when a new poll is saved
        if (poll.getOptions() != null) {
            for (Option option : poll.getOptions()) {
                option.setVotes(0); // Initialize votes to 0 for new options
            }
        }
        this.currentPoll = poll; // Set the newly created poll as the current one
        this.pollHistory.add(0, poll); // Add the new poll to the beginning of the history
    }

    public Poll getPoll() {
        return this.currentPoll;
    }

    public void vote(int optionIndex) {
        if (currentPoll != null && currentPoll.getOptions() != null) {
            if (optionIndex >= 0 && optionIndex < currentPoll.getOptions().size()) {
                currentPoll.getOptions().get(optionIndex).incrementVotes();
            }
        }
    }

    public List<Poll> getPollHistory() {
        // Return an unmodifiable list to prevent external modification
        return Collections.unmodifiableList(pollHistory);
    }

    // Optional: Method to set a specific poll as current (e.g., if you want to activate an old poll)
    public void setCurrentPoll(int index) {
        if (index >= 0 && index < pollHistory.size()) {
            this.currentPoll = pollHistory.get(index);
        }
    }
}