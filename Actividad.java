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
    public void mostrarRecurso(){
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID: " + getId());
        System.out.println("Capacidad: " + getCapacidad() + " personas");
        System.out.println("Guía: " + guia);
        System.out.println("Duración: " + duracion);
    }
}