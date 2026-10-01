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
        System.out.println("Id: " + getId());
        System.out.println("Guia: " + guia);
        System.out.println("Duración: " + duracion);
    }
}