package com.argentina.programa.modelos;

import java.util.ArrayList;
import java.util.List;

public class Casa {
    private final int idCasa;
    private final String nombre;
    private final ArrayList<Estudiante> listaEstudiantes = new ArrayList();

    public Casa(int idCasa, String nombre) {
        this.idCasa = idCasa;
        this.nombre = nombre;
    }

    public boolean agregarEstudiante(Estudiante e) {
        if (listaEstudiantes.contains(e)) {
            return false;
        } else {
            listaEstudiantes.add(e);
            return true;
        }
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

    public List<Estudiante> getEstudiantesNoHumanos() {
        List<Estudiante> listaEstudiantesNoHumanos = new ArrayList<>();
        for(Estudiante e: listaEstudiantes) {
            if (!e.esHumano()) {
                listaEstudiantesNoHumanos.add(e);
            }
        }
        return listaEstudiantesNoHumanos;
    }

    public void listadoEstudiantesHumanos() {
        for(Estudiante e: listaEstudiantes) {
            if (e.getEspecie().equalsIgnoreCase(Estudiante.ESPECIE_HUMANO)) {
                System.out.println(e);
            }
        }
    }

    private int cantidadEstudiantesPorGenero(char genero){
        int cantidad = 0;
        for(Estudiante e: listaEstudiantes){
            if (e.getGenero() == genero) {
                cantidad ++;
            }
        }
        return cantidad;
    }
}
