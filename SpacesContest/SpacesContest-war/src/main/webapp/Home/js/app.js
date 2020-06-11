
let jsonRequest = document.getElementById('jsonRequest').innerHTML;
let enroledPeoplePage;

const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds));
};
let quizMenu = {
    "action": "publicQuestionQuiz",
    "number": "",
    "opnATxt": "",
    "opnARes": false,
    "opnBTxt": "",
    "opnBRes": false,
    "opnCTxt": "",
    "opnCRes": false,
    "opnDTxt": "",
    "opnDRes": false,
    "txtQuiz": "",
    "txtTime": "",
    "txtPts": "",
    "txtImg": "",
    "txtStus": "",
    "idpregunta": "",
    "idconcurso": "",
    "idevento": "",
    "estatus": "",
    "llave": ""
};

$(document).ready(function () {
    initPage();

    var estatusQuiz = document.getElementById('estatusQuiz').innerHTML;
    if (estatusQuiz === "TERMINATED") {
        document.getElementById('closeBtn').style.width = "105px";
        document.getElementById('closeBtn').innerHTML = "";
        document.getElementById('closeBtn').innerHTML = "Open Podium";
    }
    selectFirstActiveQuiz();
});


document.getElementById('llaveDelConcurso').addEventListener('click', function (e) {
    e.preventDefault();
    console.log("Print to Clip Board");
    var el = document.createElement('textarea');
    el.value = document.getElementById('llaveDelConcurso').innerHTML.toString();
    document.getElementById('divLlaveDelConcurso').appendChild(el);
    el.select();
    document.execCommand('copy');
    document.getElementById('divLlaveDelConcurso').removeChild(el);
});

function selectFirstActiveQuiz() {
    var columnQuizesSideBar = document.getElementById('columnQuizesSideBar');
    for (var i = 0; i < columnQuizesSideBar.children.length; i++) {
        if (columnQuizesSideBar.children[i].classList.contains("ACTIVO")) {
            selectedQuz(columnQuizesSideBar.children[i]);
            break;
        }
    }
}

