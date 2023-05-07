function comprobar() {

  // Get the login form element
  var loginForm = document.getElementById("submitForm");

  // Get the input values
  var identificacionInput = document.getElementById("identificacion");
  var contrasenaInput = document.getElementById("contrasena");


  var invalidCharacters = ["'", "*", ";", ","]; //Lista de caracteres posibles de sql injection

  //Comprueba si están en los inputs
var contiene = invalidCharacters.some(function(char) {
  return (contrasenaInput.value.includes(char)||identificacionInput.value.includes(char));
});
  

  // Compruebo los inputs
  if (identificacionInput.value.trim() === "" || contrasenaInput.value.trim() === "") { //Compruebo que no estén vacíos
    showErrorModal("Por favor, complete todos los campos.");
  } else if (contiene) { //Compruebo por sql injector
    showErrorModal("La identificación y/o contraseñas contienen carácteres no válidos.");
  } else if (identificacionInput.value.length >= 45) { //Compruebo tamaño
    showErrorModal("La identificación es demasiado grande.");
  } else if (contrasenaInput.value.length >= 75) { //Compruebo tamaño
    showErrorModal("La contraseña es demasiado grande.");
  } else {
    // Submit the form
    loginForm.submit();
  }
}

//Muestra mensaje con un error
function showErrorModal(message) {
  const modal = document.getElementById('errorModal');
  const errorMessage = document.getElementById('errorMessage');

  errorMessage.textContent = message;
  new bootstrap.Modal(modal).show();
}


document.getElementById("boton_submit").addEventListener("click", function(event){
  event.preventDefault(); //Evita que haga submit directamente
  comprobar();
});

