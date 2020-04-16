import java.util.ArrayList;
import java.util.List;

public class Directorio extends Enlace {
    private List<Enlace> contenido;

    public Directorio(String name) {
        super(name, 0);
        if (name.equals("")){
            super.setName("/");
        }
        contenido = new ArrayList<>();
    }

    @Override
    public List<Enlace> getContenido() {
        return contenido;
    }

    @Override
    public boolean addContenido(Enlace nodo) throws ExcepcionArbolFicheros {
        contenido.add(nodo);
        super.setSize(super.getSize()+nodo.getSize());
        return true;
    }

    @Override
    public boolean addContenido(Integer a) throws ExcepcionArbolFicheros {
        if (a < 0){ throw new ExcepcionArbolFicheros(); }
        super.setSize(super.getSize()+a);
        return true;
    }

    @Override
    public boolean sustractSize(Integer a) throws ExcepcionArbolFicheros {
        super.setSize(super.getSize()-a);
        return true;
    }
}
