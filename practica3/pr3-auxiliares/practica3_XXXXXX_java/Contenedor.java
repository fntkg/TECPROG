public class Contenedor <T extends ProductoEspecial> extends Transporte implements Generico {
    public Contenedor(double volumen) {
        super(volumen, 0.0, "contenedor");
    }

    public boolean guardar(T elemento){
        return super.guardar_(elemento);
    }

    @Override
    public String productType() {
        return "";
    }

    @Override
    public String toString() {
        String a;
        if (!guardado.isEmpty()) {
            // En caso de que el contenedor tenga productos dentro.
            // En caso de que el contenedor tenga otro contenedor... to do va mal jaja
            switch (guardado.get(0).productType()) {
                case "Generico":
                    a = "Contenedor de genericos ";
                    break;
                case "Toxico":
                    a = "Contenedor de toxicos ";
                    break;
                case "SerVivo":
                    a = "Contenedor de seres vivos ";
                    break;
                default:
                    a = "ERROR WHILE FETCHING CONTAINER ";
            }
        }
        else {
            a = "ERROR WHILE FETCHING CONTAINER";
        }

        return a + super.toString();
    }
}
