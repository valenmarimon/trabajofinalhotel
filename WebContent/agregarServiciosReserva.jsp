<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="entidades.Recepcionista"%>
     <%@page import="entidades.Cliente"%>
     <%@page import="entidades.Persona"%>
     <%@page import="entidades.Servicio"%>
     <%@page import="entidades.Reserva"%>
     <%@page import="java.util.ArrayList"%>
     <%@page import="java.text.DecimalFormat"%>
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

  <script src="js/jquery-1.12.3.min.js"></script>
  <script src="js/agregarServiciosReservas.js"></script>
  </head>

  <body>

<%@ include file="header.jsp" %>

    <div class="container">

  

      

      <!-- Example row of columns -->
      <div class="row">
      
      <% 
      			String mensajeError=(String)request.getAttribute("mensajeError");
        		if(mensajeError!=null){
      		%>
      			<div class="alert alert-danger" role="alert">
        			<strong>Error!</strong> <%=mensajeError %>
      			</div>
      		<%
        		}
      		%>

         <div class="col-lg-1 col">         
         </div>
         
         
        <div class="col-lg-10 col" >
         
          
           
      	<%  Persona user = (Persona)session.getAttribute("userSession");
            if(user == null || user.getTipo() != null){ 
            	%> <h1> No tienes permisos para acceder a esta sección. <br>
            	 Debes ingresar como Admin</h1> <%
            
            }else{ 
            	
            	int idReserva = 0;
                if(request.getParameter("idreserva") != null){
                	
            	    	idReserva = Integer.valueOf(request.getParameter("idreserva"));
            	}else{
            		Reserva reservaActual = (Reserva)session.getAttribute("reservaActual");
            		idReserva = reservaActual.getId_reserva();
            	}
            	
            	
           %>  
           
           
           <h1 style="text-align: center; color:  #4d0000"> <b>Agregar Servicios a la Reserva: <%=idReserva%> </b></h1> <br>
      <div class="row" style="height:100%">
    	<div class="col-sm-6">
        	<form action="AgregarServiciosReserva" method="post" id="formReserva">
                <label for="txtDescripcion" class="sr-only">Servicio</label>
                <input type="text" id="txtDescripcion" name="txtDescripcion" class="form-control" placeholder="Descripción" autofocus="autofocus">
                <label for="txtDescripcion" id="errorDescripcion" style="color:#FF0004"></label>
                
                <label for="txtCod" class="sr-only">Código Servicio</label>
                <input type="text" id="txtCod" name="txtCod" class="form-control" placeholder="Código">
                <label for="txtCod" id="errorCod" style="color:#FF0004"></label>
                
                <input type="hidden" id="idreserva" name="idreserva" value="<%=idReserva%>" >
                           
               
                
                <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
            </form>
            <% 
      			String mensaje=(String)request.getAttribute("mensaje");
        		if(mensaje!=null){
      		%>
      			<div class="alert alert-danger" role="alert">
        			<strong>Error!</strong> <%=mensaje %>
      			</div>
      		<%
        		}
      		%>
        </div>
        <div class="col-sm-6">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Código</th>
						<th>Descripcion</th>
						<th>Precio</th>
					</tr>
				</thead>
				<tbody id="cuerpo">
					<tr>
						<td colspan="3"><h2>Comience a escribir para obtener los Servicios</h2></td>
					</tr>
				</tbody>
			</table>      
		</div>
	</div>
	 <% 
      			String mensajeConfirmacion=(String)request.getAttribute("mensajeConfirmacion");
        		if(mensajeError!=null){
      		%>
      			<div class="alert alert-info" role="alert">
        			<strong>Todo listo!</strong> <%=mensajeConfirmacion %>
      			</div>
      		<%
        		}
      		%>
	
	
	<%Reserva reserva= (Reserva)session.getAttribute("reservaActual"); 
	if(reserva!=null){	
	%>
	<div class="row" style="text-align: center;">
		<h1>SUS SERVICIOS</h1>
		<table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Código</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Borrar</th>
              </tr>
            </thead>
            <tbody>
            
            <%
           
            int i=1;
            float total=0;
            for(Servicio servicio:reserva.getServicios() ){
            float subtotal=servicio.getPrecio();
            %>            
              <tr>
                <td><%=i %></td>
                <td><%=servicio.getCod_servicio() %></td>
                <td><%=servicio.getDescripcion() %></td>
                <td><%=servicio.getPrecio() %></td>
                <td><a class="btn btn-danger" href="borrarServicio?nro=<%=i %>">X</a></td>
              </tr>
            <%	i++;
            	total+=subtotal;
            	
            }%>
            <tr>
                <td style="text-align: right;" colspan="5"><h4>IMPORTE TOTAL DE LOS SERVICIOS</h4></td>
                <td><h4><%= total %></h4></td>
              </tr>
            </tbody>
          </table>
	</div>
	<div class="row" style="float: right;">
		<a class="btn btn-danger" href="borrarReservaServicios">BORRAR SERVICIOS</a>
		<a class="btn btn-primary btn-lg" href="confirmarReservaServicios">CONFIRMAR SERVICIOS</a>
	</div>
	<%} %>
           <br><br><br><br>
           
           
           
            	
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
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>