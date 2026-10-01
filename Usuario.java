import java.util.Map;
import java.util.HashMap;

public class Usuario {
    private String nombre;
    private String rut;
    private int cantidad;

    private Map<String, Recurso> reservas;

    public Usuario(String n, String r, int c) {
        nombre = n;
        rut = r;
        cantidad = c;
        reservas = new HashMap<String, Recurso>();
    }
    public String getNombre() {
        return nombre;
    }
    public String getRut() {
        return rut;
    }
    public int getCantidad() {
        return cantidad;
    }
    public boolean realizarReserva(Recurso r) {
        if (reservas.containsKey(r.getId())) {
            return false;
        }

        reservas.put(r.getId(), r);
        return true;
    }
    public Recurso buscarReserva(String id) {
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
}