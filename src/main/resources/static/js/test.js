   let obtenerDato = document.getElementsByClassName('inicio');

    for(var num=0; num<obtenerDato.length; num++){


    const horaActual=new Date();
    //Voy a hacer q todos partan del mismo dia, dia 0
    horaActual.setYear(0);
    horaActual.setMonth(0);
    horaActual.setDate(0);

    //Voy a calcular el tiempo con una hora mas
    var numberOfMlSeconds = horaActual.getTime();
    var addMlSeconds = 60 * 60000;
    var horaActualMasUnaHora = new Date(numberOfMlSeconds + addMlSeconds);


    const horaCelda = converseStringInDate(obtenerDato[num].innerText);


   if(horaCelda>=horaActual && horaCelda<=horaActualMasUnaHora ){
        celda=obtenerDato[num];
        celda.style.backgroundColor="#95c799";
    }
    if(horaActual.getHours()=="23" && horaCelda.getHours()=="0" ){
        celda=obtenerDato[num];
        celda.style.backgroundColor="#95c799";
    }

}


function converseStringInDate(horaGuardada){
    let timePieces= horaGuardada.split(":");
    return (new Date(0,0,0,timePieces[0], timePieces[1], 59)); //timePieces[2]
}
