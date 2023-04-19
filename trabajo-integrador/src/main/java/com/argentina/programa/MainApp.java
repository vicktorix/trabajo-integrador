package com.argentina.programa;

import com.argentina.programa.modelos.Estudiante;
import com.argentina.programa.proceso.Hogwarts;
import com.argentina.programa.proceso.ProcesadorArchivoCsv;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        Scanner miEscanner = new Scanner(System.in);

        // Solo interfaz de usuario
        System.out.println("Cargador de Datos de Hogwards");
        System.out.println("=============================");
        System.out.println();

        // Ingrese del nombre del archivo de datos csv a procesar
        System.out.print("Ingrese el nombre del archivo de datos: ");
        String nombreArchivo = miEscanner.nextLine();

        System.out.println("\nProcesando archivo...");

        ProcesadorArchivoCsv procArchivo = new ProcesadorArchivoCsv(nombreArchivo);
        List<Estudiante> lista = procArchivo.procesarArchivoConValidacion();

        Hogwarts hogwarts = new Hogwarts();
        for(Estudiante e : lista){
            hogwarts.agregarEstudiante(e);
        }

        System.out.println("Cantidad de Estudiantes por casa: ");
        for (String casa : new String[] {"Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"}){
            System.out.println("Casa: " + casa + " ==> " + hogwarts.getCasa(casa).getCantidadEstudiantes() + " estudiantes");
        }

    }
}
