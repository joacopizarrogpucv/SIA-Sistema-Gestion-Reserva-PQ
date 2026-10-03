/**
 * Representa un recurso disponible dentro del parque.
 * Define los datos comunes de cabañas, campings y actividades,
 * además de las operaciones que cada tipo de recurso debe implementar.
 */
public abstract class Recurso {
    private String nombre;
    private String id;
    private int capacidad;

    
    public Recurso(String nombre, String id, int capacidad) {
        this.nombre = nombre;
        this.id = id;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Comprueba si el recurso posee capacidad suficiente para un grupo.
     *
     * @param cantidad cantidad de personas del grupo
     * @return true si la cantidad es válida y no supera la capacidad
     */
    public boolean hayDisponibilidad(int cantidad) {
        return cantidad <= capacidad && cantidad > 0;
    }
    
    /**
     * Muestra los datos del recurso por consola.
     */
    public abstract void mostrarRecurso();

    /**
     * Genera una descripción textual del recurso.
     *
     * @return descripción del recurso
     */
    public abstract String obtenerDescripcion();
}