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
        Player player = new Player(Game.getInstance().getPlayer0().getChoiceHeuristic(), true);
        Game.setInstance(new Game(player, new Player(Game.getInstance().getPlayer1().getChoiceHeuristic(), false)));
        model.addAttribute("game", Game.getInstance());
        return "index";
    }

    @GetMapping("/play")
    public String play(@RequestParam Integer id, Model model) {
        Game.getInstance().playField(id);
        model.addAttribute("game", Game.getInstance());
        System.out.println(Game.getInstance().isFinished());
        return "index";
    }
}