function initPage() {
    try {
        let jsonRequestObject = JSON.parse(jsonRequest);
        console.log(jsonRequestObject);
        if (jsonRequestObject.preguntas.length !== 0) {
            let columnQuizesSideBar = document.getElementById('columnQuizesSideBar');
            for (var i = 0; i < jsonRequestObject.preguntas.length; i++) {
                if (jsonRequestObject.preguntas[i].estatus !== "INACTIVO") {
                    var estatusIconActive = '<i class="fa fa-check-circle" style="color: green;"></i><span class="statusTextPregunta">READY</span>';
                    if (jsonRequestObject.preguntas[i].estatus === "ENCURSO") {
                        //<i class="fas fa-toggle-on"></i>
                        estatusIconActive = '<i class="fas fa-toggle-on" style="color: blue;"></i><span class="statusTextPregunta">IN PROGRESS</span>';
                    }
                    if (jsonRequestObject.preguntas[i].estatus === "TERMINATED") {
                        //<i class="fas fa-times"></i>
                        estatusIconActive = '<i class="fas fa-times" style="color: red;"></i><span class="statusTextPregunta">TERMINATED</span>';
                    }

                    var columnQuizesSideBarHTML = '<div class="border rounded-0 border-dark shadow-sm quizesMenu ' + jsonRequestObject.preguntas[i].estatus + '" onclick="selectedQuz(this)">' +
                            '                       <div class="row">' +
                            '                          <div class="col d-lg-flex justify-content-lg-start align-items-lg-start" style="height: 27px;">' +
                            '                              <h6 class="textoNumeroPregunta" style="font-size: 1.5vw;font-weight: bold;">' + (i + 1) + '</h6>' +
                            '                              <p class="idPregunta" style="display:none;">' + jsonRequestObject.preguntas[i].idpregunta + '</p>' +
                            '                          </div>' +
                            '                          <div class="col d-lg-flex justify-content-lg-end align-items-lg-start" style="height: 27px;">' +
                            '                              <h6 class="estatusPregunta" style="font-size: 1vw;">' + estatusIconActive + '</h6>' +
                            '                          </div>' +
                            '                      </div>' +
                            '                      <div class="row">' +
                            '                          <div class="col">' +
                            '                              <h4 class="text-center textoPregunta" style="background-color: #e4e2e2;color: black;font-size: 1vw;">' + jsonRequestObject.preguntas[i].pregunta + '</h4>' +
                            '                          </div>' +
                            '                      </div>' +
                            '                      <div class="row">' +
                            '                          <div class="col d-lg-flex justify-content-lg-center align-items-lg-center"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center timeTexto" style="height: 50px;width: 50px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 1.3vw;background-color: blue;color: white;">' + jsonRequestObject.preguntas[i].timepregunta + '</span></div>' +
                            '                          <div class="col d-lg-flex justify-content-lg-center align-items-lg-center"><img class="img-fluid base64ImageTexto" src="' + jsonRequestObject.preguntas[i].imageurl + '" style="width: 1000;height: 667;"></div>' +
                            '                      <div class="col d-lg-flex justify-content-lg-center align-items-lg-center"><span class="d-lg-flex justify-content-lg-center align-items-lg-center puntosTexto" style="height: 50px;width: 50px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 1.3vw;background-color: green;color: white;">' + jsonRequestObject.preguntas[i].points + 'pt</span></div>' +
                            '                  </div>' +
                            '                  <div class="row responsesDisplay">' +
                            '                      <div class="col d-lg-flex justify-content-lg-center">' +
                            '                          <div class="row">' +
                            '                              <div class="col d-lg-flex align-items-lg-center" style="font-size: 1vw;"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 25px;width: 25px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 1vw;background-color: green;color: white;">A</span>' +
                            '                                  <span class="opcionAText" style="display: none;">' + jsonRequestObject.preguntas[i].opciona + '</span><span class="OpcionAResponse" style="display: none;">' + jsonRequestObject.preguntas[i].respuestauno + '</span></div>' +
                            '                          </div>' +
                            '                      </div>' +
                            '                      <div class="col d-lg-flex justify-content-lg-center">' +
                            '                          <div class="row">' +
                            '                              <div class="col d-lg-flex align-items-lg-center"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 25px;width: 25px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 1vw;background-color: red;color: white;">B</span>' +
                            '                                  <span class="opcionBText" style="display: none;">' + jsonRequestObject.preguntas[i].opcionb + '</span><span class="OpcionBResponse" style="display: none;">' + jsonRequestObject.preguntas[i].respuestados + '</span></div>' +
                            '                          </div>' +
                            '                      </div>' +
                            '                  </div>' +
                            '                  <div class="row responsesDisplay">' +
                            '                      <div class="col d-lg-flex justify-content-lg-center">' +
                            '                          <div class="row">' +
                            '                              <div class="col d-lg-flex align-items-lg-center" style="font-size: 1vw;"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 25px;width: 25px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 1vw;background-color: red;color: white;">C</span>' +
                            '                                  <span class="opcionCText" style="display: none;">' + jsonRequestObject.preguntas[i].opcionc + '</span><span class="OpcionCResponse" style="display: none;">' + jsonRequestObject.preguntas[i].respuestatres + '</span></div>' +
                            '                          </div>' +
                            '                      </div>' +
                            '                      <div class="col d-lg-flex justify-content-lg-center">' +
                            '                          <div class="row">' +
                            '                              <div class="col d-lg-flex align-items-lg-center"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 25px;width: 25px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 1vw;background-color: red;color: white;">D</span>' +
                            '                                  <span class="opcionDText" style="display: none;">' + jsonRequestObject.preguntas[i].opciond + '</span><span class="OpcionDResponse" style="display: none;">' + jsonRequestObject.preguntas[i].respuestacuatro + '</span></div>' +
                            '                          </div>' +
                            '                      </div>' +
                            '                  </div>' +
                            '              </div>';
                    columnQuizesSideBar.insertAdjacentHTML('beforeend', columnQuizesSideBarHTML);
                    if (i === 0) {
                        selectedQuz(document.getElementById('columnQuizesSideBar').children[0]);
                        document.getElementById('llaveDelConcurso').innerHTML = jsonRequestObject.preguntas[i].llave;
                        quizMenu.llave = jsonRequestObject.preguntas[i].llave;
                        document.getElementById('idconcurso').innerHTML = jsonRequestObject.preguntas[i].idconcurso;
                        quizMenu.idconcurso = jsonRequestObject.preguntas[i].idconcurso;
                        enroledPeople();
                    }

                }//FIN DEL IF INACTIVO
            }//FIN DEL FOR PARA CREAR


        }
        document.getElementById('quizViewContainer').style.display = "block";

        var url_string = window.location.href;
        var url = new URL(url_string);
        var evento = url.searchParams.get("e");
        quizMenu.idevento = evento;
    } catch (err) {
        console.log(err);
    }
}

