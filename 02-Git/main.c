/**
 * @file main.c
 * @brief rock-paper-scissors game implementation in C programming language
 */

#include <stdio.h>

int main(void)
{
  char input = '\0';

  puts("*** Rock paper scissors game ***\n");
  puts("To play game enter your sign: r - for rock, p - for paper, s - for scissors");
  puts("Press Ctrl+D or Ctrl+Z to exit the game\n");

  while ((input = getchar()) != EOF)
  {
    switch(input)
    {
      case 'r':
      break;

      case 'p':
      break;

      case 's':
      break;
    }
  }

  return 0;
}
