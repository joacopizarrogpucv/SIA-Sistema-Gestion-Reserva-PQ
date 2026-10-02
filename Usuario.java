import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public boolean realizarReserva(Recurso r, int cantidad) {
        Reserva R = new Reserva(cantidad, r, new Tarifa(200), true);
        reservas.put(R.getId(), R);
        return true;
    }

    public Reserva buscarReserva(String id) {
        return reservas.get(id);
    }

    public Reserva buscarReserva(Recurso recurso) {
        for (Reserva reserva : reservas.values()) {
            if (reserva.getRecurso().getId().equals(recurso.getId())) {
                return reserva;
            }
        }
        return null;
    }

    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("El usuario no tiene reservas.");
            return;
        }

        for (Reserva reserva : reservas.values()) {
            reserva.mostrarReserva();
            System.out.println("--------------------");
        }
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

    public void mostrarUsuario(){
        System.out.println("Nombre: " + nombre);
        System.out.println("RUT: " + rut);
    }

    public boolean aprobarPermiso(String idReserva) {
        Reserva reserva = reservas.get(idReserva);
        if (reserva == null) {
            return false;
        }

        reserva.getPermiso().aprobar();
        return true;
    }

    public boolean rechazarPermiso(String idReserva) {
        Reserva reserva = reservas.get(idReserva);
        if (reserva == null) {
            return false;
        }

        reserva.getPermiso().rechazar();
        return true;
    }
}