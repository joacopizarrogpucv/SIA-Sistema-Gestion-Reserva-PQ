import java.util.Map;
import java.util.HashMap;

public class Parque {
    private String nombre;
    private String ubicacion;

    private Map<String, Camping> campings;
    private Map<String, Cabana> cabanas;
    private Map<String, Actividad> actividades;
    private Map<String, Reserva> reservas;

    public Parque(String n, String u) {
        nombre = n;
        ubicacion = u;

        campings = new HashMap<String, Camping>();
        cabanas = new HashMap<String, Cabana>();
        actividades = new HashMap<String, Actividad>();
        reservas = new HashMap<String, Reserva>();
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public void setUbicacion(String u) {
        ubicacion = u;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public boolean agregarCamping(Camping c) {
        if (campings.containsKey(c.getNombre())) {
            return false;
        }

        campings.put(c.getNombre(), c);
        return true;
    }

    public Camping buscarCamping(String nombre) {
        return campings.get(nombre);
    }

    public boolean agregarCabana(Cabana c) {
        if (cabanas.containsKey(c.getNombre())) {
            return false;
        }

        cabanas.put(c.getNombre(), c);
        return true;
    }

    public Cabana buscarCabana(String nombre) {
        return cabanas.get(nombre);
    }

    public boolean agregarActividad(Actividad a) {
        if (actividades.containsKey(a.getNombre())) {
            return false;
        }

        actividades.put(a.getNombre(), a);
        return true;
    }

    public Actividad buscarActividad(String nombre) {
        return actividades.get(nombre);
    }

    public boolean agregarReserva(Reserva r) {
        if (reservas.containsKey(r.getId())) {
            return false;
        }

        Recurso recurso = r.getRecurso();

        // Actividad: se revisa que no exista choque de horario
        if (recurso instanceof Actividad) {
            Actividad actividad = (Actividad) recurso;

            if (r.getMinutoFin() <= r.getMinutoInicio()) {
                return false;
            }

            if (actividad.horarioOcupado(
                    r.getMesInicio(),
                    r.getDiaInicio(),
                    r.getMinutoInicio(),
                    r.getMinutoFin(),
                    null)) {
                return false;
            }

            actividad.agregarReserva(r);
        }

        // Cabana: todos los dias del periodo deben estar libres
        else if (recurso instanceof Cabana) {
            Cabana cabana = (Cabana) recurso;

            for (int mes = r.getMesInicio(); mes <= r.getMesFin(); mes++) {
                int inicio = 1;
                int fin = diasDelMes(mes);

                if (mes == r.getMesInicio()) {
                    inicio = r.getDiaInicio();
                }

                if (mes == r.getMesFin()) {
                    fin = r.getDiaFin();
                }

                for (int dia = inicio; dia <= fin; dia++) {
                    if (cabana.diaOcupado(mes, dia)) {
                        return false;
                    }
                }
            }

            for (int mes = r.getMesInicio(); mes <= r.getMesFin(); mes++) {
                int inicio = 1;
                int fin = diasDelMes(mes);

                if (mes == r.getMesInicio()) {
                    inicio = r.getDiaInicio();
                }

                if (mes == r.getMesFin()) {
                    fin = r.getDiaFin();
                }

                for (int dia = inicio; dia <= fin; dia++) {
                    cabana.ocuparDia(mes, dia);
                }
            }
        }

        // Camping: todos los dias del periodo deben estar libres
        else if (recurso instanceof Camping) {
            Camping camping = (Camping) recurso;

            for (int mes = r.getMesInicio(); mes <= r.getMesFin(); mes++) {
                int inicio = 1;
                int fin = diasDelMes(mes);

                if (mes == r.getMesInicio()) {
                    inicio = r.getDiaInicio();
                }

                if (mes == r.getMesFin()) {
                    fin = r.getDiaFin();
                }

                for (int dia = inicio; dia <= fin; dia++) {
                    if (camping.diaOcupado(mes, dia)) {
                        return false;
                    }
                }
            }

            for (int mes = r.getMesInicio(); mes <= r.getMesFin(); mes++) {
                int inicio = 1;
                int fin = diasDelMes(mes);

                if (mes == r.getMesInicio()) {
                    inicio = r.getDiaInicio();
                }

                if (mes == r.getMesFin()) {
                    fin = r.getDiaFin();
                }

                for (int dia = inicio; dia <= fin; dia++) {
                    camping.ocuparDia(mes, dia);
                }
            }
        }

        reservas.put(r.getId(), r);
        return true;
    }

    public Reserva buscarReserva(String id) {
        return reservas.get(id);
    }

    public boolean cancelarReserva(String id) {
        Reserva r = reservas.get(id);

        if (r == null) {
            return false;
        }

        Recurso recurso = r.getRecurso();

        if (recurso instanceof Actividad) {
            ((Actividad) recurso).eliminarReserva(r);
        }

        else if (recurso instanceof Cabana) {
            Cabana cabana = (Cabana) recurso;

            for (int mes = r.getMesInicio(); mes <= r.getMesFin(); mes++) {
                int inicio = 1;
                int fin = diasDelMes(mes);

                if (mes == r.getMesInicio()) {
                    inicio = r.getDiaInicio();
                }

                if (mes == r.getMesFin()) {
                    fin = r.getDiaFin();
                }

                for (int dia = inicio; dia <= fin; dia++) {
                    cabana.liberarDia(mes, dia);
                }
            }
        }

        else if (recurso instanceof Camping) {
            Camping camping = (Camping) recurso;

            for (int mes = r.getMesInicio(); mes <= r.getMesFin(); mes++) {
                int inicio = 1;
                int fin = diasDelMes(mes);

                if (mes == r.getMesInicio()) {
                    inicio = r.getDiaInicio();
                }

                if (mes == r.getMesFin()) {
                    fin = r.getDiaFin();
                }

                for (int dia = inicio; dia <= fin; dia++) {
                    camping.liberarDia(mes, dia);
                }
            }
        }

        reservas.remove(id);
        return true;
    }

    public boolean cambiarHorarioActividad(String id,
                                           int horaInicio, int minutoInicio,
                                           int horaFin, int minutoFin) {
        Reserva r = reservas.get(id);

        if (r == null || !(r.getRecurso() instanceof Actividad)) {
            return false;
        }

        Actividad actividad = (Actividad) r.getRecurso();

        int nuevoInicio = horaInicio * 60 + minutoInicio;
        int nuevoFin = horaFin * 60 + minutoFin;

        if (nuevoFin <= nuevoInicio) {
            return false;
        }

        if (actividad.horarioOcupado(
                r.getMesInicio(),
                r.getDiaInicio(),
                nuevoInicio,
                nuevoFin,
                r)) {
            return false;
        }

        actividad.eliminarReserva(r);
        r.cambiarHorario(horaInicio, minutoInicio, horaFin, minutoFin);
        actividad.agregarReserva(r);

        return true;
    }

    private int diasDelMes(int mes) {
        if (mes == 2) {
            return 28;
        }

        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        }

        return 31;
    }
}
