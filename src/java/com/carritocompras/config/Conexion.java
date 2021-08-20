/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carritocompras.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Leonardo
 */
public class Conexion {
    Connection con;
    String url = "jdbc:mysql://localhost:3306/carrito";
    String user = "root";
    String pass = "";
    
    public Connection getConnection(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
    }catch(Exception ex){
    
    }
    return con;
    
    }
}
