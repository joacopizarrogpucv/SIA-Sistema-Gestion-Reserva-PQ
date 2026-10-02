public class Actividad extends Recurso {
    private String guia;
    private int duracion;

    public Actividad(String n, String i, int c, String g, int d) {
        super(n, i, c);
        guia = g;
        duracion = d;
    }
    public String getGuia() {
        return guia;
    }
    public int getDuracion() {
        return duracion;
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