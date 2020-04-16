import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ruta {
    private List<Nodo> ruta;

    public Ruta(Directorio dir) throws ExcepcionArbolFicheros {
        ruta = new ArrayList<>();
        // Todo comprobar que es directorio raiz.
        if (!dir.getName().equals("/")) { throw new ExcepcionArbolFicheros(); }
        ruta.add(dir);
    }

    public String pwd(){
        StringBuilder pwd = new StringBuilder("");
        for (Nodo nodo : ruta){
            if (nodo.getName().equals("/")){
                pwd.append(nodo.getName());
            } else {
                pwd.append(nodo.getName()).append("/");
            }
        }
        return pwd.toString();
    }

    public String ls() throws ExcepcionArbolFicheros {
        if (ruta.isEmpty()){ throw new ExcepcionArbolFicheros(); }
        StringBuilder ls = new StringBuilder("");
        Nodo nodo = ruta.get(ruta.size()-1);
        if (nodo.getContenido()!= null){
            List<Nodo> lista = nodo.getContenido();
            for (Nodo dir : lista){
                if (dir.getContenido() != null){
                    ls.append("\n").append(dir.getName()).append("/");
                } else{
                    ls.append("\n").append(dir.getName());
                }
            }
        }
        ls.deleteCharAt(0);
        return ls.toString();
    }

    public String du() throws ExcepcionArbolFicheros {
        StringBuilder du = new StringBuilder("");
        Nodo nodo = ruta.get(ruta.size()-1);
        if (nodo.getContenido()!= null){
            List<Nodo> lista = nodo.getContenido();
            for (Nodo dir : lista){
                if (dir.getContenido() != null){
                    du.append("\n").append(dir.getName()).append("/    ").append(dir.getSize()).append(" bytes");
                } else{
                    du.append("\n").append(dir.getName()).append("     ").append(dir.getSize()).append(" bytes");
                }
            }
        }
        du.deleteCharAt(0);
        return du.toString();
    }

    public void mkdir(String name) throws ExcepcionArbolFicheros {
        Nodo nodo = ruta.get(ruta.size()-1);
        Directorio dir = new Directorio(name);
        if (!nodo.addContenido(dir)) { throw new ExcepcionArbolFicheros("Error al crear directorio"); }
    }

    public void vi(String name, Integer size) throws ExcepcionArbolFicheros {
        Nodo nodo = ruta.get(ruta.size()-1);
        List<Nodo> files = nodo.getContenido();
        boolean encontrado = false;
        for (Nodo file : files){
            if (file.getName().equals(name)){
                encontrado = true;
                file.setSize(size);
                break;
            }
        }
        if (!encontrado){
            Fichero file = new Fichero(name,size);
            nodo.addContenido(file);
        }
    }

    public void cd(String path) throws ExcepcionArbolFicheros {
        List<String> list_path = new ArrayList<>(Arrays.asList(path.split("/")));
        //System.out.println(list_path);
        if (list_path.isEmpty()){
            Nodo raiz = ruta.get(0);
            ruta.clear();
            ruta.add(raiz);
        } else{
            if (path.startsWith("/")){
                list_path.remove(0);
                // Ruta absoluta
                List<Nodo> new_path = new ArrayList<>();
                new_path.add(ruta.get(0));
                //Tomar el ultimo elemento y mirar sus hijos
                boolean encontrado = false;
                for (String fichero : list_path){
                    //Cojer carpeta actual
                    for (Nodo nodo : new_path.get(new_path.size()-1).getContenido()){
                        if (fichero.equals(nodo.getName())){
                            encontrado=true;
                            new_path.add(nodo);
                            break;
                        }
                    }
                } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                ruta = new_path;
            } else {
                if (list_path.get(0).equals("..")){
                    List<Nodo> new_path = ruta;
                    if (list_path.size()==1){

                        new_path.remove(new_path.size()-1); //Nueva ruta un directorio por encima
                    } else {
                        list_path.remove(0);
                        //Subir un directorio
                        new_path.remove(new_path.size()-1); //Nueva ruta un directorio por encima
                        boolean encontrado = false;
                        for (String fichero : list_path){
                            if (fichero.equals("..")){
                                new_path.remove(new_path.size()-1); //Nueva ruta un directorio por encima
                            } else {
                                for (Nodo nodo : new_path.get(new_path.size()-1).getContenido()){
                                    if (fichero.equals(nodo.getName())){
                                        encontrado = true;
                                        new_path.add(nodo);
                                        break;
                                    }
                                }
                            }
                        } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                    }
                    ruta = new_path;
                } //TODO "." o "directorio"
            }
        }
    }
}
