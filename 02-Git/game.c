#include <stdlib.h>
#include <stdio.h>
#include <time.h>

typedef enum
{
    eROCK,
    ePAPER,
    eSCISSORS,
    eQUIT,
    eOTHER
} E_VAL;

typedef enum
{
    eWIN,
    eLOSE,
    eDRAW,
    eEXIT
} E_RESULT;

E_VAL get_bid_value(char c_bid)
{
    E_VAL val = eOTHER;
    switch (c_bid)
    {
        case 'R':
        case 'r':
            printf("Rock.");
            val = eROCK;
            break;
        case 'P':
        case 'p':
            printf("Parer.");
            val = ePAPER;
            break;
        case 'S':
        case 's':
            printf("Scissors.");
            val = eSCISSORS;
            break;
        case 'Q':
        case 'q':
            printf("Quit\n");
            break;
        default:
            printf("wrong input\n");
            break;
    }

    return val;
}

E_VAL get_bid_value_by_num(int bid)
{
    E_VAL val = eOTHER;
    switch (bid)
    {
        case eROCK:
            printf("Rock.");
            val = eROCK;
            break;
        case ePAPER:
            printf("Parer.");
            val = ePAPER;
            break;
        case eSCISSORS:
            printf("Scissors.");
            val = eSCISSORS;
            break;
        default:
            break;
    }

    return val;
}

E_RESULT rock_compare(E_VAL bid)
{
    E_RESULT res = eWIN;

    switch (bid)
    {
        case eROCK:
            res = eDRAW;
            printf("Nobody win\n");
            break;
        case ePAPER:
            printf("I win: paper beats rock\n");
            res = eWIN;
            break;
        case eSCISSORS:
            printf("You win: rock beats scissors\n");
            res = eLOSE;
            break;
        default:
            break;
    }
    return res;
}

E_RESULT paper_compare(E_VAL bid)
{
    E_RESULT res = eWIN;

    switch (bid)
    {
        case eROCK:
            res = eWIN;
            printf("You win: paper beats rock\n");
            break;
        case ePAPER:
            printf("Nobody win\n");
            res = eDRAW;
            break;
        case eSCISSORS:
            printf("I win: scissors beats paper\n");
            res = eLOSE;
            break;
        default:
            break;
    }
    return res;
}

E_RESULT scissors_compare(E_VAL bid)
{
    E_RESULT res = eWIN;

    switch (bid)
    {
        case eROCK:
            res = eLOSE;
            printf("You win: rock beats scissors\n");
            break;
        case ePAPER:
            res = eWIN;
            printf("I win: scissors beats paper\n");
            break;
        case eSCISSORS:
            printf("Nobody win\n");
            res = eDRAW;
            break;
        default:
            break;
    }
    return res;
}

E_RESULT start_round(void)
{
    E_RESULT res = eEXIT;
    char rps = '0';
    time_t t;
    E_VAL my_bid = eOTHER;
    E_VAL your_bid = eOTHER;

    printf("\nPlease choose: rock (r) - paper (p) - scissors (s)\n");
    rps = getchar();
    printf("You choose ");
    your_bid = get_bid_value(rps);
    printf("I choose ");
    srand((unsigned) time(&t));
    my_bid = get_bid_value_by_num(rand() % 3);
    printf("\n");
    switch (your_bid)
    {
        case eROCK:
            res = rock_compare(my_bid);
            break;
        case ePAPER:
            res = paper_compare(my_bid);
            break;
        case eSCISSORS:
            res = scissors_compare(my_bid);
            break;
        default:
            break;
    }

    return res;
}

int main (int argc, char* argv[])
{
    E_RESULT res = eEXIT;
    printf("=============================\n");
    printf("===Rock===Paper===Scissors===\n");
    printf("=============================\n");
    printf("Press Q to exit\n");

    while (1)
    {
        res = start_round();
        if (res == eEXIT)
            break;
    }

    return 0;
}
