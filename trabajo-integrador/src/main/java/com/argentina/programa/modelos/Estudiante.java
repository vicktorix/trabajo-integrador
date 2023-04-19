package com.argentina.programa.modelos;

public class Estudiante {
    public static final char GENERO_MASCULINO = 'M';
    public static final char GENERO_FEMENINO = 'F';
    public static final char GENERO_NO_BINARIO = 'X';
    public static final String ESPECIE_HUMANO = "Human";
    public static final String ESPECIE_MITAD_HUMANO_GIGANTE = "Half-Human/Half-Giant";
    public static final String ESPECIE_FANTASMA = "Ghost";
    public static final String ESPECIE_CENTAURO = "Centaur";
    public static final String ESPECIE_HOMBRELOBO = "Werewolf";
    public static final String ESPECIE_ELFO = "Elf";
    private final int numero;
    private final char genero;
    private final String nombre, especie, status;
    private String nombreCasa;

    public Estudiante(int numero, String nombre, char genero, String especie, String status) {
        this.numero = numero;
        this.genero = genero;
        this.nombre = nombre;
        this.especie = especie;
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public char getGenero() {
        return genero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreCasa() {
        return nombreCasa;
    }

    public void setNombreCasa(String nombreCasa) {
        this.nombreCasa = nombreCasa;
    }

    public String getEspecie() {
        return especie;
    }

    public String getStatus() {
        return status;
    }

    public boolean esHumano() {
        return especie.equalsIgnoreCase(ESPECIE_HUMANO);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder()
                .append("Numero: ")
                .append(numero)
                .append(", Nombre: ")
                .append(nombre)
                .append(", Casa: ")
                .append(nombreCasa)
                .append(", Genero: ")
                .append(genero)
                .append(", Especie: ")
                .append(especie)
                .append(", Estatus: ")
                .append(status);
        return str.toString();
    }

    public static Estudiante parseStringArray(String[] datos) {
        try {
            int numero = Integer.parseInt(datos[0]);
            String nombre = datos[1].trim();
            char genero = (datos[2].equals("Female") || datos[2].equals("Male")) ? datos[2].charAt(0) : 'X';
            String nombreCasa = datos[4].trim();
            String especie = datos[5].trim();
            String blodStatus = datos[6].trim();

            Estudiante e = new Estudiante(numero, nombre, genero, especie, blodStatus);
            e.setNombreCasa(nombreCasa);
            return e;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

    }
}
