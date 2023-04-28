package com.argentina.programa.modelos;

import java.util.ArrayList;
import java.util.List;

public class Casa {
    private final int idCasa;
    public final String nombre;
    private final ArrayList<Estudiante> listaEstudiantes;

    public Casa(int idCasa, String nombre) {
        listaEstudiantes = new ArrayList<>();
        this.idCasa = idCasa;
        this.nombre = nombre;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (listaEstudiantes.contains(estudiante)) {
            return;
        }
        listaEstudiantes.add(estudiante);
    }

    public int getIdCasa() {
        return idCasa;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadEstudiantes() {
        return listaEstudiantes.size();
    }

    public int getCantidadEstudiantesMasculinos() {
        return cantidadEstudiantesPorGenero(Estudiante.GENERO_MASCULINO);
    }

    public int getCantidadEstudiantesFemeninos() {
        return cantidadEstudiantesPorGenero(Estudiante.GENERO_FEMENINO);
    }

    public int getCantidadEstudiantesNoBinarios() {
        return cantidadEstudiantesPorGenero(Estudiante.GENERO_NO_BINARIO);
    }

    public List<Estudiante> getEstudiantes() {
        return listaEstudiantes;
    }

    public List<Estudiante> getEstudiantesNoHumanos() {
        List<Estudiante> listaEstudiantesNoHumanos = new ArrayList<>();
        for(Estudiante estudiante: listaEstudiantes) {
            if (!estudiante.esHumano()) {
                listaEstudiantesNoHumanos.add(estudiante);
            }
        }
        return listaEstudiantesNoHumanos;
    }

    public void listadoEstudiantesHumanos() {
        for(Estudiante estudiante: listaEstudiantes) {
            if (estudiante.esHumano()) {
                System.out.println(estudiante);
            }
        }
    }

    private int cantidadEstudiantesPorGenero(char genero){
        int cantidad = 0;
        for(Estudiante estudiante: listaEstudiantes){
            if (estudiante.getGenero() == genero) {
                cantidad ++;
            }
        }
        return cantidad;
    }
}
