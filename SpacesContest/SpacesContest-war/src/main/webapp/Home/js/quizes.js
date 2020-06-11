/*
 *
 *VARIABLES
 *
 */
let absolutePath = getAbsolutePath();
let jsonRequest = document.getElementById('jsonRequest').innerHTML;
const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds));
};
document.getElementById('createQuizSave').addEventListener('click', function (e) {
    let nombreQuiz = document.getElementById('inputQuizName').value;
    if (nombreQuiz.length !== 0) {
        window.location.replace("?p=createQuiz&q=" + nombreQuiz);
    } else {
        console.log("EMPTY");
    }
});


/*
 *
 *FUNCIONES
 *
 */

$(document).ready(function () {
    initPage();
    myAccFunc('demoAcc');
});



function myAccFunc(acordionID) {
    var x = document.getElementById(acordionID);
    if (x.className.indexOf("w3-show") === -1) {
        x.className += " w3-show";
        x.previousElementSibling.className += " w3-green";
    } else {
        x.className = x.className.replace(" w3-show", "");
        x.previousElementSibling.className =
                x.previousElementSibling.className.replace(" w3-green", "");
    }
}

function myDropFunc() {
    var x = document.getElementById("demoDrop");
    if (x.className.indexOf("w3-show") === -1) {
        x.className += " w3-show";
        x.previousElementSibling.className += " w3-green";
    } else {
        x.className = x.className.replace(" w3-show", "");
        x.previousElementSibling.className =
                x.previousElementSibling.className.replace(" w3-green", "");
    }
}

function initPage() {
    try {
        let jsonRequestObject = JSON.parse(jsonRequest);
        console.log(jsonRequestObject);
        if (jsonRequestObject.quizes.length !== 0) {
            //VAMOS A CREAR LAS TARJETAS DE LOS QUIZES
            let quizesGroup = document.getElementById('quizesGroup');
            let demoAcc = document.getElementById('demoAcc');
            let favorites = document.getElementById('favorites');
            for (var i = 0; i < jsonRequestObject.quizes.length; i++) {


                //SECCION PARA ORDENAR EVENTOS
                var eventos = jsonRequestObject.eventos;
                var optionsHTMLSelected = '<option value="noEvent,' + jsonRequestObject.quizes[i].idconcurso + '"> No associated event</option>';
                var optionsHTMLNotSelected = '<option value="noEvent,' + jsonRequestObject.quizes[i].idconcurso + '">No associated event</option>';
                var concat;
                var eventosArray = [];
                var idEvento = "noEvent";
                for (var j = 0; j < eventos.length; j++) {
                    if (!jsonRequestObject.quizes[i].hasOwnProperty("idavayaspaces")) {
                        var eventoToConcat = '<option value="' + eventos[j].idevento + ',' + jsonRequestObject.quizes[i].idconcurso + '">' + eventos[j].nombreevento + '</option>';
                    } else {
                        if (jsonRequestObject.quizes[i].idavayaspaces === eventos[j].idspace) {
                            eventoToConcat = '<option value="' + eventos[j].idevento + ',' + jsonRequestObject.quizes[i].idconcurso + '" selected >' + eventos[j].nombreevento + '</option>';
                            idEvento = eventos[j].idevento;
                        } else {
                            eventoToConcat = '<option value="' + eventos[j].idevento + ',' + jsonRequestObject.quizes[i].idconcurso + '">' + eventos[j].nombreevento + '</option>';
                        }
                    }
                    eventosArray.push(eventoToConcat);
                }
                if (!jsonRequestObject.quizes[i].hasOwnProperty("idavayaspaces")) {
                    concat = optionsHTMLSelected.concat(eventosArray);
                } else {
                    concat = optionsHTMLNotSelected.concat(eventosArray);
                }
                //DEFINIMOS EL BOTON PARA BORRAR
                var butonBorrarQuiz = '';
                //SECCION PARA DEFINIR SI ES FAVORITO O NO LO ES
                var iconStar = '<i class="fa fa-star-o" style="color: rgb(255,255,255);font-size: 21px;cursor: pointer;"></i>';
                if (jsonRequestObject.quizes[i].isfavorite) {
                    iconStar = '<i class="fa fa-star" style="color: yellow;font-size: 21px;cursor: pointer;"></i>';
                    var favoritesHTML = '<button class="btn w3-bar-item w3-button searchOption" type="button">' + jsonRequestObject.quizes[i].nombreconcurso + '</button>';
                    favorites.insertAdjacentHTML('beforeend', favoritesHTML);
                } else {
                    //AGREGAMOS LOS ELEMENTOS TO MY QUIZES
                    var demoAccHTML = '<button class="btn w3-bar-item w3-button searchOption" type="button">' + jsonRequestObject.quizes[i].nombreconcurso + '</button>';
                    demoAcc.insertAdjacentHTML('beforeend', demoAccHTML);
                }

                var fakeImage = '';
                if (jsonRequestObject.quizes[i].fake) {
                    //fakeImage = 'style="    background-image: url(http://www.pngmart.com/files/7/Fake-Transparent-Background.png); background-position: 41% 26%; background-repeat: no-repeat;"';
                    fakeImage = 'style="background-image: url(https://blog.eckelberry.com/wp-content/uploads/2016/02/Fake-Companies-List-Announced-By-TCS-and-IBM-2015.png?_sm_au_=iVVj6fWQ56wSDV5fj1q0vKscFs0qW); background-position: 112% 49%; background-repeat: no-repeat;"';
                    //fakeImage = 'style="    background-image: url(https://absolemarketing.com/blog/wp-content/uploads/2017/01/fake.png?_sm_au_=iVVj6fWQ56wSDV5fj1q0vKscFs0qW); background-position: 112% 49%; background-repeat: no-repeat;"';
                }

                var quizesGroupHTML = '<div class="photo-card" id="' + jsonRequestObject.quizes[i].idconcurso + '" style="margin-top: 10px; background-color: ' + jsonRequestObject.quizes[i].color + '">' +
                        '                   <div class="photo-background" style="background-image: url(' + jsonRequestObject.quizes[i].imageurl + '");">' +
                        '                      <div class="d-xl-flex justify-content-xl-end text-block">' +
                        '                          <h4 class="d-xl-flex align-items-xl-end" style="margin-right: -120px; color: ' + jsonRequestObject.quizes[i].fontcolor + ';">' + jsonRequestObject.quizes[i].preguntas + ' Questions</h4>' +
                        '                      </div>' +
                        '                  </div>' +
                        '                  <div class="text-right photo-details" ' + fakeImage + '>' +
                        '                      <div class="row">' +
                        '                          <div class="col">' +
                        '                              <h1 class="text-left" id="quizName' + jsonRequestObject.quizes[i].idconcurso + '" style="color: ' + jsonRequestObject.quizes[i].fontcolor + ';">' + jsonRequestObject.quizes[i].nombreconcurso + '</h1>' +
                        '                          </div>' +
                        '                          <div class="col">' + iconStar + +butonBorrarQuiz + '' +
                        '                              <div class="dropdown d-inline-block"><button class="btn btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button" style="background-color: rgba(0,123,255,0);"><i class="la la-ellipsis-v" style="color: rgb(0,0,0);font-size: 21px;filter: brightness(177%);cursor: pointer;"></i> </button>' +
                        '                                  <div class="dropdown-menu dropdown-menu-right" role="menu"><a class="dropdown-item" role="presentation" onclick=(borrarQuiz("' + jsonRequestObject.quizes[i].idconcurso + '")) style="cursor:pointer;">Delete Quiz <i class="fas fa-trash-alt" style="color:red;"></i></a></div>' +
                        '                              </div>' +
                        '                          </div>' +
                        '                      </div>' +
                        '                   <div class="row"><div class="col-sm-6"><select class="custom-select cardSelect" onchange="(cambioDeEventoAsociado(this))" >' + concat + '</select></div><div class="col"><p style="color: ' + jsonRequestObject.quizes[i].fontcolor + ';">' + jsonRequestObject.quizes[i].fechacreacion + '</p> </div></div>' +
                        '                      <div class="photo-tags">' +
                        '                          <ul class="text-right d-xl-flex justify-content-xl-end" style="opacity: 1;filter: brightness(93%);">' +
                        '                              <li>1<button type="button" class="btn btn-success btnToPlay" onclick=(goToPlay("' + jsonRequestObject.quizes[i].idconcurso + '","' + idEvento + '"))>Play&nbsp;<i class="fa fa-play"></i></button></li>' +
                        '                          </ul>' +
                        '                      </div>' +
                        '                  </div>' +
                        '              </div>';
                quizesGroup.insertAdjacentHTML('beforeend', quizesGroupHTML);
            }
        }
    } catch (err) {
        console.log(err);
    }
}

