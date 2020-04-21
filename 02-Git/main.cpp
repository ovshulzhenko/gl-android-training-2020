#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

string weapon[3]={"rock","scissors","paper"};
char input;
int npc,nmy;

int main(int argc, char *argv[])
{

    srand(time(NULL));

    while(1){
        cout<<"Please choose: rock (r) - scissors (s) - paper (p)"<<endl;

        cin>>input;
        if(input=='r')nmy=0;
        else if(input=='s')nmy=1;
        else if(input=='p')nmy=2;
        else {
            cout<<"Wrong key"<<endl;
            return (0);
        }

        npc=rand() % 3;

        cout<<"You choose "<<weapon[nmy]<<", I chose "<<weapon[npc]<<endl;

        if((npc-nmy)==1 || (npc-nmy)==-2) cout<<"You win: "<<weapon[nmy]<<" beats "<<weapon[npc]<<endl;
        else if ((npc-nmy)==0) cout<<"draw"<<endl;
        else  cout<<"You lose: "<<weapon[npc]<<" beats "<<weapon[nmy]<<endl;
    }

    return (0);
}
