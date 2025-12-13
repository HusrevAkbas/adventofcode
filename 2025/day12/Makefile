SRC_FILES	=	day12.java Shape.java
RUNNER		=	aoc.y2025.day12.day12

all : compile
	@java $(RUNNER) example.txt

run	: compile
	@java $(RUNNER) input.txt

compile :
	@clear; javac -d . $(SRC_FILES)

c:
	@rm -fr aoc/ out

.PHONY: all clear run compile