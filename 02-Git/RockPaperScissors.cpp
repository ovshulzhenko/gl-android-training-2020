// RockPaperScissors.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <random>

int main()
{
	std::cout << "Please choose: rock (r) - paper (p) - scissors (s)\n";

	//while user is thinking, make our choise.
	std::random_device rd;   
	std::mt19937 gen(rd());  
	const int my_choise = gen() % 3;

	char	input_char {0};
	std::cin >> input_char;

	std::cout << "You chose ";
	int user_choice;
	switch (input_char)
	{
	case 'r': user_choice = 0;	std::cout << "rock";		break;
	case 'p': user_choice = 1;	std::cout << "paper";		break;
	case 's': user_choice = 2;	std::cout << "scissors";	break;
	default:	
		std::cout << "You don't play by the rules. I quit...\n";
		return 0;
	};

	std::cout << "...\tI chose ";
	
	switch (my_choise)
	{
	case 0: std::cout << "rock";		break;
	case 1: std::cout << "paper";		break;
	case 2: std::cout << "scissors";	break;
	};

	std::cout << "...\t";

	switch (user_choice - my_choise)
	{
	case -2:
	case  1:	std::cout << "You won!\n";		break;
	case -1:
	case  2:	std::cout << "I won!\n";		break;
	case  0:	std::cout << "Nobody wins...\n";
	}
	return 1;
}
