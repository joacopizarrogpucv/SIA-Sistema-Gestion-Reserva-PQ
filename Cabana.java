public class Cabana extends Recurso {

    private int cantidadHabitaciones;

    public Cabana(String n, int c, int habitaciones) {
        super(n, c);
        cantidadHabitaciones = habitaciones;
    }

    public int getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }
}