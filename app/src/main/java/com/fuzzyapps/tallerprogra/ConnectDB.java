package com.fuzzyapps.tallerprogra;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.*;
import java.util.*;

/**
 * Created by Geovani on 22/10/2016
 */
public class ConnectDB {
    Connection conexion;
    Statement statement;

    public ConnectDB() {
        String cadena = "select * from s_REGION order by 1";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String UserName = "tallerprogra";
        String Password = "navia2016 ";
        String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
        try {
            Class.forName(driver).newInstance();
            conexion = DriverManager.getConnection(sourceURL, UserName, Password);
            statement = conexion.createStatement();
        } catch (Exception sqle) {
            System.err.println(sqle);
        }
    }

    public void closeConnection() {
        if (conexion != null)
            try {
                statement.close();
                conexion.close();
                conexion = null;
            } catch (SQLException ex) {
                System.out.println("\nSQLException-------------\n");
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message : " + ex.getMessage());
            }
    }

    ResultSet getData(String Consulta) {
        ResultSet rs = null;
        try {
            //System.out.println(Consulta);
            rs = statement.executeQuery(Consulta);
        } catch (SQLException ex) {
            System.out.println("\nSQLException-------------\n");
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("Message : " + ex.getMessage());
        }
        return rs;
    }

    int InsertaDatos(String Consulta) {
        int NroReg = -1;
        try {
            NroReg = statement.executeUpdate(Consulta);
        } catch (SQLException ex) {
            System.out.println("\nSQLException-------------\n");
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("Message : " + ex.getMessage());
        }
        return NroReg;
    }

    String ValRegistro(String Consulta, String Campo) {
        String Cadena = null;
        try {
            ResultSet rs = statement.executeQuery(Consulta);
            if (rs.next())
                Cadena = rs.getString(Campo);
            rs.close();
        } catch (SQLException ex) {
            System.out.println("\nSQLException-------------\n");
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("Message : " + ex.getMessage());
        }
        return Cadena;
    }

    boolean Existe(String Consulta) {
        boolean existe = false;
        try {
            ResultSet rs = statement.executeQuery(Consulta);
            if (rs.next())
                existe = true;
            else
                existe = false;
            rs.close();
        } catch (SQLException ex) {
            System.out.println("\nSQLException-------------\n");
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("Message : " + ex.getMessage());
        }
        return existe;
    }
}
