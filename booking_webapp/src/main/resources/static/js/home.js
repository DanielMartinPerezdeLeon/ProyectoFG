
var stateObj = {};

window.history.replaceState(stateObj,"Booking WebApp", "/home");



console.log(puestos);   



//Todo esto mejor lop hago con Java y thimeleaf :)
/*

var seccion = document.getElementById("elementos");



var url = window.location.href;




url = url.replace('home','puestos/todos');

//'http://localhost:8080/puestos/todos';


function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

var html="";

var json = JSON.parse(httpGet(url));


console.log(json);


for(var i=0; i<json.length; i++){
    reservasarray=JSON.parse(json[i].reservas);
    
    var horas_libres=0;
    for(var j=0; j<reservasarray.length; j++){
        if(reservasarray[j].detalle == ""){
            horas_libres++;
        }
    }

    if (horas_libres=8){
        horas_libres="Sin horas reservadas.";
    }else{
        horas_libres=horas_libres+" horas reservadas.";
    }

	html+=
    '<div class="col">'+
        '<div class="card shadow-sm">'+
            '<div class="card-body">'+
                '<h2>'+json[i].tipo +': '+json[i].id +'</h2>'+
                '<p class="card-text">'+horas_libres+'</p>'+
                '<div class="btn-group">'+
                '<button type="button" class="btn btn-sm btn-outline-secondary" id="boton-ver'+i+'">Ver</button>'+
    '</div></div></div></div>';
    
    console.log(reservasarray);
}


seccion.innerHTML+= html; */