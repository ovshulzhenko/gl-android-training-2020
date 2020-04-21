package com.example.game;

public enum Choice {
	ROCK(0), PAPER(1), SCISSORS(2);

	private final int choiceCode;

	Choice(int code) {
		this.choiceCode = code;
	}

	public int getChoiceCode() {
		return this.choiceCode;
	}

	public static Choice findByCode(int code) {
		for (Choice option : values()) {
			if (option.getChoiceCode() == code) {
				return option;
			}
		}
		throw new RuntimeException("Choice code is not valid");
	}
}
