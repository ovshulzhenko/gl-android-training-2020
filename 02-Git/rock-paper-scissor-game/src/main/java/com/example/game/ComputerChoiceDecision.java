package com.example.game;

import java.util.Random;

public class ComputerChoiceDecision implements ChoiceDecision {
	@Override
	public int generate() {
		Random randomChoice = new Random();
		return randomChoice.nextInt(Choice.values().length);
	}
}
