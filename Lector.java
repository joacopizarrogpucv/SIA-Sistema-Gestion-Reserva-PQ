import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Responsable de cargar desde archivos CSV los datos iniciales
 * utilizados por el sistema.
 */
public class Lector {

    public static Parque leerParque(String nombreArchivo) {
        try {
            BufferedReader archivo = new BufferedReader(new FileReader(nombreArchivo));
            Parque parque = null;
            String linea;

            while ((linea = archivo.readLine()) != null) {
                linea = linea.trim();

                if (linea.equals("") || linea.startsWith("#")) {
                    continue;
                }
                String[] datos = linea.split(";");
                if (datos[0].equalsIgnoreCase("tipo")) {
                    continue;
                }
                if (datos[0].equalsIgnoreCase("PARQUE")) {
                    parque = new Parque(datos[1], datos[4]);
                }
                else if (datos[0].equalsIgnoreCase("CABANA")) {
                    String nombre = datos[1];
                    String id = datos[2];
                    int capacidad = Integer.parseInt(datos[3]);
                    int habitaciones = Integer.parseInt(datos[4]);

                    parque.agregarRecurso(new Cabana(nombre, id, capacidad, habitaciones));
                }

                else if (datos[0].equalsIgnoreCase("CAMPING")) {
                    String nombre = datos[1];
                    String id = datos[2];
                    int capacidad = Integer.parseInt(datos[3]);
                    boolean piscina = Boolean.parseBoolean(datos[4]);

                    parque.agregarRecurso(new Camping(nombre, id, capacidad, piscina));
                }

                else if (datos[0].equalsIgnoreCase("ACTIVIDAD")) {
                    String nombre = datos[1];
                    String id = datos[2];
                    int capacidad = Integer.parseInt(datos[3]);
                    String guia = datos[4];
                    int duracion = Integer.parseInt(datos[5]);

                    parque.agregarRecurso(new Actividad(nombre, id, capacidad, guia, duracion));
                }
            }
            archivo.close();
            return parque;

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + nombreArchivo);
            return null;

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            return null;
        }
    }

    /**
     * Carga los usuarios almacenados en un archivo CSV.
     *
     * @param nombreArchivo nombre del archivo a leer
     * @param parque parque donde se registrarán los usuarios
     */
    public static void leerUsuarios(String nombreArchivo, Parque parque) {
        try {
            BufferedReader archivo = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = archivo.readLine()) != null) {
                linea = linea.trim();
                if (linea.equals("") || linea.startsWith("#")) {
                    continue;
                }
                String[] datos = linea.split(";");
                if (datos[0].equalsIgnoreCase("tipo")) {
                    continue;
                }
                if (datos[0].equalsIgnoreCase("USUARIO")) {
                    String nombre = datos[1];
                    String rut = datos[2];
                    Usuario usuario = new Usuario(nombre, rut);
                    parque.agregarUsuario(usuario);
                }
            }

            archivo.close();

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    public static void leerReservas(String nombreArchivo, Parque parque) {
        try {
            BufferedReader archivo = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = archivo.readLine()) != null) {
                linea = linea.trim();
                if (linea.equals("") || linea.startsWith("#")) {
                    continue;
                }

                String[] datos = linea.split(";");
                if (datos[0].equalsIgnoreCase("rut")) {
                    continue;
                }
                if (datos.length < 3) {
                    System.out.println("Fila ignorada (formato inválido): " + linea);
                    continue;
                }

                String rut = datos[0].trim();
                String idRecurso = datos[1].trim();
                int cantidad;
                try {
                    cantidad = Integer.parseInt(datos[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Fila ignorada (cantidad inválida): " + linea);
                    continue;
                }

                Usuario usuario = parque.buscarUsuario(rut);
                Recurso recurso = parque.buscarRecurso(idRecurso);
                boolean permisoAprobado = false;

                if (datos.length >= 4) {
                    permisoAprobado = Boolean.parseBoolean(datos[3]);
                }

                if (usuario != null && recurso != null && cantidad > 0) {
                    usuario.realizarReserva(recurso, cantidad, permisoAprobado);
                }
            }
            archivo.close();

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }
}