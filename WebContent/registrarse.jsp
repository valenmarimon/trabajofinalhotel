<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
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

         <div class="col-lg-3 col">         
         </div>
         
         
        <div class="col-lg-6 col" >
        
           <%
          String mensaje = (String)request.getAttribute("mensaje");
    		if(mensaje != null){	%>
    			<div class="alert alert-danger">
   			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    		<strong><%=mensaje %></strong> . 
  			</div>
    		<%		
    		}
          
          %>
         
          
          <form action="RegistrarUsuario" method="post" name="formRegistro" id="formRegistro"  >
            <h1 align="center">Ingrese sus datos:</h1>
            
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" class="form-control" placeholder="Usuario" required autofocus>
            <label for="usuario" style="color:red" id="msjUsuario"></label><br/>  
                    
            <label for="contrasena">Contraseña:</label>
            <input type="password" id="pass_1" name="contrasena" class="form-control" placeholder="Contraseña" required>
            <label for="contrasena" style="color:red" id="msjPass_1"></label><br/>
            
            <label for="contrasena2">Repita la contraseña:</label>
            <input type="password" id="pass_2" name="contrasena2" class="form-control" placeholder="Repita la contraseña" required>
            <label for="contrasena2" style="color:red" id="msjPass_2"></label><br/>
            
            <label for="nombre">Nombre: </label>
            <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Nombre" required>
            <label for="nombre" style="color:red" id="msjNombre"></label><br/>
            
            <label for="apellido">Apellido: </label>
            <input type="text" id="apellido" name="apellido" class="form-control" placeholder="Apellido" required>
            <label for="apellido" style="color:red" id="msjApellido"></label><br/>
            
            
            <label for="dni">Dni: </label>
            <input type="text" id="dni" name="dni" class="form-control" placeholder="Dni" required>
            <label for="dni" style="color:red" id="msjDni"></label><br/>
            
            <label for="email">E-mail: </label>
            <input type="text" id="email" name="email" class="form-control" placeholder="E-mail" required>
            <label for="email" style="color:red" id="msjEmail"></label><br/>
            
            
            <input type="submit" name="registrar" class="btn btn-lg btn-info btn-block" style="margin-top:10px" id="registrar" value="Registrarse">
			
          </form>
   <br> <br>
   
          
        </div>
          
          
        <div class="col-lg-3 col">       
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