function goToPlay(uuidConcurso, idEvento) {
    if (idEvento !== "noEvent") {
        window.location.replace("?p=playQuiz&q=" + uuidConcurso + "&e=" + idEvento);
    }
}

function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
}

function createEventRest() {
    console.log("createEvent");
    var inputEventName = document.getElementById('inputEventName').value;
    if (inputEventName.length !== 0) {
        var createEventObj = {
            "action": "createEvent",
            "eventName": inputEventName
        };
        var data = new FormData();
        data.append("jsonObject", JSON.stringify(createEventObj));

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = false;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                console.log(this.responseText);
                try {
                    var jsonObject = JSON.parse(this.responseText);
                    if (jsonObject.status === "ok") {
                        document.getElementById('eventNameCreated').innerHTML = "";
                        document.getElementById('eventNameCreated').innerHTML = inputEventName;
                        document.getElementById('joinURL').innerHTML = "";
                        document.getElementById('joinURL').innerHTML = jsonObject.avayaSpace.joinURL;
                        document.getElementById('registerPageLink').innerHTML = "";
                        document.getElementById('registerPageLink').innerHTML = "https://avayacalaevents.appspot.com/RegisterAvayaContestOnline?p=evento&e=" + jsonObject.uuid;
                        document.getElementById('createQuizModalClose').click();
                        document.getElementById('btnEventCreated').click();
                        addNewEventTOSelectChoice(jsonObject.uuid, jsonObject.nombre);
                    } else {
                        console.log(jsonObject.message);
                    }
                } catch (err) {
                    console.log(err.toString());
                }
            }
        });

        xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");

        xhr.send(data);
    } else {
        alert("Please enter the name of the event");
    }

    function addNewEventTOSelectChoice(idEvento, nombre) {
        var photoCard = document.getElementsByClassName('photo-card');
        console.log("addNewEventTOSelectChoice");
        console.log(idEvento);
        console.log(nombre);
        for (var i = 0; i < photoCard.length; i++) {
            var customSelect = photoCard[i].querySelectorAll(".cardSelect");
            console.log(customSelect[0]);
            var eventoToAdd = '<option value="' + idEvento + ',' + photoCard[i].id + '">' + nombre + '</option>';
            customSelect[0].insertAdjacentHTML('beforeend', eventoToAdd);
        }
    }
}



function joinAvayaSpaces() {
    //joinURLClass
    var joinURL = document.getElementById('eventCreated').querySelectorAll(".joinURLClass")[0].innerHTML;
    window.open(joinURL, '_blank');
}

function cambioDeEventoAsociado(_this) {
    var selectedOption = _this.options[_this.selectedIndex].value;
    var jsonRequesst = {
        "action": "associateAnEvent",
        "idevento": "",
        "idconcurso": "",
        "haseventid": "true"
    };
    var split = selectedOption.split(",");
    if (split[0] === "noEvent") {
        jsonRequesst.haseventid = "false";
    }
    jsonRequesst.idevento = split[0];
    jsonRequesst.idconcurso = split[1];


    var card = document.getElementById(split[1]);
    console.log(card);
    var playButton = card.querySelectorAll('.btnToPlay')[0];
    console.log(playButton);
    playButton.setAttribute('disabled', 'true');
    playButton.innerHTML = "";
    var updatingLoader = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Updating...';
    playButton.insertAdjacentHTML('beforeend', updatingLoader);


    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonRequesst));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            try {
                var jsonObject = JSON.parse(this.responseText);
                if (jsonObject.status === "ok") {
                    var photoCard = document.getElementsByClassName('photo-card');
                    for (var i = 0; i < photoCard.length; i++) {
                        playButton.setAttribute("onClick", 'goToPlay("' + jsonRequesst.idconcurso + '","' + jsonObject.event.idEvent + '")');
                        playButton.innerHTML = "";
                        var html = 'Play&nbsp;<i class="fa fa-play"></i>';
                        playButton.insertAdjacentHTML('beforeend', html);
                        playButton.disabled = false;
                    }
                } else {

                }
            } catch (err) {
                console.log(err.toString());
            }

        }
    });
    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);
}

function mangeEventsBTN() {
    var manageEventsModelBody = document.getElementById('manageEventsModelBody');
    manageEventsModelBody.innerHTML = "";
    //console.log("Manage Events");
    var customSelect = document.getElementsByClassName('cardSelect');
    //console.log(customSelect[0].children);
    var rowToConcatArray = [];
    var concat = "";

    var alertSuccess = '<div class="alert alert-success" role="alert" id="modalManageEventsSuccessAlert" style="display:none;"></div>';
    var alertDanger = '<div class="alert alert-danger" role="alert" id="modalManageEventDangerAlert" style="display:none;"></div>';
    var alertWarning = '<div class="alert alert-warning" role="alert" id="modalManageEventWarningAlert" style="display:none;"></div>';
    rowToConcatArray.push(alertDanger);
    rowToConcatArray.push(alertSuccess);
    rowToConcatArray.push(alertWarning);
    for (var i = 0; i < customSelect[0].children.length; i++) {

        var split = customSelect[0].children[i].value.split(",");
        if (customSelect[0].children[i].innerHTML !== "No associated event" && split[0] !== "noEvent") {
            // console.log(customSelect[0].children[i].innerHTML);
            //console.log(split[0]);
            var rowToConcat = '<div class="row" id="Event' + split[0] + '" style="margin-buttom: 5px;">' +
                    '              <div class="col" style="text-align: center;">' +
                    '                 <h4 id="Name' + split[0] + '">' + customSelect[0].children[i].innerHTML + '</h4>' +
                    '            </div>' +
                    '           <div class="col" style="margin: auto; text-align: center;' +
                    '               width: 50%;">' +
                    '<div class="btn-group" role="group" aria-label="Basic example">' +
                    '             <button type="button" class="btn btn-info" title="Press to get the registration page for the event." onclick=(copyRegisterURL("' + split[0] + '"))><i class="fas fa-user-edit" style="color: white"></i></button>' +
                    '             <button type="button" class="btn btn-success" title="Press to open the private space of the event." onclick=(goToSpaceByJoinURL("' + split[0] + '"))><i class="fas fa-rocket" style="color: white"></i></button>' +
                    '             <button type="button" class="btn btn-primary"  title="Press to send the certificates to all the members of an event."  onclick=(enviarCertificados("' + split[0] + '"))><i class="fas fa-file-import" style="color: white"></i></button>' +
                    '             <button type="button" class="btn btn-dark"  title="Press to edit the description of the registration page." id="registerPage' + split[0] + '" onclick=(abrirFormDetallesPaginaRegistro("' + split[0] + '"))><i class="fas fa-edit" style="color: white"></i></button>' +
                    '             <button type="button" class="btn btn-danger" title="Press and delete a event" onclick=(deleteEvent("' + split[0] + '"))><i class="fas fa-trash-alt" style="color: white;"></i></button>' +
                    '        </div>' +
                    '</div>' +
                    '   </div>';
            rowToConcatArray.push(rowToConcat);
        }

    }
    var concatElement = concat.concat(rowToConcatArray);
    var manageEventsModelBodyHTML = concatElement.replace(/,/g, "");
    manageEventsModelBody.insertAdjacentHTML('beforeend', manageEventsModelBodyHTML);
    ;
}

