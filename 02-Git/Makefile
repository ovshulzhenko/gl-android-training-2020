TARGET=RockPaperScissorsGame
CFLAGS=-Wall

.PHONY: all clean $(TARGET)
all: clean $(TARGET)

clean:
	rm -f *.o
	rm -f $(TARGET)

$(TARGET): game.c
	$(CC) $< $(CFLAGS) -o  $@
