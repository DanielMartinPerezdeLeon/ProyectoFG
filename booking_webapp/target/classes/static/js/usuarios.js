var stateObj = {};

window.history.replaceState(stateObj, "Booking WebApp", "/ver_usuarios");


console.log(usuarios);


function cerrarSesion() {
    var url = window.location.href;
    url = url.replace('ver_usuarios', 'desconectarse');
    window.location.replace(url);
}



function showModalAceptar() {
    const modalacepta = document.getElementById('ModalAceptar');


    new bootstrap.Modal(modalacepta).show();
}


function aceptar(id){
    var url = window.location.href;

    url = url.replace('ver_usuarios', 'usuarios/cambiar_rol');


    const data = {
        usuario: id,
        rol: 1
    };


    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(result => {
            // Handle the response from the server
            console.log(result);
        })
        .catch(error => {
            // Handle any errors that occurred during the request
            console.error(error);
        });

        showModalAceptar();
}

function showModal(usuario) {

    const modal = document.getElementById('Modal');
    const errorMessage = document.getElementById('Message');
    const header = document.getElementById('ModalLabel');
    const tbody = document.querySelector('table tbody tr');

    tbody.innerHTML="";

    var td1 = document.createElement("td");
    td1.textContent = usuario.identificacion

    var td2 = document.createElement("td");
    td2.textContent = usuario.nombre

    var td3 = document.createElement("td");
    td3.textContent = usuario.apellidos

    var td4 = document.createElement("td");
    var rol = usuario.rol
    switch (rol) {
        case 0:
            td4.textContent = "No aceptado ";
            td4.innerHTML+=' <button type="button" class="btn btn-sm btn-outline-secondary" data-bs-dismiss="modal" id="'+usuario.identificacion+'"'+'onclick="aceptar(this.id)"> Aceptar </button>';
            break;
        case 1:
            td4.textContent = "Usuario";
            break;
        case 2:
            td4.textContent = "Manager";
            break;
        case 3:
            td4.textContent = "Admin";
            break;
    }
    

    tbody.appendChild(td1);
    tbody.appendChild(td2);
    tbody.appendChild(td3);
    tbody.appendChild(td4);


    header.textContent = "Detalles de " + usuario.identificacion;
    new bootstrap.Modal(modal).show();

}


function httpGet(theUrl) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, false); // false for synchronous request
    xmlHttp.send(null);
    return xmlHttp.responseText;
}


function verusuario(id) {
    var url = window.location.href;

    url = url.replace('ver_usuarios', 'usuarios/todos'); //'http://localhost:8080/puestos/todos';


    var json = JSON.parse(httpGet(url));

    var usu;


    json.forEach(element => {
        if (element.identificacion == id) {
            usu = element;
            return;
        }
    });

    console.log(usu);

    showModal(usu);
}

