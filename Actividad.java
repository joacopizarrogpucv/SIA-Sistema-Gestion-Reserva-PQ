/**
 * Representa una actividad guiada ofrecida por el parque.
 * Almacena el guía responsable y la duración de la actividad.
 */
public class Actividad extends Recurso {
    private String guia;
    private int duracion;

    /**
     * Crea una nueva actividad.
     *
     * @param nombre nombre de la actividad
     * @param id identificador del recurso
     * @param capacidad cantidad máxima de participantes
     * @param guia nombre del guía responsable
     * @param duracion duración de la actividad
     */
    public Actividad(String nombre, String id, int capacidad, String guia, int duracion) {
        super(nombre, id, capacidad);
        this.guia = guia;
        this.duracion = duracion;
    }

    public String getGuia() {
        return guia;
    }
    public void setGuia(String guia) {
        this.guia = guia;
    }

    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Genera una descripción con los datos de la actividad.
     *
     * @return descripción textual de la actividad
     */
    @Override
    public String obtenerDescripcion() {
        return "Nombre: " + getNombre() + "\n" +
               "ID: " + getId() + "\n" +
               "Capacidad: " + getCapacidad() + " personas\n" +
               "Guía: " + guia + "\n" +
               "Duración: " + duracion + " horas";
    }

    @Override
    public void mostrarRecurso(){
        System.out.println(obtenerDescripcion());
    }
}