function selectedQuz(_this) {
    document.getElementById('imageViewPrincipal').style.display = "";
    document.getElementById("canvasRow").style.display = "none";
    quizMenu.number = _this.querySelectorAll(".textoNumeroPregunta")[0].innerHTML;
    quizMenu.opnATxt = _this.querySelectorAll(".opcionAText")[0].innerHTML;
    quizMenu.opnBTxt = _this.querySelectorAll(".opcionBText")[0].innerHTML;
    quizMenu.opnCTxt = _this.querySelectorAll(".opcionCText")[0].innerHTML;
    quizMenu.opnDTxt = _this.querySelectorAll(".opcionDText")[0].innerHTML;
    quizMenu.opnARes = _this.querySelectorAll(".OpcionAResponse")[0].innerHTML;
    quizMenu.opnBRes = _this.querySelectorAll(".OpcionBResponse")[0].innerHTML;
    quizMenu.opnCRes = _this.querySelectorAll(".OpcionCResponse")[0].innerHTML;
    quizMenu.opnDRes = _this.querySelectorAll(".OpcionDResponse")[0].innerHTML;
    quizMenu.txtQuiz = _this.querySelectorAll(".textoPregunta")[0].innerHTML;
    quizMenu.txtTime = _this.querySelectorAll(".timeTexto")[0].innerHTML;
    quizMenu.txtPts = _this.querySelectorAll(".puntosTexto")[0].innerHTML;
    quizMenu.txtImg = _this.querySelectorAll(".base64ImageTexto")[0].src;
    quizMenu.txtStus = _this.querySelectorAll(".statusTextPregunta")[0].innerHTML;
    quizMenu.idpregunta = _this.querySelectorAll(".idPregunta")[0].innerHTML;
    quizMenu.estatus = _this.querySelectorAll(".estatusPregunta")[0].innerHTML;

    document.getElementById('textQuizView').innerHTML = quizMenu.txtQuiz;
    document.getElementById('textTimeView').innerHTML = quizMenu.txtTime;
    document.getElementById('textImageView').src = quizMenu.txtImg;
    document.getElementById('textPointsView').innerHTML = quizMenu.txtPts;
    document.getElementById('textOptnAView').innerHTML = quizMenu.opnATxt;
    document.getElementById('textOptnBView').innerHTML = quizMenu.opnBTxt;
    document.getElementById('textOptnCView').innerHTML = quizMenu.opnCTxt;
    document.getElementById('textOptnDView').innerHTML = quizMenu.opnDTxt;
    document.getElementById('textNumberView').innerHTML = quizMenu.number;
    document.getElementById('idQuestion').innerHTML = quizMenu.idpregunta;
    var columnIconPuplicQuestion = document.getElementById('columnIconPuplicQuestion');
    columnIconPuplicQuestion.innerHTML = "";
    var columnIconPuplicQuestionHTML = "";
    console.log("quizMenu Estatus");
    console.log(quizMenu.estatus);
    var seeAnswers = document.getElementById('seeResonseIcon');
    if (quizMenu.estatus === '<i class="fa fa-check-circle" style="color: green;"></i><span class="statusTextPregunta">READY</span>') {
        //ESTATUS ACTIVO
        columnIconPuplicQuestionHTML = '<i class="fa fa-rocket fa-2x" style="color: white;cursor: pointer;" onclick="publicQuestionRest()"></i>';
        seeAnswers.style.display = "none";

    }
    if (quizMenu.estatus === '<i class="fas fa-times" style="color: red;"></i><span class="statusTextPregunta">TERMINATED</span>') {
        //ESTATUS TERMINATED
        columnIconPuplicQuestionHTML = '<i class="fas fa-clipboard-check fa-2x" style="color: white; cursor: pointer" onclick="getResponsesRequest()"></i>';
        seeAnswers.style.display = "";
    }
    if (quizMenu.estatus === '<i class="fas fa-toggle-on" style="color: blue;"></i><span class="statusTextPregunta">IN PROGRESS</span>') {
        //ESTATUS IN PROGRESS
        columnIconPuplicQuestionHTML = '<i class="fas fa-clipboard-check fa-2x" style="color: white;"></i>';
        seeAnswers.style.display = "none";
    }
    columnIconPuplicQuestion.insertAdjacentHTML('beforeend', columnIconPuplicQuestionHTML);
    if (seeAnswers.classList.contains('showAnsers')) {
        seeResponses(seeAnswers);
    }
}

function nextBtn() {
    var textNumberView = parseInt(document.getElementById('textNumberView').innerHTML);
    var mySidebar = document.getElementById('mySidebar').children[0].children[0].children;
    for (var indexNxt = 0; indexNxt < mySidebar.length; indexNxt++) {
        if (indexNxt === textNumberView) {
            selectedQuz(mySidebar[indexNxt]);
            break;
        }
    }

}

function prevBtn() {
    var textNumberView = parseInt(document.getElementById('textNumberView').innerHTML);
    textNumberView = textNumberView - 2;
    var mySidebar = document.getElementById('mySidebar').children[0].children[0].children;
    for (var index = 0; index < mySidebar.length; index++) {
        if (index === textNumberView) {
            selectedQuz(mySidebar[index]);
            break;
        }

    }

}





