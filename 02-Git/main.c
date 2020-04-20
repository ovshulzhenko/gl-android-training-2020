/**
 * @file main.c
 * @brief rock-paper-scissors game implementation in C programming language
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

const char *signs_names[] = { "\"Rock\"", "\"Paper\"", "\"Scissors\""};
const char game_signs[]   = {'r', 'p', 's'};


int main(void)
{
  char input = '\0';
  int  rnd   = 0;
 
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

    rnd = rand() % 3;
    printf("PC sign is %s\n", signs_names[rnd]);

    switch(input)
    {
      case 'r':
      printf("Your sign is %s\n", signs_names[0]);
      if (game_signs[rnd] == 's')
      {
        puts("PC defeat\n");
      }
      else if (game_signs[rnd] == 'p')
      {
        puts("PC win\n");
      }
      else
      {
        puts("Draw\n");
      }
      break;

      case 'p':
      printf("Your sign is %s\n", signs_names[1]);
      if (game_signs[rnd] == 'r')
      {
        puts("PC defeat\n");
      }
      else if (game_signs[rnd] == 's')
      {
        puts("PC win\n");
      }
      else
      {
        puts("Draw\n");
      }
      break;

      case 's':
      printf("Your sign is %s\n", signs_names[2]);
      if (game_signs[rnd] == 'p')
      {
        puts("PC defeat\n");
      }
      else if (game_signs[rnd] == 'r')
      {
        puts("PC win\n");
      }
      else
      {
        puts("Draw\n");
      }
      break;

      default:
      puts("Enter your sign: r - for \"Rock\", p - for \"Paper\", s - for \"Scissors\"");
    }
  }

  return 0;
}
