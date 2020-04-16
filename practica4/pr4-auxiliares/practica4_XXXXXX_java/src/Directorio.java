import java.util.ArrayList;
import java.util.List;

public class Directorio extends Nodo {
    private List<Nodo> contenido;

    public Directorio(String name) {
        super(name, 0);
        if (name.equals("")){
            super.setName("/");
        }
        contenido = new ArrayList<>();
    }

    @Override
    public List<Nodo> getContenido() {
        return contenido;
    }

    @Override
    public boolean addContenido(Nodo nodo) throws ExcepcionArbolFicheros {
        contenido.add(nodo);
        //Todo actualizar tamaño
        super.setSize(super.getSize()+nodo.getSize());
        return true;
    }
}
