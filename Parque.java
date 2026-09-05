import java.util.Map;
import java.util.HashMap;

public class Parque {
    //variables
    private String nombre;
    private String ubicacion;
    private Map<String, Camping> campings;
    private Map<String, Cabana> cabanas;
    private Map<String, Actividad> actividades;
    private Map<String, Reserva> reservas;
    //constructor
    public Parque(String n, String u) {

        nombre = n;
        ubicacion = u;

        campings = new HashMap<String, Camping>();
        cabanas = new HashMap<String, Cabana>();
        actividades = new HashMap<String, Actividad>();
        reservas = new HashMap<String, Reserva>();
    }
    //setter y getter
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
    //metodos camping
    public boolean agregarCamping(Camping c) {

        if (campings.containsKey(c.getNombre())) {
            return false;
        }

        campings.put(c.getNombre(), c);
        return true;
    }
    public Camping buscarCamping(String nombre) {
        return campings.get(nombre);
    }
    //metodos cabaña
    public boolean agregarCabana(Cabana c) {

        if (cabanas.containsKey(c.getNombre())) {
            return false;
        }

        cabanas.put(c.getNombre(), c);
        return true;
    }
    public Cabana buscarCabana(String nombre) {
        return cabanas.get(nombre);
    }
    //metodos actividad
    public boolean agregarActividad(Actividad a) {

        if (actividades.containsKey(a.getNombre())) {
            return false;
        }

        actividades.put(a.getNombre(), a);
        return true;
    }
    public Actividad buscarActividad(String nombre) {
        return actividades.get(nombre);
    }
    //metodos reserva
    public boolean agregarReserva(Reserva r) {

        if (reservas.containsKey(r.getId())) {
            return false;
        }

        reservas.put(r.getId(), r);
        return true;
    }
    public Reserva buscarReserva(String id) {
        return reservas.get(id);
    }
    public boolean cancelarReserva(String id) {
        if (!reservas.containsKey(id)) {
            return false;
        }

        reservas.remove(id);
        return true;
    }
}