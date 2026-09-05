public class Permiso {

    private String id;
    private boolean aprobado;

    public Permiso(String i, boolean a) {
        id = i;
        aprobado = a;
    }

    public String getId() {
        return id;
    }

    public boolean estaAprobado() {
        return aprobado;
    }
}