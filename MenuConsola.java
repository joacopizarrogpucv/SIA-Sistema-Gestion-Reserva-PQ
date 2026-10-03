import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class MenuConsola {
    private Parque parque;
    private Usuario usuarioActual;
    private BufferedReader br;

    public MenuConsola(Parque parque) {
        this.parque = parque;
        this.usuarioActual = null;
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void iniciar() throws IOException {

        int opcion;

        do {
            limpiarPantalla();

            System.out.println("======================================");
            System.out.println("      BIENVENIDO AL PARQUE PUCV");
            System.out.println("======================================");
            System.out.println("1. Reservar");
            System.out.println("2. Cancelar Reserva");
            System.out.println("3. Buscar Recurso");
            System.out.println("4. Ingresar / Crear Usuario");
            System.out.println("5. Gestionar Reservas");
            System.out.println("6. Gestionar Usuarios");
            System.out.println("7. Salir");
            System.out.print("Opcion: ");

            opcion = leerEntero();
            try {
                switch (opcion) {
                    case 1:
                        reservar();
                        break;
                    case 2:
                        cancelarReserva();
                        break;
                    case 3:
                        buscarRecurso();
                        break;
                    case 4:
                        crearUsuario();
                        break;
                    case 5:
                        gestionarReservas();
                        break;
                    case 6:
                        gestionarUsuarios();
                        break;
                    case 7:
                        limpiarPantalla();
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        limpiarPantalla();
                        System.out.println("Opción no válida.");
                        continuar();
                        break;
                }
            } catch (CapacidadExcedidaException | ReservaNoEncontradaException e) {
                System.out.println(e.getMessage());
                continuar();
            }
        } while (opcion != 7);
    }

    private void reservar() throws IOException {
    if (usuarioActual == null) {
        System.out.println("Primero debe crear un usuario.");
        continuar();
        return;
    }

    System.out.print("ID del recurso que desea reservar: ");
    String idReserva = br.readLine();

    Recurso recurso = parque.buscarRecurso(idReserva);

    if (recurso == null) {
        System.out.println("No existe ese recurso.");
    }
    else {
        System.out.println("Ingrese la cantidad de personas que van:");
        int cantidad = leerEntero();
        usuarioActual.realizarReserva(recurso, cantidad);
        System.out.println("Reserva realizada correctamente.");
    }
    continuar();
}

    private void cancelarReserva() throws IOException {
        if (usuarioActual == null) {
            System.out.println("Primero debe crear un usuario.");
            continuar();
            return;
        }

        System.out.print("ID de la reserva a cancelar: ");
        String idCancelar = br.readLine();

        if (usuarioActual.cancelarReserva(idCancelar)) {
            System.out.println("Reserva cancelada correctamente.");
        }
        else {
            System.out.println("No se encontró esa reserva.");
        }

        continuar();
    }

    private void buscarRecurso() throws IOException {
        limpiarPantalla();

         System.out.println("Ingrese ID del recurso:");
         String id = br.readLine();
         Recurso recurso = parque.buscarRecurso(id);
         
         if (recurso == null) {
             System.out.println("No existe un recurso con ese ID.");
         } else {
             System.out.println("Recurso encontrado:");
             recurso.mostrarRecurso();
         }

        continuar();
    }

    private void crearUsuario() throws IOException {
        limpiarPantalla();

        System.out.println("Ingrese el RUT del usuario:");
        String rut = br.readLine();

        Usuario usuarioExistente = parque.buscarUsuario(rut);
        if (usuarioExistente != null) {
            usuarioActual = usuarioExistente;
            System.out.println("Usuario existente encontrado y seleccionado.");
            System.out.println("Usuario actual: " + usuarioActual.getNombre());
            continuar();
            return;
        }

        System.out.println("Usuario no encontrado. Se creará un nuevo usuario.");
        
        System.out.println("Ingrese el nombre del usuario:");
        String nombre = br.readLine();

        Usuario nuevoUsuario = new Usuario(nombre, rut);

        parque.agregarUsuario(nuevoUsuario);
        usuarioActual = nuevoUsuario;
        System.out.println("Usuario creado correctamente.");
        
        continuar();
    }

    // Opciones para gestionar reservas
    private void gestionarReservas() throws IOException {
        if (usuarioActual == null) {
            System.out.println("Primero debe ingresar como usuario.");
            continuar();
            return;
        }

        int opcion;

        do {
            limpiarPantalla();
            System.out.println("======================================");
            System.out.println("      GESTIONAR RESERVAS");
            System.out.println("======================================");
            System.out.println("1. Listar Reservas");
            System.out.println("2. Buscar Reserva");
            System.out.println("3. Modificar Cantidad de Personas en Reserva");
            System.out.println("4. Aprobar Permiso");
            System.out.println("5. Rechazar Permiso");
            System.out.println("6. Atrás (Volver al menú principal)");
            System.out.print("Opcion: ");

            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    usuarioActual.listarReservas();
                    continuar();
                    break;
                case 2:
                    buscarReserva();
                    break;
                case 3:
                    cambiarCantidadReserva();
                    break;
                case 4:
                    aprobarPermisoReserva();
                    break;
                case 5:
                    rechazarPermisoReserva();
                    break;
                case 6:
                    limpiarPantalla();
                    break;
                default:
                    limpiarPantalla();
                    System.out.println("Opción no válida.");
                    continuar();
                    break;
            }

        } while (opcion != 6);
    }

    private void buscarReserva() throws IOException {
        System.out.print("Ingrese el ID de la reserva a buscar: ");
        String idBuscar = br.readLine();

        Reserva reserva = usuarioActual.buscarReserva(idBuscar);
        if (reserva == null) {
            System.out.println("No se encontró la reserva.");
        } else {
            reserva.mostrarReserva();
        }

        continuar();
    }

    private void cambiarCantidadReserva() throws IOException {
        System.out.print("Ingrese el ID de la reserva: ");
        String idModificar = br.readLine();

        Reserva reservaModificar = usuarioActual.buscarReserva(idModificar);
        if (reservaModificar == null) {
            System.out.println("Reserva no encontrada.");
        } else {
            System.out.print("Ingrese la nueva cantidad de personas: ");
            int nuevaCantidad = leerEntero();

            if (reservaModificar.cambiarCantidad(nuevaCantidad)) {
                System.out.println("Cantidad de personas modificada correctamente.");
            } else {
                System.out.println("No se pudo modificar la cantidad.");
            }
        }
        continuar();
    }

    private void aprobarPermisoReserva() throws IOException {
        System.out.print("Ingrese el ID de la reserva para aprobar el permiso: ");
        String idAprobar = br.readLine();

        if (usuarioActual.aprobarPermiso(idAprobar)) {
            System.out.println("Permiso aprobado correctamente.");
        } else {
            System.out.println("No se encontró la reserva o el permiso ya estaba aprobado.");
        }
        continuar();
    }

    private void rechazarPermisoReserva() throws IOException {
        System.out.print("Ingrese el ID de la reserva para rechazar el permiso: ");
        String idRechazar = br.readLine();

        if (usuarioActual.rechazarPermiso(idRechazar)) {
            System.out.println("Permiso rechazado correctamente.");
        } else {
            System.out.println("No se encontró la reserva o el permiso ya estaba rechazado.");
        }
        continuar();
    }

    // Opciones para gestionar usuarios
    private void gestionarUsuarios() throws IOException {
        int opcion;

        do {
            limpiarPantalla();
            System.out.println("======================================");
            System.out.println("      GESTIONAR USUARIOS");
            System.out.println("======================================");
            System.out.println("1. Listar Usuarios");
            System.out.println("2. Buscar Usuario");
            System.out.println("3. Modificar Nombre");
            System.out.println("4. Eliminar Usuario");
            System.out.println("5. Atrás (Volver al menú principal)");
            System.out.print("Opcion: ");

            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    parque.listarUsuarios();
                    continuar();
                    break;
                case 2:
                    buscarUsuario();
                    break;
                case 3:
                    modificarNombreUsuario();
                    break;
                case 4:
                    eliminarUsuario();
                    break;
                case 5:
                    limpiarPantalla();
                    break;
                default:
                    limpiarPantalla();
                    System.out.println("Opción no válida.");
                    continuar();
                    break;
            }

        } while (opcion != 5);
    }

    private void buscarUsuario() throws IOException {
        System.out.print("Ingrese el RUT del usuario a buscar: ");
        String rutBuscar = br.readLine();

        Usuario usuario = parque.buscarUsuario(rutBuscar);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
        } else {
            usuario.mostrarUsuario();
        }

        continuar();
    }

    private void modificarNombreUsuario() throws IOException {
        System.out.print("Ingrese el RUT del usuario a modificar: ");
        String rutModificar = br.readLine();

        Usuario usuarioModificar = parque.buscarUsuario(rutModificar);
        if (usuarioModificar == null) {
            System.out.println("Usuario no encontrado.");
        } else {
            System.out.print("Ingrese el nuevo nombre del usuario: ");
            String nuevoNombre = br.readLine();
            usuarioModificar.setNombre(nuevoNombre);
            System.out.println("Nombre del usuario modificado correctamente.");
        }

        continuar();
    }

    private void eliminarUsuario() throws IOException {
        System.out.print("Ingrese el RUT del usuario a eliminar: ");
        String rutEliminar = br.readLine();

        if (parque.eliminarUsuario(rutEliminar)) {
            System.out.println("Usuario eliminado correctamente.");

            if (usuarioActual != null && usuarioActual.getRut().equals(rutEliminar)) {
                usuarioActual = null;
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }

        continuar();
    }

    private void continuar() throws IOException {
        System.out.println("Presiona ENTER para continuar...");
        br.readLine();
    }

    private int leerEntero() throws IOException {
    while (true) {
        try {
            return Integer.parseInt(br.readLine().trim());
        } catch (NumberFormatException e) {
            System.out.print("Entrada inválida. Ingrese un número: ");
        }
    }


    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}