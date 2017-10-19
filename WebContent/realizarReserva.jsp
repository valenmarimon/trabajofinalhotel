<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="entidades.Persona"%>
    <%@page import="negocio.CtrlServicio"%>
    <%@page import="entidades.Servicio"%>
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
  <script>
  $(function() {
    $( "#from" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 2,
      onClose: function( selectedDate ) {
        $( "#to" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $( "#to" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 2,
      onClose: function( selectedDate ) {
        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
  });
  </script>
  <script type="text/javascript">//METER ESTO EN EL validarRegistro.js
  $(document).ready(function(e) {
	    $("#formRegistroReserva").submit(function(e) {
	        if($("#deluxe").val()==""&&$("#dobledeluxe").val()==""&&$("#doblesingle").val()==""){ //Si el valor de los tres txt esta vacío
				alert("Debe seleccionar al menos una habitación de alguna categoría");
				return false;//este es para que no haga el submit
			} 
	        if($("#deluxe").val()=="0" || $("#dobledeluxe").val()=="0" || $("#doblesingle").val()=="0"){ //Si el valor de los tres txt esta vacío
				alert("La cantidad debe ser mayor a cero");
				return false;//este es para que no haga el submit
			}
	    });
	});
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

         <div class="col-lg-3 col">         
         </div>
         
         
        <div class="col-lg-6 col" >
          <% 
      			String mensaje=(String)request.getAttribute("mensaje");
        		if(mensaje!=null){
      		%>
      		<div class="alert alert-danger">
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
            	
		<form action="RegistrarReserva" method="post" name="formRegistroReserva" id="formRegistroReserva">
		
            <h1 align="center">Elija la fecha para la reserva:</h1>
             
			<label for="fecha">Fecha de ingreso:</label>
			<input type="text" id="from" name="from" class="form-control" placeholder="Fecha desde"   required>
			
			<label for="fecha">Fecha de salida:</label>
			<input type="text" id="to" name="to" class="form-control" placeholder="Fecha Hasta"   required>
   			
   			<h1 align="center">Elija cantidad de habitaciones:</h1>
             
              
            <label for="doblesingle">Doble Single(2 personas): </label>
            <input type="text" id="doblesingle" name="doblesingle" class="form-control" placeholder="Ingrese un número" >
            <label for="doblesingle" style="color:red" id="msjHabitacion"></label><br/>
             
            <label for="apellido">Deluxe (2 personas): </label>
            <input type="text" id="deluxe" name="deluxe" class="form-control" placeholder="Ingrese un número" >
            <label for="deluxe" style="color:red" id="msjHabitacion2"></label><br/>
             
            <label for="apellido">Doble deluxe (4 personas): </label>
            <input type="text" id="dobledeluxe" name="dobledeluxe" class="form-control" placeholder="Ingrese un número" >
            <label for="dobledeluxe" style="color:red" id="msjHabitacion3"></label><br/>
            
             
      
    		<input type="hidden" id="cliente" name="cliente" value="<%=user%>">
            
            <input type="submit" name="registrar" class="btn btn-lg btn-info btn-block" style="margin-top:10px" id="registrar" value="Realizar reserva">
			
          </form>
            	
            	<% 
            } %>
      			
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
 
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>