public class Reserva {

    private String id;
    private Usuario usuario;
    private Recurso recurso;
    private Tarifa tarifa;

    public Reserva(String i, Usuario u, Recurso r, Tarifa t) {
        id = i;
        usuario = u;
        recurso = r;
        tarifa = t;
    }

    public double calcularCosto() {
        return tarifa.calcular(recurso);
    }

    public String getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }
}