public class ReservaNoEncontradaException extends RuntimeException {
    public ReservaNoEncontradaException(String idReserva) {
        super("No existe la reserva con ID: " + idReserva);
    }
}