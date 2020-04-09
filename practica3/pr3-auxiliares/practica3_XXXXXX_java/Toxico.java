public class Toxico extends Especial {
    public Toxico(double volumen, double peso, String nombre) {
        super(volumen, peso, nombre);
    }

    @Override
    public String productType() {
        return "Toxico";
    }
}
