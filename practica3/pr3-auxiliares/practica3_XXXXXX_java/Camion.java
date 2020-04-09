public class Camion extends Transporte {
    public Camion(double volumen) {
        super(volumen, 0.0, "camion");
    }

    boolean guardar (Generico elemento){
        return super.guardar_(elemento);
    }

    @Override
    public String toString() {
        return "Camion " + super.toString();
    }
}
