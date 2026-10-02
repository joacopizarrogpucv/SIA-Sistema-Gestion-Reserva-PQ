import java.io.IOException;
import javax.swing.JOptionPane;

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
        
        String[] modos = {"Consola", "Ventana"};

        int modo = JOptionPane.showOptionDialog(null, "Seleccione el modo de ejecución:", "Parque PUCV",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modos, modos[0]);
        
        switch (modo) {
            case 0:
                // Modo consola
                MenuConsola menuConsola = new MenuConsola(parque);
                menuConsola.iniciar();
                break;
            case 1:
                // Modo ventana
                MenuVentana menuVentana = new MenuVentana(parque);
                menuVentana.iniciar();
                break;
            default:
                System.out.println("Modo de ejecución no válido.");
                return;
        }
        
        Escritor.guardarUsuarios("usuario.csv", parque);
        Escritor.guardarReservas("reservas.csv", parque);

        System.out.println("Datos guardados correctamente.");
    }
}