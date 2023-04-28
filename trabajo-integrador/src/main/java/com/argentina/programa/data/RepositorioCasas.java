package com.argentina.programa.data;

import com.argentina.programa.modelos.Casa;

import java.sql.*;
import java.util.Arrays;

public class RepositorioCasas {

    private final Connection conexion;
    private final String TABLA_NOMBRE = "Casas";
    private final String CREAR_TABLA_STMN = "create table "+ TABLA_NOMBRE +" (" +
            "id integer primary key, nombre character(30)" +
            ");";
    private final String OBTENER_CASA_POR_ID_STMN = "select nombre from " + TABLA_NOMBRE +" where id = ?";
    private final String CREAR_CASA_STMN = "insert into " + TABLA_NOMBRE + " (id, nombre) values(?,?)";

    public RepositorioCasas(Connection conexion){
        this.conexion = conexion;
        if (ProveedorConexionSqlite.tablaNoExiste(TABLA_NOMBRE)) {
            crearTabla();
        }
    }

    public void agregarCasa(Casa unaCasa) {
        try {
            PreparedStatement pstmn = conexion.prepareStatement(CREAR_CASA_STMN);
            String nombreCasa = unaCasa.getNombre().replace("'", "\'");
            pstmn.setInt(1, unaCasa.getIdCasa());
            pstmn.setString(2, nombreCasa);
            pstmn.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: en sentencia de creación de casa: \n" + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public Casa getCasa(int idCasa){
        Casa result = null;
        try {
            PreparedStatement pstmt = conexion.prepareStatement(OBTENER_CASA_POR_ID_STMN);
            pstmt.setInt(1, idCasa);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                result = new Casa(idCasa, nombre);
            }

        } catch (SQLException ex) {
            System.out.println("Error: en sentencia de obtener Casa: \n" + ex.getMessage());
            ex.printStackTrace();
        }
        return result;

    }

    private void crearTabla() {
        try {
            Statement stmt = conexion.createStatement();
            stmt.execute(CREAR_TABLA_STMN);
            System.out.println("Tabla" + TABLA_NOMBRE + " creada");
        } catch (SQLException ex) {
            System.err.println("No se pudo crear la tabla");
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }

}
