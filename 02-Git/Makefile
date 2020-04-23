CC=g++
CXXFLAGS=-std=c++11 -Wall
LIBS+=-lpthread

all: game

game: main.o game.o
	$(CC) main.o game.o -o game $(LIBS) $(CXXFLAGS)

main.o: main.cpp
	$(CC) -c main.cpp

factorial.o: game.cpp
	$(CC) -c game.cpp

clean:
	rm -rf *.o game
