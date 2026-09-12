import java.util.Map;
import java.util.HashMap;

public class Parque {

    // Variables
    private String nombre;
    private String ubicacion;
    private Map<String, Camping> campings;
    private Map<String, Cabana> cabanas;
    private Map<String, Actividad> actividades;
    private Map<String, Reserva> reservas;

    // Constructor
    public Parque(String n, String u) {

        nombre = n;
        ubicacion = u;

        campings = new HashMap<String, Camping>();
        cabanas = new HashMap<String, Cabana>();
        actividades = new HashMap<String, Actividad>();
        reservas = new HashMap<String, Reserva>();
    }

    // Setter y getter
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

    // Metodos camping
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

    public boolean eliminarCamping(String nombre) {

        if (!campings.containsKey(nombre)) {
            return false;
        }

        campings.remove(nombre);
        return true;
    }

    public int cantidadCampings() {
        return campings.size();
    }

    // Metodos cabana
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

    public boolean eliminarCabana(String nombre) {

        if (!cabanas.containsKey(nombre)) {
            return false;
        }

        cabanas.remove(nombre);
        return true;
    }

    public int cantidadCabanas() {
        return cabanas.size();
    }

    // Metodos actividad
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

    public boolean eliminarActividad(String nombre) {

        if (!actividades.containsKey(nombre)) {
            return false;
        }

        actividades.remove(nombre);
        return true;
    }

    public int cantidadActividades() {
        return actividades.size();
    }

    // Metodos reserva
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

    public int cantidadReservas() {
        return reservas.size();
    }

    // Metodos para comprobar existencia
    public boolean existeCamping(String nombre) {
        return campings.containsKey(nombre);
    }

    public boolean existeCabana(String nombre) {
        return cabanas.containsKey(nombre);
    }

    public boolean existeActividad(String nombre) {
        return actividades.containsKey(nombre);
    }

    public boolean existeReserva(String id) {
        return reservas.containsKey(id);
    }
}