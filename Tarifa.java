/**
 * Representa la tarifa aplicada a una reserva.
 * Permite calcular el costo según la cantidad de personas
 * y opcionalmente aplicar un descuento porcentual.
 */
public class Tarifa {
    private double precioBase;

    /**
     * Crea una tarifa a partir de un precio base por persona.
     *
     * @param precioBase precio por persona
     */
    public Tarifa(double precioBase) {
        this.precioBase = precioBase;
    }

    public double getPrecioBase() {
        return precioBase;
    }
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    /**
     * Calcula el costo total sin descuento.
     *
     * @param cantidadPersonas cantidad de personas
     * @return costo total
     */
    public double calcular(int cantidadPersonas) {
        return calcular(cantidadPersonas, 0.0);
    }

    /**
     * Calcula el costo total aplicando un descuento porcentual.
     *
     * @param cantidadPersonas cantidad de personas
     * @param descuento porcentaje de descuento
     * @return costo total después del descuento
     */
    public double calcular(int cantidadPersonas, double descuento) {
        double total = precioBase * cantidadPersonas;
        return total - (total * descuento / 100.0);
    }
}
