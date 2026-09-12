import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public abstract class Recurso {
    //variables
    private String nombre;
    private int capacidad;
    private Map<Integer, ArrayList<Integer>> calendario;

    //constructor
    public Recurso(String n, int c) {
        nombre = n;
        capacidad = c;

        calendario = new HashMap<Integer, ArrayList<Integer>>();
        for (int mes = 1; mes <= 12; mes++) {
            calendario.put(mes, new ArrayList<Integer>());

            int cantidadDias = 31;

            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                cantidadDias = 30;
            } else if (mes == 2) {
                cantidadDias = 28;
            }

            for (int dia = 1; dia <= cantidadDias; dia++) {
                calendario.get(mes).add(dia);
            }
        }
    }
    public String getNombre() {
        return nombre;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public Map<Integer, ArrayList<Integer>> getCalendario() {
        return calendario;
    }
    //metodo
    public boolean hayDisponibilidad(int cantidad) {
        return cantidad <= capacidad;
    }
}