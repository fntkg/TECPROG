#pragma once

// Interfaz del TAD pila. Pre-declaraciones:
const int MAX = 40; //Límite tamaño de la agrupación, en esta implementación.

template<typename T>
class static_stack
{
private:
	// Mantenemos aqui los mismos atributos privados, ignorando aquellos que
	// tengan que ver con el iterador
	T datos[MAX];
	int total;

public:
	// Ahora la funcion iniciar de la estructura es el constructor.
	// TODO: Invoca a los constructores de los miembros privados (o deja
	// que se construyan por defecto si lo consideras asi).
	// Separados por comas (si no sabes cómo hacerlo, mira cómo está hecho
	// en la clase 'const_iterator')
	static_stack():total(0)
	{
		// Si invocas a los constructores de los miembros privados no necesitas
		// rellenar el código del constructor.
	}

	// TODO: La funcion push ahora es el metodo push. Rellénalo.
	// Para acceder a atributos
	// y métodos de la propia clase, deberás hacerlo a través del puntero this->
	bool push(const T& e)
	{
                bool sePuede = this->total < MAX;
		if(sePuede){
			this->datos[this->total] = e;
			this->total++;
		}
		return sePuede;
	}

	// TODO: La funcion pop se transforma tambien en un metodo. Rellénalo.
	bool pop()
	{
                bool sePuede = this->total > 0;
		if (sePuede){ this->total--;}
		return sePuede;
	}

	// Declarando la clase const_iterator (iterador constante) como friend y
	// como clase local, conseguimos que pueda haber varios iteradores,
	// cada uno de ellos llevando su propio indice, indicando en qué poisición
	// de la estructura estamos.
	friend class const_iterator;

	class const_iterator
	{
	private:
		// Al separar el iterador en una clase aparte, trasladamos los miembros
		// privados que estaban en el struct a esta clase.
		// Además, mantenemos una referencia a la pila.
		const static_stack<T>& stk;
		int i;
	public:
		// Este constructor sirve como inicializador del iterador,
		// tanto al principio como al final.
		const_iterator(const static_stack& stk_, int i_) : stk(stk_), i(i_)
		{ }

		// En la definición por defecto de los iteradores, se separa el avance
		// del iterador de la obtención del elemento apuntado por el iterador
		// (que en la otra implementación ocurren en la misma función).

		// Este método redefine el operador de pre-incremento (++x).
		// Representa el avance del iterador.
		const_iterator& operator++()
		{
			// TODO: Rellena este hueco para que el iterador sobre la agrupación
			// avance. Recuerda que en nuestra definición de agrupación la
			// estructura se recorre desde el último elemento introducido hasta
			// el primero (como si se tratara de una pila).
    	this -> i = i - 1;
			return (*this);
		}

		// Este método redefine el operador de "apuntado" (*x)
		// Representa la obtención de lo apuntado por el iterador.
		const T& operator*() const
		{
			// TODO: Rellena este método para que devuelva el elemento T al que
			// está apuntando el iterador.
                        return stk.datos[this->i];
		}

		// En la definición por defecto de los iteradores, no existe la
		// comprobación de si existe siguiente elemento.
		// Por defecto las estructuras de datos devuelven iteradores al
		// principio y al final,
		// Para recorrer la estructura se compara el iterador que avanza con
		// el iterador que apunta al final de la estructura.
		bool operator!=(const const_iterator& that) const
		{
			// TODO: Rellenar este método para devuelva true si este iterador y
			// el iterador "that" apuntan a sitios diferentes,
			// y false en caso contrario.
                       return this->i != that.i;
		}

	};

	// Observa como nuestra pila permite generar dos iteradores:
	// uno al principio y otro al final.
	// El iterador que apunta al final de la agrupación se mantiene en este caso
	// únicamente para hacer la comprobación de que "existe siguiente".
	// Date cuenta que los valores que le pasamos como índice del iterador son
	// para que se recorra la estructura
	// desde el último elemento (this->total - 1) hasta el primero (0).
	const_iterator begin() const
	{
		return const_iterator(*this,this->total-1);
	}
	const_iterator end() const
	{
		return const_iterator(*this,-1);
	}
};
