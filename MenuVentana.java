import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Implementa la interfaz gráfica del sistema mediante Swing.
 * Ofrece las mismas funcionalidades disponibles en el modo consola.
 */
public class MenuVentana {
    private Parque parque;
    private Usuario usuarioActual;

    public Parque getParque() {
        return parque;
    }
    public void setParque(Parque parque) {
        this.parque = parque;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public MenuVentana(Parque parque) {
        this.parque = parque;
        this.usuarioActual = null;
    }

    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            String usuario = (usuarioActual == null) ? "Ninguno" : usuarioActual.getNombre() + " (" + usuarioActual.getRut() + ")";

            String[] opciones = {
                "Reservar",
                "Cancelar Reserva",
                "Buscar Recurso",
                "Ingresar / Crear Usuario",
                "Gestionar Reservas",
                "Gestionar Usuarios",
                "Buscar Recursos por Capacidad",
                "Salir"
            };

            int opcion = mostrarMenuVertical(
                "Sistema de Gestión de Reservas",
                "PARQUE PUCV\nUsuario Actual: " + usuario,
                opciones
            );

            switch (opcion) {
                case 0:
                    reservar();
                    break;
                case 1:
                    cancelarReserva();
                    break;
                case 2:
                    buscarRecurso();
                    break;
                case 3:
                    ingresarOCrearUsuario();
                    break;
                case 4:
                    gestionarReservas();
                    break;
                case 5:
                    gestionarUsuarios();
                    break;
                case 6:
                    buscarRecursosPorCapacidad();
                    break;
                case 7:
                case -1:
                    salir = true;
                    break;
                default:
                    break;
            }
        }

