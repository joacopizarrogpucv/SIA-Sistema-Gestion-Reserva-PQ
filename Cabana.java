public class Cabana extends Recurso {
    private int habitacion;

    public Cabana(String n, String i, int c, int a) {
        super(n, i, c);
        habitacion = a;
    }

    public int gethabitacion() {
        return habitacion;
    }

    @Override
    public String obtenerDescripcion() {
        return "Nombre: " + getNombre() + "\n" +
               "ID: " + getId() + "\n" +
               "Capacidad: " + getCapacidad() + " personas\n" +
               "Habitaciones: " + habitacion;
    }
    
    @Override
    public void mostrarRecurso(){
        System.out.println(obtenerDescripcion());
    }
}