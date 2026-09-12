import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Actividad extends Recurso {
    private String guia;
    private int duracion;
    private Map<Integer, Map<Integer, ArrayList<Reserva>>> reservas;

    public Actividad(String n, int c, String g, int d) {
        super(n, c);
        guia = g;
        duracion = d;

        reservas = new HashMap<Integer, Map<Integer, ArrayList<Reserva>>>();

        for (int mes = 1; mes <= 12; mes++) {
            reservas.put(mes, new HashMap<Integer, ArrayList<Reserva>>());

            for (int dia : getCalendario().get(mes)) {
                reservas.get(mes).put(dia, new ArrayList<Reserva>());
            }
        }
    }

    public String getGuia() {
        return guia;
    }

    public int getDuracion() {
        return duracion;
    }

    public boolean horarioOcupado(int mes, int dia, int inicio, int fin, Reserva ignorar) {
        if (!reservas.containsKey(mes) || !reservas.get(mes).containsKey(dia)) {
            return true;
        }

        for (Reserva r : reservas.get(mes).get(dia)) {
            if (r == ignorar) {
                continue;
            }

            // Hay choque si los horarios se superponen
            if (inicio < r.getMinutoFin() && fin > r.getMinutoInicio()) {
                return true;
            }
        }

        return false;
    }

    public boolean agregarReserva(Reserva r) {
        if (horarioOcupado(r.getMesInicio(), r.getDiaInicio(),
                           r.getMinutoInicio(), r.getMinutoFin(), null)) {
            return false;
        }

        reservas.get(r.getMesInicio()).get(r.getDiaInicio()).add(r);
        return true;
    }

    public boolean eliminarReserva(Reserva r) {
        if (!reservas.containsKey(r.getMesInicio())) {
            return false;
        }

        if (!reservas.get(r.getMesInicio()).containsKey(r.getDiaInicio())) {
            return false;
        }

        return reservas.get(r.getMesInicio()).get(r.getDiaInicio()).remove(r);
    }

    public ArrayList<Reserva> getReservas(int mes, int dia) {
        if (!reservas.containsKey(mes) || !reservas.get(mes).containsKey(dia)) {
            return new ArrayList<Reserva>();
        }

        return reservas.get(mes).get(dia);
    }
}
