import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicStack<T> implements Iterable<T>, Stack<T>{

  class Nodo<T>{
    T dato;
    Nodo<T> siguiente;

    private Nodo(T elem){dato = elem;}
    private void enlazar(Nodo<T> a){siguiente = a;}
  }

  private Nodo<T> ultimo;
  private int total;

  protected DynamicStack(){
    total = 0;
    ultimo = null;
  }

  public boolean push(T t){
    Nodo<T> aux = new Nodo<T>(t);
    if(ultimo == null){
            ultimo = aux;
        }
        else{
            aux.enlazar(ultimo);
            ultimo = aux;
        }
        total +=1;
        return true;
  }

  public boolean pop(){
    if (ultimo != null){
      ultimo = ultimo.siguiente;
      total -=1;
      return true;
    }
    else{ return false;}
  }

  private class IteradorAgrupacion implements Iterator<T>{
    DynamicStack c;
    Nodo i;

    private IteradorAgrupacion(DynamicStack a){
      this.c = a;
      i = c.ultimo;
    }

    public boolean hasNext(){ return i != null;}

    public T next() throws NoSuchElementException{
      if (!hasNext()) throw new NoSuchElementException();
      else{
        Nodo<T> aux = i;
        i = i.siguiente;
        return aux.dato;
      }
    }

    public void remove() throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
    }
}
public Iterator<T> iterator(){
      return new IteradorAgrupacion(this);

}
}
