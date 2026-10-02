import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Escritor {
    public static void guardarUsuarios(String nombreArchivo, Parque parque) throws IOException {
        try (PrintWriter archivo = new PrintWriter(new FileWriter(nombreArchivo))) {
            archivo.println("tipo;nombre;rut");
            for (Usuario usuario : parque.getUsuarios()) {
                archivo.println("USUARIO;" + usuario.getNombre() + ";" + usuario.getRut());
            }
        }
    }

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
