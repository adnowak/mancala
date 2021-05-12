package com.example.mancala;

import com.example.mancala.AI.*;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class MancalaApplication {

	public static void main(String[] args) {
		Player player = new Player((ChoiceHeuristic) null, null);
		Game game = new Game(player, new Player(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic()), false);
		Game.setInstance(game);
		SpringApplication app = new SpringApplication(MancalaApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "80"));
		app.run(args);
	}

}
