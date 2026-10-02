import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException {

        // Primero se lee el archivo .csv completo
        Parque parque = Lector.leerParque("parque.csv");
        if (parque == null) {
            System.out.println("No se pudo cargar el archivo parque.csv.");
            return;
        }

        Lector.leerUsuarios("usuario.csv", parque);
        Lector.leerReservas("reservas.csv", parque);    

        // Luego se crea el menú de consola y se inicia
        MenuConsola menu = new MenuConsola(parque);
        menu.iniciar();
    }

}