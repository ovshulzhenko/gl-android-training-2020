/**
 * @file main.c
 * @brief rock-paper-scissors game implementation in C programming language
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

const char *signs_names[] = { "\"Rock\"", "\"Paper\"", "\"Scissors\""};
const char game_signs[]   = {'r', 'p', 's'};

enum
{
  SIGN_ROCK     = 0,
  SIGN_PAPER    = 1,
  SIGN_SCISSORS = 2,
  SIGN_INVALID  = 3,
};

int sign_to_num(char sign);

int main(void)
{
  char input    = '\0';
  int  computer = 0;
  int  human    = 0;
 
  srand(time(0));

  puts("*** Rock paper scissors game ***\n");
  puts("To play game enter your sign: r - for \"Rock\", p - for \"Paper\", s - for \"Scissors\"");
  puts("Press Ctrl+D or Ctrl+Z to exit the game\n");

  while ((input = getchar()) != EOF)
  {
    /* Skip Enter button processing */
    if (input == '\n')
    {
      continue;
    }

    if ((human = sign_to_num(input)) == SIGN_INVALID)
    {
      puts("Enter your sign: r - for \"Rock\", p - for \"Paper\", s - for \"Scissors\"");
      continue;
    }

    printf("Your sign is %s\n", signs_names[human]);

    computer = rand() % 3;
    printf("PC sign is %s\n", signs_names[computer]);
  }

  return 0;
}

int sign_to_num(char sign)
{
  int retval = 0;

  switch (sign)
  {
  case 'r':
    retval = SIGN_ROCK;
    break;

  case 'p':
    retval = SIGN_PAPER;
    break;

  case 's':
    retval = SIGN_SCISSORS;
    break;

  default:
    retval = SIGN_INVALID;
    break;
  }

  return retval;
}
