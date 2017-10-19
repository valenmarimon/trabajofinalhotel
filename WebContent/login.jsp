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
      			String mensaje=(String)request.getAttribute("mensaje");
        		if(mensaje!=null){
      		%>
      		<div class="alert alert-danger">
   			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    		<strong>El usuario <%=mensaje %> no existe o la contraseña ingresada es incorrecta</strong> .
  			</div>
      		
      			
      		<%
        		}
      			
      		%>
         
          
          <form action="IniciarSesion" method="post">
            <h1 align="center" style="color:#19334d">Ingrese sus datos de usuario:</h1><br>
            <label for="usuario">Usuario:</label> <br>
            <input type="text" id="usuario" name="usuario" class="form-control" placeholder="Usuario" required autofocus value="zzzz"><br>
            <label for="contraseña">Contraseña:</label><br>
            <input type="password" id="contrasena" name="contrasena" class="form-control" placeholder="Contraseña" required value="zzzz"><br>
           
            <button class="btn btn-lg btn-info btn-block" type="submit" style="margin-top:10px">Iniciar sesión</button>
            <a class="btn btn-lg btn-info btn-block" href="registrarse.jsp">Registrarse</a> <br>
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