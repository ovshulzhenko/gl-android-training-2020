#include <stdio.h>
#include <stdlib.h>

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
	return ROCK;
}

enum EPlayerRespond getUsersRespond()
{
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
	user = getUsersRespond();
	winner = arbitrate(computer, user);

	return EXIT_SUCCESS;
}
