import java.util.Map;
import java.util.HashMap;

public class Parque {
    private String nombre;
    private String ubicacion;

    private final Map<String, Camping> campings;
    private final Map<String, Cabana> cabanas;
    private final Map<String, Actividad> actividades;

    public Parque(String n, String u) {
        nombre = n;
        ubicacion = u;

        campings = new HashMap<String, Camping>();
        cabanas = new HashMap<String, Cabana>();
        actividades = new HashMap<String, Actividad>();
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
    public boolean agregarInstalacion(Recurso c) {
        if(c instanceof Camping) {
            if (campings.containsKey(c.getId())) {
            return false;
            }
            campings.put(c.getId(), (Camping)c);
            return true;
        }
        else if(c instanceof Cabana) {
            if (cabanas.containsKey(c.getId())) {
            return false;
            }
            cabanas.put(c.getId(), (Cabana)c);
            return true;
        }
        else{
            if (actividades.containsKey(c.getId())) {
            return false;
            }
            actividades.put(c.getId(), (Actividad)c);
            return true;
        }
    }

    public Recurso buscarRecurso(String id) {
        Recurso recurso = cabanas.get(id);

        if (recurso != null) {
            return recurso;
        }

        recurso = campings.get(id);

        if (recurso != null) {
            return recurso;
        }

        return actividades.get(id);
    }
    
    public Camping buscarCamping(String id) {
        return campings.get(id);
    }
    public Cabana buscarCabana(String id) {
        return cabanas.get(id);
    }
    public Actividad buscarActividad(String id) {
        return actividades.get(id);
    }
}
