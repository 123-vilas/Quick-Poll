package com.example.quickpoll.controller;

import com.example.quickpoll.model.Poll;
import com.example.quickpoll.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PollController {

    @Autowired
    private PollRepository pollRepo;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("poll", pollRepo.getPoll());
        model.addAttribute("pollHistory", pollRepo.getPollHistory()); // Add poll history to the model
        return "index";
    }

    @GetMapping("/create")
    public String createPollForm(Model model) {
        Poll newPoll = new Poll();
        model.addAttribute("poll", newPoll);
        return "create";
    }

    @PostMapping("/create")
    public String createPoll(@ModelAttribute Poll poll) {
        if (poll.getOptions() != null) {
            poll.getOptions().removeIf(option -> option.getName() == null || option.getName().trim().isEmpty());
        }
        pollRepo.save(poll);
        return "redirect:/";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam int optionIndex) {
        pollRepo.vote(optionIndex);
        return "redirect:/";
    }
    
    // New endpoint to set a historical poll as the current one (optional, but useful)
    @PostMapping("/selectPoll/{index}")
    public String selectPoll(@PathVariable int index) {
        pollRepo.setCurrentPoll(index);
        return "redirect:/";
    }
}