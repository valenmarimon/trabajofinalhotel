$(document).ready(function(){
	//$("#liPedido").addClass("active");
	
	
	
	$("#formRegistroServicio").submit(function() {
		var nombre=validarNombre();
		var descripcion= validarDescripcion();
		var precio=validarPrecio();
		/*--if(cod&&cant){
			return true;
		} else {
			return false;
		}*/
	});
	
	$("#nombre").focusout(function(e) {
		validarNombre();
	});
	$("#descripcion").focusout(function(e) {
		validarDescripcion();
	});
	$("#precio").focusout(function(e) {
		validarPrecio();
	});
	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY







function validarNombre(){
	$("#nombre").html("");
	$("#nombre").attr("style","background:#FFF");
	var valido=true;
	var nombre = $("#nombre").val();
	
	if(nombre==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#nombre").attr("style","background:#f2dede");
		$("#errorNombre").html("El nombre del servicio no puede estar en blanco");
		valido=false;
	}else{
		if(!nombre.match(/^[a-zA-Z]*$/)){
			$("#nombre").attr("style","background:#f2dede");
			$("#errorNombre").html("El nombre solo puede contener letras");
			valido=false
		}
	}
	return valido;
}


function validarDescripcion(){
	$("#descripcion").html("");
	$("#descripcion").attr("style","background:#FFF");
	var valido=true;
	var descripcion = $("#descripcion").val();
	
	
		if(!descripcion.match(/^[a-zA-Z]*$/)){
			$("#descripcion").attr("style","background:#f2dede");
			$("#errorDescripcion").html("La descripcion solo puede contener letras");
			valido=false
		}
	
	return valido;
}

function validarPrecio(){
	$("#errorPrecio").html("");
	$("#precio").attr("style","background:#FFF");
	var valido=true;
	var precio = $("#precio").val();
	
	if(precio==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#precio").attr("style","background:#f2dede");
		$("#errorPrecio").html("El precio del servicio no puede estar en blanco");
		valido=false;
	}
	else{
		if(!precio.match(/^([0-9])*$/)){
			$("#precio").attr("style","background:#f2dede");
			$("#errorPrecio").html("El precio solo ");
			valido=false
		}
		else {if (precio.match(/^-\d+$/)) {
			$("#precio").attr("style","background:#f2dede");
			$("#errorPrecio").html("El precio no puede ser negativo ");
					valido=false;
		}
		}
	   }
	
	return valido;
}
