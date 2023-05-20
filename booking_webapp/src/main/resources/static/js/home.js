
var stateObj = {};

window.history.replaceState(stateObj, "Booking WebApp", "/home");



console.log(puestos);





function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

function getObjectById(id, json) {
    var result = json.find(obj => obj.id === parseInt(id));
    return result ? (result) : null;
}



//Muestra modal del puesto
function showModal(puesto) {
  const modal = document.getElementById('Modal');
  const errorMessage = document.getElementById('Message');
  const header = document.getElementById('ModalLabel');
  const tbody = document.querySelector('table tbody');
  
  var reservas=JSON.parse(puesto.reservas);

  var i=0;

  tbody.querySelectorAll('tr').forEach(tr => {

    tr.innerHTML="";

    var td1=document.createElement("td");
    td1.textContent=reservas[i].id;

    var td2=document.createElement("td");

    tr.appendChild(td1);

    
    if(reservas[i].detalle==""){
        td2.textContent ="Libre";
        tr.className="table-success";
       

        td2.innerHTML+='<button type="button" class="btn btn-sm btn-outline-secondary" data-bs-dismiss="modal" id="reserva-' +puesto.id+"-"+i+" "+ '"onclick="reservar(this.id)"> Reservar </button>'
    }else{
        td2.textContent =reservas[i].detalle;
        tr.className="table-warning";
    }
    
     td2.className="d-flex justify-content-between";

    tr.appendChild(td2);


    i++;
  });

  header.textContent = puesto.tipo+" "+puesto.id+":";
  new bootstrap.Modal(modal).show();
}



function verpuesto(id) {

    var url = window.location.href;

    url = url.replace('home', 'puestos/todos'); //'http://localhost:8080/puestos/todos';


    var json=JSON.parse(httpGet(url));


    var puesto = getObjectById(id, json);

    showModal(puesto)
}







