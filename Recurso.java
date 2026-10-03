public abstract class Recurso {
    private String nombre;
    private String Id;
    private int capacidad;
    public Recurso(String n, String i, int c) {
        nombre = n;
        Id = i;
        capacidad = c;
    }
    public String getNombre() {
        return nombre;
    }
    public String getId() {
        return Id;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public boolean hayDisponibilidad(int cantidad) {
        return cantidad <= capacidad;
    }

    public abstract void mostrarRecurso();
}