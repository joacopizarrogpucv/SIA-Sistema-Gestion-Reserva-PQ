public class ReservaNoEncontradaException extends Exception {
    public ReservaNoEncontradaException(String idReserva) {
        super("No existe la reserva con ID: " + idReserva);
    }
}