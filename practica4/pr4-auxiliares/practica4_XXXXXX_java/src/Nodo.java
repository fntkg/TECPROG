import java.util.List;

public class Nodo {
    String name;
    Integer size;

    public Nodo(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) throws ExcepcionArbolFicheros {
        if (size < 0){ throw new ExcepcionArbolFicheros(); }
        this.size = size;
    }

    public List<Nodo> getContenido(){
        return null;
    }

    public boolean addContenido(Nodo nodo) throws ExcepcionArbolFicheros { return false; }
}
