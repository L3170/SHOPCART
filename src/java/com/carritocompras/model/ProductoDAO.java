/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carritocompras.model;

import com.carritocompras.config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonardo
 */
public class ProductoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement pstm;
    ResultSet rs;
//==============================================================================    
    
    public List listar(){
        List<Producto> productos = new ArrayList();
        String sql = "select * from producto where 1";
        try {
            con = cn.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                productos.add(p);
                
            }
        } catch (Exception e) {
        }
        return productos;
    }
//==============================================================================    
    public Producto listarId(int id){
        String sql = "select * from producto where idProducto = "+id;
        Producto p = new Producto();
        try {
            con = cn.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
            }
            
            
            
        } catch (Exception e) {
        }
        return p;
    }
    
//==============================================================================    
    
    public void listarImg(int id, HttpServletResponse response){
        String sql = "Select * from producto where idProducto = "+id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        
        try {
            outputStream = response.getOutputStream();
            
            con = cn.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            if(rs.next()){
                inputStream = rs.getBinaryStream("Foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while((i = bufferedInputStream.read())!= -1){
                bufferedOutputStream.write(i);
            }
            
        } catch (Exception e) {
        }
    
    }
//==============================================================================    
    
    
    
    
}
