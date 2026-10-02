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
}
