$(document).ready(function(){
	
	
	$("#txtDescripcion").keyup(function(e) {
		if($("#txtDescripcion").val()==""){
			$("#cuerpo").html('<tr><td colspan="3"><h2>Comience a escribir para obtener los Servicios</h2></td></tr>');
		} else{
	        var descripcion = {descripcion:$("#txtDescripcion").val()}
			$.post("ajaxBusquedaDesc",descripcion,llenarTabla);
		}
    });
	
	
	$("#formReserva").submit(function() {
		var cod=validarCod();
		
		if(cod){
			return true;
		} else {
			return false;
		}
	});
	
	$("#txtCod").focusout(function(e) {
		validarCod();
	});
	
	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY

function llenarTabla(respuesta){
	var servicios = $.parseJSON(respuesta);
	$("#cuerpo").html("");
	if(servicios.length==0){
		$("#cuerpo").html('<tr><td colspan="3"><h2>No se encontraron servicios que coincidan con la descripción.</h2></td></tr>');
	}else {
		for (i=1;i<=servicios.length;i++){
			$("#cuerpo").append(
				"<tr id='"+i+"'>"+
					"<td id='cod"+i+"'>"+servicios[i-1].cod_servicio+"</td>"+
					"<td id='desc"+i+"'>"+servicios[i-1].nombre+"</td>"+
					"<td>"+servicios[i-1].precio+"</td>"+
				"</tr>"
			);
		}
		$("tr").click(fila_click);
	}
}

function fila_click(){
	var fila = $(this).attr("id");
    var cod = $("#cod"+fila).html();
	var descr = $("#desc"+fila).html();
	$("#txtCod").val(cod);
	$("#txtDescripcion").val(descr);
}

function validarCod(){
	$("#errorCod").html("");
	$("#txtCod").attr("style","background:#FFF");
	var valido=true;
	var cod = $("#txtCod").val();
	
	if(cod==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#txtCod").attr("style","background:#f2dede");
		$("#errorCod").html("El codigo de servicio no puede estar en blanco");
		valido=false;
	}else {
		if(!cod.match(/^([0-9])*$/)){
			$("#txtCod").attr("style","background:#f2dede");
			$("#errorCod").html("El codigo de servicio debe ser un número entero");
			valido=false
		}
	}
	return valido;
}
