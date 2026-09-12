import java.util.Map;
import java.util.HashMap;

public class Usuario {
    private String nombre;
    private String rut;
    private Map<String, Reserva> reservas;

    public Usuario(String n, String r) {
        nombre = n;
        rut = r;
        reservas = new HashMap<String, Reserva>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public boolean realizarReserva(Reserva r) {
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