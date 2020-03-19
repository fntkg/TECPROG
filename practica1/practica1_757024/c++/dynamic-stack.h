#pragma once

#include <iostream>

template<typename T> class Nodo{
private:
  T elemento;
  Nodo* siguiente;
public:
  Nodo(T a):elemento(a),siguiente(nullptr){}

  void enlazar(Nodo* a){
    this->siguiente = a;
  }

  Nodo* sig(){return siguiente;}

  T elem(){return elemento;}
};

template <typename T> class dynamic_stack{
protected:
  int total;
  Nodo<T>* ultimo;
  Nodo<T>* primero;
public:
  dynamic_stack():ultimo(nullptr){}

  void push(const T& p){
    Nodo<T>* aux = new Nodo<T>(p);
    if (ultimo == nullptr){
      Nodo<T>* aux_2 = new Nodo<T>(p);
      ultimo = aux_2;
      primero = ultimo;
    }
    aux -> enlazar(ultimo);
    ultimo = aux;
    total++;
  }

  void pop(){
    if (total != 0){
      Nodo<T>* aux = ultimo;
      ultimo = ultimo->sig();
      delete(aux);
      total --;
    }
  }

  friend class const_iterator;

  class const_iterator{
    Nodo<T>* i;
    const dynamic_stack<T>& c;
  public:
    const_iterator(const dynamic_stack& c_, Nodo<T>* i_): i(i_), c(c_){}

    const_iterator& operator++(){
      if(i->sig() != nullptr){
        i = i->sig();
      }
      return (*this);
    }

    const T operator*() const {
      T a = i->elem();
      return a;
    }

    bool operator !=(const const_iterator& that) const{
      return this->i != that.i;
    }
  };
  const_iterator begin() const {return const_iterator(*this,this->ultimo);}
  const_iterator end() const {return const_iterator(*this, this->primero);}
};
