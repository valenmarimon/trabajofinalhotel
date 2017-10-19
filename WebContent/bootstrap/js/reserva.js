$(document).ready(function(){
	//$("#liPedido").addClass("active");
	
	
	
	$("#formRegistroReserva").submit(function() {
		var doblesingle=validarDobleSingle();
		var deluxe= validarDeluxe();
		var dobledeluxe=validarDobleDeluxe();
		//if(cod&&cant){
		//	return true;
		//} else {
		//	return false;
		//}
	});
	
	$("#doblesingle").focusout(function(e) {
		validarDobleSingle();
	});
	$("#deluxe").focusout(function(e) {
		validarDeluxe();
	});
	$("#dobledeluxe").focusout(function(e) {
		validarDobleDeluxe();
	});
	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY







function validarDobleSingle(){
	$("#errorDobleSingle").html("");
	$("#doblesingle").attr("style","background:#FFF");
	var valido=true;
	var doblesingle = $("#doblesingle").val();
	
	
		if(!doblesingle.match(/^([0-9])*$/)){
			$("#doblesingle").attr("style","background:#f2dede");
			$("#errorDobleSingle").html("La cantidad debe ser un número entero");
			valido=false
		}
	
	return valido;
}

function validarDeluxe(){
	$("#errorDeluxe").html("");
	$("#deluxe").attr("style","background:#FFF");
	var valido=true;
	var deluxe = $("#deluxe").val();
	
	
		if(!deluxe.match(/^([0-9])*$/)){
			$("#deluxe").attr("style","background:#f2dede");
			$("#errorDeluxe").html("La cantidad debe ser un número entero");
			valido=false
		}
	
	return valido;}

function validarDobleDeluxe(){
	$("#errorDobleDeluxe").html("");
	$("#dobledeluxe").attr("style","background:#FFF");
	var valido=true;
	var dobledeluxe = $("#dobledeluxe").val();
	
	
		if(!dobledeluxe.match(/^([0-9])*$/)){
			$("#dobledeluxe").attr("style","background:#f2dede");
			$("#errorDobleDeluxe").html("La cantidad debe ser un número entero");
			valido=false
		}
	
	return valido;
}
