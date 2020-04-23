#include "game.h"
namespace gm
{

void gameRPS::processResult(char user)
{
  char computer;
  printUserChoice(user);

  srand (time(NULL));
  int comp = rand() % 3 + 1;
  computer = printComputerChoice(comp);

  if(user == computer){
      std::cout << "Draw game\n";
   }
   else if(user == 'r' && computer == 's'){
      std::cout << "You win: rock beats scissors\n";
   }
   else if(user == 's' && computer == 'p'){
     std::cout << "You win: scissors beats paper\n";
   }
   else if(user == 'p' && computer == 'r'){
     std::cout << "You win: paper beats scissors\n";
   }
   else{
     std::cout << "Computer wins!\n";
   }
}

bool gameRPS::printUserChoice(char user) const
{
    bool result = false;
    switch (user) {
       case 'r':
       {
           std::cout<<"You choose rock ";
           result = true;
           break;
       }
       case 'p':
       {
           std::cout<<"You choose paper ";
           result = true;
           break;
       }
       case 's':
       {
        std::cout<<"You choose scissors ";
        result = true;
        break;
       }
       default: break;
     }
     return result;
}

char gameRPS::printComputerChoice(int comp)
{
    char computer = ' ';
    switch (comp) {
       case 1: computer = 'p'; std::cout<<"I choose paper"<<std::endl; break;
       case 2: computer = 'r'; std::cout<<"I choose rock"<<std::endl; break;
       case 3: computer = 's'; std::cout<<"I choose scissors"<<std::endl; break;
       default: computer = 'p'; break;
     }
    return computer;
}
}

