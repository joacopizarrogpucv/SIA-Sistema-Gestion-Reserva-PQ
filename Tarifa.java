public class Tarifa {
    private double precioBase;

    public Tarifa(double precioBase) {
        this.precioBase = precioBase;
    }

    public double getPrecioBase() {
        return precioBase;
    }
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public double calcular(int cantidadPersonas) {
        return calcular(cantidadPersonas, 0.0);
    }

    // Sobrecarga, permite aplicar un descuento porcentual
    public double calcular(int cantidadPersonas, double descuento) {
        double total = precioBase * cantidadPersonas;
        return total - (total * descuento / 100.0);
    }

    
}
