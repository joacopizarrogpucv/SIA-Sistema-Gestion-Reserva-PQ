import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Camping extends Recurso {
    private boolean tieneAguaPotable;

    // mes -> dias ocupados
    private Map<Integer, ArrayList<Integer>> diasOcupados;

    public Camping(String n, int c, boolean agua) {
        super(n, c);
        tieneAguaPotable = agua;
        diasOcupados = new HashMap<Integer, ArrayList<Integer>>();

        for (int mes = 1; mes <= 12; mes++) {
            diasOcupados.put(mes, new ArrayList<Integer>());
        }
    }

    public boolean getTieneAguaPotable() {
        return tieneAguaPotable;
    }

    public void setTieneAguaPotable(boolean agua) {
        tieneAguaPotable = agua;
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
