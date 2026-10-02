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
            System.out.println("4. Crear Usuario de forma manual");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(br.readLine());
            switch (opcion) {
                case 1:
                    reservar();
                    break;
                case 2:
                    cancelarReserva();
                    break;
                case 3:
                    buscarRecursos();
                    break;
                case 4:
                    crearUsuario();
                    break;
                
                case 5:
                    limpiarPantalla();
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    limpiarPantalla();
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (opcion != 5);
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
        else{
            System.out.println("Ingrese la cantidad de personas que van:");
            int cantidad = Integer.parseInt(br.readLine());
            if (!recurso.hayDisponibilidad(cantidad)) {
            System.out.println("No existe capacidad suficiente.");
            }
            else if (usuarioActual.realizarReserva(recurso, cantidad)) {
                System.out.println("Reserva realizada correctamente.");
            }
            else {
                System.out.println("El usuario ya tiene reservado ese recurso.");
            }
        }
        continuar();
    }

    private void cancelarReserva() throws IOException {
        if (usuarioActual == null) {
            System.out.println("Primero debe crear un usuario.");
            continuar();
            return;
        }

        System.out.print("ID del recurso a cancelar: ");
        String idCancelar = br.readLine();

        if (usuarioActual.cancelarReserva(idCancelar)) {
            System.out.println("Reserva cancelada correctamente.");
        }
        else {
            System.out.println("No se encontró esa reserva.");
        }

        continuar();
    }

    private void buscarRecursos() throws IOException {
        int opcion;
        do {
            limpiarPantalla();

            System.out.println("1. Cabaña");
            System.out.println("2. Camping");
            System.out.println("3. Actividad");
            System.out.println("4. Atrás");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(br.readLine());
            
            switch (opcion){
                case 1:
                    buscarCabana();
                    break;
                case 2:
                    buscarCamping();
                    break;
                case 3:
                    buscarActividad();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción no válida");
                    continuar();
            }
        } while (opcion != 4);
    }

    private void buscarCabana() throws IOException {
        limpiarPantalla();

        System.out.println("Ingresa el Id de la cabana");
        String ID = (br.readLine());
        Recurso x = parque.buscarCabana(ID);
        if(x == null){
           System.out.println("Id incorrecto");
        }
        else {
            System.out.println("Cabana encontrada");
            x.mostrarRecurso();
        }
        continuar();
    }

    private void buscarCamping() throws IOException {
        limpiarPantalla();

        System.out.println("Ingresa el Id del camping");
        String ID = (br.readLine());
        Recurso x = parque.buscarCamping(ID);
        if(x == null){
           System.out.println("Id incorrecto");
        }
        else {
            System.out.println("Camping encontrado");
            x.mostrarRecurso();
        }
        continuar();
    }

    private void buscarActividad() throws IOException {
        limpiarPantalla();

        System.out.println("Ingresa el Id de la actividad");
        String ID = (br.readLine());
        Recurso x = parque.buscarActividad(ID);
        if(x == null){
           System.out.println("Id incorrecto");
        }
        else {
            System.out.println("Actividad encontrada");
            x.mostrarRecurso();
        }
        continuar();
    }

    private void crearUsuario() throws IOException {
        limpiarPantalla();

        System.out.println("Ingrese el nombre del usuario:");
        String nombre = br.readLine();
        System.out.println("Ingrese el RUT del usuario:");
        String rut = br.readLine();
        usuarioActual = new Usuario(nombre, rut);
        System.out.println("Usuario creado correctamente.");
        continuar();
    }

    private void continuar() throws IOException {
        System.out.println("Presiona ENTER para continuar...");
        br.readLine();
    }

    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}