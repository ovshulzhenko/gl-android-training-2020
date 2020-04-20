package com.example.game;

public enum GameResult {
	DRAW(-1), COMPUTER_WIN(0), PLAYER_WIN(1);

	private final int resultCode;

	GameResult(int resultCode) {
		this.resultCode = resultCode;
	}

	public int getResultCode() {
		return this.resultCode;
	}

	public static GameResult findByCode(int code) {
		for (GameResult option : values()) {
			if (option.getResultCode() == code) {
				return option;
			}
		}
		throw new RuntimeException("Result code is not valid");
	}
}
