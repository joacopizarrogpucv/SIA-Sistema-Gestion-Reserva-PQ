import java.util.Map;
import java.util.HashMap;

/**
 * Representa a un usuario del sistema.
 * Cada usuario mantiene su propia colección de reservas,
 * identificadas mediante el ID de la reserva.
 */
public class Usuario {
    private String nombre;
    private String rut;
    private Map<String, Reserva> reservas;

    public Usuario(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
        this.reservas = new HashMap<String, Reserva>();
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
    public void setRut(String rut) {
        this.rut = rut;
    }

    public Map<String, Reserva> getReservasMap() {
        return reservas;
    }
    public void setReservasMap(Map<String, Reserva> reservas) {
        this.reservas = reservas;
    }

    public Iterable<Reserva> getReservas() {
        return reservas.values();
    }

    /**
     * Crea una nueva reserva para el usuario.
     *
     * @param recurso recurso que será reservado
     * @param cantidad número de personas
     * @return true si la reserva pudo ser creada
     */
    public boolean realizarReserva(Recurso recurso, int cantidad) {
        return realizarReserva(recurso, cantidad, false);
    }

    /**
     * Crea una reserva permitiendo indicar el estado inicial del permiso.
     * Esta versión es utilizada principalmente durante la carga desde archivo.
     *
     * @param recurso recurso reservado
     * @param cantidad número de personas
     * @param permisoAprobado estado inicial del permiso
     * @return true si la reserva pudo ser creada
     */
    public boolean realizarReserva(Recurso recurso, int cantidad, boolean permisoAprobado) {
        Reserva R = new Reserva(cantidad, recurso, new Tarifa(200), true);
        
        R.getPermiso().setAprobado(permisoAprobado);
        
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