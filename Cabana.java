import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Cabana extends Recurso {
    private int cantidadHabitaciones;

    // mes -> dias ocupados
    private Map<Integer, ArrayList<Integer>> diasOcupados;

    public Cabana(String n, int c, int habitaciones) {
        super(n, c);
        cantidadHabitaciones = habitaciones;
        diasOcupados = new HashMap<Integer, ArrayList<Integer>>();

        for (int mes = 1; mes <= 12; mes++) {
            diasOcupados.put(mes, new ArrayList<Integer>());
        }
    }

    public int getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(int habitaciones) {
        cantidadHabitaciones = habitaciones;
    }

    public boolean diaOcupado(int mes, int dia) {
        if (!diasOcupados.containsKey(mes)) {
            return true;
        }

        return diasOcupados.get(mes).contains(dia);
    }

    public void ocuparDia(int mes, int dia) {
        if (!diaOcupado(mes, dia)) {
            diasOcupados.get(mes).add(dia);
        }
    }

    public void liberarDia(int mes, int dia) {
        if (diasOcupados.containsKey(mes)) {
            diasOcupados.get(mes).remove(Integer.valueOf(dia));
        }
    }
}
