import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException {

        // Primero se lee el archivo .csv completo
        Parque parque = leerArchivo("parque.csv");
        if (parque == null) {
            System.out.println("No se pudo cargar el archivo parque.csv.");
            return;
        }

        // Luego se crea el menú de consola y se inicia
        MenuConsola menu = new MenuConsola(parque);
        menu.iniciar();
    }

    public static Parque leerArchivo(String nombreArchivo) {
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
                if (datos[0].equals("PARQUE")) {
                    parque = new Parque(datos[1], datos[3]);
                }
                else if (datos[0].equals("CABANA")) {
                    String nombre = datos[1];
                    String id = datos[2];
                    int capacidad = Integer.parseInt(datos[3]);
                    int habitaciones = Integer.parseInt(datos[4]);
                    parque.agregarInstalacion(new Cabana(nombre, id, capacidad, habitaciones));
                }
                else if (datos[0].equals("CAMPING")) {
                    String nombre = datos[1];
                    String id = datos[2];
                    int capacidad = Integer.parseInt(datos[3]);
                    boolean picina = Boolean.parseBoolean(datos[4]);
                    parque.agregarInstalacion(new Camping(nombre, id, capacidad, picina));
                }
                else if (datos[0].equals("ACTIVIDAD")) {
                    String nombre = datos[1];
                    String id = datos[2];
                    int capacidad = Integer.parseInt(datos[3]);
                    String guia = datos[4];
                    int duracion = Integer.parseInt(datos[5]);
                    parque.agregarInstalacion(new Actividad(nombre, id,capacidad,guia,duracion));
                }
            }
            archivo.close();
            return parque;
        } 
        catch (FileNotFoundException e) {
                System.out.println("Archivo no encontrado: " + nombreArchivo);
                return null;
        } 
        catch (IOException e) {
                System.out.println("Error al leer el archivo.");
                return null;
        }
    }
}