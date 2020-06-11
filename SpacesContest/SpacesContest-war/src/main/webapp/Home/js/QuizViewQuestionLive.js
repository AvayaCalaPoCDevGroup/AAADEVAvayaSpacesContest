/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log("QuizViewQuestionLive");
let enroladosObjGlobal = JSON.parse(document.getElementById('enrolados').innerHTML);
let answersGlobal = JSON.parse(document.getElementById('answers').innerHTML);
let llave = document.getElementById('llave').innerHTML;
let pregunta = document.getElementById('pregunta').innerHTML;
let intervalEnrolados;
let intervalRespuestas;
$(document).ready(function () {
    initEnrolados();
    initResponsesByQuestion();
});
function initResponsesByQuestion() {
    console.log(answersGlobal);
    console.log(pregunta);
    document.getElementById('question').innerHTML = pregunta;
    var responsesByQuestionBody = document.getElementById('responsesByQuestionBody');
    responsesByQuestionBody.innerHTML = "";
    if (pregunta !== "There is no current question") {
        if (answersGlobal.answers.length !== 0) {
            for (var i = 0; i < enroladosObjGlobal.enrolados.length; i++) {
                var abstinence = true;
                for (var a = 0; a < answersGlobal.answers.length; a++) {
                    if (enroladosObjGlobal.enrolados[i].idintegrante === answersGlobal.answers[a].idintegrante) {
                        abstinence = false;
                        var notAbstinence = ' <tr>' +
                                '      <td>' + enroladosObjGlobal.enrolados[i].nombreintegrante + '</td>' +
                                '     <td>' + enroladosObjGlobal.enrolados[i].correo + '</td>' +
                                '    <td>' + answersGlobal.answers[a].bodytext + '</td>' +
                                '</tr>';
                        responsesByQuestionBody.insertAdjacentHTML('beforeend', notAbstinence);
                        continue;
                    }
                    if (a === answersGlobal.answers.length - 1) {
                        if (abstinence) {
                            var abstinences = ' <tr style="background-color: #707070;">' +
                                    '      <td>' + enroladosObjGlobal.enrolados[i].nombreintegrante + '</td>' +
                                    '     <td>' + enroladosObjGlobal.enrolados[i].correo + '</td>' +
                                    '    <td>Abstinence</td>' +
                                    '</tr>';
                            responsesByQuestionBody.insertAdjacentHTML('beforeend', abstinences);
                        }
                    }

                }
            }
        } else {
            for (var i = 0; i < enroladosObjGlobal.enrolados.length; i++) {
                var abstinences = ' <tr style="background-color: #707070;">' +
                        '      <td>' + enroladosObjGlobal.enrolados[i].nombreintegrante + '</td>' +
                        '     <td>' + enroladosObjGlobal.enrolados[i].correo + '</td>' +
                        '    <td>Abstinence</td>' +
                        '</tr>';
                responsesByQuestionBody.insertAdjacentHTML('beforeend', abstinences);
            }
        }
    }
    $('#responsesByQuestion').DataTable();
    intervalRespuestas = setInterval(intervalRespuestasFunction, 3000);
}

function initEnrolados() {
    var enroladosTableBody = document.getElementById('enroladosTableBody');
    enroladosTableBody.innerHTML = "";
    for (var i = 0; i < enroladosObjGlobal.enrolados.length; i++) {
        var htmlTR = ' <tr>' +
                '         <td>' + enroladosObjGlobal.enrolados[i].nombreintegrante + '</td>' +
                '         <td>' + enroladosObjGlobal.enrolados[i].correo + '</td>' +
                '        <td>' + enroladosObjGlobal.enrolados[i].fecharegistro + '</td>' +
                '        <td>' +
                '            <div class="row">' +
                '               <div class="col">' +
                '                  <div class="btn-group" role="group" aria-label="Basic example">' +
                '                     <button type="button" class="btn btn-danger" onclick=(unroll("' + enroladosObjGlobal.enrolados[i].idintegrante + '"))>Unrolling</button>' +
                '                </div>' +
                '            </div>' +
                '        </div>' +
                '     </td>' +
                '  </tr>';
        enroladosTableBody.insertAdjacentHTML('beforeend', htmlTR);
    }
    $('#enroladosTable').DataTable();
    intervalEnrolados = setInterval(intervalEnroladosFunction, 3000);
}

function intervalEnroladosFunction() {
    let jsonObject = {
        "action": "obtenerEnroledPeople",
        "llave": llave
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            try {
                var jsonObjectRest = JSON.parse(this.responseText);
                if (jsonObjectRest.status === "ok") {
//                    console.log("GLOBAL");
//                    console.log(JSON.stringify(enroladosObjGlobal.enrolados));
//                    console.log("RESPUESTA");
//                    console.log(JSON.stringify(jsonObjectRest.enrolados));
                    if (JSON.stringify(jsonObjectRest.enrolados) === JSON.stringify(enroladosObjGlobal.enrolados)) {
//                        console.log("IGUALES");
                    } else {
//                        console.log("NO IGUALES");
                        enroladosObjGlobal.enrolados = jsonObjectRest.enrolados;
                        initEnrolados();
                    }
                } else {
                    console.log(jsonObjectRest.message);
                }
            } catch (err) {
                console.log(err.toString());
            }
        }
    });
    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);
}


function intervalRespuestasFunction() {
    let jsonObject = {
        "action": "obtenerResponsesLive",
        "llave": llave
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {

            try {
                var jsonObjectRest = JSON.parse(this.responseText);
                if (jsonObjectRest.status === "ok") {
//                    console.log("GLOBAL");
//                    console.log(JSON.stringify(answersGlobal.answers));
//                    console.log("RESPUESTA");
//                    console.log(JSON.stringify(jsonObjectRest.answers));
                    if (JSON.stringify(jsonObjectRest.answers) === JSON.stringify(answersGlobal.answers)) {
//                        console.log("IGUALES");
                    } else {
//                        console.log("NO IGUALES");
//                        console.log(this.responseText);
//                        document.getElementById('question').innerHTML = "";
//                        document.getElementById('question').innerHTML = jsonObjectRest.message;
                        pregunta = jsonObjectRest.message;
                        answersGlobal.answers = jsonObjectRest.answers;
                        initResponsesByQuestion();
                    }
                } else {
                    console.log(jsonObjectRest.message);
                }
            } catch (err) {
                console.log(err.toString());
            }
        }
    });
    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);
}

function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
}


function unroll(idIntegrante) {
    console.log(idIntegrante);
}


function cleanResponses() {
    var jsonObject = {
        "action": "cleanRespuestaActual"
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            try {
                var jsonObjectRest = JSON.parse(this.responseText);
                if (jsonObjectRest.status === "ok") {
                    console.log(jsonObjectRest.message);
                    $('#responsesByQuestion').DataTable();
                } else {
                    console.log(jsonObjectRest.message);
                }
            } catch (err) {
                console.log(err.toString());
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");
    xhr.send(data);
}