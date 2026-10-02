public class Camping extends Recurso {
    private boolean picina;

    public Camping(String n, String i, int c, boolean x) {
        super(n, i, c);
        picina = x;
    }
    public boolean getPicina() {
        return picina;
    }
    public void setPicina(boolean x) {
        picina = x;
    }
    @Override
    public String obtenerDescripcion() {
        return "Nombre: " + getNombre() + "\n" +
               "ID: " + getId() + "\n" +
               "Capacidad: " + getCapacidad() + " personas\n" +
               (picina ? "Tiene servicio de Picina" : "No tiene servicio de Picina");
    }
    @Override
    public void mostrarRecurso(){
        System.out.println(obtenerDescripcion());
    }
}