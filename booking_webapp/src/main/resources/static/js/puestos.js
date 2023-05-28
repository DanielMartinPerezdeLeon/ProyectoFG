var stateObj = {};

window.history.replaceState(stateObj, "Booking WebApp", "/ver_puestos");

function cerrarSesion() {
    var url = window.location.href;
    url = url.replace('ver_usuarios', 'desconectarse');
    window.location.replace(url);
}


function httpGet(theUrl) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, false); // false for synchronous request
    xmlHttp.send(null);
    return xmlHttp.responseText;
}


function verpuesto(id) {
    var url = window.location.href;

    url = url.replace('ver_puestos', 'puestos/todos'); //'http://localhost:8080/puestos/todos';


    var json = JSON.parse(httpGet(url));

    var puesto;


    json.forEach(element => {
        if (element.id == id) {
            puesto = element;
            return;
        }
    });

    
    var switchb= document.getElementById('flexSwitchCheckDefault');
    var switchlabel =document.getElementById('label-switch');




    showModal(puesto);
}



function showModal(puesto) {

    const modal = document.getElementById('Modal');
    const errorMessage = document.getElementById('Message');
    const header = document.getElementById('ModalLabel');
    var switchb= document.getElementById('flexSwitchCheckDefault');
    var switchlabel =document.getElementById('label-switch');

    document.getElementById("reiniciar_horas").id=puesto.id;


    var button =document.getElementsByClassName("btn btn-secondary");
    button[0].id=puesto.id;


    if(puesto.estado===false){
        switchb.checked=false;
        switchlabel.textContent="Desactivado"
    }else{
        switchb.checked=true;
        switchlabel.textContent="Activado"
    }




    header.textContent = "Puesto: "+puesto.tipo+" "+puesto.id;
    new bootstrap.Modal(modal).show();

}



function check(){
    var switchb= document.getElementById('flexSwitchCheckDefault');
    var switchlabel =document.getElementById('label-switch');

    if(switchb.checked){
        switchlabel.textContent="Activado"
    }else{
        switchlabel.textContent="Desactivado"
    }
}


function cambiarEstado(id){
    var switchb= document.getElementById('flexSwitchCheckDefault');

    var url = window.location.href;

    url = url.replace('ver_puestos', 'puestos/cambiar_estado');


    const data = {
        id: id,
        estado: switchb.checked
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

        setTimeout(() => { location.reload(); }, 500);
        
}


function reiniciarHoras(id){

    var url = window.location.href;

    url = url.replace('ver_puestos', 'puestos/reiniciar');


    const data = {
        puesto: id,
        hora: 0,
        usuario: "null"
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

        setTimeout(() => { location.reload(); }, 500);

}



function modalPuestoNuevo(){

    const modal = document.getElementById('ModalPuestoNuevo');
    new bootstrap.Modal(modal).show();
}