function goToSpaceByJoinURL(idEvent) {
    console.log("goToSpaceByJoinURL");
    var jsonRequest = {
        "action": "obtenerJoinURL",
        "idevent": idEvent
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonRequest));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            try {
                var jsonResponse = JSON.parse(this.responseText);
                if (jsonResponse.status === "ok") {
                    window.open(jsonResponse.event.joinurl);
                } else {
                    console.log(jsonResponse.message);
                }
            } catch (err) {
                console.log(err.toString());
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);
}


function enviarCertificados(idEvent) {

    var modalManageEventWarningAlert = document.getElementById('modalManageEventWarningAlert');
    modalManageEventWarningAlert.innerHTML = "";

    var messageTwo = '<div class="row">' +
            '                    <div class="col-sm-12" style="text-align: center;">' +
            '                     <p>Are you sure you want to send the certificates to the members of the event ' + document.getElementById('Name' + idEvent).innerHTML + ' ?</p> ' +
            '                  </div>' +
            '             </div>' +
            '             <div class="row">' +
            '                <div class="col-sm-12" style="text-align: center;">' +
            '                <button type="button" class="btn btn-secondary" onclick=(closeWarning())><i class="far fa-times-circle"></i></button>' +
            '                <button type="button" class="btn btn-primary confirmSendCertificate" onclick=(sendCertificatesEventRest("' + idEvent + '"))>Send</button>' +
            '              </div>' +
            '         </div>';
    modalManageEventWarningAlert.insertAdjacentHTML('beforeend', messageTwo);
    modalManageEventWarningAlert.style.display = "";

}

function closeWarning() {
    var modalManageEventWarningAlert = document.getElementById('modalManageEventWarningAlert');
    modalManageEventWarningAlert.innerHTML = "";
    modalManageEventWarningAlert.style.display = "none";
}


function sendCertificateViaSpacesRest(idEvent) {
    var jsonObject = {
        "action": "sendCertificateViaSpaces",
        "idEvent": idEvent
    };

    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
        }
    });
    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");

    xhr.send(data);

}

function sendCertificatesEventRest(idEvent) {
    var buttonDelete = document.getElementsByClassName('confirmSendCertificate');
    buttonDelete[0].setAttribute('disabled', 'true');
    buttonDelete[0].innerHTML = "";
    var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Sending...';
    buttonDelete[0].insertAdjacentHTML('beforeend', htmlSpinner);

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            closeWarning();
            sendCertificateViaSpacesRest(idEvent);
            try {
                var pMessage = document.createElement('P');
                var pMessageTextnode = document.createTextNode('The certificate submission has been successfully completed.');
                pMessage.appendChild(pMessageTextnode);
                var alertMessage = document.getElementById('modalManageEventsSuccessAlert');
                alertMessage.innerHTML = "";
                alertMessage.appendChild(pMessage);
                alertMessage.style.display = "";
                sleep(10000).then(() => {
                    alertMessage.innerHTML = "";
                    alertMessage.style.display = "none";
                });

            } catch (err) {
                console.log(err.toString());
                var pMessage = document.createElement('P');
                var pMessageTextnode = document.createTextNode('Failed to send certificates.');
                pMessage.appendChild(pMessageTextnode);
                var alertMessage = document.getElementById('modalManageEventDangerAlert');
                alertMessage.innerHTML = "";
                alertMessage.appendChild(pMessage);
                alertMessage.style.display = "";
                sleep(10000).then(() => {
                    alertMessage.innerHTML = "";
                    alertMessage.style.display = "none";
                });
            }
            console.log(this.responseText);
        }
    });

    xhr.open("POST", "https://avayacalaevents.appspot.com/Certificado?u=" + idEvent);

    xhr.send();



}

function copyRegisterURL(idEvent) {
    var copyText = "https://avayacalaevents.uc.r.appspot.com/RegisterAvayaContestOnline?p=evento&e=" + idEvent;
    var el = document.createElement('textarea');
    el.value = copyText.toString();
    document.getElementById('manageEventsModelBody').appendChild(el);
    el.select();
    document.execCommand('copy');
    document.getElementById('manageEventsModelBody').removeChild(el);
    //alert("Copied!");
    var pMessage = document.createElement('p');
    var link = document.createElement('a');
    link.setAttribute('href', copyText);
    link.setAttribute('target', "_blank");
    link.setAttribute('style', 'color: black; font-weight: bold');
    var linkTextNode = document.createTextNode('click to open register page');
    link.appendChild(linkTextNode);
    var pMessageTextnode = document.createTextNode('The registration page has been copied to your clipboard,');
    pMessage.appendChild(pMessageTextnode);
    pMessage.appendChild(link);
    var alertMessage = document.getElementById('modalManageEventsSuccessAlert');
    alertMessage.innerHTML = "";
    alertMessage.appendChild(pMessage);
    alertMessage.style.display = "";


    sleep(5000).then(() => {
        alertMessage.innerHTML = "";
        alertMessage.style.display = "none";
    });

}

function deleteEvent(idEvent) {

    var modalManageEventWarningAlert = document.getElementById('modalManageEventWarningAlert');
    modalManageEventWarningAlert.innerHTML = "";

    var messageTwo = '<div class="row">' +
            '                    <div class="col-sm-12" style="text-align: center;">' +
            '                     <p>Sure you want to delete the event ' + document.getElementById('Name' + idEvent).innerHTML + ' ?</p> ' +
            '                  </div>' +
            '             </div>' +
            '       <div class="row">' +
            '                    <div class="col-sm-12" style="text-align: center;">' +
            '                     <p>Deleting the event will delete the associated space in <span style="font-weight: bold;">Avaya Spaces</span></p> ' +
            '                  </div>' +
            '             </div>' +
            '             <div class="row">' +
            '                <div class="col-sm-12" style="text-align: center;">' +
            '                <button type="button" class="btn btn-secondary" onclick=(closeWarning())><i class="far fa-times-circle"></i></button>' +
            '                <button type="button" class="btn btn-danger confirmDelete" onclick=(deleteEventRest("' + idEvent + '"))>DELETE</button>' +
            '              </div>' +
            '         </div>';
    modalManageEventWarningAlert.insertAdjacentHTML('beforeend', messageTwo);
    modalManageEventWarningAlert.style.display = "";
}

