.SUFFIXES: .cc .o

PROG:=main-static-stack-struct main-static-stack-class main
SRCS:=main-static-stack-struct.cc main-static-stack-class.cc main.cc

CXX:=g++ -std=c++14 -Wall

all: $(PROG)

%: %.o
	$(CXX) -o $@ $^

%.o: %.cc
	$(CXX) -c $<

.PHONY: edit go

edit:
	@geany -s -i $(SRCS) &

go:
	@$(PROG)

clean:
	@rm -f $(PROG) *.o core tags

main-static-stack-struct.o: static-stack-struct.h
main-static-stack-class.o:  static-stack.h
main.o:  static-stack.h dynamic-stack.h
