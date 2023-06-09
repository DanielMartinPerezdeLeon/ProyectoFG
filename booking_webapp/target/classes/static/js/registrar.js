function comprobar() {

  // Get the login form element
  var Form = document.getElementById("registrarForm");

  // Get the input values
  var nombreInput = document.getElementById("nombre");
  var identificacionInput = document.getElementById("identificacion");
  var contrasenaInput = document.getElementById("contrasena");
  var repetirContrasenaInput = document.getElementById("repetir-contrasena");
  var nombreInput = document.getElementById("nombre");
  var apellidosInput = document.getElementById("apellidos");


  var invalidCharacters = ["'", "*", ";", ","]; //Lista de caracteres posibles de sql injection

  //Comprueba si están en los inputs
var contiene = invalidCharacters.some(function(char) {
     return (
      contrasenaInput.value.includes(char) ||
      identificacionInput.value.includes(char) ||
      nombreInput.value.includes(char) ||
      apellidosInput.value.includes(char)
    );
});


//Comprueba si están vacios
var vacio = [
    identificacionInput,
    contrasenaInput,
    repetirContrasenaInput,
    nombreInput,
    apellidosInput
  ].some(function(input) {
    return input.value.trim() === "";
  });
  

  // Compruebo los inputs
  if ((identificacionInput.value.trim() === "" || contrasenaInput.value.trim() === ""|| nombreInput.value.trim() === ""|| apellidosInput.value.trim() === "")) { //Compruebo que no estén vacíos
    showErrorModal("Por favor, complete todos los campos.");
  } else if (contiene) { //Compruebo por sql injector
    showErrorModal("La identificación y/o contraseñas contienen carácteres no válidos.");
  } else if (identificacionInput.value.length >= 45) { //Compruebo tamaño
    showErrorModal("La identificación es demasiado grande.");
  } else if (contrasenaInput.value.length >= 75){
    showErrorModal("La contraseña es demasiado grande.");
  }else if (nombreInput.value.length >= 49 || apellidosInput.value.length >= 90) { //Compruebo tamaño{
	  showErrorModal("Nombre o apellidos demasiado grande.");
  } else if(contrasenaInput.value!=repetirContrasenaInput.value){
	  showErrorModal("Las contraseñas no coinciden.");
  } else {
    // Submit the form
    Form.submit();
  }
}

//Muestra mensaje con un error
function showErrorModal(message) {
  const modal = document.getElementById('errorModal');
  const errorMessage = document.getElementById('errorMessage');

  errorMessage.textContent = message;
  new bootstrap.Modal(modal).show();
}


var stateObj = {};
			
window.history.replaceState(stateObj,"Booking WebApp", "/registrar");

document.getElementById("boton_registrar").addEventListener("click", function(event){
  event.preventDefault(); //Evita que haga submit directamente
  comprobar();
});