function deleteEventRest(idEvent) {
    console.log(idEvent);
    var buttonDelete = document.getElementsByClassName('confirmDelete');
    buttonDelete[0].setAttribute('disabled', 'true');
    buttonDelete[0].innerHTML = "";
    var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Erasing...';
    buttonDelete[0].insertAdjacentHTML('beforeend', htmlSpinner);

    var jsonObject = {
        "action": "deleteEvent",
        "idevent": idEvent
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            try {
                var jsonObjectResponse = JSON.parse(this.responseText);
                if (jsonObjectResponse.status === "ok") {
                    var modalManageEventWarningAlert = document.getElementById('modalManageEventWarningAlert');
                    modalManageEventWarningAlert.style.display = "none";
                    var modalManageEventsSuccessAlert = document.getElementById('modalManageEventsSuccessAlert');
                    modalManageEventsSuccessAlert.innerHTML = "";
                    var message = " <p>The event and space has been successfully deleted</p>";
                    modalManageEventsSuccessAlert.insertAdjacentHTML('beforeend', message);
                    modalManageEventsSuccessAlert.style.display = "";

                    var eventInModalBody = document.getElementById('Event' + idEvent);
                    eventInModalBody.style.display = "none";

                    var customSelect = document.getElementsByClassName('cardSelect');
                    //console.log(customSelect);
                    for (var i = 0; i < customSelect.length; i++) {
                        //console.log(customSelect[i]);
                        for (var j = 0; j < customSelect[i].children.length; j++) {
                            //console.log(customSelect[i].children[j].value);
                            var valueOptions = customSelect[i].children[j].value.split(",");
                            //console.log(valueOptions[0]);
                            if (valueOptions[0] === idEvent) {
                                console.log("MATCH");
                                customSelect[i].children[j].remove();
                            }
                        }
                    }

                    sleep(2000).then(() => {
                        modalManageEventsSuccessAlert.innerHTML = "";
                        modalManageEventsSuccessAlert.style.display = "none";
                    });

                } else {
                    console.log(jsonObjectResponse.message);
                }
            } catch (err) {
                console.log(err.toString());
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");

    xhr.send(data);

}


function borrarQuiz(idQuiz) {
    console.log("Borrar Quiz");
    document.getElementById('deleteQuizBTN').click();

    //quizName+
    var labelDeleteQuiz = document.getElementById('labelDeleteQuiz');
    labelDeleteQuiz.innerHTML = "";
    labelDeleteQuiz.innerHTML = "<h5>Sure you want to delete the quiz " + document.getElementById('quizName' + idQuiz).innerHTML + " ?</h5>";

    var deleteQuizConfirmation = document.getElementById('deleteQuizConfirmation');
    deleteQuizConfirmation.setAttribute('onclick', 'deleteConfirmationRest("' + idQuiz + '","' + document.getElementById('quizName' + idQuiz).innerHTML + '")');
}

function deleteConfirmationRest(idQuiz, nombreQuiz) {

    var deleteQuizConfirmation = document.getElementById('deleteQuizConfirmation');
    deleteQuizConfirmation.innerHTML = "";
    deleteQuizConfirmation.setAttribute('disabled', true);

    var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Erasing...';
    deleteQuizConfirmation.insertAdjacentHTML('beforeend', htmlSpinner);


    /*
     * AJAX BORRAR QUIZ
     */
    var jsonObject = {
        "action": "deleteQuiz",
        "idquiz": idQuiz
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            try {
                var jsonObjectResponse = JSON.parse(this.responseText);
                if (jsonObjectResponse.status === "ok") {
                    deleteQuizConfirmation.innerHTML = "";
                    deleteQuizConfirmation.innerHTML = "DELETE";
                    deleteQuizConfirmation.disabled = false;
                    deleteQuizConfirmation.setAttribute('onclick', 'deleteConfirmationRest("")');
                    document.getElementById(idQuiz).remove();
                    var demoAcc = document.getElementById('demoAcc');
                    for (var i = 0; i < demoAcc.children.length; i++) {
                        if (demoAcc.children[i].innerHTML === nombreQuiz) {
                            demoAcc.children[i].remove();
                        }
                    }
                    var favorites = document.getElementById('favorites');
                    for (var i = 0; i < favorites.children.length; i++) {
                        if (favorites.children[i].innerHTML === nombreQuiz) {
                            favorites.children[i].remove();
                        }
                    }
                    document.getElementById('closeBtnDeleteQuiz').click();
                } else {
                    console.log(jsonObjectResponse.message);
                }
            } catch (err) {
                console.log(err.toString())
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");

    xhr.send(data);
}

function openRegisterPage() {
    console.log("openRegisterPage");
    var registerPage = document.getElementById('registerPageLink').innerHTML;
    console.log(registerPage.replace("amp;", ""));
    window.open(document.getElementById('registerPageLink').innerHTML.replace("amp;", ""), '_blank');
}

function abrirFormDetallesPaginaRegistro(idEvento) {
    console.log("ID EVENTO " + idEvento);
    var registerPageBTN = document.getElementById('registerPage' + idEvento);
    registerPageBTN.innerHTML = "";
    registerPageBTN.setAttribute('disabled', true);
    var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
    registerPageBTN.insertAdjacentHTML('beforeend', htmlSpinner);
    var jsonObject = {
        "action": "obtenerDatosPaginaRegistroPorEvento",
        "idEvento": idEvento
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            //console.log(this.responseText);
            try {
                registerPageBTN.innerHTML = "";
                registerPageBTN.innerHTML = '<i class="fas fa-edit" style="color: white"></i>';
                registerPageBTN.disabled = false;
                var jsonObjectRequest = JSON.parse(this.responseText);
                if (jsonObjectRequest.status === "ok") {
                    //console.log(jsonObjectRequest);
                    var titulosArray = jsonObjectRequest.registerInfo.titulosArray;
                    var descripcionEvento = jsonObjectRequest.registerInfo.descripcion;
                    var speakersEvento = jsonObjectRequest.registerInfo.speakersEvento;
                    console.log(speakersEvento);
                    var speakersUsuario = jsonObjectRequest.registerInfo.speakersUsuario;
                    //TITULOS
                    var inputSubTitles = document.getElementById('inputSubTitles');
                    inputSubTitles.innerHTML = "";
                    var textInput = '<input type="text" class="form-control">';
                    inputSubTitles.insertAdjacentHTML('beforeend', textInput);

                    if (titulosArray.length !== 0) {
                        titulosArray = JSON.parse(jsonObjectRequest.registerInfo.titulosArray);
                        inputSubTitles.innerHTML = "";
                        for (var i = 0; i < titulosArray.length; i++) {
                            var hr = document.createElement('HR');
                            hr.setAttribute('style', 'margin: 2px;');
                            var input = document.createElement('INPUT');
                            input.setAttribute('type', 'text');
                            input.setAttribute('class', 'form-control');
                            input.setAttribute('value', titulosArray[i]);
                            inputSubTitles.appendChild(hr);
                            inputSubTitles.appendChild(input);
                        }
                    }
                    //DESCRIPCION
                    var textAreaEvent = document.getElementById('textAreaEvent');
                    textAreaEvent.innerHTML = "";
                    if (descripcionEvento.length !== 0) {
                        var textAreaEventTextNode = document.createTextNode(descripcionEvento);
                        textAreaEvent.appendChild(textAreaEventTextNode);
                    }
                    //SPEAKERS ASIGNADOS AL EVENTO
                    var exampleFormControlSelect2 = document.getElementById('exampleFormControlSelect2');
                    exampleFormControlSelect2.innerHTML = "";
                    if (speakersEvento.length !== 0) {
                        exampleFormControlSelect2.innerHTML = "";
                        for (var j = 0; j < speakersEvento.length; j++) {
                            var speakersEventObject = JSON.parse(speakersEvento[j]);
                            var optionSpeakerEvent = document.createElement('OPTION');
                            optionSpeakerEvent.setAttribute('id', speakersEventObject.idspeaker);
                            optionSpeakerEvent.setAttribute('onclick', 'optionSpeakerEventClick("' + speakersEventObject.idspeaker + '")');
                            optionSpeakerEvent.setAttribute('style', 'cursor:pointer;');
                            optionSpeakerEvent.setAttribute('title', 'Press to see the ' + speakersEventObject.nombrespeaker + ' information');
                            var optionSpeakerEventTextnode = document.createTextNode(speakersEventObject.nombrespeaker);
                            optionSpeakerEvent.appendChild(optionSpeakerEventTextnode);
                            exampleFormControlSelect2.appendChild(optionSpeakerEvent);
                        }
                    }
                    //PULL SPEAKERS
                    var speakersSelect = document.getElementsByClassName('speakers-select')[0];
                    speakersSelect.innerHTML = "";
                    var optionDefault = '<option selected>Select a Speakers</option>';
                    speakersSelect.insertAdjacentHTML('beforeend', optionDefault);
                    if (speakersUsuario.length !== 0) {
                        for (var k = 0; k < speakersUsuario.length; k++) {
                            var optionSpeakerUser = document.createElement('OPTION');
                            optionSpeakerUser.setAttribute('class', speakersUsuario[k].idspeaker);
                            var optionSpeakerUserTextNode = document.createTextNode(speakersUsuario[k].nombrespeaker);
                            optionSpeakerUser.appendChild(optionSpeakerUserTextNode);
                            speakersSelect.appendChild(optionSpeakerUser);
                        }
                    }

                    document.getElementsByClassName('closeModalEvent')[0].click();
                    document.getElementById('descriptionEvent').click();
                    document.getElementById('idEventForRegisterPage').innerHTML = "";
                    document.getElementById('idEventForRegisterPage').innerHTML = idEvento;

                } else {
                    console.log(jsonObjectRequest.message);
                }
            } catch (err) {
                console.log(err.toString());
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);


}

function agregarInputSubTitleEvent() {
    console.log("agregarInputSubTitleEvent");
    var inputSubTitles = document.getElementById('inputSubTitles');
    console.log(inputSubTitles.children.length);
    if (inputSubTitles.children.length <= 7) {
        var hr = document.createElement('HR');
        hr.setAttribute('style', 'margin: 2px;');
        var input = document.createElement('INPUT');
        input.setAttribute('type', 'text');
        input.setAttribute('class', 'form-control');
        inputSubTitles.appendChild(hr);
        inputSubTitles.appendChild(input);


        var pMessage = document.createElement('P');
        var pMessageTextnode = document.createTextNode('A new subtitle has been added');
        pMessage.appendChild(pMessageTextnode);
        var alertMessage = document.getElementById('modalDescriptionEventsSuccessAlert');
        alertMessage.innerHTML = "";
        alertMessage.appendChild(pMessage);
        alertMessage.style.display = "";
        sleep(3000).then(() => {
            alertMessage.innerHTML = "";
            alertMessage.style.display = "none";
        });
    } else {
        var pMessage = document.createElement('P');
        var pMessageTextnode = document.createTextNode('The maximum of subtitles are 5.');
        pMessage.appendChild(pMessageTextnode);
        var alertMessage = document.getElementById('modalDescriptionEventDangerAlert');
        alertMessage.innerHTML = "";
        alertMessage.appendChild(pMessage);
        alertMessage.style.display = "";
        sleep(3000).then(() => {
            alertMessage.innerHTML = "";
            alertMessage.style.display = "none";
        });
    }
}

function disminuirSubTitleEvent() {
    console.log("agregarInputSubTitleEvent");
    var inputSubTitles = document.getElementById('inputSubTitles');
    console.log(inputSubTitles.children.length);
    if (inputSubTitles.children.length >= 2) {
        for (var i = 0; i < 2; i++) {
            inputSubTitles.children[inputSubTitles.children.length - 1].remove();
        }
        var pMessage = document.createElement('P');
        var pMessageTextnode = document.createTextNode('The last subtitle has been removed.');
        pMessage.appendChild(pMessageTextnode);
        var alertMessage = document.getElementById('modalDescriptionEventsSuccessAlert');
        alertMessage.innerHTML = "";
        alertMessage.appendChild(pMessage);
        alertMessage.style.display = "";
        sleep(3000).then(() => {
            alertMessage.innerHTML = "";
            alertMessage.style.display = "none";
        });
    } else {
        var pMessage = document.createElement('P');
        var pMessageTextnode = document.createTextNode('Must have at least one subtitle.');
        pMessage.appendChild(pMessageTextnode);
        var alertMessage = document.getElementById('modalDescriptionEventDangerAlert');
        alertMessage.innerHTML = "";
        alertMessage.appendChild(pMessage);
        alertMessage.style.display = "";
        sleep(3000).then(() => {
            alertMessage.innerHTML = "";
            alertMessage.style.display = "none";
        });
    }
}

function uploadImageBTN() {
    var inputUpload = document.createElement("INPUT");
    inputUpload.setAttribute('type', 'file');
    inputUpload.setAttribute('id', 'imgInp');
    inputUpload.setAttribute('style', 'display:none;');
    document.getElementById('divImage').appendChild(inputUpload);
    document.getElementById('imgInp').click();

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#imageSpeaker').attr('src', e.target.result);
                document.getElementById('imgInp').remove();
            };

            reader.readAsDataURL(input.files[0]); // convert to base64 string
        }
    }

    $("#imgInp").change(function () {
        readURL(this);
    });
}

function createSpeakerBTN() {
    var speakerDisplaySection = document.getElementById('speakerDisplaySection');
    speakerDisplaySection.innerHTML = "";
    var htmlToAppend = '<div class="row">' +
            '                                    <div class="col-sm-4" style="text-align: center;" id="divImage">' +
            '                                       <img src="https://via.placeholder.com/150" id="imageSpeaker" alt="Image Speaker" style="max-width: 100%; cursor: pointer;" onclick="uploadImageBTN()">' +
            '                                      <br> ' +
            '                                     <br> ' +
            '                                    <br> ' +
            '                                   <button type="button" class="btn btn-success" onclick="createSpeakerSubmit(this)">Create</button>' +
            '                              </div>' +
            '                             <div class="col-sm-8">' +
            '                                <div class="form-group">' +
            '                                   <small class="form-text text-muted">Speaker Name</small>' +
            '                                  <input type="text" class="form-control form-control-sm" id="nombreSpeaker"  placeholder="Speaker Name">' +
            '                             </div>' +
            '                            <div class="form-group">' +
            '                               <small class="form-text text-muted">Speaker Position</small>' +
            '                              <input type="text" class="form-control form-control-sm" id="posicionSpeaker"  placeholder="Speaker Position">' +
            '                         </div>' +
            '                        <div class="form-group">' +
            '                           <small class="form-text text-muted">Speaker Company</small>' +
            '                          <input type="text" class="form-control form-control-sm" id="companySpeaker"  placeholder="Speaker Company">' +
            '                     </div>' +
            '                    <div>' +
            '                       <small class="form-text text-muted">Speaker Description</small>' +
            '                      <textarea class="form-control" id="descriptionSpeaker" rows="3" id="descriptionSpeaker" ></textarea>' +
            '                   <p style="display: none;" id="idOfSpeaker" ></p>' +
            '                 </div>' +
            '            </div>' +
            '       </div>';
    speakerDisplaySection.insertAdjacentHTML('beforeend', htmlToAppend);
}

function createSpeakerSubmit(_this) {
    var errorMessage;
    var speakerJson = {
        "action": "createSpeaker",
        "imageSpeaker": document.getElementById('imageSpeaker').src,
        "nombreSpeaker": document.getElementById('nombreSpeaker').value,
        "posicionSpeaker": document.getElementById('posicionSpeaker').value,
        "companySpeaker": document.getElementById('companySpeaker').value,
        "descriptionSpeaker": document.getElementById('descriptionSpeaker').value
    };


    if (validInputForm(speakerJson)) {
        _this.innerHTML = "";
        _this.setAttribute('disabled', true);
        var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Creating...';
        _this.insertAdjacentHTML('beforeend', htmlSpinner);
        var data = new FormData();
        data.append("jsonObject", JSON.stringify(speakerJson));
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = false;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                console.log(this.responseText);
                try {
                    var jsonObjectRequest = JSON.parse(this.responseText);
                    if (jsonObjectRequest.status === "ok") {
                        _this.setAttribute('title', 'Press to add speaker to the event');
                        _this.setAttribute('onclick', 'addToEventBTN(this)');
                        _this.disabled = false;
                        _this.innerHTML = "";
                        _this.innerHTML = "Add";
                        var speakersSelect = document.getElementsByClassName('speakers-select');
                        var optionSpeaker = document.createElement('OPTION');
                        optionSpeaker.setAttribute('id', jsonObjectRequest.message);
                        var textOptionSpeaker = document.createTextNode(speakerJson.nombreSpeaker);
                        optionSpeaker.appendChild(textOptionSpeaker);
                        speakersSelect[0].appendChild(optionSpeaker);
                        document.getElementById('idOfSpeaker').innerHTML = "";
                        document.getElementById('idOfSpeaker').innerHTML = jsonObjectRequest.message;
                    } else {
                        console.log(jsonObjectRequest.message);
                    }
                } catch (err) {
                    console.log(err.toString());
                }
            }
        });
        xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
        xhr.send(data);

    } else {
        var pMessage = document.createElement('P');
        var pMessageTextnode = document.createTextNode(errorMessage);
        pMessage.appendChild(pMessageTextnode);
        var alertMessage = document.getElementById('modalDescriptionEventDangerAlert');
        alertMessage.innerHTML = "";
        alertMessage.appendChild(pMessage);
        alertMessage.style.display = "";
        sleep(3000).then(() => {
            alertMessage.innerHTML = "";
            alertMessage.style.display = "none";
        });
    }




    function validInputForm(speakerJson) {
        if (speakerJson.imageSpeaker === "https://via.placeholder.com/150") {
            errorMessage = "Please insert an image of the speaker.";
            return false;
        }
        if (speakerJson.nombreSpeaker.length === 0) {
            errorMessage = "Please provide the name of the speaker";
            return false;
        }
        if (speakerJson.posicionSpeaker.length === 0) {
            errorMessage = "Please provide the position of the speaker";
            return false;
        }
        if (speakerJson.companySpeaker.length === 0) {
            errorMessage = "Please provide the company of the speaker";
            return false;
        }
        if (speakerJson.descriptionSpeaker.length === 0) {
            errorMessage = "Please provide a description of the speaker";
            return false;
        }
        return true;
    }
}

