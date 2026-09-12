public class Tarifa {
    private double precio;

    public Tarifa(double p) {
        precio = p;
    }

    public double calcular(Recurso recurso) {
        // Las actividades tienen un precio fijo.
        // Cabañas y campings multiplican por su capacidad.
        if (recurso instanceof Actividad) {
            return precio;
        }

        return precio * recurso.getCapacidad();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double p) {
        precio = p;
    }

}
