SRC_FILES	=	day11.java
RUNNER		=	aoc.y2025.day11.day11

all : compile
	@java $(RUNNER) example.txt

run	: compile
	@java $(RUNNER) input.txt

compile :
	@clear; javac -d . $(SRC_FILES)

c:
	@rm -fr aoc/ out

.PHONY: all clear run compile