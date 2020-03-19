#include "static-stack.h"
#include "dynamic-stack.h"
#include <iostream>

// TODO: Define la cabecera de esta función mediante programacion generica para
// que sea equivalente a declarar las dos funciones siguientes:
//
// void anyadir_elementos(static-stack<int>& ss)
// void anyadir_elementos(dynamic-stack<int>& ss)
template <typename T>
void anyadir_elementos(T& ss)
{
	ss.push(42);
	ss.push(15);
	ss.pop();
	for (int i = 0; i < 100; i+=5)
		ss.push(i);
}

//TODO: Define la cabecera de esta función mediante programacion generica para
//que sea equivalente a declarar las dos funciones siguientes:
//
//void mostrar_elementos(const static_stack<int>& ss)
//void mostrar_elementos(const dynamic_stack<int>& ss)
template <typename T>
void mostrar_elementos(T& ss)
{
	for (int i : ss)
		std::cout<<i<<" ";
	std::cout<<std::endl;
}

int main(int argc,char* argv[])
{
	static_stack<int>  ss_e;
	dynamic_stack<int> ss_d;

	anyadir_elementos(ss_e);
	anyadir_elementos(ss_d);

	mostrar_elementos(ss_e);
	mostrar_elementos(ss_d);

	return 0;
}
