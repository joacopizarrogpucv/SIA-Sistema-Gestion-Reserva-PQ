public class CapacidadExcedidaException extends RuntimeException {
    public CapacidadExcedidaException(String recurso, int solicitado, int capacidad) {
        super("El recurso '" + recurso + "' admite " + capacidad
              + " personas, pero se solicitaron " + solicitado + ".");
    }
}