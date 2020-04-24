#pragma once
#include <iostream>

namespace gm
{
   class gameRPS final
   {
      private:
      bool printUserChoice(char) const;
      char printComputerChoice(int);
      int getRandomValue(void);

      public:
      gameRPS()=default;
      ~gameRPS()=default;
      void processResult(char);
   };
}


