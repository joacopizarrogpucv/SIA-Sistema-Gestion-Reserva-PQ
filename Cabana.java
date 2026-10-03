public class Cabana extends Recurso {
    private int habitaciones;

    public Cabana(String nombre, String id, int capacidad, int habitaciones) {
        super(nombre, id, capacidad);
        this.habitaciones = habitaciones;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }
    
    @Override
    public String obtenerDescripcion() {
        return "Nombre: " + getNombre() + "\n" +
               "ID: " + getId() + "\n" +
               "Capacidad: " + getCapacidad() + " personas\n" +
               "Habitaciones: " + habitaciones;
    }
    
    @Override
    public void mostrarRecurso(){
        System.out.println(obtenerDescripcion());
    }
}