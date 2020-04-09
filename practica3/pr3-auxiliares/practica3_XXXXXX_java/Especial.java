public abstract class Especial implements ProductoEspecial{
    double peso;
    double volumen;
    String nombre;

    public Especial(double volumen, double peso, String nombre) {
        this.peso = peso;
        this.volumen = volumen;
        this.nombre = nombre;
    }

    @Override
    public double peso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public double volumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public String nombre() {
        return nombre;
    }

    public abstract String productType();

    @Override
    public String toString() {
        return "   " + nombre() + " [" + volumen() + " m3] [" + peso() + " kg]";
    }

}