function speakersUserSelect(_this) {
    var strUser = _this.options[_this.selectedIndex].classList;
    var speakerDisplaySection = document.getElementById('speakerDisplaySection');
    speakerDisplaySection.innerHTML = "";
    speakerDisplaySection.setAttribute('disabled', true);
    var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Obtaining information from the speaker...';
    speakerDisplaySection.insertAdjacentHTML('beforeend', htmlSpinner);

    var jsonObject = {
        "action": "obtenerSpeakerPorIdSpeaker",
        "idSpeaker": strUser[0]
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            //console.log(this.responseText);
            try {
                var jsonObject = JSON.parse(this.responseText);
                console.log(jsonObject);
                if (jsonObject.status === "ok") {
                    speakerDisplaySection.innerHTML = "";
                    var htmlToAppend = '<div class="row">' +
                            '                                  <div class="col-sm-4" style="text-align: center;" id="divImage">' +
                            '                                       <img src="' + jsonObject.Speaker.foto + '" id="imageSpeaker" alt="Image Speaker" style="max-width: 100%; cursor: pointer;" title="Press to add a new photo to ' + jsonObject.Speaker.nombrespeaker + '" onclick="uploadImageBTN()">' +
                            '                                      <br> ' +
                            '                                     <br> ' +
                            '                                    <br> ' +
                            '                               <div class="btn-group-sm" role="group" aria-label="Basic example">' +
                            '                                   <button type="button" class="btn btn-success" title="Press to add ' + jsonObject.Speaker.nombrespeaker + ' to the event" onclick="addToEventBTN(this)">Add</button>' +
                            '                                   <button type="button" class="btn btn-warning" title="Press to update info of ' + jsonObject.Speaker.nombrespeaker + '" onclick="updateSpeakerBTN(this)">Update</button>' +
                            '                              </div>' +
                            '                              </div>' +
                            '                             <div class="col-sm-8">' +
                            '                                <div class="form-group">' +
                            '                                   <small class="form-text text-muted">Speaker Name</small>' +
                            '                                  <input type="text" class="form-control form-control-sm" id="nombreSpeaker"  placeholder="Speaker Name" value="' + jsonObject.Speaker.nombrespeaker + '">' +
                            '                             </div>' +
                            '                            <div class="form-group">' +
                            '                               <small class="form-text text-muted">Speaker Position</small>' +
                            '                              <input type="text" class="form-control form-control-sm" id="posicionSpeaker"  placeholder="Speaker Position" value="' + jsonObject.Speaker.titulo + '">' +
                            '                         </div>' +
                            '                        <div class="form-group">' +
                            '                           <small class="form-text text-muted">Speaker Company</small>' +
                            '                          <input type="text" class="form-control form-control-sm" id="companySpeaker"  placeholder="Speaker Company" value="' + jsonObject.Speaker.empresa + '">' +
                            '                     </div>' +
                            '                    <div>' +
                            '                       <small class="form-text text-muted">Speaker Description</small>' +
                            '                      <textarea class="form-control" id="descriptionSpeaker" rows="3" id="descriptionSpeaker" >' + jsonObject.Speaker.descripcion + '</textarea>' +
                            '                   <p style="display: none;" id="idOfSpeaker" >' + jsonObject.Speaker.idspeaker + '</p>' +
                            '                 </div>' +
                            '            </div>' +
                            '       </div>';
                    speakerDisplaySection.insertAdjacentHTML('beforeend', htmlToAppend);


                } else {
                    console.log(jsonObject.message);
                    var pMessage = document.createElement('P');
                    var pMessageTextnode = document.createTextNode(jsonObject.message);
                    pMessage.appendChild(pMessageTextnode);
                    var alertMessage = document.getElementById('modalDescriptionEventDangerAlert');
                    alertMessage.innerHTML = "";
                    alertMessage.appendChild(pMessage);
                    alertMessage.style.display = "";
                    sleep(3000).then(() => {
                        alertMessage.innerHTML = "";
                        alertMessage.style.display = "none";
                    });
                }
            } catch (err) {
                console.log(err.toString());
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);

}

function addToEventBTN(_this) {
    console.log("addToEventBTN");
    var exampleFormControlSelect2 = document.getElementById('exampleFormControlSelect2');
    //console.log(exampleFormControlSelect2.children);
    if (exampleFormControlSelect2.children.length < 5) {
        var idOfSpeaker = document.getElementById('idOfSpeaker').innerHTML;
        var isOnSelect = false;
        for (var i = 0; i < exampleFormControlSelect2.children.length; i++) {
            console.log(exampleFormControlSelect2.children[i].id);
            var idSpeakersInSelect = exampleFormControlSelect2.children[i].id;
//            var idSpeakersInSelect = document.getElementById('exampleFormControlSelect2').childen[i].id;
            if (idSpeakersInSelect === idOfSpeaker) {
                console.log("True");
                isOnSelect = true;
            }
        }

        if (isOnSelect === false) {
            var optionSpeakerEvent = document.createElement('OPTION');
            optionSpeakerEvent.setAttribute('id', idOfSpeaker);
            optionSpeakerEvent.setAttribute('onclick', 'optionSpeakerEventClick("' + idOfSpeaker + '")');
            optionSpeakerEvent.setAttribute('style', 'cursor:pointer;');
            optionSpeakerEvent.setAttribute('title', 'Press to see the ' + document.getElementById('nombreSpeaker').value + ' information');
            var optionSpeakerEventTextnode = document.createTextNode(document.getElementById('nombreSpeaker').value);
            optionSpeakerEvent.appendChild(optionSpeakerEventTextnode);
            exampleFormControlSelect2.appendChild(optionSpeakerEvent);
            updateSpeakersRest(document.getElementById('idEventForRegisterPage').innerHTML);

        } else {
            var pMessage = document.createElement('P');
            var pMessageTextnode = document.createTextNode("The Speaker Info is already displayed in the register page.");
            pMessage.appendChild(pMessageTextnode);
            var alertMessage = document.getElementById('modalDescriptionEventDangerAlert');
            alertMessage.innerHTML = "";
            alertMessage.appendChild(pMessage);
            alertMessage.style.display = "";
            sleep(3000).then(() => {
                alertMessage.innerHTML = "";
                alertMessage.style.display = "none";
            });
        }

    } else {
        var pMessage = document.createElement('P');
        var pMessageTextnode = document.createTextNode("It is not possible to attach more than 5 speakers to the event.");
        pMessage.appendChild(pMessageTextnode);
        var alertMessage = document.getElementById('modalDescriptionEventDangerAlert');
        alertMessage.innerHTML = "";
        alertMessage.appendChild(pMessage);
        alertMessage.style.display = "";
        sleep(3000).then(() => {
            alertMessage.innerHTML = "";
            alertMessage.style.display = "none";
        });
    }

}



function updateSpeakerBTN(_this) {
    console.log("updateSpeakerBTN");
    var errorMessage;
    var speakerJson = {
        "action": "updateSpeaker",
        "imageSpeaker": document.getElementById('imageSpeaker').src,
        "nombreSpeaker": document.getElementById('nombreSpeaker').value,
        "posicionSpeaker": document.getElementById('posicionSpeaker').value,
        "companySpeaker": document.getElementById('companySpeaker').value,
        "descriptionSpeaker": document.getElementById('descriptionSpeaker').value,
        "idOfSpeaker": document.getElementById('idOfSpeaker').innerHTML
    };

    if (validInputForm(speakerJson)) {
        _this.innerHTML = "";
        _this.setAttribute('disabled', true);
        var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Updating...';
        _this.insertAdjacentHTML('beforeend', htmlSpinner);
        var data = new FormData();
        data.append("jsonObject", JSON.stringify(speakerJson));
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = false;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                console.log(this.responseText);
                try {
                    var jsonObjectRequest = JSON.parse(this.responseText);
                    if (jsonObjectRequest.status === "ok") {
                        _this.innerHTML = "";
                        _this.innerHTML = "Update";
                        _this.disabled = false;


                        var speakersSelect = document.getElementsByClassName('speakers-select')[0];
                        for (var i = 0; i < speakersSelect.children.length; i++) {
                            console.log(speakersSelect.children[i]);
                            var classSpealerOption = speakersSelect.children[i].classList[0];
                            if (classSpealerOption === speakerJson.idOfSpeaker) {
                                speakersSelect.children[i].innerHTML = "";
                                speakersSelect.children[i].innerHTML = speakerJson.nombreSpeaker;
                            }
                        }

                        var exampleFormControlSelect2 = document.getElementById('exampleFormControlSelect2');
                        for (var k = 0; k < exampleFormControlSelect2.children.length; k++) {
                            console.log(exampleFormControlSelect2.children[k].id);
                            var idSpeakersInSelect = exampleFormControlSelect2.children[k].id;
                            if (idSpeakersInSelect === speakerJson.idOfSpeaker) {
                                exampleFormControlSelect2.children[k].innerHTML = "";
                                exampleFormControlSelect2.children[k].innerHTML = speakerJson.nombreSpeaker;
                            }
                        }

                        var pMessage = document.createElement('P');
                        var pMessageTextnode = document.createTextNode(jsonObjectRequest.message);
                        pMessage.appendChild(pMessageTextnode);
                        var alertMessage = document.getElementById('modalDescriptionEventsSuccessAlert');
                        alertMessage.innerHTML = "";
                        alertMessage.appendChild(pMessage);
                        alertMessage.style.display = "";
                        sleep(3000).then(() => {
                            alertMessage.innerHTML = "";
                            alertMessage.style.display = "none";
                        });


                    } else {
                        console.log(jsonObjectRequest.message);
                        var pMessage = document.createElement('P');
                        var pMessageTextnode = document.createTextNode(jsonObjectRequest.message);
                        pMessage.appendChild(pMessageTextnode);
                        var alertMessage = document.getElementById('modalDescriptionEventDangerAlert');
                        alertMessage.innerHTML = "";
                        alertMessage.appendChild(pMessage);
                        alertMessage.style.display = "";
                        sleep(3000).then(() => {
                            alertMessage.innerHTML = "";
                            alertMessage.style.display = "none";
                        });
                    }
                } catch (err) {
                    console.log(err.toString());
                }
            }
        });
        xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
        xhr.send(data);


    } else {
        var pMessage = document.createElement('P');
        var pMessageTextnode = document.createTextNode(errorMessage);
        pMessage.appendChild(pMessageTextnode);
        var alertMessage = document.getElementById('modalDescriptionEventDangerAlert');
        alertMessage.innerHTML = "";
        alertMessage.appendChild(pMessage);
        alertMessage.style.display = "";
        sleep(3000).then(() => {
            alertMessage.innerHTML = "";
            alertMessage.style.display = "none";
        });
    }


    function validInputForm(speakerJson) {
        if (speakerJson.imageSpeaker === "https://via.placeholder.com/150") {
            errorMessage = "Please insert an image of the speaker.";
            return false;
        }
        if (speakerJson.nombreSpeaker.length === 0) {
            errorMessage = "Please provide the name of the speaker";
            return false;
        }
        if (speakerJson.posicionSpeaker.length === 0) {
            errorMessage = "Please provide the position of the speaker";
            return false;
        }
        if (speakerJson.companySpeaker.length === 0) {
            errorMessage = "Please provide the company of the speaker";
            return false;
        }
        if (speakerJson.descriptionSpeaker.length === 0) {
            errorMessage = "Please provide a description of the speaker";
            return false;
        }
        return true;
    }
}

