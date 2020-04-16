TARGET = rock_paper_scissors

BUILD_DIR = build

all: $(TARGET).c $(BUILD_DIR)
	gcc $(TARGET).c -o ./$(BUILD_DIR)/$(TARGET)

$(BUILD_DIR):
	mkdir $@	

clean:
	-rm ./$(BUILD_DIR)/$(TARGET)

run:all
	./$(BUILD_DIR)/$(TARGET)

