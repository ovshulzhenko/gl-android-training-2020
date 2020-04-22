#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

typedef enum 
{
    RPS_ROCK = 0,
    RPS_PAPER,
    RPS_SCISSORS,
    RPS_NUM_OF_CHOISE
} rock_paper_scissors_t;

typedef enum
{
    CRES_DRAW,
    CRES_WIN,
    CRES_LOSS
} compare_result_t;

static char *bet_names[(unsigned)RPS_NUM_OF_CHOISE] = 
{
    "Rock",
    "Paper",
    "Scissors"
};

static rock_paper_scissors_t random_bet_get()
{
    return (rock_paper_scissors_t)(rand() % (unsigned)RPS_NUM_OF_CHOISE);
}

static bool char_to_bet_convert(char choice, rock_paper_scissors_t *bet)
{
    bool ret = true;
    switch (choice)
    {
        case 'R':
        case 'r':
        {
            *bet = RPS_ROCK;
            break;
        }
        case 'P':
        case 'p':
        {
            *bet = RPS_PAPER;
            break;
        }
        case 'S':
        case 's':
        {
            *bet = RPS_SCISSORS;
            break;
        }
        default:
        {
            ret = false;
        }
    }
    return ret;
}

static compare_result_t bets_compere(rock_paper_scissors_t bet_1, rock_paper_scissors_t bet_2)
{
    if (bet_1 == bet_2)
    {
        return CRES_DRAW;
    }
    
    if (((RPS_ROCK == bet_1) && (RPS_SCISSORS == bet_2))
        || ((RPS_SCISSORS == bet_1) && (RPS_PAPER == bet_2))
        || ((RPS_PAPER == bet_1) && (RPS_ROCK == bet_2)))
    {
        return CRES_WIN;
    }

    return CRES_LOSS;
}


int main(int argc, char const *argv[])
{
    char choice = '\0';

    long ltime = time(NULL);
    int stime = (unsigned) ltime/2;
    srand(stime);

    printf("*******************************************************\n");
    printf("***                                                 ***\n");
    printf("***         The Rock-Paper-Scissors game            ***\n");
    printf("***                                                 ***\n");
    printf("*******************************************************\n\n");

    do
    {
        printf("Please choose: rock (r) - paper (p) - scissors (s) or quit (q).\n");

        choice = getchar();
        while(getchar() != '\n');
        rock_paper_scissors_t player_bet = RPS_ROCK;
        rock_paper_scissors_t CPU_bet = random_bet_get();

        if ('q' != choice)
        {
            if (char_to_bet_convert(choice, &player_bet))
            {
                printf("You chose %s, I chose %s.\n", bet_names[player_bet], bet_names[CPU_bet]);
                compare_result_t game_result = bets_compere(CPU_bet, player_bet);
                switch (game_result)
                {
                    case CRES_DRAW:
                    {
                        printf("The draw.\n");
                        break;
                    }
                    case CRES_WIN:
                    {
                        printf("I win! %s beats %s.\n", bet_names[CPU_bet], bet_names[player_bet]);
                        break;
                    }
                    case CRES_LOSS:
                    {
                        printf("You win! %s beats %s.\n", bet_names[player_bet], bet_names[CPU_bet]);
                        break;
                    }
                }
            } 
            else
            {
                printf("Wrong letter. There is no such bet.\n");
            }

            printf("Let's play again!\n\n");
        }
    } while ('q' != choice);   
    
    return 0;
}
