import java.util.List;

public class Enlace {
    String name;
    Integer size;

    public Enlace(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public Enlace(){
        this.name = "";
        this.size = -1;
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

    public List<Enlace> getContenido(){
        return null;
    }

    public boolean addContenido(Enlace nodo) throws ExcepcionArbolFicheros { return false; }
    public boolean addContenido(Integer a) throws ExcepcionArbolFicheros { return false; }
    public boolean sustractSize(Integer a) throws ExcepcionArbolFicheros { return false; }
}
