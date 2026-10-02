import java.util.Map;
import java.util.HashMap;

public class Usuario {
    private String nombre;
    private String rut;
    private int cantidad;

    private Map<String, Reserva> reservas;

    public Usuario(String n, String r, int c) {
        nombre = n;
        rut = r;
        cantidad = c;
        reservas = new HashMap<String, Reserva>();
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
    //RESERVA
    public boolean realizarReserva(Recurso r) {
        if (reservas.containsKey(r.getId())) {
            return false;
        }
        Reserva R = new Reserva(cantidad, r, new Tarifa(200), true);
        reservas.put(R.getId(), R);
        R.mostrarReserva();

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
    public void mostrarUsuario(){
        System.out.println("Nombre: " + nombre);
        System.out.println("RUT: " + rut);
        System.out.println("Tamaño de grupo: " + cantidad);
    }
}