import java.util.ArrayList;
import java.util.List;

public class Transporte {
    double peso;
    double volumen;
    String nombre;

    List<ProductoEspecial> guardado;
    double capacidad;

    public Transporte(double volumen, double peso, String nombre) {
        this.peso = peso;
        this.volumen = volumen;
        this.nombre = nombre;
        capacidad = volumen;
        guardado = new ArrayList<>();
    }

    public double peso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double volumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public String nombre() {
        return nombre;
    }

    public void setCapacidad(double valor){
        this.capacidad = valor;
    }

    public double capacidad(){ return capacidad; }

    public boolean guardar_(ProductoEspecial elemento) {
        boolean result;
        if (capacidad() >= elemento.volumen() && capacidad() > 0) {
            setCapacidad(capacidad() - elemento.volumen());
            setPeso(peso() + elemento.peso());
            guardado.add(elemento);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder a = new StringBuilder("[" + volumen() + " m3] [" + peso() + " kg]");
        for (ProductoEspecial producto : guardado){
            a.append('\n').append("   ").append(producto.toString());
        }
        return a.toString();
    }
}
