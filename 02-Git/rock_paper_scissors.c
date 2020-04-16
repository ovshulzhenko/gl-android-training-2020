#include <stdio.h>

int main(int argc, char const *argv[])
{
    char bet = '\0';

    printf("*******************************************************\n");
    printf("***                                                 ***\n");
    printf("***         The Rock-Paper-Scissors game            ***\n");
    printf("***                                                 ***\n");
    printf("*******************************************************\n\n");

    printf("Please choose: rock (r) - paper (p) - scissors (s)\n");

    bet = getchar();
    switch (bet)
    {
        case 'R':
        case 'r':
        {
            printf("You choose rock, I choose paper\n");
            printf("I win: paper beats rock\n");
            break;
        }
        case 'P':
        case 'p':
        {
            printf("You choose paper, I choose scissors\n");
            printf("I win: scissors beat paper\n");
            break;
        }
        case 'S':
        case 's':
        {
            printf("You choose scissors, I choose rock\n");
            printf("I win: rock beats scissors\n");
            break;
        }
        default:
        {
            printf("You choose wrong letter\n");
            printf("I win anyway\n");
        }
    }
    return 0;
}
