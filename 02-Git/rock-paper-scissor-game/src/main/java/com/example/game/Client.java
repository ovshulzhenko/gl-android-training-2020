package com.example.game;

public class Client {
	public static void main(String[] args) {
		ChoiceDecision computerDecision = new ComputerChoiceDecision();
		ChoiceDecision playerDecision = new PlayerChoiceDecision();
		RockPaperScissorGame game = new RockPaperScissorGame();

		int playerChoice = playerDecision.generate();
		int computerChoice = computerDecision.generate();

		game.play(playerChoice, computerChoice);
	}
}
