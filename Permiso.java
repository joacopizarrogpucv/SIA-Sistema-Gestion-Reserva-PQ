/**
 * Representa el permiso asociado a una reserva.
 * Permite registrar si dicho permiso se encuentra aprobado o pendiente.
 */
public class Permiso {

    private String id;
    private boolean aprobado;
    private static int contador = 1;

    /**
     * Crea un permiso nuevo inicialmente pendiente.
     */
    public Permiso() {
        id = "PER" + contador;
        aprobado = false;
        contador++;
    }

    /**
     * Reconstruye un permiso existente desde los datos almacenados.
     *
     * @param id identificador del permiso
     * @param aprobado estado almacenado del permiso
     */
    public Permiso(String id, boolean aprobado) {
        this.id = id;
        this.aprobado = aprobado;

        actualizarContador(id);
    }

    private static void actualizarContador(String id) {
        if (id == null || !id.startsWith("PER")) {
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

    public boolean estaAprobado() {
        return aprobado;
    }
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public static int getContador() {
        return contador;
    }
    public static void setContador(int contador) {
        Permiso.contador = contador;
    }

    /**
     * Aprueba el permiso.
     */
    public void aprobar() {
        aprobado = true;
    }

    /**
     * Deja el permiso en estado no aprobado.
     */
    public void rechazar() {
        aprobado = false;
    }

    public void mostrarPermiso() {
        System.out.println("ID del permiso: " + id);
        System.out.println("Estado permiso: " + (aprobado ? "Aprobado" : "Pendiente"));
    }
}