function startEnrollRequest() {
    let jsonRequestObject = JSON.parse(jsonRequest);
    if (jsonRequestObject.preguntas.length !== 0) {
        let startEnroll = {
            "action": "enroll",
            "time": "120",
            "llave": jsonRequestObject.preguntas[0].llave,
            "nombreConcurso": jsonRequestObject.preguntas[0].llave,
            "idConcurso": jsonRequestObject.preguntas[0].idconcurso,
            "idevento": jsonRequestObject.preguntas[0].idevento
        };
        var data = new FormData();
        data.append("jsonObject", JSON.stringify(startEnroll));
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = false;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                console.log(this.responseText);
                try {
                    var jsonObject = JSON.parse(this.responseText);
                    if (jsonObject.status === "ok") {
                        var time = 2;
                        var d = new Date,
                                dformat = [
                                    d.getFullYear(),
                                    d.getMonth() + 1,
                                    d.getDate()
                                ].join('-') + ' ' +
                                [d.getHours(),
                                    d.getMinutes() + time,
                                    d.getSeconds()].join(':');
                        // Set the date we're counting down to
                        var countDownDate = new Date(dformat).getTime();

                        // Update the count down every 1 second
                        var x = setInterval(function () {

                            // Get today's date and time
                            var now = new Date().getTime();

                            // Find the distance between now and the count down date
                            var distance = countDownDate - now;
                            // Time calculations for days, hours, minutes and seconds
                            var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                            var seconds = Math.floor((distance % (1000 * 60)) / 1000);
                            // Output the result in an element with id="demo"
//        document.getElementById("demo").innerHTML = days + "d " + hours + "h "
//                + minutes + "m " + seconds + "s ";
                            document.getElementById('clockDownEnroll').style.width = "100px";
                            document.getElementById('clockDownEnroll').style.fontWeight = "bold";
                            document.getElementById("clockDownEnroll").innerHTML = minutes.toString().trim() + "";
                            document.getElementById("clockDownEnroll").innerHTML = minutes.toString().trim() + "m : " + seconds.toString().trim() + "s ";

                            // If the count down is over, write some text 
                            if (distance <= 0) {
                                clearInterval(x);
                                enroledPeople();
                                document.getElementById('clockDownEnroll').style.width = "70px";
                                document.getElementById('clockDownEnroll').style.fontWeight = "bold";
                                document.getElementById("clockDownEnroll").innerHTML = "Enroll";
                            }
                        }, 1000);
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
    }
}


function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
}


function publicQuestion() {
    console.log("Public Question");
    var columnIconPuplicQuestion = document.getElementById('columnIconPuplicQuestion');
    columnIconPuplicQuestion.innerHTML = "";
    var columnIconPuplicQuestionHTML = '<i class="fas fa-clipboard-check fa-2x" style="color: white;"></i>';
    columnIconPuplicQuestion.insertAdjacentHTML('beforeend', columnIconPuplicQuestionHTML);
    var number = (parseInt(quizMenu.number, 10) - 1);
    var estatusEncurso = '<i class="fas fa-toggle-on" style="color: blue;"></i><span class="statusTextPregunta">IN PROGRESS</span>';
    document.getElementById('columnQuizesSideBar').children[number].querySelectorAll(".estatusPregunta")[0].innerHTML = estatusEncurso;
    let seconds = quizMenu.txtTime.replace("s", "");
    var x = setInterval(function () {

        document.getElementById("textTimeView").innerHTML = seconds + "s";
        seconds--;
        // If the count down is over, write some text 
        if (seconds <= 0) {
            clearInterval(x);
            document.getElementById("textTimeView").innerHTML = "0s";
            document.getElementById('textTimeView').style.display = quizMenu.txtTime;
            sendConfirmationOfResponseActualQuestion();
            getResponsesRequest();
            document.getElementById('seeResonseIcon').style.display = "";
            document.getElementById('columnQuizesSideBar').children[number].querySelectorAll(".estatusPregunta")[0].innerHTML = '<i class="fas fa-times" style="color: red;"></i><span class="statusTextPregunta">TERMINATED</span>';
        }
    }, 1000);

}

function publicQuestionRest() {
    var data = new FormData();
    console.log(quizMenu);
    data.append("jsonObject", JSON.stringify(quizMenu));
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            try {
                var jsonObjectResponse = JSON.parse(this.responseText);
                if (jsonObjectResponse.status === "ok") {
                    publicQuestion();
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



function getResponsesRequest() {
    var data = new FormData();
    quizMenu.action = "getCurrentResponses";
    data.append("jsonObject", JSON.stringify(quizMenu));
    quizMenu.action = "publicQuestionQuiz";
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            try {
                var jsonObject = JSON.parse(this.responseText);
                if (jsonObject.status === "ok") {
                    var dataPieChart = [];
                    var A = 0;
                    var B = 0;
                    var C = 0;
                    var D = 0;
                    var Z = 0;
                    var ABS = 0;
                    if (jsonObject.responses.quizes.length !== 0) {
                        for (var i = 0; i < jsonObject.responses.quizes.length; i++) {
                            var respuesta, replaceJumps, replaceSpaces;
                            replaceJumps = jsonObject.responses.quizes[i].bodytext.trim();
                            replaceJumps.replace("(\n|\r)", "");
                            replaceSpaces = replaceJumps.replace(" ", "").toUpperCase();
                            respuesta = replaceSpaces.substring(0, 1);
                            switch (respuesta) {
                                case "A":
                                    A++;
                                    break;
                                case "B":
                                    B++;
                                    break;
                                case "C":
                                    C++;
                                    break;
                                case "D":
                                    D++;
                                    break;
                                default:
                                    Z++;
                                    break;
                            }
                        }
                    }

                    var cantidadDeRespuestas = jsonObject.responses.quizes.length;
                    var cantidadDeEnrolados = jsonObject.responses.totalEnrolados;
                    ABS = cantidadDeEnrolados - cantidadDeRespuestas;

                    dataPieChart.push(A);
                    dataPieChart.push(B);
                    dataPieChart.push(C);
                    dataPieChart.push(D);
                    dataPieChart.push(Z);
                    dataPieChart.push(ABS);
                    generatePieChart(dataPieChart);

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
}

function generatePieChart(data) {
    document.getElementById('imageViewPrincipal').style.display = "none";
    document.getElementById("canvasRow").style.display = "block";
    var divCanvasPieChart = document.getElementById('canvasPieChart');
    divCanvasPieChart.innerHTML = "";
    var canvas = document.createElement('CANVAS');
    canvas.setAttribute('id', 'pieChartRespuestas');
    canvas.setAttribute('style', 'max-width: 38%');
    divCanvasPieChart.appendChild(canvas);
    var ctx = document.getElementById("pieChartRespuestas").getContext('2d');

    var myDoughnutChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ["A", "B", "C", "D", "Invalid", "Abstinences"],
            datasets: [{
                    data: data,
                    backgroundColor: ['#990000', '#b37700', '#003380', '#520066', '#000000', '#707070']
                }]
        },
        options: {
            legend: {
                display: false
            }
        }
    });

}

function sendConfirmationOfResponseActualQuestion() {
    var data = new FormData();
    quizMenu.action = "confirmationResponse";
    data.append("jsonObject", JSON.stringify(quizMenu));
    quizMenu.action = "publicQuestionQuiz";
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            try {
                var jsonObjectResponse = JSON.parse(this.responseText);
                if (jsonObjectResponse.status === "ok") {
                    console.log(this.responseText);
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

function closeQuiz() {
    //ACEPTO CERRAR EL QUIZ
    var data = new FormData();
    quizMenu.action = "closeQuiz";
    console.log(quizMenu);
    quizMenu.llave = document.getElementById('llaveDelConcurso').innerHTML;
    data.append("jsonObject", JSON.stringify(quizMenu));
    quizMenu.action = "publicQuestionQuiz";
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            try {
                closeEnroledPeople();
            } catch (err) {
                console.log("Cloasing page enroled people error:");
                console.log(err.toString());
            }


            try {
                var jsonObjectResponse = JSON.parse(this.responseText);
                if (jsonObjectResponse.status === "ok") {
                    var jsonRequestQuizes = jsonObjectResponse.responses.quizes;

                    document.getElementById('quizViewContainer').style.display = "none";
                    document.getElementById('closeModal').click();
                    document.getElementById('podiumContainer').style.display = "";
                    var ganadores = [];
                    for (var i = 0; i < jsonRequestQuizes.length; i++) {
                        var nombreActual = jsonRequestQuizes[i].nombreintegrante;
                        var aciertos = jsonRequestQuizes[i].acierto;
                        var idRespuesta = jsonRequestQuizes[i].id;
                        var image = jsonRequestQuizes[i].urlimage;
                        if (ganadores.length === 0) {
                            ganadores.push({
                                "nombre": nombreActual,
                                "putuacion": aciertos,
                                "urlimage": image,
                                "id": idRespuesta
                            });
                        } else {
                            for (var j = 0; j < ganadores.length; j++) {
                                if (ganadores[j].nombre === nombreActual) {
                                    //EXSTE DENTRO DEL ARREGLO
                                    if (aciertos !== 0) {
                                        if (ganadores[j].id !== idRespuesta) {
                                            ganadores[j].putuacion += aciertos;
                                        }

                                    }
                                    break;
                                }
                                if (j === ganadores.length - 1) {
                                    ganadores.push({
                                        "nombre": nombreActual,
                                        "putuacion": aciertos,
                                        "urlimage": image,
                                        "id": idRespuesta
                                    });
                                }
                            }
                        }

                    }
                    //PRIMER LUGAR
                    ganadores.sort(function (a, b) {
                        return parseFloat(b.putuacion) - parseFloat(a.putuacion);
                    });
                    console.log("GANADORES");
                    console.log(ganadores);
                    var puntosPrimerLugar, puntosSegundoLugar, puntosTercerLugar;
                    try {
                        document.getElementById('segundoLugarImage').src = "";
                        document.getElementById('segundoLugarImage').src = ganadores[1].urlimage;
                        document.getElementById('sedundoLugarNombre').innerHTML = "";
                        document.getElementById('sedundoLugarNombre').innerHTML = ganadores[1].nombre;
                        document.getElementById('segundoLugarPuntos').innerHTML = "";
                        document.getElementById('segundoLugarPuntos').innerHTML = ganadores[1].putuacion + "pts";
                        puntosSegundoLugar = parseInt(ganadores[1].putuacion, 10);
                    } catch (err) {
                        document.getElementById('segundoLugarImage').src = "";
                        document.getElementById('segundoLugarImage').src = "https://developers.google.com/web/images/contributors/no-photo.jpg";
                        document.getElementById('sedundoLugarNombre').innerHTML = "";
                        document.getElementById('sedundoLugarNombre').innerHTML = "Without participant";
                        document.getElementById('segundoLugarPuntos').innerHTML = "";
                        document.getElementById('segundoLugarPuntos').innerHTML = "0" + "pts";
                        puntosSegundoLugar = 0;
                    }

                    try {
                        document.getElementById('primerLugarImage').src = "";
                        document.getElementById('primerLugarImage').src = ganadores[0].urlimage;
                        document.getElementById('primerLugarNombre').innerHTML = "";
                        document.getElementById('primerLugarNombre').innerHTML = ganadores[0].nombre;
                        document.getElementById('primerLugarPuntos').innerHTML = "";
                        document.getElementById('primerLugarPuntos').innerHTML = ganadores[0].putuacion + "pts";
                        puntosPrimerLugar = parseInt(ganadores[0].putuacion, 10);
                    } catch (err) {
                        document.getElementById('primerLugarImage').src = "";
                        document.getElementById('primerLugarImage').src = "https://developers.google.com/web/images/contributors/no-photo.jpg";
                        document.getElementById('primerLugarNombre').innerHTML = "";
                        document.getElementById('primerLugarNombre').innerHTML = "Without participant";
                        document.getElementById('primerLugarPuntos').innerHTML = "";
                        document.getElementById('primerLugarPuntos').innerHTML = "0" + "pts";
                        puntosPrimerLugar = 0;
                    }
                    try {
                        document.getElementById('tercerLugarImage').src = "";
                        document.getElementById('tercerLugarImage').src = ganadores[2].urlimage;
                        document.getElementById('tercerLugarNombre').innerHTML = "";
                        document.getElementById('tercerLugarNombre').innerHTML = ganadores[2].nombre;
                        document.getElementById('tercerLugarPuntos').innerHTML = "";
                        document.getElementById('tercerLugarPuntos').innerHTML = ganadores[2].putuacion + "pts";
                        puntosTercerLugar = parseInt(ganadores[2].putuacion, 10);
                    } catch (err) {
                        document.getElementById('tercerLugarImage').src = "";
                        document.getElementById('tercerLugarImage').src = "https://developers.google.com/web/images/contributors/no-photo.jpg";
                        document.getElementById('tercerLugarNombre').innerHTML = "";
                        document.getElementById('tercerLugarNombre').innerHTML = "Without participant";
                        document.getElementById('tercerLugarPuntos').innerHTML = "";
                        document.getElementById('tercerLugarPuntos').innerHTML = "0" + "pts";
                        puntosTercerLugar = 0;
                    }
                    console.log("puntosPrimerLugar");
                    console.log(puntosPrimerLugar);
                    console.log("puntosSegundoLugar");
                    console.log(puntosSegundoLugar);
                    if (puntosPrimerLugar === puntosSegundoLugar) {
                        document.getElementById('primerLugarFastest').style.display = "";
                    }
                    console.log("puntosTercerLugar");
                    console.log(puntosTercerLugar);
                    console.log("puntosSegundoLugar");
                    console.log(puntosSegundoLugar);
                    if (puntosSegundoLugar === puntosTercerLugar) {
                        document.getElementById('segundoLugarFastest').style.display = "";
                    }



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

function cerrarPodium() {
    document.getElementById('quizViewContainer').style.display = "";
    document.getElementById('podiumContainer').style.display = "none";
}

function seeResponses(_this) {
    if (!_this.classList.contains('showAnsers')) {
        console.log("SIN CLASE");
        //var circleCorrectHTML = '<span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center correctCircleAnswers" style="height: 80px;width: 80px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: green;color: white;position: absolute;z-index: -1;">A</span>';
        var circleCorrectHTML = '<span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center correctCircleAnswers" style="height: 80px;width: 80px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: green;color: white;position: absolute;"><i class="fas fa-check-circle fa-1x" style="color:white; font-size: 45px;"></i></span>';
        if (quizMenu.opnARes === "true") {
            document.getElementById('optionABoxCircle').insertAdjacentHTML('beforeend', circleCorrectHTML);
        }
        if (quizMenu.opnBRes === "true") {
            document.getElementById('optionBBoxCircle').insertAdjacentHTML('beforeend', circleCorrectHTML);
        }
        if (quizMenu.opnCRes === "true") {
            document.getElementById('optionCBoxCircle').insertAdjacentHTML('beforeend', circleCorrectHTML);
        }
        if (quizMenu.opnDRes === "true") {
            document.getElementById('optionDBoxCircle').insertAdjacentHTML('beforeend', circleCorrectHTML);
        }
        _this.classList.add('showAnsers');
    } else {
        _this.classList.remove('showAnsers');
        var correctCircleAnswers = document.getElementsByClassName('correctCircleAnswers');
        var correctCircleAnswersLenght = correctCircleAnswers.length;
        for (var i = 0; i < correctCircleAnswersLenght; i++) {
            correctCircleAnswers[0].remove();
        }
    }
}


function enroledPeople() {
    console.log("Enrolled Poeple");
    var enorolledPeople = document.getElementById('enorolledPeople');
    enorolledPeople.innerHTML = "";

    var loaderMessage = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Loading...';
    enorolledPeople.insertAdjacentHTML('beforeend', loaderMessage);


    var jsonObject = {
        "action": "enrolledPeople",
        "llave": quizMenu.llave
    };
    var data = new FormData();
    data.append("jsonObject", JSON.stringify(jsonObject));

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            try {
                var jsonObject = JSON.parse(this.responseText);
                if (jsonObject.status === "ok") {
                    enorolledPeople.innerHTML = "";
                    enorolledPeople.innerHTML = "Enrolled People: " + jsonObject.players.count;
                } else {
                    alert(jsonObject.message);
                    console.log(jsonObject.message);
                }
            } catch (err) {
                console.log(err.toString());
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");

    xhr.send(data);


}

function sendDirectMessageConfirmation() {
    console.log("sendDirectMessageConfirmation");
    var sendMessageConfirmation = document.getElementById('sendMessageConfirmation');

    sendMessageConfirmation.innerHTML = "";

    var loaderHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Sending...';
    sendMessageConfirmation.insertAdjacentHTML('beforeend', loaderHTML);

    var jsonObject = {
        "action": "sendEnrollConfirmation",
        "llave": quizMenu.llave
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
                    sendMessageConfirmation.innerHTML = "";
                    sendMessageConfirmation.innerHTML = "Confirmation messages sent";
                    sleep(5000).then(() => {
                        sendMessageConfirmation.innerHTML = "";
                        sendMessageConfirmation.innerHTML = "Send direct message confirmation";

                    });
                } else {
                    sendMessageConfirmation.innerHTML = "";
                    sendMessageConfirmation.innerHTML = jsonObjectResponse.message;
                    console.log(jsonObjectResponse.message);
                }
            } catch (err) {
                console.log(err.toLocaleString());
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");

    xhr.send(data);


}

function editQuestion() {
    console.log("Edit question");
    console.log(quizMenu);
    /*
     * 
     * Seccion para editar la pregunta
     */
    var textQuestion = document.getElementsByClassName('textQuestion')[0];
    var heightTextQuestion = textQuestion.style.height;
    var fontSize = textQuestion.children[0].style.fontSize;
    var textAreaTextQuestion = document.createElement('TEXTAREA');
    textAreaTextQuestion.setAttribute('rows', '1');
    textAreaTextQuestion.setAttribute('maxlength', '120');
    textAreaTextQuestion.setAttribute('minlength', '1');
    textAreaTextQuestion.setAttribute('type', 'text');
    textAreaTextQuestion.setAttribute('required', 'true');
    textAreaTextQuestion.setAttribute('class', 'textAreaAutoRezise form-control-lg form-control');
    textAreaTextQuestion.setAttribute('style', 'display: inline-block;width: 100%;height: ' + heightTextQuestion + ';border: none;background-color: #e4e2e2;font-size: ' + fontSize + ';overflow: hidden;text-align: center;font-weight: bold; color: black; border: solid;border-width: 1px;');
    textAreaTextQuestion.innerHTML = quizMenu.txtQuiz;
    textQuestion.innerHTML = "";
    textQuestion.appendChild(textAreaTextQuestion);

    /*
     * 
     * Seccion para editar la Opcion A
     */
    var textOptnAView = document.getElementById('textOptnAView');
    var textOptnAViewDiv = document.getElementById('textOptnAViewDiv');
    var textAreaTextOptnAView = document.createElement('TEXTAREA');
    textAreaTextOptnAView.setAttribute('class', 'form-control-lg form-control textAreaAutoRezise');
    textAreaTextOptnAView.setAttribute('rows', '1');
    textAreaTextOptnAView.setAttribute('type', 'text');
    textAreaTextOptnAView.setAttribute('required', 'true');
    textAreaTextOptnAView.setAttribute('style', 'display: inline-block;width: 100%;height: 92px;border: none;background-color: #990000;font-size: ' + textOptnAView.style.fontSize + ';overflow: hidden;color: white; font-weight: bold;');
    textAreaTextOptnAView.setAttribute('maxlength', '75');
    textAreaTextOptnAView.setAttribute('minlength', '1');
    textAreaTextOptnAView.innerHTML = quizMenu.opnATxt;
    textOptnAViewDiv.innerHTML = "";
    textOptnAViewDiv.appendChild(textAreaTextOptnAView);

    /*
     * 
     * Seccion para editar la Opcion B
     */
    var textOptnBView = document.getElementById('textOptnBView');
    var textOptnBViewDiv = document.getElementById('textOptnBViewDiv');
    var textAreaTextOptnBView = document.createElement('TEXTAREA');
    textAreaTextOptnBView.setAttribute('class', 'form-control-lg form-control textAreaAutoRezise');
    textAreaTextOptnBView.setAttribute('rows', '1');
    textAreaTextOptnBView.setAttribute('type', 'text');
    textAreaTextOptnBView.setAttribute('required', 'true');
    textAreaTextOptnBView.setAttribute('style', 'display: inline-block;width: 100%;height: 92px;border: none;background-color: #b37700;font-size: ' + textOptnBView.style.fontSize + ';overflow: hidden;color: white; font-weight: bold;');
    textAreaTextOptnBView.setAttribute('maxlength', '75');
    textAreaTextOptnBView.setAttribute('minlength', '1');
    textAreaTextOptnBView.innerHTML = quizMenu.opnBTxt;
    textOptnBViewDiv.innerHTML = "";
    textOptnBViewDiv.appendChild(textAreaTextOptnBView);

    /*
     * 
     * Seccion para editar la Opcion C
     */
    var textOptnCView = document.getElementById('textOptnCView');
    var textOptnCViewDiv = document.getElementById('textOptnCViewDiv');
    var textAreaTextOptnCView = document.createElement('TEXTAREA');
    textAreaTextOptnCView.setAttribute('class', 'form-control-lg form-control textAreaAutoRezise');
    textAreaTextOptnCView.setAttribute('rows', '1');
    textAreaTextOptnCView.setAttribute('type', 'text');
    textAreaTextOptnCView.setAttribute('required', 'true');
    textAreaTextOptnCView.setAttribute('style', 'display: inline-block;width: 100%;height: 85px;border: none;background-color: #003380;font-size: ' + textOptnCView.style.fontSize + ';overflow: hidden;color: white; font-weight: bold;');
    textAreaTextOptnCView.setAttribute('maxlength', '75');
    textAreaTextOptnCView.setAttribute('minlength', '1');
    textAreaTextOptnCView.innerHTML = quizMenu.opnCTxt;
    textOptnCViewDiv.innerHTML = "";
    textOptnCViewDiv.appendChild(textAreaTextOptnCView);

    /*
     * 
     * Seccion para editar la Opcion C
     */
    var textOptndView = document.getElementById('textOptnDView');
    var textOptnDViewDiv = document.getElementById('textOptnDViewDiv');
    var textAreaTextOptnDView = document.createElement('TEXTAREA');
    textAreaTextOptnDView.setAttribute('class', 'form-control-lg form-control textAreaAutoRezise');
    textAreaTextOptnDView.setAttribute('rows', '1');
    textAreaTextOptnDView.setAttribute('type', 'text');
    textAreaTextOptnDView.setAttribute('required', 'true');
    textAreaTextOptnDView.setAttribute('style', 'display: inline-block;width: 100%;height: 85px;border: none;background-color: #520066;font-size: ' + textOptnDView.style.fontSize + ';overflow: hidden;color: white; font-weight: bold;');
    textAreaTextOptnDView.setAttribute('maxlength', '75');
    textAreaTextOptnDView.setAttribute('minlength', '1');
    textAreaTextOptnDView.innerHTML = quizMenu.opnDTxt;
    textOptnDViewDiv.innerHTML = "";
    textOptnDViewDiv.appendChild(textAreaTextOptnDView);


    var columnIconPuplicQuestion = document.getElementById('columnIconPuplicQuestion');
    columnIconPuplicQuestion.innerHTML = "";
    var columnIconPuplicQuestionIcon = document.createElement('I');
    columnIconPuplicQuestionIcon.setAttribute('class', 'fa fa-ban fa-2x');
    columnIconPuplicQuestionIcon.setAttribute('style', 'color: white;cursor: pointer;');
    columnIconPuplicQuestion.appendChild(columnIconPuplicQuestionIcon);

    var editQuuestion = document.getElementById('editQuuestion');
    editQuuestion.innerHTML = "";
    editQuuestion.setAttribute('onclick', 'closeEditMode()');
    editQuuestion.setAttribute('style', 'width: 32%; height: 27px; font-weight: bold; font-size: 15px;');
    editQuuestion.innerHTML = "Close Edit Mode";

    initTextAreaRezise();

    var textTimeViewDiv = document.getElementById('textTimeViewDiv');

    var divSliderTime = document.createElement('DIV');
    divSliderTime.setAttribute('id', 'timeEditSlider');
    divSliderTime.setAttribute('style', 'width: 80%; position: absolute; top : 85%;');

    var divcustomHandleTime = document.createElement('DIV');
    divcustomHandleTime.setAttribute('id', 'custom-handle-time');
    divcustomHandleTime.setAttribute('class', 'ui-slider-handle');
    divcustomHandleTime.setAttribute('style', 'width: 3em; height: 1.6em; top: 50%; margin-top: -.8em; text-align: center;line-height: 1.6em;');
    divSliderTime.appendChild(divcustomHandleTime);

    textTimeViewDiv.appendChild(divSliderTime);



    var handle = $("#custom-handle-time");
    $("#timeEditSlider").slider({
        range: "min",
        min: 20,
        max: 300,
        step: 20,
        value: quizMenu.txtTime.replace("s", ""),
        slide: function (event, ui) {
            $("#textTimeView").html(ui.value + "s");

        }
    });


    document.getElementById('timeEditSlider').children[1].style.backgroundColor = "blue";


    var textPointsViewDiv = document.getElementById('textPointsViewDiv');
    var divSlidePoints = document.createElement('DIV');
    divSlidePoints.setAttribute('id', 'pointsEditSlider');
    divSlidePoints.setAttribute('style', 'width: 80%; position: absolute; top : 85%;');
    var divcustomHandlePoints = document.createElement('DIV');
    divcustomHandlePoints.setAttribute('id', 'custom-handle-points');
    divcustomHandlePoints.setAttribute('class', 'ui-slider-handle');
    divcustomHandlePoints.setAttribute('style', 'width: 3em; height: 1.6em; top: 50%; margin-top: -.8em; text-align: center;line-height: 1.6em;');
    divSlidePoints.appendChild(divcustomHandlePoints);
    textPointsViewDiv.appendChild(divSlidePoints);

    var handlePoints = $("#custom-handle-points");
    $("#pointsEditSlider").slider({
        range: "min",
        min: 10,
        max: 100,
        step: 10,
        value: quizMenu.txtPts.replace("pt", ""),
        slide: function (event, ui) {
            $("#textPointsView").html(ui.value + "pt");
        }
    });
    document.getElementById('pointsEditSlider').children[1].style.backgroundColor = "green";

}


function initTextAreaRezise() {
    var observe;
    if (window.attachEvent) {
        observe = function (element, event, handler) {
            element.attachEvent('on' + event, handler);
        };
    } else {
        observe = function (element, event, handler) {
            element.addEventListener(event, handler, false);
        };
    }
    var text = document.getElementsByClassName('textAreaAutoRezise');
    function resize() {

        for (var j = 0; j < text.length; j++) {
            text[j].style.height = 'auto';
            text[j].style.height = text[j].scrollHeight + 'px';
        }


    }
    /* 0-timeout to get the already changed text */
    function delayedResize() {
        window.setTimeout(resize, 0);
    }


    for (var i = 0; i < text.length; i++) {
        observe(text[i], 'change', resize);
        observe(text[i], 'cut', delayedResize);
        observe(text[i], 'paste', delayedResize);
        observe(text[i], 'drop', delayedResize);
        observe(text[i], 'keydown', delayedResize);
        text[i].focus();
        text[i].select();
    }


}

function inputQuestions(_this) {
    let elementInput = _this;
    if (elementInput.value.length !== 0) {
        if (elementInput.classList.contains("is-invalid")) {
            elementInput.classList.remove("is-invalid");
        }
        elementInput.classList.add("is-valid");
    } else {
        if (elementInput.classList.contains("is-valid")) {
            elementInput.classList.remove("is-valid");
        }
        elementInput.classList.add("is-invalid");
    }

}

function closeEditMode() {
    document.getElementById('closeEditModeBtn').click();
}

function openEnroledPeople() {
    console.log("openEnroledPeople()");
    enroledPeoplePage = window.open("?p=enroledPeople&q=" + quizMenu.idconcurso + "&e=" + quizMenu.idevento, "_blank");
}



window.onbeforeunload = function () {
    closeEnroledPeople();
};

function closeEnroledPeople() {
    enroledPeoplePage.close();
}