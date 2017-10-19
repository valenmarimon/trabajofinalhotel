$(document).ready(function(){
	//$("#liPedido").addClass("active");
	
	
	
	$("#formRegistroHab").submit(function() {
		var numero=validarNumero();
		var piso= validarPiso();
		
		/*--if(cod&&cant){
			return true;
		} else {
			return false;
		}*/
	});
	
	$("#numero").focusout(function(e) {
		validarNumero();
	});
	$("#piso").focusout(function(e) {
		validarPiso();
	});
	
	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY







function validarNumero(){
	$("#numero").html("");
	$("#numero").attr("style","background:#FFF");
	var valido=true;
	var numero = $("#numero").val();
	
	if(numero==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#numero").attr("style","background:#f2dede");
		$("#errorNumero").html("El numero de la habitacion no puede estar en blanco");
		valido=false;
	}else{
		if(!numero.match(/^([0-9])*$/)){
			$("#numero").attr("style","background:#f2dede");
			$("#errorNumero").html("El numero tiene que ser entero ");
			valido=false
		}
		else {if (numero.match(/^-\d+$/)) {
			$("#numero").attr("style","background:#f2dede");
			$("#errorNumero").html("El numero no puede ser negativo ");
	            valido=false;
		}
		}
	}
	return valido;
}


function validarPiso(){
	$("#piso").html("");
	$("#piso").attr("style","background:#FFF");
	var valido=true;
	var piso = $("#piso").val();
	
	if(piso==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#piso").attr("style","background:#f2dede");
		$("#errorPiso").html("El piso de la habitacion no puede estar en blanco");
		valido=false;
	}else{
		if(!piso.match(/^([0-9])*$/)){
			$("#piso").attr("style","background:#f2dede");
			$("#errorPiso").html("El piso tiene que ser entero ");
			valido=false
		}
		else {if (piso.match(/^-\d+$/)) {
			$("#piso").attr("style","background:#f2dede");
			$("#errorPiso").html("El piso no puede ser negativo ");
	            valido=false;
		}
		}
	}
	return valido;
}
