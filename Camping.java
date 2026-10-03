public class Camping extends Recurso {
    private boolean piscina;

    public Camping(String nombre, String id, int capacidad, boolean piscina) {
        super(nombre, id, capacidad);
        this.piscina = piscina;
    }

    public boolean getPiscina() {
        return piscina;
    }
    public void setPiscina(boolean piscina) {
        this.piscina = piscina;
    }
    
    @Override
    public String obtenerDescripcion() {
        return "Nombre: " + getNombre() + "\n" +
               "ID: " + getId() + "\n" +
               "Capacidad: " + getCapacidad() + " personas\n" +
               (piscina ? "Tiene servicio de Piscina" : "No tiene servicio de Piscina");
    }
    
    @Override
    public void mostrarRecurso(){
        System.out.println(obtenerDescripcion());
    }
}