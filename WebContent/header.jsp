<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="entidades.Persona"%>
    
 <head>
 <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/justified-nav.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<!-- ACA FALTA <script type="text/javascript" src="bootstrap/js/jquery.js"></script> PERO SI LO PONGO NO FUNCIONA EL RANGO DE FECHAS DE
RESERVAR.JSP  -->

 </head>

<nav class="navbar navbar-inverse navbar-static-top" style="background: #ffffff; border-color: #ffffff; ">
      <div class="container">
				<div class="masthead">
         
            <a style="margin:0px; padding:0px" href="index.jsp"><img src="imgs/fourpointsHeader.png" class="img-responsive" alt="Logo" img style="height:100%"></a>
          
        
        <nav>
          <ul class="nav nav-justified">
            <li> <a href="index.jsp">Home</a></li>
            <li><a href="habitaciones.jsp">Rooms&Suites</a></li>
            <li><a href="servicios.jsp">Servicios</a></li>
            
            <%  Persona userSession = (Persona)session.getAttribute("userSession");
            if(userSession == null || userSession.getTipo() != null){
            	%>  <li><a href="reservar.jsp">Reservar</a></li>  <% } %>
                             
                
                <li><a href="administrar.jsp">Admin</a></li>
            <%  
            if(userSession == null){
            	%> <li> <a href="login.jsp">Login</a></li> <%
            } else {
            	%><li><a id="linkCerrarSesion" href="CerrarSesion">Log <%=userSession.getNombre()%> Salir.</a></li>
            	
            	<% 
            } %>
            	
            
            
            
    
	
            
            
            
            
            
          </ul>
        </nav>
		 </div>
       
</nav>