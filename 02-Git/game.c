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

const char* printRespond(const enum EPlayerRespond respond)
{
	static const char* responds[] = {"ROCK", "PAPER", "SCISSORS"};
	return responds[respond];
}

enum eRoundWinner arbitrate(const enum EPlayerRespond computer,const enum EPlayerRespond user)
{
	const int res = computer - user;
	if(!res)
		return BOTH;

	if(-1 == res || 2 ==res )
		return USER;
	else
		return COMPUTER;
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
	printf("players answers:"
			"\n\t COMPUTER [%s]"
			"\n\t USER     [%s]", printRespond(computer), printRespond(user) );
	if(BOTH == winner)
		printf("\nresult !!!DRAW!!!\n");
	else if(COMPUTER == winner)
		printf("\nwinner is !!!COMPUTER!!!\n");
	else
		printf("\n Congratulations! you're the winner!");


	return EXIT_SUCCESS;
}
