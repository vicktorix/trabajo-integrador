package com.argentina.programa.data;

import com.argentina.programa.modelos.Casa;
import com.argentina.programa.modelos.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioEstudiantes {
    private final Connection conexion;
    private final String TABLA_NOMBRE = "Estudiantes";
    private final String CREAR_TABLA_STMN = "create table "+ TABLA_NOMBRE +" (" +
            "id integer primary key, nombre character(30), genero character(1), especie character(20), " +
            "status character(20), casaId integer, foreign key(casaId) references Casas(id) ON UPDATE SET NULL);";

    private final String CREAR_ESTUDIANTE_STMN = "insert into " + TABLA_NOMBRE + " (" +
            "id, nombre, genero, especie, status) values(?,?,?,?,?)";
    private final String AGREGAR_ESTUDIANTE_A_CASA_STMN = "update " + TABLA_NOMBRE + " SET " +
            "casaId = ? where id = ?";
    private final String OBTENER_ESTUDIANTE_POR_ID_STMN = "select * from " + TABLA_NOMBRE +
            " inner join Casas on Casas.id = " + TABLA_NOMBRE +".casaId "+
            "where " + TABLA_NOMBRE +".id = ?";
    private final String OBTENER_ESTUDIANTES_POR_CASA_STMN = "select * from " + TABLA_NOMBRE +
            " inner join Casas on Casas.id = " + TABLA_NOMBRE +".id "+
            "where casaId = ?";

    public RepositorioEstudiantes(Connection conexion) {
        this.conexion = conexion;
        if (ProveedorConexionSqlite.tablaNoExiste(TABLA_NOMBRE)) {
            crearTabla();
        }
    }

    public void agregarEstudiante(Estudiante estudiante) {
        try {
            PreparedStatement pstmn = conexion.prepareStatement(CREAR_ESTUDIANTE_STMN);
            String nombreCasa = estudiante.getNombre().replace("'", "\'");
            pstmn.setInt(1, estudiante.getNumero());
            pstmn.setString(2, nombreCasa);
            pstmn.setString(3, String.valueOf(estudiante.getGenero()));
            pstmn.setString(4, estudiante.getEspecie().replace("'", "\'"));
            pstmn.setString(5, estudiante.getStatus().replace("'", "\'"));
            pstmn.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: en sentencia de creación de estudiante: \n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void agregarEstudianteaCasa(Estudiante estudiante, Casa casa) {
        try {
            PreparedStatement pstmn = conexion.prepareStatement(AGREGAR_ESTUDIANTE_A_CASA_STMN);
            pstmn.setInt(1, casa.getIdCasa());
            pstmn.setInt(2, estudiante.getNumero());
            pstmn.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: en sentencia de actualizacion de casaId: \n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Estudiante obtenerEstudiantePorId(int estudianteId) {
        Estudiante result =null;
        try {
            PreparedStatement pstmn = conexion.prepareStatement(OBTENER_ESTUDIANTE_POR_ID_STMN);
            pstmn.setInt(1, estudianteId);
            ResultSet rs = pstmn.executeQuery();
            while (rs.next()) {
                result = new Estudiante(rs.getInt(1), rs.getString(2), rs.getString(3).charAt(0),
                        rs.getString(4), rs.getString(5));
            }
        } catch (SQLException ex) {
            System.out.println("Error: en sentencia de obtener estudiante por Id: \n" + ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    private List<Estudiante> obtenerEstudiantesPorCasa(Casa casa){
        ArrayList<Estudiante> result = new ArrayList<>();
        try {
            PreparedStatement pstmn = conexion.prepareStatement(OBTENER_ESTUDIANTES_POR_CASA_STMN);
            pstmn.setInt(1, casa.getIdCasa());
            ResultSet rs = pstmn.executeQuery();
            while (rs.next()) {
                result.add(new Estudiante(rs.getInt(1), rs.getString(2), rs.getString(3).charAt(0),
                        rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.out.println("Error: en sentencia de obtener estudiantes por Casa: \n" + ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    private void crearTabla() {
        try {
            Statement stmt = conexion.createStatement();
            stmt.execute(CREAR_TABLA_STMN);
        } catch (SQLException ex) {
            System.err.println("No se pudo crear la tabla");
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }
}
