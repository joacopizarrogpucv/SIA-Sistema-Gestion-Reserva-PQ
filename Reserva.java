public class Reserva {
    private String id;
    private Usuario usuario;
    private Recurso recurso;
    private Tarifa tarifa;

    private int mesInicio;
    private int diaInicio;
    private int mesFin;
    private int diaFin;

    // Para actividades se guardan tambien las horas
    private int minutoInicio;
    private int minutoFin;

    // Reserva de cabana/camping
    public Reserva(String i, Usuario u, Recurso r, Tarifa t, int mesI, int diaI, int mesF, int diaF) {
        id = i;
        usuario = u;
        recurso = r;
        tarifa = t;
        mesInicio = mesI;
        diaInicio = diaI;
        mesFin = mesF;
        diaFin = diaF;
        minutoInicio = 0;
        minutoFin = 0;
    }

    // Reserva de actividad
    public Reserva(String i, Usuario u, Recurso r, Tarifa t, int mes, int dia, int horaI, int minutoI, int horaF, int minutoF) {
        id = i;
        usuario = u;
        recurso = r;
        tarifa = t;
        mesInicio = mes;
        diaInicio = dia;
        mesFin = mes;
        diaFin = dia;
        minutoInicio = horaI * 60 + minutoI;
        minutoFin = horaF * 60 + minutoF;
    }

    public String getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public int getDiaInicio() {
        return diaInicio;
    }

    public int getMesFin() {
        return mesFin;
    }

    public int getDiaFin() {
        return diaFin;
    }

    public int getMinutoInicio() {
        return minutoInicio;
    }

    public int getMinutoFin() {
        return minutoFin;
    }

    public void cambiarHorario(int horaInicio, int minutoInicio, int horaFin, int minutoFin) {
        this.minutoInicio = horaInicio * 60 + minutoInicio;
        this.minutoFin = horaFin * 60 + minutoFin;
    }

    public int calcularDias() {
        if (mesInicio == mesFin) {
            return diaFin - diaInicio + 1;
        }

        int dias = 0;

        for (int mes = mesInicio; mes < mesFin; mes++) {
            if (mes == 2) {
                dias += 28;
            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                dias += 30;
            } else {
                dias += 31;
            }
        }

        dias -= diaInicio - 1;
        dias += diaFin;

        return dias;
    }

    public double calcularCosto() {
        if (recurso instanceof Actividad) {
            return tarifa.calcular(recurso);
        }

        return tarifa.calcular(recurso) * calcularDias();
    }
}
