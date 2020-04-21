package com.example.game;

import javax.xml.transform.Result;

public class RockPaperScissorGame {

	private int[][] decisionMatrix = new int[][]{
			{-1, 0, 1},
			{1, -1, 0},
			{0, 1, -1}};

	public GameResult play(int playerChoice, int computerChoice) {

		if (playerChoice >= decisionMatrix.length)
			throw new RuntimeException("Your choice out of range, possible values 0-2. You input: " + playerChoice);

		int result = decisionMatrix[playerChoice][computerChoice];
		Choice player = Choice.findByCode(playerChoice);
		Choice computer = Choice.findByCode(computerChoice);

		StringBuilder text = new StringBuilder().append("Your choice ").append(player.name()).append(", Computer choice ").append(computer.name());

		switch (result) {
			case -1: {
				System.out.println("You are both win! " + text.toString());
				break;
			}
			case 0: {
				System.out.println("Computer is win! " + text.toString());
				break;
			}
			case 1: {
				System.out.println("You are win! " + text.toString());
				break;
			}
		}
		return GameResult.findByCode(result);
	}
}
