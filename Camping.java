/**
 * Representa un camping disponible dentro del parque.
 * Registra su capacidad y si posee servicio de piscina.
 */
public class Camping extends Recurso {
    private boolean piscina;

    /**
     * Crea un nuevo camping.
     *
     * @param nombre nombre del camping
     * @param id identificador del recurso
     * @param capacidad cantidad máxima de personas
     * @param piscina indica si posee piscina
     */
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
    
    /**
     * Genera una descripción con los datos del camping.
     *
     * @return descripción textual del camping
     */
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