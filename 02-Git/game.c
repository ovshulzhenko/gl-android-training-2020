#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define BUF_SIZE 100

enum EPlayerRespond
{
	  ROCK
	, PAPER
	, SCISSORS
	, COUNT
};

enum eRoundWinner
{
	  USER
	, COMPUTER
	, BOTH
};

enum EPlayerRespond getComputerRespond()
{
	return rand() % COUNT;
}

enum EPlayerRespond getUsersRespond(bool* continue_execution)
{
	char buf[BUF_SIZE];
	char mask[50];
	sprintf(mask, "%%%ds",BUF_SIZE -1);

	while(1)
	{
		printf("\nrock (r) - paper (p) - scissors (s)\n\t\t\t or just (x) to finish the game\n\n>");
		scanf(mask,buf);
		char *s = buf;
		while(isspace(*s) && s-buf < BUF_SIZE) ++s;

	switch(tolower(*s))
	{
	case 'r':
		return ROCK;
	case 's':
		return SCISSORS;
	case 'p':
		return PAPER;
	case 'x':
		*continue_execution = false;
		return ROCK;
	default:
		printf("wrong input, try again!");
		continue;
	}
	}

	return ROCK;
}

enum eRoundWinner arbitrate(enum EPlayerRespond computer, enum EPlayerRespond user)
{
	return BOTH;
}

int main(int argc, char* argv[])
{
	enum EPlayerRespond computer, user;
	enum eRoundWinner winner;

	computer = getComputerRespond();
	bool continue_execution = true;
	user = getUsersRespond(&continue_execution);
	if(!continue_execution)
	{
		printf("\n\nBye!\n");
		return EXIT_SUCCESS;
	}
	winner = arbitrate(computer, user);

	return EXIT_SUCCESS;
}
