package com.example.mancala;

import com.example.mancala.AI.ChoiceHeuristic;
import com.example.mancala.AI.MinMaxChoiceHeuristic;
import com.example.mancala.AI.RandomChoiceHeuristic;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class MancalaApplication {

	public static void main(String[] args) {
		Player player = new Player((ChoiceHeuristic) null);
		Game game = new Game(player, new Player(new MinMaxChoiceHeuristic(6)), false);
		Game.setInstance(game);
		SpringApplication app = new SpringApplication(MancalaApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "80"));
		app.run(args);
	}

}
