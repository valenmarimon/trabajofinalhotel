<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="entidades.Recepcionista"%>
     <%@page import="entidades.Cliente"%>
     <%@page import="entidades.Persona"%>
      <%@page import="entidades.Habitacion"%>
     <%@page import="negocio.CtrlHabitacion"%>
     <%@page import="entidades.Reserva"%>
     <%@page import="negocio.CtrlReserva"%>
     <%@page import="negocio.CtrlServicio"%>
     
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="imgs/fp-icon.png">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Oxygen&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="bootstrap/js/validarRegistro.js"></script>
    <title>Four:Points:By::SHERATON</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="bootstrap/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap/css/justified-nav.css" rel="stylesheet">

  
  </head>

  <body>
  

<%@ include file="header.jsp" %>

    <div class="container">

  

      

      <!-- Example row of columns -->
      <div class="row">

         <div class="col-lg-2 col">         
         </div>
         
         
        <div class="col-lg-8 col" >
         <% 
      			Reserva reserva =(Reserva)request.getAttribute("reserva");    		
        		
      		%>
      		<div class="alert alert-success">
   			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    		<strong>Reserva realizada</strong> .
  			</div>
         
          
           <p><h2 align="center" style="color:  #4d0000"> <b>Servicios Adicionales</b></h2><p><br>

	 <table class="table table-hover">
    <thead>
      <tr>
        <th><h2>Nombre</h2></th>
        <th><h2>Descripcion</h2></th>
        <th><h2>Precio</h2></th>
        <th><h2>Agregar</h2></th>
        <th><h2>Eliminar</h2></th>
       
        
        
      </tr>
    </thead>
    
    <tbody>
      <tr>
<%
	
    		CtrlServicio ctrl = new CtrlServicio();
    
    		
    		//PUEDO HACER TMB
			// ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
			// habitaciones = ctrl.Listar();

	for (int indice = 0; indice < ctrl.listarServicios().size(); indice++){
	%>  
		<td><h4><%= ctrl.listarServicios().get(indice).getNombre() %></h4></td>
	   <td><h4><%= ctrl.listarServicios().get(indice).getDescripcion() %></h4></td>
	   <td><h4>€ <%= ctrl.listarServicios().get(indice).getPrecio()%></h4></td>
	   
	   <td><form method="post" action="modificarServicio.jsp">
           <input type="hidden" id="cod_servicio" name="cod_servicio" value="<%=ctrl.listarServicios().get(indice).getCod_servicio()%>" >
           <button type="submit" class="btn2" name="modificarservicio" id="modificarservicio"><span class="glyphicon glyphicon-pencil" style="color: blue;">
           </span></a></form></td>
           
	   <td><form method="post" action="BajaServicio">
           <input type="hidden" id="cod_servicio" name="cod_servicio" value="<%= ctrl.listarServicios().get(indice).getCod_servicio()%>" >
           <button type="submit" class="btn2" name="bajaservicio" id="bajaservicio" onClick="return confirm('¿Esta Seguro que deseas eliminar este servicio?')">
           <span class="glyphicon glyphicon-remove" style="color: red"></span></a></form></td>
           
       
	
	</tr>
	<%
	
}


      %>
      
     
    </tbody>
  </table>

    <a href="index.jsp" class="btn btn-info btn-lg btn-block" role="button">Finalizar</a>
    <br> <br>  	
      			
   <br> <br>
   
          
        </div>
          
          
        <div class="col-lg-2 col">       
        </div>
          
          
        
        
      </div>

    

    </div> <!-- /container -->


		  		<!-- footer -->
      	<%@ include file="footer.jsp" %>
      

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>