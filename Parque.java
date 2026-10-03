import java.util.Map;
import java.util.HashMap;
/**
 * Clase principal del dominio del sistema.
 * Mantiene las colecciones de recursos y usuarios registrados.
 */
public class Parque {
    private String nombre;
    private String ubicacion;
    private  Map<String, Recurso> recursos;
    private  Map<String, Usuario> usuarios;

    public Parque(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.recursos = new HashMap<String, Recurso>();
        this.usuarios = new HashMap<String, Usuario>();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Map<String, Recurso> getMapaRecursos() {
        return recursos;
    }
    public void setRecursos(Map<String, Recurso> recursos) {
        this.recursos = recursos;
    }

    public Map<String, Usuario> getMapaUsuarios() {
        return usuarios;
    }
    public void setUsuarios(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Iterable<Usuario> getUsuarios() {
        return usuarios.values();
    }

    public Iterable<Recurso> getRecursos() {
        return recursos.values();
    }

    public boolean agregarRecurso(Recurso r) {
        if (recursos.containsKey(r.getId())) {
            return false;
        }
        recursos.put(r.getId(), r);
        return true;
    }
    public boolean agregarUsuario(Usuario u) {
        if (usuarios.containsKey(u.getRut())) {
            return false;
        }
        usuarios.put(u.getRut(), u);
        return true;
    }

    public Recurso buscarRecurso(String id) {
        return recursos.get(id);
    }

    public Usuario buscarUsuario(String rut) {
        return usuarios.get(rut);
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        for (Usuario usuario : usuarios.values()) {
            usuario.mostrarUsuario();
            System.out.println("--------------------");
        }
    }

    public boolean eliminarUsuario(String rut) {
        if (!usuarios.containsKey(rut)) {
            return false;
        }
        usuarios.remove(rut);
        return true;
    }

    /**
     * Muestra los recursos cuya capacidad permite recibir
     * al número de personas indicado.
     *
     * @param cantidadPersonas tamaño del grupo
     */
    public void mostrarRecursosPorCapacidad(int cantidadPersonas) {
        boolean encontrado = false;

        System.out.println("Recursos disponibles para " + cantidadPersonas + " personas:");

        for (Recurso recurso : recursos.values()) {
            if (recurso.hayDisponibilidad(cantidadPersonas)) {
                recurso.mostrarRecurso();
                System.out.println("--------------------");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay recursos disponibles con capacidad suficiente.");
        }
    }
}
