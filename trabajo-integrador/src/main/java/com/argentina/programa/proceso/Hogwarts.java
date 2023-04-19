package com.argentina.programa.proceso;

import com.argentina.programa.modelos.Casa;
import com.argentina.programa.modelos.Estudiante;
import java.util.HashMap;
import java.util.Map;

public class Hogwarts {

    private final Map<String, Casa> casas = new HashMap<>();

    public Hogwarts() {
        casas.put("Gryffindor", new Casa(1,"Gryffindor"));
        casas.put("Slytherin", new Casa(2, "Slytherin"));
        casas.put("Hufflepuff", new Casa(3, "Hufflepuff"));
        casas.put("Ravenclaw", new Casa(4, "Ravenclaw"));
    }

    public void agregarEstudiante(Estudiante e) {
        if (casas.containsKey(e.getNombreCasa())) {
            casas.get(e.getNombreCasa()).agregarEstudiante(e);
        }
        else
            throw new RuntimeException("Error: El nombre de la casa no existe.");

    }

    public Casa getCasa(String nombre){
        return casas.get(nombre);
    }

}
