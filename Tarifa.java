public class Tarifa {
    private double precioBase;

    public Tarifa(double precioBase) {
        this.precioBase = precioBase;
    }

    public double calcular(int cantidadPersonas) {
        return precioBase * cantidadPersonas;
    }

    // Sobrecarga, permite aplicar un descuento porcentual
    public double calcular(int cantidadPersonas, double descuento) {
        double total = precioBase * cantidadPersonas;
        return total - (total * descuento / 100.0);
    }

    public double getPrecioBase() {
        return precioBase;
    }
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
}
