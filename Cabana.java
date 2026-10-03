/**
 * Representa una cabaña disponible como recurso del parque.
 * Además de los datos generales de un recurso, almacena
 * la cantidad de habitaciones disponibles.
 */
public class Cabana extends Recurso {
    private int habitaciones;

    /**
     * Crea una nueva cabaña.
     *
     * @param nombre nombre de la cabaña
     * @param id identificador del recurso
     * @param capacidad cantidad máxima de personas
     * @param habitaciones cantidad de habitaciones
     */
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
    
    /**
     * Genera una descripción con los datos de la cabaña.
     *
     * @return descripción textual de la cabaña
     */
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