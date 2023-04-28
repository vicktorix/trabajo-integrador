package com.argentina.programa.proceso;

import com.argentina.programa.data.ProveedorConexionSqlite;
import com.argentina.programa.data.RepositorioCasas;
import com.argentina.programa.data.RepositorioEstudiantes;
import com.argentina.programa.modelos.Casa;
import com.argentina.programa.modelos.Estudiante;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcesoAplicacion {
    private final String DB_NAME = "baseDeDatos.sqlite";
    private final String DB_DIR = "data/";
    private final Scanner miEscanner = new Scanner(System.in);
    private ArrayList<Estudiante> lista;
    private final Hogwarts hogwarts;

    private Connection miConexion;

    public ProcesoAplicacion() {
        hogwarts = new Hogwarts();
    }

    public void iniciarAplicacion() {
        encabezadoApp();

        String nombreArchivo = leerNombreArchivo();
        procesarArchvio(nombreArchivo);

        agregarEstudiantesHogwarts();
        cantidadEstudiantesPorCasa();
        listadoEstudiantesNoHumanos();

        persistirCasas();
        persistirEstudiantesNoHumanos();
    }

    private static void encabezadoApp() {
        System.out.println("Cargador de Datos de Hogwards");
        System.out.println("=============================");
        System.out.println();
    }

    private String leerNombreArchivo() {
        System.out.print("Ingrese el nombre del archivo de datos: ");
        return miEscanner.nextLine();
    }

    private void procesarArchvio(String nombreArchivo) {
        System.out.println("\nProcesando archivo...");
        ProcesadorArchivoCsv procArchivo = new ProcesadorArchivoCsv(nombreArchivo, true);
        lista = new ArrayList<>();
        lista.addAll(procArchivo.procesarArchivoConValidacion());
        System.out.println("Proceso finalizado, " + lista.size() + " estudiantes leídos.");
    }

    private void agregarEstudiantesHogwarts() {
        for (Estudiante e : lista) {
            hogwarts.agregarEstudiante(e);
        }
    }

    private void cantidadEstudiantesPorCasa() {
        System.out.println("Cantidad de Estudiantes por casa: ");
        for (Casa casa : hogwarts.getCasas()) {
            System.out.println("Casa: " + casa + " ==> " + casa.getCantidadEstudiantes() + " estudiantes");
        }
    }

    private void listadoEstudiantesNoHumanos() {
        System.out.println("Estudiantes no humanos: ");
        for (Casa casa: hogwarts.getCasas()) {
            for (Estudiante estudiante: hogwarts.getCasa(casa.getNombre()).getEstudiantes()) {
                if (!estudiante.esHumano()) {
                    System.out.println(estudiante);
                }
            }
        }
    }

    private void persistirCasas() {
        RepositorioCasas repositorio = new RepositorioCasas(getConexion());
        for (Casa casa: hogwarts.getCasas()) {
            Casa casaGuardada = repositorio.getCasa(casa.getIdCasa());
            if (casaGuardada == null) {
                repositorio.agregarCasa(casa);
            }
        }
        System.out.println("Tabla Casas en DB");
    }

    private void persistirEstudiantesNoHumanos() {
        RepositorioEstudiantes repositorio = new RepositorioEstudiantes(getConexion());
        for (Casa casa : hogwarts.getCasas()) {
            for (Estudiante estudiante: hogwarts.getCasa(casa.getNombre()).getEstudiantesNoHumanos()){
                if (repositorio.obtenerEstudiantePorId(estudiante.getNumero()) == null) {
                    repositorio.agregarEstudiante(estudiante);
                    repositorio.agregarEstudianteaCasa(estudiante, casa);
                }
            }
        }
        System.out.println("Tabla Estudiantes en DB");
    }

    private Connection getConexion(){
        if (miConexion == null){
            miConexion = ProveedorConexionSqlite.conectar(DB_DIR, DB_NAME);
        }
        return miConexion;
    }
}
