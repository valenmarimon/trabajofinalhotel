<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="entidades.Recepcionista"%>
     <%@page import="entidades.Cliente"%>
     <%@page import="entidades.Persona"%>
     <%@page import="entidades.Habitacion"%>
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

    <div class="container" style="text-align: justify;">

  
 <!-- Example row of columns -->
      <div class="row">

         <div class="col-lg-1 col">         
         </div>
        <div class="col-lg-10 col"style="color:  #001233" >
         
         <h1>Servicios adicionales para una estadía más placentera</h1><br>
         
         
         
         <table class="table table-hover">
    <thead>
      <tr>
        <th><h2>Nombre</h2></th>
        <th><h2>Descripcion</h2></th>
        <th><h2>Precio</h2></th>
        
       
        
        
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
	   <td><h4>€ <%= ctrl.listarServicios().get(indice).getPrecio() %></h4></td>
	   
	   
       
	
	</tr>
	<%
	
}


      %>
      
     
    </tbody>
  </table>
   
        
        </div>
          
          
        <div class="col-lg-1 col">       
        </div>
          
          
        
        
      </div>

       <!-- 

      
      <div class="row" style="color:  #001233">

         <div class="col-lg-3 col">
         <h2>Sauna<b>  € 15 </b></b></h2>
         <img src="imgs/sauna.png" class="img-responsive" alt="Logo"> 
         <p><h4 style="line-height: 1.6em">
         Es un baño de calor seco a muy alta temperatura con humedad ambiente mínima, que se utiliza con fines terapéuticos. 
          Es perfecto para  reducir el stress corporal, desintoxicar 
          la piel, eliminar toxinas a través de la transpiración, favorecer 
          la perdida de peso y contribuir con la relajación en general.
         </h4></p>   
            
         </div>
        <div class="col-lg-3 col">
         <h2>Masaje<b>  € 20 </b></h2>
         <img src="imgs/masaje.png" class="img-responsive" alt="Logo">
          <p><h4 style="line-height: 1.6em">
          Produce una total armonización a través de piedras 
          que se colocan en distintos puntos del cuerpo de forma estratégica.
Duración: 50 minutos.
         </h4></p> 
          
        </div>
          
          
        <div class="col-lg-3 col" > 
        <h2>Piscina <b>  € 24 </b></h2>  
        <img src="imgs/piscina.png" class="img-responsive" alt="Logo">   
          <p><h4 style="line-height: 1.6em">En la terraza se encuentran la piscina
           climatizada al aire libre y el jacuzzi, especial para tomar sol y disfrutar
            de la vista panorámica de la ciudad y del río Paraná y sus islas.
         </h4></p>
        </div>
        
         <div class="col-lg-3 col" > 
        <h2>Fitness<b>  € 18 </b></h2>  
        <img src="imgs/fitness.png" class="img-responsive" alt="Logo">   
          <p><h4 style="line-height: 1.6em">
          También está a su disposición  el Fitness Center,
           para aquellos amantes del deporte o quienes busquen en 
           su estadía llevar adelante un entrenamiento físico;  se encuentra totalmente equipado 
           y con profesionales que lo pueden asesorar en las diferentes actividades físicas, trabajos 
           aeróbicos y rutinas de musculación.
         </h4></p>
        </div>
          
          
        
        
      </div>

      -->

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