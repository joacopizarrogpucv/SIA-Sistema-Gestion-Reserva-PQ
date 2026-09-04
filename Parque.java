import java.util.Map;
import java.util.HashMap;

public class Parque{
    private String nombre;
    private String ubicacion;
    private Map<String, Camping> campings;

    public Parque(String n, String u){
        nombre = n;
        ubicacion = u;
        campings = new HashMap<String, Camping>();
    }
}