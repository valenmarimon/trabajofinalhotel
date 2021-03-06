<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="entidades.Servicio"%>
     
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

  
  <% int codservicio = Integer.parseInt(request.getParameter("cod_servicio"));  
      CtrlServicio ctrl = new CtrlServicio();
      Servicio s = new Servicio();
      s = ctrl.getByCodigo(codservicio);
	%>  

<%@ include file="header.jsp" %>

    <div class="container">

  

      

      <!-- Example row of columns -->
      <div class="row">

         <div class="col-lg-1 col">         
         </div>
         
         
        <div class="col-lg-10 col" >
         
 		 <form action="ModificarServicio" method="post" name="formRegistro" id="formRegistro">
		
      		<label for="titulo">Nombre:</label>
            <input type="text" id="nombre" name="nombre" class="form-control" value="<%= s.getNombre() %>">
            <br/>  
            
			
            <label for="nombre">Descripcion: </label>
            <textarea id="descripcion" name="descripcion" class="form-control" ><%= s.getDescripcion() %></textarea>
            
            
	       
	       <input type="hidden" value="<%= s.getCod_servicio() %>" name="codservicio" id="codservicio">
  
            <input type="submit" name="registrar" class="btn btn-lg btn-info btn-block" style="margin-top:10px" id="registrar" value="Actualizar Servicio">
 			
        </form>
        
        <form action="cambiarPrecioServicio.jsp" method="post" name="formRegistro" id="formRegistro">
	       <input type="hidden" value="<%= s.getCod_servicio() %>" name="codservicio" id="codservicio">
            <input type="submit" name="registrar" class="btn btn-lg btn-info btn-block" style="margin-top:10px" id="registrar" value="Cambiar precio">
 			<a href="admServicios.jsp" class="btn btn-info btn-lg btn-block" role="button">Regresar</a> <br> <br>
        </form>
          
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>