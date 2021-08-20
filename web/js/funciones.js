/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){ //representa la funcion principal
    //ponemos tr porque dentro de tr se encuentra el btnDelete
    $("tr #btnDelete").click(function (){  //btnDelete hace referencia al id del boton delete en compras.jsp
        var idProducto = $(this).parent().find("#idProducto").val(); //capturamos el id del producto
        eliminar(idProducto);
    });
    function eliminar(idProducto){
        var url = "Controlador?accion=Delete";
        $.ajax({
            type:'POST',
            url: url,
            data: "idProducto="+idProducto,//idProducto que viene como parametro
            success: function (data, textStatus, jqXHR){
                alert("Registro eliminado!");
            }
        })
    }
    
});

