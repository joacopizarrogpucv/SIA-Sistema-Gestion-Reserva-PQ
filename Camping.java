public class Camping extends Recurso {

    private boolean tieneAguaPotable;

    public Camping(String n, int c, boolean agua) {
        super(n, c);
        tieneAguaPotable = agua;
    }

    public boolean getTieneAguaPotable() {
        return tieneAguaPotable;
    }
}