public class Reserva {
    private String id;
    private int cantidad;
    private Recurso recurso;
    private Tarifa tarifa;
    private boolean estado;
    private static int contador = 11;

    public Reserva(int c, Recurso r, Tarifa t, boolean e) {
        id = "RES" + contador;
        cantidad = c;
        recurso = r;
        tarifa = t;
        estado = e; 
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
    public void mostrarReserva(){
        System.out.println("ID de reserva: " + id);
        System.out.println("Tamaño de grupo: " + cantidad);
        System.out.println("Recurso: ");
        recurso.mostrarRecurso();
        System.out.println("Tarifa: " + tarifa.getPrecio());
        System.out.println("Estado: " + estado);
    }
}

