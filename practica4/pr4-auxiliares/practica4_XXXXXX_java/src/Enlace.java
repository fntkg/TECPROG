import java.util.List;

public class Enlace extends Nodo{
    Nodo apunta;

    private void emptyPath(List<Nodo> path) throws ExcepcionArbolFicheros {
        if (path.isEmpty()){ throw new ExcepcionArbolFicheros(); }
    }

    public Enlace(String name, List<Nodo> path) throws ExcepcionArbolFicheros {
        super(name, 0);
        emptyPath(path);
        //Actualizar tamaño
        apunta = path.get(path.size()-1);
        super.setSize(apunta.getSize());
    }
}
