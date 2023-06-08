var stateObj = {};

window.history.replaceState(stateObj, "Booking WebApp", "/admin");


function cerrarSesion() {
    var url = window.location.href;
    url = url.replace('admin', 'desconectarse');
    window.location.replace(url);
}


function showModalBorrarUsuario() {
    const modalreserva = document.getElementById('ModalBorrarUsuario');


    new bootstrap.Modal(modalreserva).show();
}


function borrarUsuario(id) {

    fetch('/usuarios/' + id, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                console.log('User eliminado correctamente.');
                location.reload(); // Reload the page
            } else {
                console.log('Fallo al eliminar usuario.');
            }
        })
        .catch(error => {
            console.log('An error occurred:', error);
        });
}


function showModalBorrarPuesto() {
    const modalreserva = document.getElementById('ModalBorrarPuesto');


    new bootstrap.Modal(modalreserva).show();
}


function borrarPuesto(id) {

    fetch('/puestos/' + id, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                console.log('Puesto eliminado correctamente.');
                location.reload(); // Reload the page
            } else {
                console.log('Fallo al eliminar el puesto.');
            }
        })
        .catch(error => {
            console.log('An error occurred:', error);
        });
}


function reiniciarHoras(){
    fetch('/puestos/reiniciar_todos', {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                console.log('Puestos reiniciados correctamente.');
                location.reload(); // Reload the page
            } else {
                console.log('Fallo al reiniciar horarios.');
            }
        })
        .catch(error => {
            console.log('An error occurred:', error);
        });
}