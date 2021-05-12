package com.example.mancala;

import com.example.mancala.AI.*;
import com.example.mancala.Exceptions.AllFieldsEmptyException;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

class MancalaApplicationTests {
	@Test
	void minmaxComparision(){
		int winnerMoves = 0;
		Player player;
		Game game;
		for(int i=0; i<10; i++){
			player = new Player(new MinMaxChoiceHeuristic(8), new BasicEvaluationHeuristic());
			game = new Game(player, new Player(new MinMaxChoiceHeuristic(8), new BasicEvaluationHeuristic()), true);
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
			System.out.println("The winner did "+game.getWinnerMovesDone()+" moves");
			winnerMoves += game.getWinnerMovesDone();
		}
		System.out.println(winnerMoves);
	}

	@Test
	void alphabetaComparision(){
		int winnerMoves = 0;
		Player player;
		Game game;
		for(int i=0; i<10; i++) {
			player = new Player(new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic());
			game = new Game(player, new Player(new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic()), true);
			while (!game.isFinished()) {
				try {
					if (!game.isStarted() && game.isRandomFirstMove()) {
						game.playField(new Random().nextInt(6));
						game.setStarted(true);
					} else {
						game.playField(player.makeChoice(game));
					}
				} catch (AllFieldsEmptyException gameOver) {
					break;
				}
			}
			System.out.println(game.getScore());
			System.out.println("The winner did "+game.getWinnerMovesDone()+" moves");
			winnerMoves += game.getWinnerMovesDone();
		}
		System.out.println(winnerMoves);
	}

	@Test
	void weightedSumVSBasicComparision(){
		int winnerMoves = 0;
		Player player;
		Game game;
		for(int i=0; i<10; i++) {
			player = new Player(new AlphaBetaChoiceHeuristic(8), new SummedElementsEvaluationHeuristic());
			game = new Game(player, new Player(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic()), false);
			while (!game.isFinished()) {
				try {
					if (!game.isStarted() && game.isRandomFirstMove()) {
						game.playField(new Random().nextInt(6));
						game.setStarted(true);
					} else {
						game.playField(player.makeChoice(game));
					}
				} catch (AllFieldsEmptyException gameOver) {
					break;
				}
			}
			System.out.println(game.getScore());
			System.out.println("The winner did "+game.getWinnerMovesDone()+" moves");
			winnerMoves += game.getWinnerMovesDone();
		}
		System.out.println(winnerMoves);
	}
}
