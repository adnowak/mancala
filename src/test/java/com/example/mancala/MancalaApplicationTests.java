package com.example.mancala;

import com.example.mancala.AI.AlphaBetaChoiceHeuristic;
import com.example.mancala.AI.MinMaxChoiceHeuristic;
import com.example.mancala.AI.RandomChoiceHeuristic;
import com.example.mancala.Exceptions.AllFieldsEmptyException;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

class MancalaApplicationTests {


	@Test
	void aiComparision(){
		Player player = new Player(new AlphaBetaChoiceHeuristic(8));
		Game game = new Game(player, new Player(new AlphaBetaChoiceHeuristic(8)), true);
		while (!game.isFinished()){
			try{
				if(!game.isStarted()&&game.isRandomFirstMove()){
					game.playField(new Random().nextInt(6));
					game.setStarted(true);
				}
				else {
					game.playField(player.makeChoice(game));
				}
			}
			catch (AllFieldsEmptyException gameOver){
				break;
			}
		}
		System.out.println(game.getScore());
	}

}
