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
    public void mostrarRecurso(){
        System.out.println("Nombre: " + getNombre());
        System.out.println("Id: " + getId());
        System.out.println("habitaciones : " + habitacion);
    }
}