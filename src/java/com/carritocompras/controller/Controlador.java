/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carritocompras.controller;

import com.carritocompras.model.Carrito;
import com.carritocompras.model.Producto;
import com.carritocompras.model.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonardo
 */
public class Controlador extends HttpServlet {

    ProductoDAO pDao = new ProductoDAO();
    Producto p = new Producto();
    
    List<Producto> productos = new ArrayList();
    List<Carrito> carritos = new ArrayList();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;
    int idp; //id del producto que realizo la accion
    Carrito carrito;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //accion que realiza el usuario
        String accion = request.getParameter("accion");
        //almacenamos los productos de la base de datos
        productos = pDao.listar();
        switch(accion){
            
            case "Comprar":
                totalPagar = 0.0;
                idp = Integer.parseInt(request.getParameter("id"));
                p = pDao.listarId(idp);
                item =+1;
                 carrito = new Carrito();
                carrito.setItem(item);
                carrito.setIdProducto(p.getId());
                carrito.setNombre(p.getNombre());
                carrito.setDescripcion(p.getDescripcion());
                carrito.setPrecioCompra(p.getPrecio());
                carrito.setCantidad(cantidad);
                carrito.setSubTotal(cantidad*p.getPrecio());
                carritos.add(carrito);
                for(Carrito c : carritos){
                    totalPagar = totalPagar + c.getSubTotal();
                }
                request.setAttribute("carrito", carritos);
                request.setAttribute("totalPagar", totalPagar);
                
                request.getRequestDispatcher("carritos.jsp").forward(request, response);
            
                break;
            case "AgregarCarrito":
                idp = Integer.parseInt(request.getParameter("id"));
                p = pDao.listarId(idp);
                item =+1;
                carrito = new Carrito();
                carrito.setItem(item);
                carrito.setIdProducto(p.getId());
                carrito.setNombre(p.getNombre());
                carrito.setDescripcion(p.getDescripcion());
                carrito.setPrecioCompra(p.getPrecio());
                carrito.setCantidad(cantidad);
                carrito.setSubTotal(cantidad*p.getPrecio());
                carritos.add(carrito);
                request.setAttribute("contador", carritos.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "carrito":
                totalPagar = 0.0;
                for(Carrito c : carritos){
                    totalPagar = totalPagar + c.getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", carritos);
                request.getRequestDispatcher("carritos.jsp").forward(request, response);
                break;
                
            default:
                //setAttribute carga los productos en la vista
                //el primer productos es como se va a nombrar en el jsp
                //el segundo es la lista donde almacenamos los productos
                request.setAttribute("productos", productos);
                //cargamos los datos en pantalla del jsp y su correspondiente request y response
                request.getRequestDispatcher("index.jsp").forward(request, response);

        
        }//switch
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
