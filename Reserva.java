/**
 * Representa la reserva de un recurso realizada por un usuario.
 * Contiene la cantidad de personas, tarifa, estado y permiso asociado.
 */
public class Reserva {
    private String id;
    private int cantidad;
    private Recurso recurso;
    private Tarifa tarifa;
    private boolean estado;
    private Permiso permiso;
    private static int contador = 1;

    // Para reservas nuevas
    public Reserva(int cantidad, Recurso recurso, Tarifa tarifa, boolean estado) {
        this.id = "RES" + contador;
        this.cantidad = cantidad;
        this.recurso = recurso;
        this.tarifa = tarifa;
        this.estado = estado;
        this.permiso = new Permiso();
        contador++;
    }

    // Para reservas existentes desde archivo
    public Reserva(String id, int cantidad, Recurso recurso, Tarifa tarifa, boolean estado, Permiso permiso) {
        this.id = id;
        this.cantidad = cantidad;
        this.recurso = recurso;
        this.tarifa = tarifa;
        this.estado = estado;
        this.permiso = permiso;

        actualizarContador(id);
    }

    private static void actualizarContador(String id) {
        if (id == null || !id.startsWith("RES")) {
            return;
        }

        try {
            int numero = Integer.parseInt(id.substring(3));
            if (numero >= contador) {
                contador = numero + 1;
            }
        } catch (NumberFormatException e) {
            // Si el ID no tiene el formato esperado, no se modifica el contador
        }
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
        actualizarContador(id);
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Recurso getRecurso() {
        return recurso;
    }
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }
    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public boolean getEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Permiso getPermiso() {
        return permiso;
    }
    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public static int getContador() {
        return contador;
    }
    public static void setContador(int contador) {
        Reserva.contador = contador;
    }

    /**
     * Modifica la cantidad de personas de la reserva verificando
     * previamente la capacidad del recurso.
     *
     * @param nuevaCantidad nueva cantidad de personas
     * @return true si la modificación es válida
     */
    public boolean cambiarCantidad(int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            return false;
        }

        if (nuevaCantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }
        recurso.validarCapacidad(nuevaCantidad);
        cantidad = nuevaCantidad;
        }

        cantidad = nuevaCantidad;
        return true;
    }

    public void mostrarReserva(){
        System.out.println("ID de reserva: " + id);
        System.out.println("Tamaño de grupo: " + cantidad);
        System.out.println("Recurso: ");
        recurso.mostrarRecurso();
        System.out.println("Precio por persona: $ " + tarifa.getPrecioBase());
        System.out.println("Costo total: $ " + tarifa.calcular(cantidad));
        System.out.println("Estado: " + (estado ? "Activa" : "Cancelada"));
        permiso.mostrarPermiso();
    }
}

