#include <pthread.h>
#include <queue>
#include <unistd.h>
#include "game.h"

using namespace gm;

void *display_thread(void *ptr);
void *keyboard_thread(void *ptr);
pthread_mutex_t queue_mut;
std::queue<char> transferBuffer;
static const unsigned int DELAY = 100000;

int main(int argc, char *argv[])
{
   /*Create threads for display and keyboard*/
   pthread_mutex_init(&queue_mut, NULL);

   pthread_t displayThread;
   pthread_t keyboardThread;

   pthread_create( &displayThread, NULL, display_thread, (void*)"Display thread");
   pthread_create( &keyboardThread, NULL, keyboard_thread, (void*)"Keyboard thread");

   pthread_join(displayThread,NULL);
   pthread_join(keyboardThread,NULL);

   pthread_mutex_destroy(&queue_mut);
   exit(0);
}

void *display_thread(void *ptr)
{
   gameRPS gObj;
   std::cout<<"Please choose: rock (r) - paper (p) - scissors (s)"<<std::endl;
   while(1)
   {
     pthread_mutex_lock(&queue_mut);
     if(!transferBuffer.empty())
     {
        gObj.processResult(transferBuffer.front());
        transferBuffer.pop();
        std::cout<<"Please choose: rock (r) - paper (p) - scissors (s)"<<std::endl;
     }
     pthread_mutex_unlock(&queue_mut);
     usleep(DELAY);
   }
}

void *keyboard_thread(void *ptr)
{
   unsigned char inputData;
   while(1)
   {      
      std::cin >> inputData;

      pthread_mutex_lock(&queue_mut);
      transferBuffer.push(inputData);
      pthread_mutex_unlock(&queue_mut);
   }
}

