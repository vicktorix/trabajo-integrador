package com.argentina.programa.proceso;

import com.argentina.programa.modelos.Estudiante;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcesadorArchivoCsv {
    private final String ESTUDIANTE = "Student";
    private final String CHARSET = "UTF-8";

    private final String nombreArchivo;
    private boolean debugmode = false;

    public ProcesadorArchivoCsv(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public ProcesadorArchivoCsv(String nombreArchivo, boolean debugmode) {
        this.nombreArchivo = nombreArchivo;
        this.debugmode = debugmode;
    }

    public ArrayList<Estudiante> procesarArchivo(){
        ArrayList<Estudiante> lista = new ArrayList<>();
        Path f = Paths.get(nombreArchivo);

        if (Files.exists(f) && Files.isReadable(f)) {

            try (Scanner miEscaner = new Scanner(f, CHARSET)) {

                int contadorLineas = 1;

                while (miEscaner.hasNextLine()) {
                    String linea = miEscaner.nextLine();
                    if (contadorLineas > 1) {
                        String[] datos = separarLinea(linea);
                        if (debugmode) {
                            System.out.println("Procesando línea: " + contadorLineas);
                        }
                        if (datos[3].trim().contains(ESTUDIANTE)){
                            Estudiante e = Estudiante.parseStringArray(datos);
                            lista.add(e);
                        }
                    }
                    contadorLineas++;
                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        else {
            System.out.println("Error: El archivo no existe.");
        }
        return lista;

    }

    public List<Estudiante> procesarArchivoConValidacion(){
        List<Estudiante> lista = new ArrayList<>();
        Path f = Paths.get(nombreArchivo);
        String regex = "[0-9]+,[a-zA-Z ().-]+,[a-zA-Z]*,[a-zA-Z0-9 |()-]*,[a-zA-Z ]+,[a-zA-Z ()/-]*,[a-zA-Z ()|-]*";
        Pattern pattern = Pattern.compile(regex);

        if (Files.exists(f) && Files.isReadable(f)) {

            try (Scanner miEscaner = new Scanner(f, CHARSET)) {
                int contadorLineasInvalidas = 0;
                int contadorLineas = 1;

                while (miEscaner.hasNextLine()) {
                    String linea = miEscaner.nextLine();
                    Matcher matcher = pattern.matcher(linea);
                    if (matcher.matches()) {
                        String[] datos = separarLinea(linea);
                        if (debugmode) {
                            System.out.println("Procesando línea: " + contadorLineas);
                        }
                        if (datos[3].trim().contains(ESTUDIANTE)){
                            Estudiante e = Estudiante.parseStringArray(datos);
                            lista.add(e);
                        }
                    }
                    else {
                        if (debugmode) {
                            System.out.println("Línea inválida: " + contadorLineas + " | " + linea);
                        }
                        contadorLineasInvalidas++;
                    }
                    contadorLineas ++;
                }
                if (contadorLineasInvalidas > 0 && debugmode){
                    System.err.println("Líneas inválidas encontradas: " + contadorLineasInvalidas );
                }

            }
            catch(IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }

        }
        else {
            System.err.println("Error: El archivo no existe.");
        }
        return lista;

    }


    private String[] separarLinea(String linea) {
        if (!linea.equals("") && !linea.equals("\n")) {
            return linea.split("\\,");
        }

        return new String[0];

    }
}
