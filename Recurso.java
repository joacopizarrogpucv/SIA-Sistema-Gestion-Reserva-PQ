public abstract class Recurso {

    // Variables
    private String nombre;
    private int capacidad;
    private int ocupacion;

    // Constructor
    public Recurso(String n, int c) {
        nombre = n;
        capacidad = c;
        ocupacion = 0;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    // Metodos
    public boolean hayDisponibilidad(int cantidad) {
        return ocupacion + cantidad <= capacidad;
    }

    public boolean reservar(int cantidad) {

        if (!hayDisponibilidad(cantidad)) {
            return false;
        }

        ocupacion += cantidad;
        return true;
    }

    public void liberar(int cantidad) {

        ocupacion -= cantidad;

        if (ocupacion < 0) {
            ocupacion = 0;
        }
    }
}