/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examen2_leimanwu.baseDeDatos;

/**
 *
 * @author leymanwu
 */
import java.sql.*;

public class ConexionBDD {
    private static final String URL = "jdbc:mysql://localhost:3306/libreria_digital";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection conectarBDD() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    
}
