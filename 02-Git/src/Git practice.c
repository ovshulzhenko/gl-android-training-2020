/*
 ============================================================================
 Name        : Git.c
 Author      : Oleksandr Shulzhenko
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define SCISSORS_CHAR "s"
#define ROCK_CHAR "r"
#define PAPER_CHAR "p"

#define SCISSORS_ROW "scissors"
#define ROCK_ROW "rock"
#define PAPER_ROW "paper"
#define INVALID_ROW "invalid"

typedef enum
{
    SCISSORS_NUM = 0,
    ROCK_NUM,
    PAPER_NUM,
    INVALID_NUM = 255
} turn;
static bool validate_input(char* inp)
{
    bool result = false;

    if ((strlen(inp) > 1) || (strcmp(inp, SCISSORS_CHAR)
            && strcmp(inp, ROCK_CHAR) && strcmp(inp, PAPER_CHAR)))
    {
        goto exit;
    }
    else
    {
        result = true;
    }
exit:
    return result;
}

static const char* enum_to_string(turn* inp)
{
    switch(*inp)
    {
    case SCISSORS_NUM:
        return SCISSORS_ROW;
    case ROCK_NUM:
        return ROCK_ROW;
    case PAPER_NUM:
        return PAPER_ROW;
    default:
        printf("Invalid input value! \n");
        return INVALID_ROW;
    }
}

static const char* char_to_string(char* inp)
{
    if (!strcmp(inp, SCISSORS_CHAR))
    {
        return SCISSORS_ROW;
    }
    else if (!strcmp(inp, PAPER_CHAR))
    {
        return PAPER_ROW;
    }
    else if(!strcmp(inp, ROCK_CHAR))
    {
        return ROCK_ROW;
    }

    printf("%s: Invalid input value %s \n", __FUNCTION__, inp);
    return INVALID_ROW;
}

static const turn char_to_enum(char* inp)
{
    if (!strcmp(inp, SCISSORS_CHAR))
    {
        return SCISSORS_NUM;
    }
    else if (!strcmp(inp, PAPER_CHAR))
    {
        return PAPER_NUM;
    }
    else if(!strcmp(inp, ROCK_CHAR))
    {
        return ROCK_NUM;
    }

    printf("%s: Invalid input value %s \n", __FUNCTION__, inp);
    return INVALID_NUM;
}

/* 0 - tie, 1 - pc winner, 2 - player winner, -1 - error */
static int define_winner(turn pc_inp, turn player_inp)
{
    switch (pc_inp)
    {
    case SCISSORS_NUM:
        switch(player_inp)
        {
        case SCISSORS_NUM:
            return 0;
        case ROCK_NUM:
            return 2;
        case PAPER_NUM:
            return 1;
        case INVALID_NUM:
            return -1;
        }
    case ROCK_NUM:
        switch(player_inp)
        {
        case SCISSORS_NUM:
            return 1;
        case ROCK_NUM:
            return 0;
        case PAPER_NUM:
             return 2;
        case INVALID_NUM:
             return -1;
        }
    case PAPER_NUM:
        switch(player_inp)
        {
        case SCISSORS_NUM:
            return 2;
        case ROCK_NUM:
            return 1;
        case PAPER_NUM:
            return 0;
        case INVALID_NUM:
            return -1;
        }
    case INVALID_NUM:
    default:
        return -1;
    }

    return -1;
}

static void ai_turn(char* inp)
{
    turn result = rand() % 3;
    int winner = define_winner(result, char_to_enum(inp));
    printf("You choose %s, I choose %s\n", char_to_string(inp), enum_to_string(&result));
    switch (winner)
    {
    case 0:
        printf("Tie!\n");
        break;
    case 1:
        printf("I win: %s beats %s\n", enum_to_string(&result), char_to_string(inp));
        break;
    case 2:
        printf("You win: %s beats %s \n", char_to_string(inp), enum_to_string(&result));
        break;
    default:
        printf("define_winner failed! Return code = %d \n", winner);
    }
}

int main(void) {
    while (true)
    {
        char user_input;

        printf("Please choose: rock (r) - paper (p) - scissors (s) ");
        scanf(" %c", &user_input);
        printf("user_input = %c \n", user_input);
        if (validate_input(&user_input))
        {
            ai_turn(&user_input);
        }
        else
        {
            printf("invalid input! \n");
        }
    }


    return EXIT_SUCCESS;
}
