package com.example.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerChoiceDecision implements ChoiceDecision {
	@Override
	public int generate() {
		int choice;

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.print("Please, select a choice:" + "\n" +
					"0 - Rock" + "\n" +
					"1 - Paper" + "\n" +
					"2 - Scissors" + "\n");
			choice = Integer.parseInt(reader.readLine());
			if (choice > 2 || choice < 0)
				throw new RuntimeException("Your choice out of range, possible values 0-2. You input: " + choice);
			return choice;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
