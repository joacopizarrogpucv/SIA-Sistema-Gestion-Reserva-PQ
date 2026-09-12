public class Actividad extends Recurso {

    private String guia;
    private int duracion;

    public Actividad(String n, int c, String g, int d) {
        super(n, c);
        guia = g;
        duracion = d;
    }

    public String getGuia() {
        return guia;
    }

    public void setGuia(String g) {
        guia = g;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int d) {
        duracion = d;
    }
}