public class Actividad extends Recurso {
    private String guia;
    private int duracion;

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