import java.lang.Iterable;

public interface Stack<T> extends Iterable<T>{
  boolean push(T t);
  boolean pop();
}