function optionSpeakerEventClick(idSpeaker) {
    console.log("optionSpeakerEventClick");
    var speakerDisplaySection = document.getElementById('speakerDisplaySection');
    speakerDisplaySection.innerHTML = "";
    speakerDisplaySection.setAttribute('disabled', true);
    var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Obtaining information from the speaker...';
    speakerDisplaySection.insertAdjacentHTML('beforeend', htmlSpinner);

    var jsonObject = {
        "action": "obtenerSpeakerPorIdSpeaker",
        "idSpeaker": idSpeaker
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            //console.log(this.responseText);
            try {
                var jsonObject = JSON.parse(this.responseText);
                console.log(jsonObject);
                if (jsonObject.status === "ok") {
                    speakerDisplaySection.innerHTML = "";
                    var htmlToAppend = '<div class="row">' +
                            '       <div class="col-sm-4" style="text-align: center;" id="divImage">' +
                            '                                       <img src="' + jsonObject.Speaker.foto + '" id="imageSpeaker" alt="Image Speaker" style="max-width: 100%; cursor: pointer;" title="Press to add a new photo to ' + jsonObject.Speaker.nombrespeaker + '" onclick="uploadImageBTN()">' +
                            '                                      <br> ' +
                            '                                     <br> ' +
                            '                                    <br> ' +
                            '                               <div class="btn-group-sm" role="group" aria-label="Basic example">' +
                            '                                   <button type="button" class="btn btn-danger" title="Press to remove ' + jsonObject.Speaker.nombrespeaker + ' from the event" onclick="removeSpeakerFromEventBTN(this)">Remove</button>' +
                            '                                   <button type="button" class="btn btn-warning" title="Press to update info of ' + jsonObject.Speaker.nombrespeaker + '" onclick="updateSpeakerBTN(this)">Update</button>' +
                            '                              </div>' +
                            '                              </div>' +
                            '                             <div class="col-sm-8">' +
                            '                                <div class="form-group">' +
                            '                                   <small class="form-text text-muted">Speaker Name</small>' +
                            '                                  <input type="text" class="form-control form-control-sm" id="nombreSpeaker"  placeholder="Speaker Name" value="' + jsonObject.Speaker.nombrespeaker + '">' +
                            '                             </div>' +
                            '                            <div class="form-group">' +
                            '                               <small class="form-text text-muted">Speaker Position</small>' +
                            '                              <input type="text" class="form-control form-control-sm" id="posicionSpeaker"  placeholder="Speaker Position" value="' + jsonObject.Speaker.titulo + '">' +
                            '                         </div>' +
                            '                        <div class="form-group">' +
                            '                           <small class="form-text text-muted">Speaker Company</small>' +
                            '                          <input type="text" class="form-control form-control-sm" id="companySpeaker"  placeholder="Speaker Company" value="' + jsonObject.Speaker.empresa + '">' +
                            '                     </div>' +
                            '                    <div>' +
                            '                       <small class="form-text text-muted">Speaker Description</small>' +
                            '                      <textarea class="form-control" id="descriptionSpeaker" rows="3" id="descriptionSpeaker" >' + jsonObject.Speaker.descripcion + '</textarea>' +
                            '                   <p style="display: none;" id="idOfSpeaker" >' + jsonObject.Speaker.idspeaker + '</p>' +
                            '                 </div>' +
                            '            </div>' +
                            '       </div>';
                    speakerDisplaySection.insertAdjacentHTML('beforeend', htmlToAppend);


                } else {
                    console.log(jsonObject.message);
                    var pMessage = document.createElement('P');
                    var pMessageTextnode = document.createTextNode(jsonObject.message);
                    pMessage.appendChild(pMessageTextnode);
                    var alertMessage = document.getElementById('modalDescriptionEventDangerAlert');
                    alertMessage.innerHTML = "";
                    alertMessage.appendChild(pMessage);
                    alertMessage.style.display = "";
                    sleep(3000).then(() => {
                        alertMessage.innerHTML = "";
                        alertMessage.style.display = "none";
                    });
                }
            } catch (err) {
                console.log(err.toString());
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);

}

function removeSpeakerFromEventBTN(_this) {
    _this.innerHTML = "";
    _this.setAttribute('disabled', true);
    var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Deleting...';
    _this.insertAdjacentHTML('beforeend', htmlSpinner);
    var exampleFormControlSelect2 = document.getElementById('exampleFormControlSelect2');
    var idOfSpeaker = document.getElementById('idOfSpeaker').innerHTML;
    var successfullRemove = false;
    for (var i = 0; i < exampleFormControlSelect2.children.length; i++) {
        var idSpeakersInSelect = exampleFormControlSelect2.children[i].id;
        if (idSpeakersInSelect === idOfSpeaker) {

            exampleFormControlSelect2.children[i].remove();
            successfullRemove = true;
        }
    }
    updateSpeakersRest(document.getElementById('idEventForRegisterPage').innerHTML);
    if (successfullRemove === true) {
        //_this.remove();
        //<button type="button" class="btn btn-success" title="Press to add ' + jsonObject.Speaker.nombrespeaker + ' to the event" onclick="addToEventBTN(this)">Add</button>' +
        _this.classList.remove('btn-danger');
        _this.classList.add('btn-success');
        _this.setAttribute('title', 'Press to add speaker to the event');
        _this.setAttribute('onclick', 'addToEventBTN(this)');
        _this.disabled = false;
        _this.innerHTML = "";
        _this.innerHTML = "Add";
        var pMessage = document.createElement('P');
        var pMessageTextnode = document.createTextNode("Speaker Removed Successfully.");
        pMessage.appendChild(pMessageTextnode);
        var alertMessage = document.getElementById('modalDescriptionEventsSuccessAlert');
        alertMessage.innerHTML = "";
        alertMessage.appendChild(pMessage);
        alertMessage.style.display = "";
        sleep(3000).then(() => {
            alertMessage.innerHTML = "";
            alertMessage.style.display = "none";
        });
    }

}

function saveChangesRegisterPageInfo(_this) {
    _this.innerHTML = "";
    _this.setAttribute('disabled', true);
    var htmlSpinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Saving changes...';
    _this.insertAdjacentHTML('beforeend', htmlSpinner);

    updateTitlesRest(document.getElementById('idEventForRegisterPage').innerHTML);

    _this.disabled = false;
    _this.innerHTML = "";
    _this.innerHTML = "Save changes";

}

function updateTitlesRest(idEvent) {
    var jsonObject = {
        "action": "updateTitles",
        "inputSubTitles": [],
        "textAreaEvent": document.getElementById('textAreaEvent').value,
        "idEvent": idEvent
    };
    var inputSubTitles = document.getElementById('inputSubTitles');
    for (var i = 0; i < inputSubTitles.children.length; i++) {
        if (inputSubTitles.children[i].tagName === "INPUT") {
            if (inputSubTitles.children[i].value.length !== 0) {
                jsonObject.inputSubTitles.push(inputSubTitles.children[i].value);
            }
        }
    }

    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);
}


function updateSpeakersRest(idEvent) {
    var jsonObject = {
        "action": "agregarSpeakersAlEvento",
        "idEvento": idEvent,
        "idSpeakersArray": []
    };
    var exampleFormControlSelect2 = document.getElementById('exampleFormControlSelect2');
    for (var i = 0; i < exampleFormControlSelect2.children.length; i++) {
        var idSpeakersInSelect = exampleFormControlSelect2.children[i].id;
        jsonObject.idSpeakersArray.push(idSpeakersInSelect);
    }
    console.log(jsonObject);
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log("updateSpeakersRest response");
            console.log(this.responseText);
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);
}