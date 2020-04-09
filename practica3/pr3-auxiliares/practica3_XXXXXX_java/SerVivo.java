public class SerVivo extends Especial {
    public SerVivo(double volumen, double peso, String nombre) {
        super(volumen, peso, nombre);
    }

    @Override
    public String productType() {
        return "SerVivo";
    }
}
