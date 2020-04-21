package com.example.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class RockPaperScissorGameTest {
	private RockPaperScissorGame game = new RockPaperScissorGame();

	@Test
	public void gameWithEqualsChoicesOfBothPlayersShouldReturnDrawResult() {
		assertEquals(GameResult.DRAW, game.play(Choice.PAPER.getChoiceCode(), Choice.PAPER.getChoiceCode()));
		assertEquals(GameResult.DRAW, game.play(Choice.SCISSORS.getChoiceCode(), Choice.SCISSORS.getChoiceCode()));
		assertEquals(GameResult.DRAW, game.play(Choice.ROCK.getChoiceCode(), Choice.ROCK.getChoiceCode()));
	}

	@Test
	public void gameWithPlayerChoiceRockAndComputerChoicePaperShouldReturnComputerWinResult() {
		assertEquals(GameResult.COMPUTER_WIN, game.play(Choice.ROCK.getChoiceCode(), Choice.PAPER.getChoiceCode()));
	}

	@Test
	public void gameWithPlayerChoiceRockAndComputerChoiceScissorsShouldReturnPlayerWinResult() {
		assertEquals(GameResult.PLAYER_WIN, game.play(Choice.ROCK.getChoiceCode(), Choice.SCISSORS.getChoiceCode()));
	}

	@Test
	public void gameWithPlayerChoicePaperAndComputerChoiceRockShouldReturnPlayerWinResult() {
		assertEquals(GameResult.PLAYER_WIN, game.play(Choice.PAPER.getChoiceCode(), Choice.ROCK.getChoiceCode()));
	}

	@Test
	public void gameWithPlayerChoicePaperAndComputerChoiceScissorsShouldReturnComputerWinResult() {
		assertEquals(GameResult.COMPUTER_WIN, game.play(Choice.PAPER.getChoiceCode(), Choice.SCISSORS.getChoiceCode()));
	}

	@Test
	public void gameWithPlayerChoiceScissorsAndComputerChoiceRockShouldReturnComputerWinResult() {
		assertEquals(GameResult.COMPUTER_WIN, game.play(Choice.SCISSORS.getChoiceCode(), Choice.ROCK.getChoiceCode()));
	}

	@Test
	public void gameWithPlayerChoiceScissorsAndComputerChoicePaperShouldReturnPlayerWinResult() {
		assertEquals(GameResult.PLAYER_WIN, game.play(Choice.SCISSORS.getChoiceCode(), Choice.PAPER.getChoiceCode()));
	}
}
