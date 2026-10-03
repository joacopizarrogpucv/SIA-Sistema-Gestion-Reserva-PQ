import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Responsable de guardar en archivos CSV los cambios realizados
 * durante la ejecución del sistema.
 */
public class Escritor {
    /**
     * Guarda los usuarios actualmente registrados en el parque.
     *
     * @param nombreArchivo archivo CSV de destino
     * @param parque parque cuyos usuarios serán almacenados
     * @throws IOException si ocurre un error durante la escritura
     */
    public static void guardarUsuarios(String nombreArchivo, Parque parque) throws IOException {
        try (PrintWriter archivo = new PrintWriter(new FileWriter(nombreArchivo))) {
            archivo.println("tipo;nombre;rut");
            for (Usuario usuario : parque.getUsuarios()) {
                archivo.println("USUARIO;" + usuario.getNombre() + ";" + usuario.getRut());
            }
        }
    }
    /**
     * Guarda las reservas actualmente registradas en el parque.
     *
     * @param nombreArchivo archivo CSV de destino
     * @param parque parque cuyas reservas serán almacenadas
     * @throws IOException si ocurre un error durante la escritura
     */
    public static void guardarReservas(String nombreArchivo, Parque parque) throws IOException {
        try (PrintWriter archivo = new PrintWriter(new FileWriter(nombreArchivo))) {
            archivo.println("rut;idRecurso;cantidad;permisoAprobado");
            for (Usuario usuario : parque.getUsuarios()) {
                for (Reserva reserva : usuario.getReservas()) {
                    archivo.println(usuario.getRut() + ";" + reserva.getRecurso().getId() + ";" + reserva.getCantidad() + ";" + reserva.getPermiso().estaAprobado());
                }
            }
        }
    }
}
