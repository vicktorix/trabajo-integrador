package com.argentina.programa.data;

import java.io.File;
import java.sql.*;
import java.util.Arrays;

public class ProveedorConexionSqlite {

    private static Connection conexion;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: No se encuentra el driver de conexión...");
            System.err.println(ex);
            System.exit(-1);
        }
    }

    public static Connection conectar(String directorio, String nombreArchivo){
        checkDbDirectory(directorio);
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection("jdbc:sqlite:" + directorio + nombreArchivo);
                checkDbExiste(directorio, nombreArchivo);
            }
        } catch (SQLException ex) {
            System.err.println("Error: no se puede conectar a la base de datos...");
            System.err.print(ex);
            System.exit(-1);
        }

        return conexion;
    }

    public static void checkDbExiste(String directorio, String nombreArchivo) {
        File fileDb = new File (directorio, nombreArchivo);
        if (!fileDb.exists()) {
            try {
                if (conexion != null) {
                    DatabaseMetaData meta = conexion.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean tablaNoExiste(String tableName) {
        try {
            DatabaseMetaData md = conexion.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);
            rs.next();
            return rs.getRow() <= 0;
        } catch (SQLException ex) {
            System.err.println("Error al verificar la existencia de la tabla");
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }

        return true;
    }

    private static void checkDbDirectory(String directorio){
        File path = new File(directorio);
        if (!path.exists()) {
                if (!path.mkdirs()) {
                    System.err.println("No se pudo crear el directorio de la DB");
                    System.exit(-1);
                }

        }
     }
}
