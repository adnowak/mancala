package com.example.mancala;

import com.example.mancala.AI.*;
import com.example.mancala.Exceptions.AllFieldsEmptyException;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

class MancalaApplicationTests {
	private void playGameXTimes(ChoiceHeuristic player0ChoiceHeuristic, BoardEvaluationHeuristic player0BoardEvaluationHeuristic, ChoiceHeuristic player1ChoiceHeuristic, BoardEvaluationHeuristic player1BoardEvaluationHeuristic, boolean randomFirstMove, int times){
		int winnerMoves = 0;
		Player player;
		Game game;
		for(int i=0; i<times; i++){
			player = new Player(player0ChoiceHeuristic, player0BoardEvaluationHeuristic);
			game = new Game(player, new Player(player1ChoiceHeuristic, player1BoardEvaluationHeuristic), randomFirstMove);
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
		System.out.println(winnerMoves+" moves during "+times+" games");
	}

	@Test
	void minmaxComparision(){
		playGameXTimes(new MinMaxChoiceHeuristic(8), new BasicEvaluationHeuristic(), new MinMaxChoiceHeuristic(8), new BasicEvaluationHeuristic(), true, 10);
	}

	@Test
	void alphabetaComparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic(), new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic(), false, 10);
	}

	@Test
	void alphabetaComparisionSum(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new SummedElementsEvaluationHeuristic(), new AlphaBetaChoiceHeuristic(8), new SummedElementsEvaluationHeuristic(), false, 10);
	}

	@Test
	void alphabetaComparisionWeightedSum2(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(2), new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(2), false, 10);
	}

	@Test
	void alphabetaComparisionWeightedSum5(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(5), new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(54), false, 10);
	}

	@Test
	void BasicVSSumComparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic(), new AlphaBetaChoiceHeuristic(8), new SummedElementsEvaluationHeuristic(), false, 10);
	}

	@Test
	void BasicVSWeighted2Comparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic(), new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(2), false, 10);
	}

	@Test
	void BasicVSWeighted5Comparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic(), new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(5), false, 10);
	}

	@Test
	void sumVSBasicComparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new SummedElementsEvaluationHeuristic(), new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic(), false, 10);
	}

	@Test
	void sumVSWeighted2Comparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new SummedElementsEvaluationHeuristic(), new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(2), false, 10);
	}

	@Test
	void sumVSWeighted5Comparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new SummedElementsEvaluationHeuristic(), new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(5), false, 10);
	}

	@Test
	void Weighted2VSBasicComparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(2), new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic(), false, 10);
	}

	@Test
	void Weighted2VSSumComparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(2), new AlphaBetaChoiceHeuristic(8), new SummedElementsEvaluationHeuristic(), false, 10);
	}

	@Test
	void Weighted2VSWeighted5Comparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(2), new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(5), false, 10);
	}

	@Test
	void Weighted5VSBasicComparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(5), new AlphaBetaChoiceHeuristic(8), new BasicEvaluationHeuristic(), false, 10);
	}

	@Test
	void Weighted5VSSumComparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(5), new AlphaBetaChoiceHeuristic(8), new SummedElementsEvaluationHeuristic(), false, 10);
	}

	@Test
	void Weighted5VSWeighted2Comparision(){
		playGameXTimes(new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(5), new AlphaBetaChoiceHeuristic(8), new WeightedSumEvaluationHeuristic(2), false, 10);
	}
}
