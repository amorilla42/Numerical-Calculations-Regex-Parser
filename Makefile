
# Define the Java compiler
JC = javac

# Define the Java compiler flags
JFLAGS = -g

# Define the source directory
SRC_DIR = Expresion\ Transformation\ Proyect/src

# Define the build directory
BUILD_DIR = build

# Define the main class
MAIN_CLASS = Main

# Define the source files
SRCS = $(wildcard $(SRC_DIR)/*.java)

# Define the object files
OBJS = $(SRCS:$(SRC_DIR)/%.java=$(BUILD_DIR)/%.class)

# Define the default target
default: $(OBJS)
	$(JC) $(JFLAGS) -cp $(BUILD_DIR) $(SRC_DIR)/$(MAIN_CLASS).java

# Define the object file targets
$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java | $(BUILD_DIR)
	$(JC) $(JFLAGS) -cp $(BUILD_DIR) -d $(BUILD_DIR) $<

# Define the build directory target
$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

# Define the clean target
clean:
	rm -rf $(BUILD_DIR)
