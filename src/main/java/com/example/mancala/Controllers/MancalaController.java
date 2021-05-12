package com.example.mancala.Controllers;

import com.example.mancala.AI.RandomChoiceHeuristic;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MancalaController {

    @GetMapping("/")
    public String home(Model model) {
        Game.setInstance(Game.getRestartedGame(Game.getInstance()));
        model.addAttribute("game", Game.getInstance());
        return "index";
    }

    @GetMapping("/play")
    public String play(@RequestParam Integer id, Model model) {
        Game.getInstance().playField(id);
        model.addAttribute("game", Game.getInstance());
        return "index";
    }
}
