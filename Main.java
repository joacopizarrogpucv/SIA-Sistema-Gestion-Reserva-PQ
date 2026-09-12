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

        System.out.println("======================================");
        System.out.println("      BIENVENIDO AL PARQUE PUCV");
        System.out.println("======================================");

        boolean salir = false;

        while (!salir) {

            System.out.println("\n¿Que servicio quiere?");
            System.out.println("1. Reservar");
            System.out.println("2. Cancelar Reserva");
            System.out.println("3. Buscar Reserva");
            System.out.println("4. Crear Usuario de forma manual");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");

            int opcion = Integer.parseInt(br.readLine());

            if (opcion == 1) {

                if (usuarioActual == null) {
                    System.out.println("Primero debe crear un usuario.");
                    continue;
                }

                System.out.println("\n--- RESERVAR ---");
                System.out.println("1. Cabana");
                System.out.println("2. Camping");
                System.out.println("3. Actividad");
                System.out.print("Tipo: ");

                int tipo = Integer.parseInt(br.readLine());

                System.out.print("Nombre del recurso: ");
                String nombre = br.readLine();

                Recurso recurso = null;
                Tarifa tarifa = null;

                if (tipo == 1) {
                    recurso = parque.buscarCabana(nombre);
                    tarifa = new Tarifa(100.0);
                }

                else if (tipo == 2) {
                    recurso = parque.buscarCamping(nombre);
                    tarifa = new Tarifa(50.0);
                }

                else if (tipo == 3) {
                    recurso = parque.buscarActividad(nombre);
                    tarifa = new Tarifa(30.0);
                }

                else {
                    System.out.println("Tipo invalido.");
                    continue;
                }

                if (recurso == null) {
                    System.out.println("No existe ese recurso.");
                    continue;
                }

                System.out.print("ID de la reserva: ");
                String id = br.readLine();

                boolean creada = false;
                Reserva reserva = null;

                if (tipo == 1 || tipo == 2) {

                    System.out.print("Mes de inicio: ");
                    int mesI = Integer.parseInt(br.readLine());

                    System.out.print("Dia de inicio: ");
                    int diaI = Integer.parseInt(br.readLine());

                    System.out.print("Mes de fin: ");
                    int mesF = Integer.parseInt(br.readLine());

                    System.out.print("Dia de fin: ");
                    int diaF = Integer.parseInt(br.readLine());

                    if (!fechaValida(mesI, diaI) ||
                        !fechaValida(mesF, diaF)) {

                        System.out.println("Fecha invalida.");
                        continue;
                    }

                    if (mesF < mesI ||
                        (mesF == mesI && diaF < diaI)) {

                        System.out.println("El periodo es invalido.");
                        continue;
                    }

                    reserva = new Reserva(
                            id, usuarioActual, recurso, tarifa,
                            mesI, diaI, mesF, diaF
                    );

                    creada = parque.agregarReserva(reserva);
                }

                else {

                    System.out.print("Mes: ");
                    int mes = Integer.parseInt(br.readLine());

                    System.out.print("Dia: ");
                    int dia = Integer.parseInt(br.readLine());

                    System.out.print("Hora de inicio: ");
                    int horaI = Integer.parseInt(br.readLine());

                    System.out.print("Minuto de inicio: ");
                    int minI = Integer.parseInt(br.readLine());

                    System.out.print("Hora de fin: ");
                    int horaF = Integer.parseInt(br.readLine());

                    System.out.print("Minuto de fin: ");
                    int minF = Integer.parseInt(br.readLine());

                    if (!fechaValida(mes, dia) ||
                        !horaValida(horaI, minI) ||
                        !horaValida(horaF, minF) ||
                        horaF * 60 + minF <= horaI * 60 + minI) {

                        System.out.println("Fecha u horario invalido.");
                        continue;
                    }

                    reserva = new Reserva(
                            id, usuarioActual, recurso, tarifa,
                            mes, dia, horaI, minI, horaF, minF
                    );

                    creada = parque.agregarReserva(reserva);
                }

                if (creada && usuarioActual.realizarReserva(reserva)) {

                    System.out.println("\nReserva creada correctamente.");
                    mostrarReserva(reserva);
                }

                else {
                    parque.cancelarReserva(id);
                    System.out.println("No se pudo crear la reserva.");
                }
            }

            else if (opcion == 2) {

                if (usuarioActual == null) {
                    System.out.println("Primero debe crear un usuario.");
                    continue;
                }

                System.out.println("\n--- CANCELAR RESERVA ---");
                System.out.println("1. Cabana");
                System.out.println("2. Camping");
                System.out.println("3. Actividad");
                System.out.print("Tipo: ");

                int tipo = Integer.parseInt(br.readLine());

                System.out.print("ID de la reserva: ");
                String id = br.readLine();

                Reserva r = usuarioActual.buscarReserva(id);

                if (r == null) {
                    System.out.println(
                            "La reserva no pertenece al usuario actual."
                    );
                    continue;
                }

                if (r.getRecurso() instanceof Cabana && tipo != 1 ||
                    r.getRecurso() instanceof Camping && tipo != 2 ||
                    r.getRecurso() instanceof Actividad && tipo != 3) {

                    System.out.println(
                            "El tipo seleccionado no coincide con la reserva."
                    );
                    continue;
                }

                if (parque.cancelarReserva(id) &&
                    usuarioActual.cancelarReserva(id)) {

                    System.out.println(
                            "Reserva cancelada correctamente."
                    );
                }

                else {
                    System.out.println("No se pudo cancelar.");
                }
            }

            else if (opcion == 3) {

                if (usuarioActual == null) {
                    System.out.println("Primero debe crear un usuario.");
                    continue;
                }

                System.out.println("\n--- BUSCAR RESERVA ---");
                System.out.println("1. Cabana");
                System.out.println("2. Camping");
                System.out.println("3. Actividad");
                System.out.print("Tipo: ");

                int tipo = Integer.parseInt(br.readLine());

                System.out.print("ID de la reserva: ");
                String id = br.readLine();

                Reserva r = usuarioActual.buscarReserva(id);

                if (r == null) {
                    System.out.println(
                            "No existe esa reserva para el usuario."
                    );
                    continue;
                }

                if (r.getRecurso() instanceof Cabana && tipo != 1 ||
                    r.getRecurso() instanceof Camping && tipo != 2 ||
                    r.getRecurso() instanceof Actividad && tipo != 3) {

                    System.out.println(
                            "El tipo seleccionado no coincide con la reserva."
                    );
                    continue;
                }

                mostrarReserva(r);
            }

            else if (opcion == 4) {

                System.out.println("\n--- CREAR USUARIO ---");

                System.out.print("Nombre: ");
                String nombre = br.readLine();

                System.out.print("RUT: ");
                String rut = br.readLine();

                usuarioActual = new Usuario(nombre, rut);

                System.out.println("Usuario creado correctamente.");
                System.out.println("Ahora puede realizar reservas.");
            }

            else if (opcion == 5) {

                salir = true;
                System.out.println(
                        "Gracias por visitar el Parque PUCV."
                );
            }

            else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    // Lee todos los datos del archivo .csv
    public static Parque leerArchivo(String nombreArchivo) {

        try {
            BufferedReader archivo =
                    new BufferedReader(new FileReader(nombreArchivo));

            Parque parque = null;
            String linea;

            while ((linea = archivo.readLine()) != null) {

                linea = linea.trim();

                if (linea.equals("") || linea.startsWith("#")) {
                    continue;
                }

                String[] datos = linea.split(";");

                // Se ignora la fila de encabezado del archivo CSV
                if (datos[0].equalsIgnoreCase("tipo")) {
                    continue;
                }

                if (datos[0].equals("PARQUE")) {

                    parque = new Parque(datos[1], datos[3]);
                }

                else if (datos[0].equals("CABANA")) {

                    String nombre = datos[1];
                    int capacidad = Integer.parseInt(datos[2]);
                    int habitaciones = Integer.parseInt(datos[3]);

                    parque.agregarCabana(
                            new Cabana(nombre, capacidad, habitaciones)
                    );
                }

                else if (datos[0].equals("CAMPING")) {

                    String nombre = datos[1];
                    int capacidad = Integer.parseInt(datos[2]);
                    boolean agua =
                            Boolean.parseBoolean(datos[3]);

                    parque.agregarCamping(
                            new Camping(nombre, capacidad, agua)
                    );
                }

                else if (datos[0].equals("ACTIVIDAD")) {

                    String nombre = datos[1];
                    int capacidad = Integer.parseInt(datos[2]);
                    String guia = datos[3];
                    int duracion = Integer.parseInt(datos[4]);

                    parque.agregarActividad(
                            new Actividad(
                                    nombre,
                                    capacidad,
                                    guia,
                                    duracion
                            )
                    );
                }
            }

            archivo.close();
            return parque;

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Archivo no encontrado: " + nombreArchivo
            );

            return null;

        } catch (IOException e) {

            System.out.println(
                    "Error al leer el archivo."
            );

            return null;
        }
    }

    public static boolean fechaValida(int mes, int dia) {

        if (mes < 1 || mes > 12 || dia < 1) {
            return false;
        }

        if (mes == 2) {
            return dia <= 28;
        }

        if (mes == 4 || mes == 6 ||
            mes == 9 || mes == 11) {

            return dia <= 30;
        }

        return dia <= 31;
    }

    public static boolean horaValida(int hora, int minuto) {

        return hora >= 0 &&
               hora <= 23 &&
               minuto >= 0 &&
               minuto <= 59;
    }

    public static void mostrarReserva(Reserva r) {

        System.out.println("\n========== RESERVA ==========");
        System.out.println("ID: " + r.getId());
        System.out.println(
                "Usuario: " + r.getUsuario().getNombre()
        );
        System.out.println(
                "Recurso: " + r.getRecurso().getNombre()
        );
        System.out.println(
                "Capacidad: " + r.getRecurso().getCapacidad()
        );

        if (r.getRecurso() instanceof Actividad) {

            System.out.println(
                    "Fecha: " +
                    r.getDiaInicio() +
                    "/" +
                    r.getMesInicio()
            );

            System.out.printf(
                    "Horario: %02d:%02d - %02d:%02d%n",
                    r.getMinutoInicio() / 60,
                    r.getMinutoInicio() % 60,
                    r.getMinutoFin() / 60,
                    r.getMinutoFin() % 60
            );
        }

        else {

            System.out.println(
                    "Fecha inicio: " +
                    r.getDiaInicio() +
                    "/" +
                    r.getMesInicio()
            );

            System.out.println(
                    "Fecha fin: " +
                    r.getDiaFin() +
                    "/" +
                    r.getMesFin()
            );

            System.out.println(
                    "Cantidad de dias: " +
                    r.calcularDias()
            );
        }

        System.out.println(
                "Costo: $" + r.calcularCosto()
        );

        System.out.println("=============================");
    }
}