        JOptionPane.showMessageDialog(null, "Saliendo del modo ventana...");
    }

    private void reservar() {
        if (!hayUsuarioActual()) {
            return;
        }

        String id = JOptionPane.showInputDialog("Ingrese el ID del recurso:");
        if (id == null) {
            return;
        }

        Recurso recurso = parque.buscarRecurso(id.trim());

        if (recurso == null) {
            mostrarError("No existe un recurso con el ID proporcionado.");
            return;
        }

        Integer cantidad = pedirEntero("Ingrese la cantidad de personas:");

        if (cantidad == null) {
            return;
        }

        if (cantidad <= 0) {
         mostrarError("La cantidad debe ser mayor que 0.");
         return;
        }
        
        if (!recurso.hayDisponibilidad(cantidad)) {
            mostrarError("La cantidad debe ser mayor que 0 y no superar la capacidad del recurso.");
            return;
        }

        try {
            recurso.validarCapacidad(cantidad);

            if (usuarioActual.realizarReserva(recurso, cantidad)) {
                JOptionPane.showMessageDialog(
                    null,
                    "Reserva realizada correctamente."
                );
            } else {
                mostrarError("No se pudo realizar la reserva.");
            }

        } catch (CapacidadExcedidaException |
                IllegalArgumentException e) {

            mostrarError(e.getMessage());
        }
    }

    private void cancelarReserva() {
        if (!hayUsuarioActual()) {
            return;
        }

        String id = JOptionPane.showInputDialog(null, "Ingrese el ID de la reserva a cancelar:");
        if (id == null) {
            return;
        }

        try {
            usuarioActual.cancelarReserva(id.trim());

            JOptionPane.showMessageDialog(
                null,
                "Reserva cancelada correctamente."
            );

        } catch (ReservaNoEncontradaException e) {
            mostrarError(e.getMessage());
        }
    }

    private void buscarRecurso() {
        String id = JOptionPane.showInputDialog(null, "Ingrese el ID del recurso:");
        if (id == null) {
            return;
        }

        Recurso recurso = parque.buscarRecurso(id.trim());
        if (recurso == null) {
            mostrarError("No existe un recurso con ese ID.");
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    recurso.obtenerDescripcion(),
                    "Recurso encontrado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void ingresarOCrearUsuario() {
        String rut = JOptionPane.showInputDialog(null, "Ingrese el RUT del usuario:");
        if (rut == null || rut.trim().isEmpty()) {
            return;
        }

        rut = rut.trim();
        Usuario existente = parque.buscarUsuario(rut);

        if (existente != null) {
            usuarioActual = existente;
            JOptionPane.showMessageDialog(
                    null,
                    "Usuario encontrado y seleccionado:\n" + textoUsuario(usuarioActual)
            );
            return;
        }

        String nombre = JOptionPane.showInputDialog(
                null,
                "Usuario no encontrado.\nIngrese el nombre para crear uno nuevo:"
        );

        if (nombre == null || nombre.trim().isEmpty()) {
            return;
        }

        Usuario nuevo = new Usuario(nombre.trim(), rut);
        if (parque.agregarUsuario(nuevo)) {
            usuarioActual = nuevo;
            JOptionPane.showMessageDialog(null, "Usuario creado correctamente.");
        } else {
            mostrarError("No se pudo crear el usuario.");
        }
    }

    private void gestionarReservas() {
        if (!hayUsuarioActual()) {
            return;
        }

        boolean volver = false;
        String[] opciones = {
            "Listar Reservas",
            "Buscar Reserva",
            "Modificar Cantidad",
            "Aprobar Permiso",
            "Rechazar Permiso",
            "Atrás"
        };

        while (!volver) {
            int opcion = mostrarMenuVertical(
                "Reservas",
                "GESTIONAR RESERVAS\nUsuario: " + usuarioActual.getNombre() + " (" + usuarioActual.getRut() + ")",
                opciones
            );

            switch (opcion) {
                case 0:
                    listarReservas();
                    break;
                case 1:
                    buscarReserva();
                    break;
                case 2:
                    modificarCantidadReserva();
                    break;
                case 3:
                    cambiarPermiso(true);
                    break;
                case 4:
                    cambiarPermiso(false);
                    break;
                case 5:
                case -1:
                    volver = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void listarReservas() {
        StringBuilder texto = new StringBuilder();

        for (Reserva reserva : usuarioActual.getReservas()) {
            texto.append(textoReserva(reserva));
            texto.append("\n------------------------------\n");
        }
        
        if (texto.length() == 0) {
            texto.append("El usuario no tiene reservas.");
        }

        mostrarTextoDesplazable("Reservas", texto.toString());
    }

    private void buscarReserva() {
        String id = JOptionPane.showInputDialog(null, "Ingrese el ID de la reserva:");
        if (id == null) {
            return;
        }

        Reserva reserva = usuarioActual.buscarReserva(id.trim());
        if (reserva == null) {
            mostrarError("Reserva no encontrada.");
        } else {
            JOptionPane.showMessageDialog(null, textoReserva(reserva), "Reserva", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void modificarCantidadReserva() {
        String id = JOptionPane.showInputDialog(null, "Ingrese el ID de la reserva:");
        if (id == null) {
            return;
        }

        Reserva reserva = usuarioActual.buscarReserva(id.trim());
        if (reserva == null) {
            mostrarError("Reserva no encontrada.");
            return;
        }

        Integer cantidad = pedirEntero("Ingrese la nueva cantidad de personas:");
        if (cantidad == null) {
            return;
        }

        try {
            if (reserva.cambiarCantidad(cantidad)) {
                JOptionPane.showMessageDialog(
                    null,
                    "Cantidad modificada correctamente."
                );
            } else {
                mostrarError(
                    "La cantidad debe ser mayor a 0."
                );
            }

        } catch (CapacidadExcedidaException e) {
            mostrarError(e.getMessage());
        }
    }

    private void cambiarPermiso(boolean aprobar) {
        String id = JOptionPane.showInputDialog(null, "Ingrese el ID de la reserva:");
        if (id == null) {
            return;
        }

        boolean resultado = aprobar
                ? usuarioActual.aprobarPermiso(id.trim())
                : usuarioActual.rechazarPermiso(id.trim());

        if (resultado) {
            JOptionPane.showMessageDialog(
                    null,
                    aprobar ? "Permiso aprobado correctamente." : "Permiso rechazado correctamente."
            );
        } else {
            mostrarError("Reserva no encontrada.");
        }
    }

    private void gestionarUsuarios() {
        boolean volver = false;
        String[] opciones = {
            "Listar Usuarios",
            "Buscar Usuario",
            "Modificar Nombre",
            "Eliminar Usuario",
            "Atrás"
        };

        while (!volver) {
            int opcion = mostrarMenuVertical(
                "Usuarios",
                "GESTIONAR USUARIOS",
                opciones
            );

            switch (opcion) {
                case 0:
                    listarUsuarios();
                    break;
                case 1:
                    buscarUsuario();
                    break;
                case 2:
                    modificarUsuario();
                    break;
                case 3:
                    eliminarUsuario();
                    break;
                case 4:
                case -1:
                    volver = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void listarUsuarios() {
        StringBuilder texto = new StringBuilder();

        for (Usuario usuario : parque.getUsuarios()) {
            texto.append(textoUsuario(usuario));
            texto.append("\n------------------------------\n");
        }

        if (texto.length() == 0) {
            texto.append("No hay usuarios registrados.");
        }

        mostrarTextoDesplazable("Usuarios", texto.toString());
    }

    private void buscarUsuario() {
        String rut = JOptionPane.showInputDialog(null, "Ingrese el RUT del usuario:");
        if (rut == null) {
            return;
        }

        Usuario usuario = parque.buscarUsuario(rut.trim());
        if (usuario == null) {
            mostrarError("Usuario no encontrado.");
        } else {
            JOptionPane.showMessageDialog(null, textoUsuario(usuario), "Usuario", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void modificarUsuario() {
        String rut = JOptionPane.showInputDialog(null, "Ingrese el RUT del usuario a modificar:");
        if (rut == null) {
            return;
        }

        Usuario usuario = parque.buscarUsuario(rut.trim());
        if (usuario == null) {
            mostrarError("Usuario no encontrado.");
            return;
        }

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre:", usuario.getNombre());
        if (nombre == null || nombre.trim().isEmpty()) {
            return;
        }

        usuario.setNombre(nombre.trim());
        JOptionPane.showMessageDialog(null, "Usuario modificado correctamente.");
    }

    private void eliminarUsuario() {
        String rut = JOptionPane.showInputDialog(null, "Ingrese el RUT del usuario a eliminar:");
        if (rut == null) {
            return;
        }

        rut = rut.trim();
        if (parque.eliminarUsuario(rut)) {
            if (usuarioActual != null && usuarioActual.getRut().equals(rut)) {
                usuarioActual = null;
            }
            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
        } else {
            mostrarError("Usuario no encontrado.");
        }
    }

    private void buscarRecursosPorCapacidad() {
        Integer cantidad = pedirEntero("Ingrese la cantidad de personas:");
        if (cantidad == null) {
            return;
        }

        if (cantidad <= 0) {
            mostrarError("La cantidad debe ser mayor que 0.");
            return;
        }

        StringBuilder texto = new StringBuilder();
        texto.append("Recursos disponibles para ").append(cantidad).append(" personas:\n\n");

        boolean encontrado = false;
        for (Recurso recurso :
            parque.filtrarRecursosPorCapacidad(cantidad)) {

            texto.append(recurso.obtenerDescripcion());
            texto.append("\n------------------------------\n");
            encontrado = true;
        }

        if (!encontrado) {
            texto.append("No hay recursos disponibles con capacidad suficiente.");
        }

        mostrarTextoDesplazable("Recursos disponibles", texto.toString());
    }

    private boolean hayUsuarioActual() {
        if (usuarioActual == null) {
            mostrarError("Primero debe ingresar o crear un usuario.");
            return false;
        }
        return true;
    }

    private Integer pedirEntero(String mensaje) {
        String entrada = JOptionPane.showInputDialog(null, mensaje);
        if (entrada == null) {
            return null;
        }

        try {
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            mostrarError("Debe ingresar un número entero válido.");
            return null;
        }
    }

    private String textoUsuario(Usuario usuario) {
        return "Nombre: " + usuario.getNombre() + "\n" +
               "RUT: " + usuario.getRut();
    }

    private String textoReserva(Reserva reserva) {
        return "ID de reserva: " + reserva.getId() + "\n" +
               "Tamaño de grupo: " + reserva.getCantidad() + "\n" +
               "Recurso:\n" + reserva.getRecurso().obtenerDescripcion() + "\n" +
               "Precio por persona: $" + reserva.getTarifa().getPrecioBase() + "\n" +
               "Costo total: $" + reserva.getTarifa().calcular(reserva.getCantidad()) + "\n" +
               "Estado: " + (reserva.getEstado() ? "Activa" : "Cancelada") + "\n" +
               "ID del permiso: " + reserva.getPermiso().getId() + "\n" +
               "Estado permiso: " + (reserva.getPermiso().estaAprobado() ? "Aprobado" : "Pendiente");
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private int mostrarMenuVertical(String titulo, String mensaje, String[] opciones) {

        final int[] seleccion = {-1};
        
        JDialog dialogo = new JDialog((Frame) null, titulo, true);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JLabel texto = new JLabel("<html>" + mensaje.replace("\n", "<br>") + "</html>");
        
        panelPrincipal.add(texto, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(0, 1, 5, 5));
        
        for (int i = 0; i < opciones.length; i++) {
            final int indice = i;
            
            JButton boton = new JButton(opciones[i]);
            
            boton.addActionListener(e -> {
                seleccion[0] = indice;
                dialogo.dispose();
            });
            
            panelBotones.add(boton);
        }
        
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        
        dialogo.setContentPane(panelPrincipal);
        dialogo.pack();
        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setVisible(true);
        
        return seleccion[0];
    }

    private void mostrarTextoDesplazable(String titulo, String contenido) {
        JTextArea areaTexto = new JTextArea(contenido);
        
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(false);
        areaTexto.setCaretPosition(0);
        
        JScrollPane scroll = new JScrollPane(areaTexto);
        
        scroll.setPreferredSize(new Dimension(450, 500));
        
        JOptionPane.showMessageDialog(
            null,
            scroll,
            titulo,
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
