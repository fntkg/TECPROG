PROG:=Main.class
SRCS:=Main.java

all: $(PROG)

$(PROG): $(SRCS)
	javac $<

.PHONY: edit

edit:
	@geany -s -i *.java &

clean:
	@rm -f *.class core tags
