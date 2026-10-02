import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws IOException {

        // Primero se lee el archivo .csv completo
        Parque parque = leerArchivo("parque.csv");
        if (parque == null) {
            System.out.println("No se pudo cargar el archivo parque.csv.");
            return;
        }
        // BufferedReader para leer datos desde la consola
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Usuario usuarioActual = null;
        int opcion;

        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("======================================");
            System.out.println("      BIENVENIDO AL PARQUE PUCV");
            System.out.println("======================================");
            System.out.println("1. Reservar");
            System.out.println("2. Cancelar Reserva");
            System.out.println("3. Buscar Reserva");
            System.out.println("4. Crear Usuario de forma manual");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(br.readLine());
            switch (opcion) {
                case 1:
                    if (usuarioActual == null) {
                        System.out.println("Primero debe crear un usuario.");
                        continuar(br);
                        break;
                    }
                
                    System.out.print("ID del recurso que desea reservar: ");
                    String idReserva = br.readLine();
                
                    Recurso recurso = parque.buscarRecurso(idReserva);

                    if (recurso == null) {
                        System.out.println("No existe ese recurso.");
                    }
                    else if (!recurso.hayDisponibilidad(usuarioActual.getCantidad())) {
                        System.out.println("No existe capacidad suficiente.");
                    }
                    else if (usuarioActual.realizarReserva(recurso)) {
                        System.out.println("Reserva realizada correctamente.");
                    }
                    else {
                        System.out.println("El usuario ya tiene reservado ese recurso.");
                    }
                
                    continuar(br);
                    break;
                
                case 2:
                    if (usuarioActual == null) {
                        System.out.println("Primero debe crear un usuario.");
                        continuar(br);
                        break;
                    }
                
                    System.out.print("ID del recurso a cancelar: ");
                    String idCancelar = br.readLine();
                                
                    if (usuarioActual.cancelarReserva(idCancelar)) {
                        System.out.println("Reserva cancelada correctamente.");
                    }
                    else {
                        System.out.println("No se encontró esa reserva.");
                    }
                
                    continuar(br);
                    break;
                case 3:
                    do {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("1. Cabaña");
                        System.out.println("2. Camping");
                        System.out.println("3. Actividad");
                        System.out.println("4. Atrás");
                        System.out.print("Opcion: ");
                        opcion = Integer.parseInt(br.readLine());
                        String ID;
                        Recurso x;
                        switch (opcion){
                            case 1:
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Ingresa el Id de la cabana");
                                ID = (br.readLine());
                                x = parque.buscarCabana(ID);
                                if(x == null){
                                   System.out.println("Id incorrecto");
                                }
                                else {
                                    System.out.println("Cabana encontrada");
                                    x.mostrarRecurso();
                                }
                                continuar(br);
                                break;
                            case 2:
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            
                                System.out.println("Ingresa el Id del Camping");
                                ID = br.readLine();
                            
                                x = parque.buscarCamping(ID);
                            
                                if (x == null) {
                                    System.out.println("Id incorrecto");
                                }
                                else {
                                    System.out.println("Camping encontrado");
                                    x.mostrarRecurso();
                                }
                            
                                continuar(br);
                                break;
                            case 3:
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Ingresa el Id de la Actividad");
                                ID = (br.readLine());
                                x = parque.buscarActividad(ID);
                                if(x == null){
                                   System.out.println("Id incorrecto");
                                }
                                else {
                                    System.out.println("Actividad encontrada");
                                    x.mostrarRecurso();
                                }
                                continuar(br);
                                break;
                            case 4:
                                break;
                            default:
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Opción no válida.");
                                break;
                        }
                    } while (opcion != 4);
                        break;
                case 4:
                    System.out.println("--- CREAR USUARIO ---");
                
                    System.out.print("Nombre: ");
                    String nombre = br.readLine();

                    System.out.print("RUT: ");
                    String rut = br.readLine();
                    
                    System.out.print("Cantidad de personas: ");
                    int cantidad = Integer.parseInt(br.readLine());
                    
                    usuarioActual = new Usuario(nombre, rut, cantidad);
                    
                    System.out.println("Usuario creado correctamente.");
                    continuar(br);
                    break;
                
                case 5:
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (opcion != 5);
    }
    public static void continuar(BufferedReader br) throws IOException {
    System.out.println("Presiona ENTER para continuar...");
    br.readLine();
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