public class ExcepcionArbolFicheros extends Exception{
    String mensaje;

    public ExcepcionArbolFicheros() {
        super();
        mensaje = "";
    }

    public ExcepcionArbolFicheros(String message) {
        super();
        mensaje = message;
    }

    @Override
    public String toString() {
        String result = "";
        if (mensaje.equals("")){
            result = "ExcepcionArbolFicheros";
        } else {
            result = "ExcepcionArbolFicheros: \n    " + mensaje;
        }
        return result;
    }
}
