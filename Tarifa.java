public class Tarifa {

    private double precio;

    public Tarifa(double p) {
        precio = p;
    }

    public double calcular(Recurso recurso) {
        return precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double p) {
        precio = p;
    }
}