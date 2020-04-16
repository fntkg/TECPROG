import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ruta {
    private List<Enlace> ruta;

    private void emptyString(String path) throws ExcepcionArbolFicheros {
        if(path.isEmpty()) { throw new ExcepcionArbolFicheros(); } }

    public Ruta(Directorio dir) throws ExcepcionArbolFicheros {
        ruta = new ArrayList<>();
        // Todo comprobar que es directorio raiz.
        if (!dir.getName().equals("/")) { throw new ExcepcionArbolFicheros(); }
        ruta.add(dir);
    }

    public String pwd(){
        StringBuilder pwd = new StringBuilder("");
        for (Enlace nodo : ruta){
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
        Enlace nodo = ruta.get(ruta.size()-1);
        if (nodo.getContenido()!= null){
            List<Enlace> lista = nodo.getContenido();
            for (Enlace dir : lista){
                if (dir.getContenido() != null){
                    ls.append("\n").append(dir.getName()).append("/");
                } else{
                    ls.append("\n").append(dir.getName());
                }
            }
        } if (!ls.toString().isEmpty()){ ls.deleteCharAt(0); }
        return ls.toString();
    }

    public String du() throws ExcepcionArbolFicheros {
        StringBuilder du = new StringBuilder("");
        Enlace nodo = ruta.get(ruta.size()-1);
        if (nodo.getContenido()!= null){
            List<Enlace> lista = nodo.getContenido();
            for (Enlace dir : lista){
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
        Enlace nodo = ruta.get(ruta.size()-1);
        Directorio dir = new Directorio(name);
        if (!nodo.addContenido(dir)) { throw new ExcepcionArbolFicheros("Error al crear directorio"); }
    }

    public void vi(String name, Integer size) throws ExcepcionArbolFicheros {
        Enlace nodo = ruta.get(ruta.size()-1);
        List<Enlace> files = nodo.getContenido();
        boolean encontrado = false;
        for (Enlace file : files){
            if (file.getName().equals(name)){
                encontrado = true;
                Integer last_size = file.getSize();
                file.setSize(size);
                nodo.sustractSize(last_size);
                nodo.addContenido(size);
                break;
            }
        }
        if (!encontrado){
            Fichero file = new Fichero(name,size);
            nodo.addContenido(file);
        }
    }

    public void cd(String path) throws ExcepcionArbolFicheros {
        emptyString(path);
        List<String> list_path = new ArrayList<>(Arrays.asList(path.split("/")));
        //System.out.println(list_path);
        if (list_path.isEmpty()){
            Enlace raiz = ruta.get(0);
            ruta.clear();
            ruta.add(raiz);
        } else{
            if (path.startsWith("/")){
                list_path.remove(0);
                // Ruta absoluta
                List<Enlace> new_path = new ArrayList<>();
                new_path.add(ruta.get(0));
                //Tomar el ultimo elemento y mirar sus hijos
                boolean encontrado = false;
                for (String fichero : list_path){
                    //Cojer carpeta actual
                    for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
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
                    List<Enlace> new_path = new ArrayList<>(ruta);
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
                                for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
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
                } else{
                    if (list_path.get(0).equals(".")) { list_path.remove(0); }
                    //Buscar primer elemento en la ruta actual
                    List<Enlace> new_path = new ArrayList<>(ruta);
                    boolean encontrado = false;
                    for (String fichero : list_path){
                        for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                            if (fichero.equals(nodo.getName())){
                                encontrado = true;
                                new_path.add(nodo);
                                break;
                            }
                        }
                    } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                    ruta = new_path;
                }
            }
        }
    }

    public Integer stat(String path) throws ExcepcionArbolFicheros {
        Integer size;
        emptyString(path);
        List<String> list_path = new ArrayList<>(Arrays.asList(path.split("/")));
        if (list_path.isEmpty()){
            size = ruta.get(0).getSize();
        } else{
            if (path.startsWith("/")){
                list_path.remove(0);
                // Ruta absoluta
                List<Enlace> new_path = new ArrayList<>();
                new_path.add(ruta.get(0));
                //Tomar el ultimo elemento y mirar sus hijos
                boolean encontrado = false;
                for (String fichero : list_path){
                    //Cojer carpeta actual
                    for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                        if (fichero.equals(nodo.getName())){
                            encontrado=true;
                            new_path.add(nodo);
                            break;
                        }
                    }
                } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                size = new_path.get(new_path.size()-1).getSize();
            } else {
                if (list_path.get(0).equals("..")){
                    List<Enlace> new_path = new ArrayList<>(ruta);
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
                                for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                                    if (fichero.equals(nodo.getName())){
                                        encontrado = true;
                                        new_path.add(nodo);
                                        break;
                                    }
                                }
                            }
                        } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                    }
                    size = new_path.get(new_path.size()-1).getSize();
                } else{
                    if (list_path.get(0).equals(".")) { list_path.remove(0); }
                    //Buscar primer elemento en la ruta actual
                    List<Enlace> new_path = new ArrayList<>(ruta);
                    boolean encontrado = false;
                    for (String fichero : list_path){
                        for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                            if (fichero.equals(nodo.getName())){
                                encontrado = true;
                                new_path.add(nodo);
                                break;
                            }
                        }
                    } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                    size = new_path.get(new_path.size()-1).getSize();
                }
            }
        }
        return size;
    }

    public void rm(String path) throws ExcepcionArbolFicheros {
        emptyString(path);
        List<String> list_path = new ArrayList<>(Arrays.asList(path.split("/")));
        if (list_path.isEmpty()){
            //Eliminar toda la ruta;
            //NO LE DEJAMOS, QUE SALTE EXCEPCION
            throw new ExcepcionArbolFicheros("No borres la raiz, vaquero ;)");
        } else{
            if (path.startsWith("/")){
                list_path.remove(0);
                // Ruta absoluta
                List<Enlace> new_path = new ArrayList<>();
                new_path.add(ruta.get(0));
                //Tomar el ultimo elemento y mirar sus hijos
                boolean encontrado = false;
                for (String fichero : list_path){
                    //Cojer carpeta actual
                    for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                        if (fichero.equals(nodo.getName())){
                            encontrado=true;
                            new_path.add(nodo);
                            break;
                        }
                    }
                } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                Enlace directorio = new_path.get(new_path.size()-2);
                //TODO eliminar el que tenga elmismo nombre)
                int index = -1;
                for (Enlace nodo : directorio.getContenido()){
                    if (nodo.getName().equals(list_path.get(list_path.size()-1))){
                        //Eliminar este nodo
                        index = directorio.getContenido().indexOf(nodo);
                    }
                } if (index == -1) { throw new ExcepcionArbolFicheros("Archivo no existe"); }
                List<Enlace> lista = directorio.getContenido();
                lista.remove(index);
            } else {
                if (list_path.get(0).equals("..")){
                    List<Enlace> new_path = new ArrayList<>(ruta);
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
                                for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                                    if (fichero.equals(nodo.getName())){
                                        encontrado = true;
                                        new_path.add(nodo);
                                        break;
                                    }
                                }
                            }
                        } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                    }
                    Enlace directorio = new_path.get(new_path.size()-2);
                    //TODO eliminar el que tenga elmismo nombre)
                    int index = -1;
                    for (Enlace nodo : directorio.getContenido()){
                        if (nodo.getName().equals(list_path.get(list_path.size()-1))){
                            //Eliminar este nodo
                            index = directorio.getContenido().indexOf(nodo);
                        }
                    } if (index == -1) { throw new ExcepcionArbolFicheros("Archivo no existe"); }
                    List<Enlace> lista = directorio.getContenido();
                    lista.remove(index);
                } else{
                    if (list_path.get(0).equals(".")) { list_path.remove(0); }
                    //Buscar primer elemento en la ruta actual
                    List<Enlace> new_path = new ArrayList<>(ruta);
                    boolean encontrado = false;
                    for (String fichero : list_path){
                        for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                            if (fichero.equals(nodo.getName())){
                                encontrado = true;
                                new_path.add(nodo);
                                break;
                            }
                        }
                    } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                    Enlace directorio = new_path.get(new_path.size()-2);
                    int index = -1;
                    for (Enlace nodo : directorio.getContenido()){
                        if (nodo.getName().equals(list_path.get(list_path.size()-1))){
                            //Eliminar este nodo
                            index = directorio.getContenido().indexOf(nodo);
                        }
                    } if (index == -1) { throw new ExcepcionArbolFicheros("Archivo no existe"); }
                    List<Enlace> lista = directorio.getContenido();
                    lista.remove(index);
                }
            }
        }
    }

    public void ln(String path, String name) throws ExcepcionArbolFicheros {
        emptyString(path);
        List<String> list_path = new ArrayList<>(Arrays.asList(path.split("/")));
        if (list_path.isEmpty()){
            Enlace enlace = ruta.get(0);
            enlace.setName(name);
            //Añadir al ultimo elemento de la ruta actual el nuevo Enlace
            ruta.get(ruta.size()-1).addContenido(enlace);
        } else{
            if (path.startsWith("/")){
                list_path.remove(0);
                // Ruta absoluta
                List<Enlace> new_path = new ArrayList<>();
                new_path.add(ruta.get(0));
                //Tomar el ultimo elemento y mirar sus hijos
                boolean encontrado = false;
                for (String fichero : list_path){
                    //Cojer carpeta actual
                    for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                        if (fichero.equals(nodo.getName())){
                            encontrado=true;
                            new_path.add(nodo);
                            break;
                        }
                    }
                } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                Enlace enlace = new_path.get(new_path.size()-1);
                enlace.setName(name);
                //Añadir al ultimo elemento de la ruta actual el nuevo Enlace
                ruta.get(ruta.size()-1).addContenido(enlace);
            } else {
                if (list_path.get(0).equals("..")){
                    List<Enlace> new_path = new ArrayList<>(ruta);
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
                                for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                                    if (fichero.equals(nodo.getName())){
                                        encontrado = true;
                                        new_path.add(nodo);
                                        break;
                                    }
                                }
                            }
                        } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                        Enlace enlace = new_path.get(new_path.size()-1);
                        enlace.setName(name);
                        //Añadir al ultimo elemento de la ruta actual el nuevo Enlace
                        ruta.get(ruta.size()-1).addContenido(enlace);
                    }
                } else{
                    if (list_path.get(0).equals(".")) { list_path.remove(0); }
                    //Buscar primer elemento en la ruta actual
                    List<Enlace> new_path = new ArrayList<>(ruta);
                    boolean encontrado = false;
                    for (String fichero : list_path){
                        for (Enlace nodo : new_path.get(new_path.size()-1).getContenido()){
                            if (fichero.equals(nodo.getName())){
                                encontrado = true;
                                new_path.add(nodo);
                                break;
                            }
                        }
                    } if (!encontrado) { throw new ExcepcionArbolFicheros("Ruta no encontrada"); }
                    Enlace enlace = new_path.get(new_path.size()-1);
                    enlace.setName(name);
                    //Añadir al ultimo elemento de la ruta actual el nuevo Enlace
                    ruta.get(ruta.size()-1).addContenido(enlace);
                }
            }
        }
    }
}
