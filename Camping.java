public class Camping extends Recurso {
    private boolean picina;

    public Camping(String n, String i, int c, boolean x) {
        super(n, i, c);
        picina = x;
    }
    public boolean getPicina() {
        return picina;
    }
    public void setPicina(boolean x) {
        picina = x;
    }
    @Override
    public void mostrarRecurso(){
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID: " + getId());
        System.out.println("Capacidad: " + getCapacidad() + " personas");
        if(picina){
            System.out.println("Tiene servicio de Picina");
        }
        else{
            System.out.println("No tiene servicio de Picina");
        }
    }
}