public class Permiso {

    private String id;
    private boolean aprobado;
    private static int contador = 1;

    public Permiso() {
        id = "PER" + contador;
        aprobado = false;
        contador++;
    }

    public String getId() {
        return id;
    }

    public boolean estaAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public void aprobar() {
        aprobado = true;
    }

    public void rechazar() {
        aprobado = false;
    }

    public void mostrarPermiso() {
        System.out.println("ID del permiso: " + id);
        System.out.println("Estado permiso: " + (aprobado ? "Aprobado" : "Pendiente"));
    }
}