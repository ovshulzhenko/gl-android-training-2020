#include <iostream>

using namespace std;

void win() {
    cout << endl << "You win!" << endl;
}

void lose() {
    cout << endl << "You lose!" << endl;
}

void tie() {
    cout << endl << "It's a tie!" << endl;
}

int main() {

    for (;;)
    {
    srand(time(NULL));

    cout << endl << "Please choose:" << endl;
    cout << " " << endl;
    cout << "rock (r)" << endl;
    cout << "paper (p)" << endl;
    cout << "scissors (s)" << endl;

    std::string userChoice;
    cin >> userChoice;

    std::string cpuChoice;

    int tmp = (rand() % 3) + 1;
    if (tmp == 1) {
        cpuChoice = "r";
    } else if (tmp == 2) {
        cpuChoice = "p";
    } else {
        cpuChoice = "s";
    }

    if (userChoice == "r") {
        cout << endl << "You choose rock" << endl;
    } else if (userChoice == "p") {
        cout << endl << "You choose paper" << endl;
    } else if (userChoice == "s") {
        cout << endl << "You choose scissors!" << endl;
    } else {
        cout << "You entered an invaild choice" << endl;
        return 1;
    }

    if (cpuChoice == "r") {
        cout << "The computer choose rock!" << endl;
    } else if (cpuChoice == "p") {
        cout << "The computer choose paper!" << endl;
    } else if (cpuChoice == "s") {
        cout << "The computer choose scissors!" << endl;
    }

    if (userChoice == cpuChoice) {
        tie();
    }

    if (userChoice == "r") {
        if (cpuChoice == "p") {
            lose();
        }
        if (cpuChoice == "s") {
            win();
        }
    }

    if (userChoice == "p") {
        if (cpuChoice == "r") {
            win();
        }
        if (cpuChoice == "s") {
            lose();
        }
    }

    if (userChoice == "s") {
        if (cpuChoice == "r") {
            lose();
        }
        if (cpuChoice == "p") {
            win();
        }
    }

        cout << endl << "Would you like to play again? (y or n):";
        std::string decision;
        cin >> decision;
        if (decision != "y" & decision != "Y")
        {
            cout << "Bye!" << endl;
            break;
        }
    }

}

