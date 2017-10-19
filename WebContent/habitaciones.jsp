<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    

     <%@page import="entidades.TipoHabitacion"%>
     <%@page import="negocio.CtrlTipoHabitacion"%>
    
    
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
    
    <%
	
    		
    		CtrlTipoHabitacion ctrl = new CtrlTipoHabitacion();
    		
	
	%>  
	

  

      

      <!-- Example row of columns -->
      <div class="row" style="color:  #001233">

         <div class="col-lg-4 col">
         <h2>Doble Single <b>  € <%=ctrl.listarTiposHabitacion().get(0).getPrecio_th()%> </b></b></h2>
         <img src="imgs/h1.png" class="img-responsive" alt="Logo"> 
         <p><h4 style="line-height: 1.6em">Capacidad: 2 personas <br>
         		Habitación: 22 m2 <br>
         		Tipos de cama: 2 singles <br>
         		Servicios Base: 
			<ul>
				<li>Air conditioning</li>
				<li>Coffee / tea maker</li>
				<li>Connecting room available</li>
				<li>Electrical adapters available</li>
				<li>Hairdryer</li>
				<li>Minibar</li>
				<li>Wi-Fi</li>
				
			</ul>
         </h4></p>   
            
         </div>
        <div class="col-lg-4 col">
         <h2>Deluxe  <b>  € <%=ctrl.listarTiposHabitacion().get(1).getPrecio_th()%></b></h2>
         <img src="imgs/h2.jpg" class="img-responsive" alt="Logo">
          <p><h4 style="line-height: 1.6em">Capacidad: 2 personas <br>
         		Habitación: 32 m2 <br>
         		Tipos de cama: 1 doble <br>
         		Servicios Base: 
			<ul>
				<li>Air conditioning</li>
				<li>Bathroom amenities set</li>
				
				<li>Connecting room available</li>
				<li>Electrical adapters available</li>
				<li>Hairdryer</li>
				
				<li>Minibar</li>
				<li>Sofa</li>
				<li>Telephone</li>
				<li>Wi-Fi</li>
				
			</ul>
         </h4></p> 
          
        </div>
          
          
        <div class="col-lg-4 col" > 
        <h2>Doble Deluxe  <b>  € <%=ctrl.listarTiposHabitacion().get(2).getPrecio_th()%> </b></h2>  
        <img src="imgs/h4.jpg" class="img-responsive" alt="Logo">   
          <p><h4 style="line-height: 1.6em">Capacidad: 4 personas <br>
         		Habitación: 45 m2 <br>
         		Tipos de cama: 2 doble <br>
         		Servicios Base: 
			<ul>
				<li>Air conditioning</li>
				<li>Bathroom amenities set</li>
				<li>Coffee / tea maker</li>
				<li>Connecting room available</li>
				<li>Electrical adapters available</li>
				<li>Hairdryer</li>
				<li>Jacuzzi</li>
				<li>Minibar</li>
				<li>Sofa</li>
				<li>Telephone</li>
				<li>Wi-Fi</li>
				
			</ul>
         </h4></p>
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