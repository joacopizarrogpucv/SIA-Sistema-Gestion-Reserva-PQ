import java.util.Map;
import java.util.HashMap;

public class Parque {
    private String nombre;
    private String ubicacion;

    private  Map<String, Recurso> recursos;
    private  Map<String, Usuario> usuarios;

    public Parque(String n, String u) {
        nombre = n;
        ubicacion = u;

        recursos = new HashMap<String, Recurso>();
        usuarios = new HashMap<String, Usuario>();
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public void setUbicacion(String u) {
        ubicacion = u;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
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

    public void mostrarRecursosporCapacidad(int cantidadPersonas) {
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

    public Iterable<Usuario> getUsuarios() {
        return usuarios.values();
    }

    public Iterable<Recurso> getRecursos() {
        return recursos.values();
    }
}
