public abstract class Recurso {
    //variables
    private String nombre;
    private int capacidad;
    //constructor
    public Recurso(String n, int c) {
        nombre = n;
        capacidad = c;
    }
    //getter
    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }
    //metodo
    public boolean hayDisponibilidad(int cantidad) {
        return cantidad <= capacidad;
    }
}