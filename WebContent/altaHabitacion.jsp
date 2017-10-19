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
	<script type="text/javascript" src="bootstrap/js/validarRegistro.js"></script>
<link href='https://fonts.googleapis.com/css?family=Oxygen&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="bootstrap/js/validarRegistro.js"></script>
    <script> 
		function validar(esto){ 
		valido=false; 
		for(a=0;a<esto.elements.length;a++){ 
		if(esto[a].type=="radio" && esto[a].checked==true){ 
		valido=true; 
		break 
			} 
				} 
			if(!valido){ 
			alert("Por favor seleccione una casilla");return false 
			} 

			}  
</script>
    <script src="bootstrap/js/alta.js"></script>
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
         
         <h1 style="text-align: center;">Agregar Habitación</h1>
         
		<form action="RegistrarHabitacion" method="post" name="formRegistroHab" id="formRegistroHab" onsubmit="return validar(this)">
		
      		<label for="numero">Número: </label>
            <input type="text" id="numero" name="numero" class="form-control" placeholder="numero" required>
            <label for="numero" style="color:red" id="msjNum"></label><br/>
            
            <label for="piso">piso: </label>
            <input type="text" id="piso" name="piso" class="form-control" placeholder="piso" required>
            <label for="piso" style="color:red" id="msjPiso"></label><br/>
            
            <label for="tipo_habitacion">Tipo Habitación: </label> <br>
			<input type="radio" id="tipo_habitacion" name="tipo_habitacion"value="1"> Doble Single 
  			<input type="radio" id="tipo_habitacion" name="tipo_habitacion"value="2"> Deluxe 
 			<input type="radio" id="tipo_habitacion" name="tipo_habitacion" value="3"> Doble Deluxe
			<br>
	       
	       
  
            <input type="submit" name="registrar" class="btn btn-lg btn-info btn-block" style="margin-top:10px" id="registrar" value="Registrar Habitacion">
 			<a href="admHabitaciones.jsp" class="btn btn-info btn-lg btn-block" role="button">Regresar</a> <br> <br>
        </form>
      	
      			
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