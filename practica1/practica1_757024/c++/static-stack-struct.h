#pragma once

// Interfaz del TAD agrupación genérico. Pre-declaraciones:
const int MAX = 40; //Límite tamaño de la agrupación, en esta implementación.

template<typename T> struct static_stack;

//definir operaciones de agrupacion
template<typename T> void init(static_stack<T>& c);
template<typename T> bool push(static_stack<T>& c, const T& p);
template<typename T> bool pop(static_stack<T>& c);
//  -> Iterador
template<typename T> void startIterator(static_stack<T>& c);
template<typename T> bool hasNext(const static_stack<T>& c);
template<typename T> T next (static_stack<T>& c, bool& err);

// Declaración:
template<typename T>
struct static_stack
{
	friend void init<T> (static_stack<T>& c);
	friend bool push<T> (static_stack<T>& c, const T& p);
	friend bool pop<T> (static_stack<T>& c);

	friend void startIterator<T> (static_stack<T>& c);
	friend bool hasNext<T> (const static_stack<T>& c);
	friend T next<T> (static_stack<T>& c, bool& err);

private://declaración de la representación interna del tipo:
	T datos[MAX];
	int total;
	int iterador;
};

//ATENCIÓN: para tipos de datos genéricos
//la implementación de las operaciones también estará en agrupacion.h
template<typename T>
void init(static_stack<T>& c)
{
	c.total = 0;
}

template<typename T>
bool push(static_stack<T>& c, const T& e)
{
	bool sePuede = c.total < MAX;

	if (sePuede)
	{
		c.datos[c.total] = e;
		c.total++;
	}

	return sePuede;
}

template<typename T>
bool pop(static_stack<T>& c)
{
	bool sePuede = c.total > 0;
	if (sePuede) c.total--;

	return sePuede;
}

template<typename T>
void startIterator(static_stack<T>& c)
{
	c.iterador = (c.total - 1);
}

template<typename T>
bool hasNext(const static_stack<T>& c)
{
	return c.iterador >= 0;
}

template<typename T>
T next(static_stack<T>& c, bool& err)
{
	err = (c.iterador < 0);
	return c.datos[c.iterador--];
}
