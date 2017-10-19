<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="entidades.Persona"%>
    <%@page import="negocio.CtrlServicio"%>
    <%@page import="entidades.Servicio"%>
    <%@page import="negocio.CtrlReserva"%>
    
    
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
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
 

</script>
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

         <div class="col-lg-1 col">         
         </div>
         
         
        <div class="col-lg-10 col" >
         <% 
      			String mensaje=(String)request.getAttribute("mensaje");
        		if(mensaje!=null){
      		%>
      		<div class="alert alert-success">
   			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    		<strong><%=mensaje %></strong> . 
  			</div>
      		
      			
      		<%
        		}
      			
      		%>
         
          
           
      		<%  Persona user = (Persona)session.getAttribute("userSession");
            if(user == null){
            	%> <h1> Debes estar logueado para poder realizar reservas
            	 <a href="login.jsp">Login</a></h1> <%
            } else {
            	%>
            	
		   <a href="realizarReserva.jsp" class="btn btn-info btn-lg btn-block" role="button">Nueva Reserva</a><br>
	       

	<table class="table table-hover">
    <thead>
      <tr>
        <th><h2>Fecha desde</h2></th>
        <th><h2>Fecha hasta</h2></th>
        <th><h2>Habitacion</h2></th>        
        <th><h2>Estado</h2></th>
        <th><h2>Cancelar</h2></th>
      </tr>
    </thead>
    
    <tbody>
      <tr>
<%
	    CtrlReserva ctrlR = new CtrlReserva();
    
	for (int indice = 0; indice < ctrlR.listarReservas().size(); indice++){
		if(ctrlR.listarReservas().get(indice).getCliente().getDni()==user.getDni()){		
	%>  
		<td><h4><%= ctrlR.listarReservas().get(indice).getFecha_desde() %></h4></td>
	   <td><h4><%= ctrlR.listarReservas().get(indice).getFecha_hasta() %></h4></td>
	   <td><% for (int i = 0; i < ctrlR.listarReservas().get(indice).getHabitaciones().size(); i++){   %>
		 <h4><%=ctrlR.listarReservas().get(indice).getHabitaciones().get(i).getNumero() %> (
		 <%=ctrlR.listarReservas().get(indice).getHabitaciones().get(i).getDescripcionTipo() %> )</h4>
		
		  <% 
	   }
	   
		   %></td>
	   <td><h4><%= ctrlR.listarReservas().get(indice).getEstado().getDescripcionEstado() %></h4></td>
	   <% if(ctrlR.listarReservas().get(indice).getEstado().getId()==1){ %>
	   <td><h4> <form method="post" action="CancelarReserva">
            <input type="hidden" id="idreserva" name="idreserva" value="<%= ctrlR.listarReservas().get(indice).getId_reserva() %>">
            <input type="hidden" id="persona" name="persona" value="cliente">
            <button type="submit" class="btn2" name="cancelar" id="cancelar" onClick="return confirm('¿Está Seguro que desea cancelar esta reserva?')">
            <span class="glyphicon glyphicon-remove" style="color: red;"></span></a></form></h4></td>
	    <%} %>
	
	</tr>
	<%
	
		}
		
	}
	


      %>
     
     
    </tbody>
  </table>
            	
            	<% 
            } %>
      			
   <br> <br>
   
          
        </div>
          
          
        <div class="col-lg-1 col">       
        </div>
          
          
        
        
      </div>

    

    </div> <!-- /container -->


		  		<!-- footer -->
      	<%@ include file="footer.jsp" %>
 
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
 
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>