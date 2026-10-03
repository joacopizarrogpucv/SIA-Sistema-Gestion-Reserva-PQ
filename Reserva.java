public class Reserva {
    private String id;
    private int cantidad;
    private Recurso recurso;
    private Tarifa tarifa;
    private boolean estado;
    private Permiso permiso;
    private static int contador = 1;

    public Reserva(int c, Recurso r, Tarifa t, boolean e) {
        id = "RES" + contador;
        cantidad = c;
        recurso = r;
        tarifa = t;
        estado = e; 
        permiso = new Permiso();
        contador ++;
    }

    public String getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public boolean getEstado() {
        return estado;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public boolean cambiarCantidad(int nuevaCantidad) throws CapacidadExcedidaException{
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

