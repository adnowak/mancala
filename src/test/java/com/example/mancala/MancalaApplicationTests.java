package com.example.mancala;

import com.example.mancala.AI.AlphaBetaChoiceHeuristic;
import com.example.mancala.AI.MinMaxChoiceHeuristic;
import com.example.mancala.AI.RandomChoiceHeuristic;
import com.example.mancala.Exceptions.AllFieldsEmptyException;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class MancalaApplicationTests {

	@Test
	void aiComparision(){
		Player player = new Player(new AlphaBetaChoiceHeuristic(8), true);
		Game game = new Game(player, new Player(new AlphaBetaChoiceHeuristic(8), false));
		while (!game.isFinished()){
			try{
				game.playField(player.makeChoice(game));
			}
			catch (AllFieldsEmptyException gameOver){
				break;
			}
		}
		System.out.println(game.getScore());
	}

}
