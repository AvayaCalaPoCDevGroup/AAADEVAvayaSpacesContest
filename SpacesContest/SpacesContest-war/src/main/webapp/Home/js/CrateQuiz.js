/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log("Create Quiz V");
document.getElementsByClassName('btn-circle')[0].click();
/*
 * OBJETOS
 */
let quiz = {
    "action": "createQuiz",
    "nombre": document.getElementById('inputNameQuiz').value,
    "preguntas": [],
    "fechaApertura": "",
    "imageQuiz": "Transparent.png",
    "quizColor": "#708090",
    "isFavorite": false,
    "fontColor": "#FFFFFF",
    "isFake": false
};
/*
 * VARIABLEs
 */
const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
};
/*
 * EVENTOS
 */
window.onload = function () {
    var readCookie = read_cookie("questionsCookie");
    console.log(readCookie);
    if (readCookie !== null) {
        console.log("NOT NULL");
        var slideShowContainer = document.getElementById('slideShowContainer');
        slideShowContainer.innerHTML = "";
        let opcionA = makeid(5);
        let opcionB = makeid(5);
        let opcionC = makeid(5);
        let opcionD = makeid(5);
        let idNewQuestionID = makeid(5);
        let idtime;
        let amountTime;
        let idpoint;
        let amountPoint;
        var timeValues = [];
        var amountTimeValues = [];
        var pointValues = [];
        var amountpointsValues = [];
        var sliderTimeValue = [];
        var sliderPointsValue = [];
        for (var i = readCookie.length - 1; i >= 0; i--) {
            idNewQuestionID = makeid(5);
            opcionA = makeid(5);
            opcionB = makeid(5);
            opcionC = makeid(5);
            opcionD = makeid(5);
            idtime = makeid(5);
            timeValues.push(idtime);
            amountTime = makeid(5);
            amountTimeValues.push(amountTime);
            idpoint = makeid(5);
            pointValues.push(idpoint);
            amountPoint = makeid(5);
            amountpointsValues.push(amountPoint);
            sliderTimeValue.push(readCookie[i].sliderTimeHeight);
            sliderPointsValue.push(readCookie[i].sliderPointsHight);
            var imageHTML;
            if (readCookie[i].image === "EMPTY") {
                imageHTML = '                              <div class="drop-region" style="display: inline-block;border-style: dotted;width: 441px;" onclick="clickDropRegion(this)">' +
                        '                                 <div class="drop-message"><strong>Drag&nbsp;&amp;&nbsp;Drop&nbsp;image&nbsp;or&nbsp;click&nbsp;to&nbsp;upload<br></strong></div>' +
                        '                                <div class="image-preview-question"></div>';
            } else {
                imageHTML = '<div class="drop-region" style="display: inline-block; width: 441px;" onclick="clickDropRegion(this)">                                 <div class="drop-message"><strong>Drag&nbsp;&amp;&nbsp;Drop&nbsp;image&nbsp;or&nbsp;click&nbsp;to&nbsp;upload<br></strong></div>             <div class="image-preview-question"><div class="image-view"><img src="' + readCookie[i].image + '"><div></div></div></div></div>';
            }


            var str = '<div class="fadeImage mySlides" style="display: block;">' +
                    '                                    <div class="numbertext"><strong class="numberOfSlides" style="float: left;">1 / 1</strong></div>' +
                    '                                   <div class="form">' +
                    '                                      <div class="row">' +
                    '                                         <textarea class="form-control-lg form-control textAreaAutoReziseNuevo questionInput" id="' + idNewQuestionID + '" rows="1" maxlength="120" minlength="1" type="text" required="true" style="display: inline-block; width: 100%; height: 107px; background-color: rgb(228, 226, 226); font-size: 2vw; overflow: hidden; text-align: center; font-weight: bold; color: black; border: 1px solid;" onblur="inputQuestions(this)" autofocus="">' + readCookie[i].questionInput + '</textarea>' +
                    '                                    </div>' +
                    '                                   <div class="row" style="margin-top: 5px;">' +
                    '                                      <div class="text-left d-inline-block col-sm-3 timeToRespond">' +
                    '                                         <h6>Set&nbsp;the&nbsp;time&nbsp;to&nbsp;respond<br></h6>' +
                    '                                        <div class="text-center row">' +
                    '                                           <div class="col-sm-1 slidevertical">' +
                    '                                              <div id="' + idtime + '" class="slidevertical ui-slider ui-corner-all ui-slider-vertical ui-widget ui-widget-content" style="height: 130px;left: 50%;"><div class="ui-slider-range ui-corner-all ui-widget-header ui-slider-range-min" style="height: 0%;"></div><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default" style="bottom: 0%;"></span></div>' +
                    '                                         </div>' +
                    '                                        <div class="col-sm-3 slidevertical">' +
                    '                                           <div class="circle" style="display: inline-block;"><span id="' + amountTime + '" class="amountTime" style="color: white;font-size: 40px;font-weight: bold;line-height: 120px;">' + readCookie[i].amountTime + '</span></div>' +
                    '                                      </div>' +
                    '                                 </div>' +
                    '                            </div>' +
                    '                           <div class="col-sm-6">' +
                    imageHTML +
                    '                           </div>' +
                    '                      </div>' +
                    '                     <div class="text-right col-sm-3">' +
                    '                        <h6>Set&nbsp;the&nbsp;points&nbsp;of&nbsp;the&nbsp;question<br></h6>' +
                    '                       <div class="row">' +
                    '                          <div class="col-sm-10">' +
                    '                             <div class="circlePoints" style="display: inline-block;"><span id="' + amountPoint + '" class="amountPoints" style="color: white;font-size: 40px;font-weight: bold;line-height: 120px;">' + readCookie[i].amountPoints + '</span></div>' +
                    '                        </div>' +
                    '                       <div class="col-sm-1">' +
                    '                          <div id="' + idpoint + '" style="height: 130px;left: 50%;" class="ui-slider ui-corner-all ui-slider-vertical ui-widget ui-widget-content"><div class="ui-slider-range ui-corner-all ui-widget-header ui-slider-range-min" style="height: 0%;"></div><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default" style="bottom: 0%;"></span></div>' +
                    '                     </div>' +
                    '                </div>' +
                    '           </div>' +
                    '      </div>' +
                    '                                        <h5 style="margin-bottom: 0px;">Options<br></h5>' +
                    '                                       <div class="form-row colab">' +
                    '                                          <div class="col-md-6 mb-6">' +
                    '                                             <div class="row">' +
                    '                                                <div class="col-md-10">' +
                    '                                                   <div class="row rowA" style="background-color: #990000;' +
                    '                                                       border: 1px solid;' +
                    '                                                      padding: 10px;' +
                    '                                                     box-shadow: 3px 4px #888888;' +
                    '                                                    color: white;"> ' +
                    '                                                 <div class="col-sm-2">' +
                    '                                                      <div style="margin: 0 auto;">' +
                    '                                                         <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
                    '                                                              width: 60px;' +
                    '                                                             border-radius: 60%;' +
                    '                                                            display: inline-block;' +
                    '                                                           font-size: 2vw;' +
                    '                                                          background-color: #292929;' +
                    '                                                         color: white;">' +
                    '                                                      <span style="margin:auto; height: 96%;">A</span>    ' +
                    '                                                 </span>' +
                    '                                            </div>' +
                    '                                       </div>' +
                    '                                      <div class="col-sm-10">' +
                    '                                         <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionAText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(153, 0, 0); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1">' + readCookie[i].optionAText + '</textarea>' +
                    '                                    </div>' +
                    '                               </div>' +
                    '                                                   </div>' +
                    '                                                  <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
                    '                                                     <div class="radio-toolbar"><input type="checkbox" id="' + opcionA + '" class="optionAcheckBox" onclick="checkBoxClick(this)"><label for="' + opcionA + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
                    '                                                </div>' +
                    '                                           </div>' +
                    '                                      </div>' +
                    '                                     <div class="col-md-6 mb-6">' +
                    '                                        <div class="row rowB">' +
                    '                                           <div class="col-md-10">' +
                    '                                              <div class="row" style="background-color: #86592d;' +
                    '                                                  border: 1px solid;' +
                    '                                                 padding: 10px;' +
                    '                                                box-shadow: 3px 4px #888888;' +
                    '                                               color: white;"> ' +
                    '                                             <div class="col-sm-2">' +
                    '                                                <div style="margin: 0 auto;">' +
                    '                                                   <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
                    '                                                        width: 60px;' +
                    '                                                       border-radius: 60%;' +
                    '                                                      display: inline-block;' +
                    '                                                     font-size: 2vw;' +
                    '                                                    background-color: #292929;' +
                    '                                                   color: white;">' +
                    '                                                <span style="margin:auto; height: 96%;">B</span>    ' +
                    '                                           </span>' +
                    '                                      </div>' +
                    '                                 </div>' +
                    '                                <div class="col-sm-10">' +
                    '                                   <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionBText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(134, 89, 45); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1">' + readCookie[i].optionBText + '</textarea>' +
                    '                              </div>' +
                    '                         </div>' +
                    '                    </div>' +
                    '                   <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
                    '                      <div class="radio-toolbar"><input type="checkbox" id="' + opcionB + '" class="optionBcheckBox" onclick="checkBoxClick(this)"><label for="' + opcionB + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
                    '                 </div>' +
                    '            </div>' +
                    '       </div>' +
                    '  </div>' +
                    ' <div class="form-row colcd">' +
                    '                                            <div class="col-md-6 mb-6">' +
                    '                                               <div class="row rowC">' +
                    '                                                  <div class="col-md-10">' +
                    '                                                     <div class="row" style="background-color: #003380;' +
                    '                                                         border: 1px solid;' +
                    '                                                        padding: 10px;' +
                    '                                                       box-shadow: 3px 4px #888888;' +
                    '                                                      color: white;"> ' +
                    '                                                    <div class="col-sm-2">' +
                    '                                                       <div style="margin: 0 auto;">' +
                    '                                                          <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
                    '                                                               width: 60px;' +
                    '                                                              border-radius: 60%;' +
                    '                                                             display: inline-block;' +
                    '                                                            font-size: 2vw;' +
                    '                                                           background-color: #292929;' +
                    '                                                          color: white;">' +
                    '                                                       <span style="margin:auto; height: 96%;">C</span>    ' +
                    '                                                  </span>' +
                    '                                             </div>' +
                    '                                        </div>' +
                    '                                       <div class="col-sm-10">' +
                    '                                          <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionCText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(0, 51, 128); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1">' + readCookie[i].optionCText + '</textarea>' +
                    '                                                           </div>' +
                    '                                                      </div>' +
                    '                                                    </div>' +
                    '                                                   <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
                    '                                                      <div class="radio-toolbar"><input type="checkbox" class="optionCcheckBox" id="' + opcionC + '" onclick="checkBoxClick(this)"><label for="' + opcionC + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
                    '                                                 </div>' +
                    '                                            </div>' +
                    '                                       </div>' +
                    '                                      <div class="col-md-6 mb-6">' +
                    '                                         <div class="row rowD">' +
                    '                                            <div class="col-md-10">' +
                    '                                               <div class="row" style="background-color: #520066;' +
                    '                                                   border: 1px solid;' +
                    '                                                  padding: 10px;' +
                    '                                                 box-shadow: 3px 4px #888888;' +
                    '                                                color: white;"> ' +
                    '                                              <div class="col-sm-2">' +
                    '                                                 <div style="margin: 0 auto;">' +
                    '                                                    <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
                    '                                                         width: 60px;' +
                    '                                                        border-radius: 60%;' +
                    '                                                       display: inline-block;' +
                    '                                                      font-size: 2vw;' +
                    '                                                     background-color: #292929;' +
                    '                                                    color: white;">' +
                    '                                                 <span style="margin:auto; height: 96%;">D</span>    ' +
                    '                                            </span>' +
                    '                                       </div>' +
                    '                                  </div>' +
                    '                                 <div class="col-sm-10">' +
                    '                                    <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionDText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(82, 0, 102); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1">' + readCookie[i].optionDText + '</textarea>' +
                    '                               </div>' +
                    '                          </div>' +
                    '                     </div>' +
                    '                    <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
                    '                       <div class="radio-toolbar"><input type="checkbox" id="' + opcionD + '" class="optionDcheckBox" onclick="checkBoxClick(this)"><label for="' + opcionD + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
                    '                  </div>' +
                    '             </div>' +
                    '        </div>' +
                    '   </div>' +
                    '  </div>' +
                    '  </div>';
            slideShowContainer.insertAdjacentHTML('afterbegin', str);
            var dotsSlider = document.getElementById('dotsSlider');
            var nuevoDot = dotsSlider.children.length + 1;
            var dots = '<span class="dot" onclick="currentSlide(' + nuevoDot + ')"></span>';

            dotsSlider.insertAdjacentHTML('afterbegin', dots);
            if (readCookie[i].optionAcheckBox === true) {
                document.getElementById(opcionA).click();
            }
            if (readCookie[i].optioBcheckBox === true) {
                document.getElementById(opcionB).click();
            }
            if (readCookie[i].optionCcheckBox === true) {
                document.getElementById(opcionC).click();
            }
            if (readCookie[i].optionDcheckBox === true) {
                document.getElementById(opcionD).click();
            }

            initNewTextArea(idNewQuestionID);
            initNewTextArea(opcionA);
            initNewTextArea(opcionB);
            initNewTextArea(opcionC);
            initNewTextArea(opcionD);
        }

        var numberOfSlides = document.getElementsByClassName('numberOfSlides');
        for (var i = numberOfSlides.length - 1; i >= 0; i--) {
            numberOfSlides[i].innerHTML = ((numberOfSlides.length) - i) + " / " + numberOfSlides.length;
        }
        var dropRegionClassses = document.getElementsByClassName('drop-region');
        for (var i = 0; i < dropRegionClassses.length; i++) {
            dropRegionClassses[i].addEventListener('dragenter', preventDefault, false);
            dropRegionClassses[i].addEventListener('dragleave', preventDefault, false);
            dropRegionClassses[i].addEventListener('dragover', preventDefault, false);
            dropRegionClassses[i].addEventListener('drop', preventDefault, false);
            dropRegionClassses[i].addEventListener('drop', handleDrop, false);
        }

        myLoopCookie(readCookie.length, timeValues, amountTimeValues, pointValues, amountpointsValues, sliderTimeValue, sliderPointsValue);
        let slideShowContainerAdd = document.getElementById('slideShowContainer');
        if (slideShowContainerAdd.children.length === 1) {
            plusSlides(-1);
        } else {
            plusSlides(slideShowContainerAdd.children.length);
        }
    }
};
/*
 * FUNCIONEs
 */
function prevBtn() {
    var tabContent = document.getElementsByClassName('tab-content');
    if (tabContent[0].children[0].classList.contains('active') && tabContent[0].children[0].classList.contains('show')) {
    } else if (tabContent[0].children[1].classList.contains('active') && tabContent[0].children[1].classList.contains('show')) {
        processIcons('menu1');
    } else if (tabContent[0].children[2].classList.contains('active') && tabContent[0].children[2].classList.contains('show')) {
        processIcons('menu2');
    } else {
        console.error("Error, no existe el icono seleccionado.");
    }
}


function nextBtn() {
    var tabContent = document.getElementsByClassName('tab-content');
    if (tabContent[0].children[0].classList.contains('active') && tabContent[0].children[0].classList.contains('show')) {
        processIcons('menu2');
    } else if (tabContent[0].children[1].classList.contains('active') && tabContent[0].children[1].classList.contains('show')) {
        processIcons('menu3');
    } else if (tabContent[0].children[2].classList.contains('active') && tabContent[0].children[2].classList.contains('show')) {
    } else {
        console.error("Error, no existe el icono seleccionado.");
    }
}

var progressBar = 0;
function move() {
    var btncirclesuccess = document.getElementsByClassName('btn-circle');
    for (var i = 0; i < btncirclesuccess.length; i++) {
        btncirclesuccess[i].classList.remove('btn-default');
        btncirclesuccess[i].classList.remove('btn-success');
        btncirclesuccess[i].classList.remove('btn-info');
        btncirclesuccess[i].classList.remove('btn-danger');
    }
    if (progressBar === 0) {
        progressBar = 1;
        var elem = document.getElementById("myBar");
        var width = 10;
        var id = setInterval(frame, 50);
        function frame() {
            if (width >= 100) {
                clearInterval(id);
                progressBar = 0;
                showData();
            } else {
                switch (width) {
                    case 33:
                        getImage();
                        if (quiz.nombre.length !== 0) {
                            btncirclesuccess[0].classList.add('btn-success');
                            btncirclesuccess[0].classList.add('active');
                            btncirclesuccess[0].classList.add('show');
                        } else {
                            clearInterval(id);
                            progressBar = 0;
                            btncirclesuccess[0].classList.add('btn-danger');
                            btncirclesuccess[0].classList.add('active');
                            btncirclesuccess[0].classList.add('show');
                        }

                        break;
                    case 66:
                        if (validarSeccionPreguntas()) {
                            btncirclesuccess[1].classList.add('btn-success');
                            btncirclesuccess[1].classList.add('active');
                            btncirclesuccess[1].classList.add('show');
                        } else {
                            clearInterval(id);
                            progressBar = 0;
                            btncirclesuccess[1].classList.add('btn-danger');
                            btncirclesuccess[1].classList.add('active');
                            btncirclesuccess[1].classList.add('show');
                        }
                        break;
                    case 80:
                        let current_datetime = new Date()
                        let formatted_date = current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds()
                        quiz.fechaApertura = formatted_date;
                        break;
                    case 99:
                        btncirclesuccess[2].classList.add('btn-success');
                        btncirclesuccess[2].classList.add('active');
                        btncirclesuccess[2].classList.add('show');
                        break;
                }
                width++;
                elem.style.width = width + "%";
                elem.innerHTML = width + "%";
            }
        }
    }
}

function getImage() {
    var imagePreviewQuizImage = document.getElementById('imagePreviewQuizImage');
    if (imagePreviewQuizImage.children.length === 0) {
        //console.log("Sin IMAGEN");
        //SRC = avaya image.png
        //ASIGNAMOS UNA IMAGEN POR DEFAULT
        quiz.imageQuiz = "Home/img/Transparent.png";
    } else {
        //console.log("Con imagen");
        //console.log(imgaePreviewQuestion.children[0].children[0].src);
        var imageB64 = imagePreviewQuizImage.children[0].children[0].src;
        //ASIGNAMOS LA IMAGEN SUBIDA AL OBJETO.
        quiz.imageQuiz = imageB64;
    }
}

function validarSeccionPreguntas() {
    quiz.preguntas = [];
    let slideShowContainerValidate = document.getElementById('slideShowContainer');
    //    console.log(questionsDiv.children);
    for (var i = 0; i < slideShowContainerValidate.children.length; i++) {
        //PREGUNTA
        //console.log(slideShowContainerValidate.children[i].children[1].children[0].children[0].value);
        let pregunta = slideShowContainerValidate.children[i].children[1].children[0].children[0].value;
        if (pregunta.length === 0) {
            inputQuestions(slideShowContainerValidate.children[i].children[1].children[0].children[0]);
            plusSlides(i);
            return false;
        }
        //OPCION A
        //console.log(questionsDiv.children[i].children[2].children[0].children[1]);
        //console.log("Opcion A");
        //console.log(slideShowContainerValidate.children[i].children[1].children[3].children[0].children[0].children[0].children[0].children[1].children[0].value);
        let opcionA = slideShowContainerValidate.children[i].children[1].children[3].children[0].children[0].children[0].children[0].children[1].children[0].value;
        if (opcionA.length === 0) {
            inputQuestions(slideShowContainerValidate.children[i].children[1].children[3].children[0].children[0].children[0].children[0].children[1].children[0]);
            plusSlides(i);
            return false;
        }
        //OPCION C
        //        console.log(questionsDiv.children[i].children[2].children[2].children[1]);
        //console.log(slideShowContainerValidate.children[i].children[1].children[4].children[0].children[0].children[0].children[0].children[1]);
        let opcionC = slideShowContainerValidate.children[i].children[1].children[4].children[0].children[0].children[0].children[0].children[1].children[0].value;
        if (opcionC.length === 0) {
            inputQuestions(slideShowContainerValidate.children[i].children[1].children[4].children[0].children[0].children[0].children[0].children[1].children[0]);
            plusSlides(i);
            return false;
        }


        //OPCION B
        //console.log(slideShowContainerValidate.children[i].children[1].children[3].children[1].children[0].children[0].children[0].children[1].children[0].value);
        let opcionB = slideShowContainerValidate.children[i].children[1].children[3].children[1].children[0].children[0].children[0].children[1].children[0].value;
        if (opcionB.length === 0) {
            inputQuestions(slideShowContainerValidate.children[i].children[1].children[3].children[1].children[0].children[0].children[0].children[1].children[0]);
            plusSlides(i);
            return false;
        }


        //OPCION D
        //console.log(slideShowContainerValidate.children[i].children[1].children[4].children[1].children[0].children[0].children[0].children[1].children[0]);
        let opcionD = slideShowContainerValidate.children[i].children[1].children[4].children[1].children[0].children[0].children[0].children[1].children[0].value;
        if (opcionD.length === 0) {
            inputQuestions(slideShowContainerValidate.children[i].children[1].children[4].children[1].children[0].children[0].children[0].children[1].children[0]);
            plusSlides(i);
            return false;
        }


        //console.log(slideShowContainerValidate.children[i].children[1].children[4].children[0].children[0].children[1].children[0].children[0].checked);
        //CHECK BOX A

        let checkBoxA = slideShowContainerValidate.children[i].children[1].children[3].children[0].children[0].children[1].children[0].children[0].checked;
        //console.log(checkBoxA);
        //CHECK BOX B
        let checkBoxB = slideShowContainerValidate.children[i].children[1].children[3].children[1].children[0].children[1].children[0].children[0].checked;
        //console.log(checkBoxB);
        //CHECK BOX C

        let checkBoxC = slideShowContainerValidate.children[i].children[1].children[4].children[0].children[0].children[1].children[0].children[0].checked;
        //console.log(checkBoxC);

        //CHECK BOX D
        let checkBoxD = slideShowContainerValidate.children[i].children[1].children[4].children[1].children[0].children[1].children[0].children[0].checked;
        //        console.log(checkBoxD);

        //TIEMPO
        //console.log(slideShowContainerValidate.children[i].children[1].children[1].children[0].children[1].children[1].children[0].children[0].innerHTML);
        let timeQuiz = slideShowContainerValidate.children[i].children[1].children[1].children[0].children[1].children[1].children[0].children[0].innerHTML;
        //console.log(slideShowContainerValidate.children[i].children[1].children[1].children[2].children[1].children[0].children[0].children[0].innerHTML);
        let points = slideShowContainerValidate.children[i].children[1].children[1].children[2].children[1].children[0].children[0].children[0].innerHTML;
        //console.log(slideShowContainerValidate.children[i].children[0].children[0].innerHTML);
        var indexOreder = slideShowContainerValidate.children[i].children[0].children[0].innerHTML;
        //console.log(indexOreder.charAt(0));

        //console.log(slideShowContainerValidate.children[i].children[1].children[1].children[1].children[0].children[1]);
        let imageQuiz = slideShowContainerValidate.children[i].children[1].children[1].children[1].children[0].children[1];
        var imageQuizObject;
        if (imageQuiz.children.length === 0) {
            //console.log("Sin IMAGEN");
            //SRC = avaya image.png
            //ASIGNAMOS UNA IMAGEN POR DEFAULT
            imageQuizObject = "Home/img/Transparent.png";
        } else {
            //console.log("Con imagen");
            //console.log(imgaePreviewQuestion.children[0].children[0].src);
            var imageB64 = imageQuiz.children[0].children[0].src;
            //ASIGNAMOS LA IMAGEN SUBIDA AL OBJETO.
            imageQuizObject = imageB64;
        }

        if (checkBoxA === false && checkBoxB === false && checkBoxC === false && checkBoxD === false) {
            alert("You need to select the correct option from the options in the question " + (i + 1));
            plusSlides(i);
            return false;
        }

        var preguntaObject = {
            "preguta": pregunta,
            "opcionA": opcionA,
            "opcionB": opcionB,
            "opcionC": opcionC,
            "opcionD": opcionD,
            "checkBoxA": checkBoxA,
            "checkBoxB": checkBoxB,
            "checkBoxC": checkBoxC,
            "checkBoxD": checkBoxD,
            "points": points,
            "time": timeQuiz,
            "indexorder": parseInt(indexOreder, 10),
            "image": imageQuizObject
        };
        quiz.preguntas.push(preguntaObject);
    }
    return true;
}

function processIcons(menu) {
    let tabPane = document.getElementsByClassName('tab-pane');
    for (var i = 0; i < tabPane.length; i++) {
        if (tabPane[i].id === menu) {
            tabPane[i].classList.add('active');
            tabPane[i].classList.add('show');
        } else {
            tabPane[i].classList.remove('active');
            tabPane[i].classList.remove('show');
        }
    }
    var btncircle = document.getElementsByClassName('btn-circle');
    for (var i = 0; i < btncircle.length; i++) {
        btncircle[i].classList.remove('btn-info');
        btncircle[i].classList.remove('active');
        btncircle[i].classList.remove('show');
    }
    switch (menu) {
        case "menu1":
            btncircle[0].classList.add('btn-info');
            btncircle[0].classList.add('active');
            btncircle[0].classList.add('show');
            break;
        case "menu2":
            quiz.preguntas = [];
            document.getElementById('menuoptionsCreate').style.display = "";
            btncircle[1].classList.add('btn-info');
            btncircle[1].classList.add('active');
            btncircle[1].classList.add('show');
            break;
        case "menu3":
            btncircle[2].classList.add('btn-info');
            btncircle[2].classList.add('active');
            btncircle[2].classList.add('show');
            document.getElementById('initBarProgress').click();
            break;
        case "menu4":
            btncircle[3].classList.add('btn-info');
            btncircle[3].classList.add('active');
            btncircle[3].classList.add('show');
            break;
    }
}


function addQuestion() {
    let slideShowContainer = document.getElementById('slideShowContainer');
    var opcionA = makeid(5);
    var opcionB = makeid(5);
    var opcionC = makeid(5);
    var opcionD = makeid(5);
    var idtime = makeid(5);
    var amountTime = makeid(5);
    var idpoint = makeid(5);
    var amountPoint = makeid(5);
    var idNewQuestionID = makeid(5);
    var strTwo = '<div class="fadeImage mySlides" style="display: block;">' +
            '                                    <div class="numbertext"><strong class="numberOfSlides" style="float: left;">1 / 1</strong></div>' +
            '                                   <div class="form">' +
            '                                      <div class="row">' +
            '                                         <textarea class="form-control-lg form-control textAreaAutoReziseNuevo questionInput" id="' + idNewQuestionID + '" rows="1" maxlength="120" minlength="1" type="text" required="true" style="display: inline-block; width: 100%; height: 107px; background-color: rgb(228, 226, 226); font-size: 2vw; overflow: hidden; text-align: center; font-weight: bold; color: black; border: 1px solid;" onblur="inputQuestions(this)" autofocus=""></textarea>' +
            '                                    </div>' +
            '                                   <div class="row" style="margin-top: 5px;">' +
            '                                      <div class="text-left d-inline-block col-sm-3 timeToRespond">' +
            '                                         <h6>Set&nbsp;the&nbsp;time&nbsp;to&nbsp;respond<br></h6>' +
            '                                        <div class="text-center row">' +
            '                                           <div class="col-sm-1 slidevertical">' +
            '                                              <div id="' + idtime + '" class="slidevertical ui-slider ui-corner-all ui-slider-vertical ui-widget ui-widget-content" style="height: 130px;left: 50%;"><div class="ui-slider-range ui-corner-all ui-widget-header ui-slider-range-min" style="height: 0%;"></div><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default" style="bottom: 0%;"></span></div>' +
            '                                         </div>' +
            '                                        <div class="col-sm-3 slidevertical">' +
            '                                           <div class="circle" style="display: inline-block;"><span id="' + amountTime + '" class="amountTime" style="color: white;font-size: 40px;font-weight: bold;line-height: 120px;">20s</span></div>' +
            '                                      </div>' +
            '                                 </div>' +
            '                            </div>' +
            '                           <div class="col-sm-6">' +
            '                              <div class="drop-region" style="display: inline-block;border-style: dotted;width: 441px;" onclick="clickDropRegion(this)">' +
            '                                 <div class="drop-message"><strong>Drag&nbsp;&amp;&nbsp;Drop&nbsp;image&nbsp;or&nbsp;click&nbsp;to&nbsp;upload<br></strong></div>' +
            '                                <div class="image-preview-question"></div>' +
            '                           </div>' +
            '                      </div>' +
            '                     <div class="text-right col-sm-3">' +
            '                        <h6>Set&nbsp;the&nbsp;points&nbsp;of&nbsp;the&nbsp;question<br></h6>' +
            '                       <div class="row">' +
            '                          <div class="col-sm-10">' +
            '                             <div class="circlePoints" style="display: inline-block;"><span id="' + amountPoint + '" class="amountPoints" style="color: white;font-size: 40px;font-weight: bold;line-height: 120px;">10pts</span></div>' +
            '                        </div>' +
            '                       <div class="col-sm-1">' +
            '                          <div id="' + idpoint + '" style="height: 130px;left: 50%;" class="ui-slider ui-corner-all ui-slider-vertical ui-widget ui-widget-content"><div class="ui-slider-range ui-corner-all ui-widget-header ui-slider-range-min" style="height: 0%;"></div><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default" style="bottom: 0%;"></span></div>' +
            '                     </div>' +
            '                </div>' +
            '           </div>' +
            '      </div>' +
            '                                        <h5 style="margin-bottom: 0px;">Options<br></h5>' +
            '                                       <div class="form-row">' +
            '                                          <div class="col-md-6 mb-6">' +
            '                                             <div class="row">' +
            '                                                <div class="col-md-10">' +
            '                                                   <div class="row rowA" style="background-color: #990000;' +
            '                                                       border: 1px solid;' +
            '                                                      padding: 10px;' +
            '                                                     box-shadow: 3px 4px #888888;' +
            '                                                    color: white;"> ' +
            '                                                 <div class="col-sm-2">' +
            '                                                      <div style="margin: 0 auto;">' +
            '                                                         <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
            '                                                              width: 60px;' +
            '                                                             border-radius: 60%;' +
            '                                                            display: inline-block;' +
            '                                                           font-size: 2vw;' +
            '                                                          background-color: #292929;' +
            '                                                         color: white;">' +
            '                                                      <span style="margin:auto; height: 96%;">A</span>    ' +
            '                                                 </span>' +
            '                                            </div>' +
            '                                       </div>' +
            '                                      <div class="col-sm-10">' +
            '                                         <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionAText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(153, 0, 0); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1"></textarea>' +
            '                                    </div>' +
            '                               </div>' +
            '                                                   </div>' +
            '                                                  <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
            '                                                     <div class="radio-toolbar"><input type="checkbox" id="' + opcionA + '" class="optionAcheckBox" onclick="checkBoxClick(this)"><label for="' + opcionA + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
            '                                                </div>' +
            '                                           </div>' +
            '                                      </div>' +
            '                                     <div class="col-md-6 mb-6">' +
            '                                        <div class="row rowB">' +
            '                                           <div class="col-md-10">' +
            '                                              <div class="row" style="background-color: #86592d;' +
            '                                                  border: 1px solid;' +
            '                                                 padding: 10px;' +
            '                                                box-shadow: 3px 4px #888888;' +
            '                                               color: white;"> ' +
            '                                             <div class="col-sm-2">' +
            '                                                <div style="margin: 0 auto;">' +
            '                                                   <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
            '                                                        width: 60px;' +
            '                                                       border-radius: 60%;' +
            '                                                      display: inline-block;' +
            '                                                     font-size: 2vw;' +
            '                                                    background-color: #292929;' +
            '                                                   color: white;">' +
            '                                                <span style="margin:auto; height: 96%;">B</span>    ' +
            '                                           </span>' +
            '                                      </div>' +
            '                                 </div>' +
            '                                <div class="col-sm-10">' +
            '                                   <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionBText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(134, 89, 45); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1"></textarea>' +
            '                              </div>' +
            '                         </div>' +
            '                    </div>' +
            '                   <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
            '                      <div class="radio-toolbar"><input type="checkbox" id="' + opcionB + '" class="optionBcheckBox" onclick="checkBoxClick(this)"><label for="' + opcionB + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
            '                 </div>' +
            '            </div>' +
            '       </div>' +
            '  </div>' +
            ' <div class="form-row" style="margin-top: 10px;">' +
            '                                            <div class="col-md-6 mb-6">' +
            '                                               <div class="row rowC">' +
            '                                                  <div class="col-md-10">' +
            '                                                     <div class="row" style="background-color: #003380;' +
            '                                                         border: 1px solid;' +
            '                                                        padding: 10px;' +
            '                                                       box-shadow: 3px 4px #888888;' +
            '                                                      color: white;"> ' +
            '                                                    <div class="col-sm-2">' +
            '                                                       <div style="margin: 0 auto;">' +
            '                                                          <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
            '                                                               width: 60px;' +
            '                                                              border-radius: 60%;' +
            '                                                             display: inline-block;' +
            '                                                            font-size: 2vw;' +
            '                                                           background-color: #292929;' +
            '                                                          color: white;">' +
            '                                                       <span style="margin:auto; height: 96%;">C</span>    ' +
            '                                                  </span>' +
            '                                             </div>' +
            '                                        </div>' +
            '                                       <div class="col-sm-10">' +
            '                                          <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionCText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(0, 51, 128); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1"></textarea>' +
            '                                                           </div>' +
            '                                                      </div>' +
            '                                                    </div>' +
            '                                                   <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
            '                                                      <div class="radio-toolbar"><input type="checkbox" class="optionCcheckBox" id="' + opcionC + '" onclick="checkBoxClick(this)"><label for="' + opcionC + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
            '                                                 </div>' +
            '                                            </div>' +
            '                                       </div>' +
            '                                      <div class="col-md-6 mb-6">' +
            '                                         <div class="row rowD">' +
            '                                            <div class="col-md-10">' +
            '                                               <div class="row" style="background-color: #520066;' +
            '                                                   border: 1px solid;' +
            '                                                  padding: 10px;' +
            '                                                 box-shadow: 3px 4px #888888;' +
            '                                                color: white;"> ' +
            '                                              <div class="col-sm-2">' +
            '                                                 <div style="margin: 0 auto;">' +
            '                                                    <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
            '                                                         width: 60px;' +
            '                                                        border-radius: 60%;' +
            '                                                       display: inline-block;' +
            '                                                      font-size: 2vw;' +
            '                                                     background-color: #292929;' +
            '                                                    color: white;">' +
            '                                                 <span style="margin:auto; height: 96%;">D</span>    ' +
            '                                            </span>' +
            '                                       </div>' +
            '                                  </div>' +
            '                                 <div class="col-sm-10">' +
            '                                    <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionDText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(82, 0, 102); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1"></textarea>' +
            '                               </div>' +
            '                          </div>' +
            '                     </div>' +
            '                    <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
            '                       <div class="radio-toolbar"><input type="checkbox" id="' + opcionD + '" class="optionDcheckBox" onclick="checkBoxClick(this)"><label for="' + opcionD + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
            '                  </div>' +
            '             </div>' +
            '        </div>' +
            '   </div>' +
            '  </div>' +
            '                               </div>';
    slideShowContainer.insertAdjacentHTML('afterbegin', strTwo);
    var dotsSlider = document.getElementById('dotsSlider');
    var nuevoDot = dotsSlider.children.length + 1;
    var numberOfSlides = document.getElementsByClassName('numberOfSlides');
    for (var i = numberOfSlides.length - 1; i >= 0; i--) {
        numberOfSlides[i].innerHTML = ((numberOfSlides.length) - i) + " / " + numberOfSlides.length;
    }
    var dots = '<span class="dot" onclick="currentSlide(' + nuevoDot + ')"></span>';
    dotsSlider.insertAdjacentHTML('afterbegin', dots);
    $(function () {
        $("#" + idtime).slider({
            orientation: "vertical",
            range: "min",
            min: 20,
            max: 300,
            value: 20,
            step: 20,
            slide: function (event, ui) {
                createCookieObjectPreguntas();
                $("#" + amountTime).html(ui.value + "s");
            }
        });
        $("#" + amountTime).html($("#" + idtime).slider("value") + "s");
        document.getElementById(idtime).children[2].style = "background: blue;";
    });
    $(function () {
        $("#" + idpoint).slider({
            orientation: "vertical",
            range: "min",
            min: 10,
            max: 100,
            value: 10,
            step: 10,
            slide: function (event, ui) {
                createCookieObjectPreguntas();
                $("#" + amountPoint).html(ui.value + "pts");
            }
        });
        $("#" + amountPoint).html($("#" + idpoint).slider("value") + "pts");
        document.getElementById(idpoint).children[2].style = "background: green;";
    });
    let slideShowContainerAdd = document.getElementById('slideShowContainer');
    if (slideShowContainerAdd.children.length === 1) {
        plusSlides(-1);
    } else {
        plusSlides(slideShowContainerAdd.children.length);
    }
    initNewTextArea(idNewQuestionID);
    initNewTextArea(opcionA);
    initNewTextArea(opcionB);
    initNewTextArea(opcionC);
    initNewTextArea(opcionD);
    var dropRegionClassses = document.getElementsByClassName('drop-region');
    for (var i = 0; i < dropRegionClassses.length; i++) {
        dropRegionClassses[i].addEventListener('dragenter', preventDefault, false);
        dropRegionClassses[i].addEventListener('dragleave', preventDefault, false);
        dropRegionClassses[i].addEventListener('dragover', preventDefault, false);
        dropRegionClassses[i].addEventListener('drop', preventDefault, false);
        dropRegionClassses[i].addEventListener('drop', handleDrop, false);
    }

}

function checkBoxClick(_this) {
    let checkBoxElement = _this;
    if (checkBoxElement.checked) {
        let parentNode = checkBoxElement.parentNode;
        parentNode.children[1].innerHTML = "";
        var str = '<i class="fas fa-check fa-4x"></i>';
        parentNode.children[1].insertAdjacentHTML('beforeend', str);
    } else {
        let parentNode = checkBoxElement.parentNode;
        parentNode.children[1].innerHTML = "";
        var str = '<i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i>';
        parentNode.children[1].insertAdjacentHTML('beforeend', str);
    }
    createCookieObjectPreguntas();
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

    createCookieObjectPreguntas();
}

function createCookieObjectPreguntas() {
    console.log("createCookieObjectPreguntas");
    var slideShowContainer = document.getElementById('slideShowContainer');
    var questionsCookie = [];
    for (var i = 0; i < slideShowContainer.children.length; i++) {
//        var questionInput = slideShowContainer.children[i].querySelectorAll(".questionInput");
//        var amountPoints = slideShowContainer.children[i].querySelectorAll(".amountPoints");
//        var amountTime = slideShowContainer.children[i].querySelectorAll(".amountTime");
//        var optionAText = slideShowContainer.children[i].querySelectorAll(".optionAText");
//        var optionAcheckBox = slideShowContainer.children[i].querySelectorAll(".optionAcheckBox");
//        var optionBText = slideShowContainer.children[i].querySelectorAll(".optionBText");
//        var optionBcheckBox = slideShowContainer.children[i].querySelectorAll(".optionBcheckBox");
//        var optionCText = slideShowContainer.children[i].querySelectorAll(".optionCText");
//        var optionCcheckBox = slideShowContainer.children[i].querySelectorAll(".optionCcheckBox");
//        var optionDText = slideShowContainer.children[i].querySelectorAll(".optionDText");
//        var optionDcheckBox = slideShowContainer.children[i].querySelectorAll(".optionDcheckBox");
        var sliders = slideShowContainer.children[i].querySelectorAll(".ui-slider-range");
        var sliderTimeHeight;
        var sliderPointsHight;

        if (sliders.length === 2) {
            try {
                sliderTimeHeight = sliders[0].style.height;
            } catch (err) {
                console.log("sliderTimeHeight Error");
            }
            try {
                sliderPointsHight = sliders[1].style.height;
            } catch (err) {
                console.log("sliderPointsHight Error");
            }
        } else {
            try {
//                console.log("sliderTimeHeight");
//                console.log(sliders[0].style.height);
//                console.log(sliders[1].style.height);
//                console.log(sliders[2].style.height);
//                console.log(sliders[3].style.height);
                sliderTimeHeight = sliders[1].style.height;
            } catch (err) {

                console.log("sliderTimeHeight Error");
            }
            try {
//                console.log("sliderPointsHight");
//                console.log(sliders[0].style.height);
//                console.log(sliders[1].style.height);
//                console.log(sliders[2].style.height);
//                console.log(sliders[3].style.height);
                sliderPointsHight = sliders[3].style.height;
            } catch (err) {
                console.log("sliderPointsHight Error");
            }
        }
//        console.log(questionInput[0].value);
//        console.log(amountPoints[0].innerHTML);
//        console.log(amountTime[0].innerHTML);
//        console.log(optionAText[0].value);
//        console.log(optionAcheckBox[0].checked);
//        console.log(optionBText[0].value);
//        console.log(optionBcheckBox[0].checked);
//        console.log(optionCText[0].value);
//        console.log(optionCcheckBox[0].checked);
//        console.log(optionDText[0].value);
//        console.log(optionDcheckBox[0].checked);



        var image = "EMPTY";
//        var imagePreviewQuestion = slideShowContainer.children[i].querySelectorAll(".image-preview-question")[0];
//        if (imagePreviewQuestion.children.length !== 0) {
//            image = imagePreviewQuestion.children[0].children[0].src;
//        }

        questionsCookie.push({
            "questionInput": slideShowContainer.children[i].querySelectorAll(".questionInput")[0].value,
            "amountPoints": slideShowContainer.children[i].querySelectorAll(".amountPoints")[0].innerHTML,
            "amountTime": slideShowContainer.children[i].querySelectorAll(".amountTime")[0].innerHTML,
            "optionAText": slideShowContainer.children[i].querySelectorAll(".optionAText")[0].value,
            "optionAcheckBox": slideShowContainer.children[i].querySelectorAll(".optionAcheckBox")[0].checked,
            "optionBText": slideShowContainer.children[i].querySelectorAll(".optionBText")[0].value,
            "optionBcheckBox": slideShowContainer.children[i].querySelectorAll(".optionBcheckBox")[0].checked,
            "optionCText": slideShowContainer.children[i].querySelectorAll(".optionCText")[0].value,
            "optionCcheckBox": slideShowContainer.children[i].querySelectorAll(".optionCcheckBox")[0].checked,
            "optionDText": slideShowContainer.children[i].querySelectorAll(".optionDText")[0].value,
            "optionDcheckBox": slideShowContainer.children[i].querySelectorAll(".optionDcheckBox")[0].checked,
            "sliderTimeHeight": sliderTimeHeight,
            "sliderPointsHight": sliderPointsHight,
            "image": image
        });
    }
    console.log(questionsCookie);
    create_cookie("questionsCookie", JSON.stringify(questionsCookie));
}

function deleteForm() {
    let slideShowContainer = document.getElementById('slideShowContainer');
    for (let index = 0; index < slideShowContainer.children.length; index++) {
        if (slideShowContainer.children[index].style.display === "block") {
            slideShowContainer.removeChild(slideShowContainer.children[index]);
            var numberOfSlides = document.getElementsByClassName('numberOfSlides');
            for (var i = numberOfSlides.length - 1; i >= 0; i--) {
                numberOfSlides[i].innerHTML = ((numberOfSlides.length) - i) + " / " + numberOfSlides.length;
            }
            plusSlides(index);
            break;
        }
    }

}

function changeNameByInput() {

    let input = document.getElementById("inputNameQuiz").value;
    let quizNameModify = document.getElementsByClassName('quizNameModify');
    for (var i = 0; i < quizNameModify.length; i++) {
        quizNameModify[i].innerHTML = "";
        quizNameModify[i].innerHTML = input;
        quiz.nombre = "";
        quiz.nombre = input;
    }
}

function makeid(length) {
    var result = '';
    var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for (var i = 0; i < length; i++) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
}

function showData() {
    //questionsNumber
    //questionsText
    //quizName
    //quizCreated
    document.getElementById('photoBackGround').style.backgroundImage = "";
    document.getElementById('photoBackGround').style.backgroundImage = 'url(' + quiz.imageQuiz + ')';
    document.getElementById('quizNameCard').innerHTML = "";
    document.getElementById('quizNameCard').innerHTML = quiz.nombre;
    document.getElementById('quizCreated').innerHTML = "";
    document.getElementById('quizCreated').innerHTML = quiz.fechaApertura;
    document.getElementById('questionsNumber').innerHTML = "";
    document.getElementById('questionsNumber').innerHTML = document.getElementById('slideShowContainer').children.length;
    var sortable = document.getElementById('sortable');
    document.getElementById('sortable').innerHTML = "";
    $('#sortable').empty();
    for (var i = 0; i < quiz.preguntas.length; i++) {
        var liElementTwo = '<li class="ui-state-default" id="' + i + '">' +
                '<div class="border-dark shadow row">' +
                '          <div class="col-md-1" style="margin: auto;width: 50%; padding: 10px;"><img class="img-fluid d-lg-flex align-items-lg-center" style="width: 75%;" src="' + quiz.preguntas[i].image + '"/></div>' +
                '           <div class="col-sm-11">' +
                '              <div class="row d-lg-flex" style="margin: 11px;">' +
                '                 <div class="col-sm-11 d-lg-flex align-items-lg-center">' +
                '                    <h4 class="text-left d-lg-flex align-items-lg-center">' + quiz.preguntas[i].preguta + '</h4>' +
                '               </div>' +
                '               <div class="col-sm-1">' +
                '                   <h4 class="text-right">' + quiz.preguntas[i].points + '</h4>' +
                '               </div>' +
                '           </div>' +
                '       </div>' +
                '    </div>' +
                '</li>';
        sortable.insertAdjacentHTML('afterbegin', liElementTwo);
    }

    document.getElementById('resultsShow').style.display = "";
    document.getElementById('createQuiz').disabled = false;
    document.getElementById('createQuiz').style.backgroundColor = "green";
    var iconCreateQuiz = document.getElementById('iconCreateQuiz');
    iconCreateQuiz.classList.add("blink_me");
    iconCreateQuiz.style.color = "white";
    //blink_me
    $(function () {
        $("#sortable").sortable({
            revert: true,
            change: function (event, ui) {
                console.log("Change");
                console.log(event);
                console.log(ui);
            }

        });
        $("#draggable").draggable({
            connectToSortable: "#sortable",
            helper: "clone",
            revert: "invalid"
        });
        $("ul, li").disableSelection();
    });
}


document.getElementById('createQuiz').addEventListener('click', function () {
    var idsInOrder = $("#sortable").sortable("toArray");
    for (var i = 0; i < idsInOrder.length; i++) {
        quiz.preguntas[idsInOrder[i]].indexorder = i;
    }
    postAvayaSpacesContestCreateSpace(JSON.stringify(quiz));
});
function postAvayaSpacesContestCreateSpace(jsonString) {
    document.getElementById('loaderDisplay').classList.add('is-active');
    document.getElementById("loaderDisplay").setAttribute("data-text", "Creating quiz");
    var data = new FormData();
    data.append("jsonObject", jsonString);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            document.getElementById("loaderDisplay").classList.remove("is-active");
            document.getElementById("loaderDisplay").setAttribute("data-text", "");
            console.log(this.responseText);
            document.getElementById('launchModal').click();
            try {
                let jsonObject = JSON.parse(this.responseText);
                if (jsonObject.status === "ok") {
                    var questionsCookie = read_cookie("questionsCookie");
                    if (questionsCookie !== null) {
                        //delete_cookie("tourQuiz", JSON.stringify(jsonTour));
                        document.cookie = "questionsCookie=" + JSON.stringify(questionsCookie) + "; expires=Thu, 18 Dec 1970 12:00:00 UTC; path=/";
                    }
                    //MODIFICAMOS EL MODAL CON EL UUID DEL NNUEVO QUIZ
                    document.getElementById('uuidQuiz').innerHTML = "";
                    document.getElementById('uuidQuiz').innerHTML = jsonObject.quiz.quizUUID;
                    //MODIFICAMOS EL MODAL HEADER
                    document.getElementById('exampleModalLabel').innerHTML = "";
                    document.getElementById('exampleModalLabel').innerHTML = jsonObject.quiz.modalHeader;
                    //MODIFICAMOS EL MODAL BODY
                    document.getElementById('modalBody').innerHTML = "";
                    document.getElementById('modalBody').innerHTML = "Congratulations, the quiz has been created correctly.";
                } else {
                    document.getElementById('uuidQuiz').innerHTML = "";
                    document.getElementById('exampleModalLabel').innerHTML = "";
                    document.getElementById('exampleModalLabel').innerHTML = "Error detected";
                    document.getElementById('modalBody').innerHTML = "";
                    document.getElementById('modalBody').innerHTML = jsonObject.message;
                }
            } catch (err) {
                console.log(err.toString());
                document.getElementById('uuidQuiz').innerHTML = "";
                document.getElementById('exampleModalLabel').innerHTML = "";
                document.getElementById('exampleModalLabel').innerHTML = "Error detected";
                document.getElementById('modalBody').innerHTML = "";
                document.getElementById('modalBody').innerHTML = "An error has been detected, please try again later";
            }
        }
    });
    xhr.open("POST", getAbsolutePath() + "/AvayaSpacesContest");
    xhr.send(data);
}

function backToMenu() {
    window.location.replace("?p=quizes");
}

function createAnotherQuiz() {
    window.location.reload();
}

function playQuiz() {
    window.location.replace("?p=playQuiz&q=" + document.getElementById('uuidQuiz').innerHTML);
}
$(function () {
    $("#slider-vertical").slider({
        orientation: "vertical",
        range: "min",
        min: 20,
        max: 300,
        value: 20,
        step: 20,
        slide: function (event, ui) {
            $("#amount").html(ui.value + "s");
            createCookieObjectPreguntas();
        }
    });
    $("#amount").html($("#slider-vertical").slider("value") + "s");
    document.getElementById('slider-vertical').children[0].style = "background: blue;";
});
$(function () {
    $("#slider-vertical-points").slider({
        orientation: "vertical",
        range: "min",
        min: 10,
        max: 100,
        value: 10,
        step: 10,
        slide: function (event, ui) {
            $("#amount-points").html(ui.value + "pts");
            createCookieObjectPreguntas();
        }
    });
    $("#amount-points").html($("#slider-vertical-points").slider("value") + "pts");
    document.getElementById('slider-vertical-points').children[0].style = "background: green;";
});
function changeColor(nuevoValorDeColor) {
    document.getElementsByClassName('photo-card')[0].style.backgroundColor = "";
    document.getElementsByClassName('photo-card')[0].style.backgroundColor = nuevoValorDeColor;
    quiz.quizColor = nuevoValorDeColor;
}

function changeColorFont(fontColorInput) {
    //questionsNumber
    //questionsText
    //quizName
    //quizCreated
    document.getElementById('questionsNumber').style.color = fontColorInput;
    document.getElementById('questionsText').style.color = fontColorInput;
    document.getElementById('quizNameCard').style.color = fontColorInput;
    document.getElementById('quizCreated').style.color = fontColorInput;
    quiz.fontColor = fontColorInput;
}

function isFavorite(_this) {
    //<i class="fa fa-star-o" style="color: rgb(255,255,255);font-size: 21px;cursor: pointer;cursor: pointer;" onclick="isFavorite(this)"></i>
    if (_this.classList.contains('favorite')) {
        _this.classList.add('fa-star-o');
        _this.classList.remove('fa-star');
        _this.classList.remove('favorite');
        _this.style.color = "rgb(255,255,255)";
        quiz.isFavorite = false;
    } else {
        //fa fa-star
        _this.style.color = "yellow";
        _this.classList.remove('fa-star-o');
        _this.classList.add('fa-star');
        _this.classList.add('favorite');
        quiz.isFavorite = true;
    }
}

function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
}


function uploadQuestions() {
    console.log("Upload Questions");
    document.getElementById('uploadQuestionsModalBTN').click();
}

function uploadFileRest() {
    let filesUpload = [];
    let count = 0;
    if (validateExistingFiles()) {
        function sleep2(ms) {
            return new Promise(resolve => setTimeout(resolve, ms));
        }
        async function demo2(file, files) {
            getBase64(file);
            await sleep2(1000);
            count++;
            if (count === files) {
                makeRest(JSON.stringify(filesUpload));
            }
        }

        for (var i = 0; i < document.getElementById('customFile').files.length; i++) {
            if (validate_fileupload(document.getElementById('customFile').files[i].name)) {
                demo2(document.getElementById('customFile').files[i], document.getElementById('customFile').files.length);
            }
        }

    } else {
        alert("NO file to validate");
    }

    function makeRest(jsonString) {
        document.getElementById('loaderDisplay').classList.add('is-active');
        document.getElementById("loaderDisplay").setAttribute("data-text",
                "Reading Excell");
        var jsonObjectRest = {
            "action": "uploadFileToGetQuestions",
            "arrayFiles": jsonString
        };
        var data = new FormData();
        data.append("jsonObject", JSON.stringify(jsonObjectRest));
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = false;
        xhr.timeout = 60000;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                document.getElementById("loaderDisplay").classList
                        .remove("is-active");
                document.getElementById("loaderDisplay")
                        .setAttribute("data-text", "");
                //console.log(this.responseText);
                try {
                    var jsonObject = JSON.parse(this.responseText);
                    if (jsonObject.status === "ok") {
                        createQuiestonsInForm(jsonObject);
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


    function validateExistingFiles() {
        if (document.getElementById('customFile').files.length !== 0) {
            return true;
        }
        return false;
    }

    function validate_fileupload(fileName)
    {
        var allowed_extensions = new Array("xlsx");
        var file_extension = fileName.split('.').pop().toLowerCase(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

        for (var i = 0; i <= allowed_extensions.length; i++)
        {
            if (allowed_extensions[i] === file_extension)
            {
                return true; // valid file extension
            }
        }

        return false;
    }

    function getBase64(file) {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
            var fileItem = {
                filename: file.name,
                base64item: reader.result
            };
            filesUpload.push(fileItem);
        };
        reader.onerror = function (error) {
            console.log('Error: ', error);
        };
    }

    function createQuiestonsInForm(jsonResponse) {
        if (jsonResponse.hasOwnProperty("quizPreguntas")) {
            if (jsonResponse.quizPreguntas.length !== 0) {
                var slideShowContainer = document.getElementById('slideShowContainer');
                let opcionA = makeid(5);
                let opcionB = makeid(5);
                let opcionC = makeid(5);
                let opcionD = makeid(5);
                let idNewQuestionID = makeid(5);
                let idtime;
                let amountTime;
                let idpoint;
                let amountPoint;
                var timeValues = [];
                var amountTimeValues = [];
                var pointValues = [];
                var amountpointsValues = [];
                for (var i = 0; i < jsonResponse.quizPreguntas.length; i++) {
                    console.log(jsonResponse.quizPreguntas[i].image);
                    idNewQuestionID = makeid(5);
                    opcionA = makeid(5);
                    opcionB = makeid(5);
                    opcionC = makeid(5);
                    opcionD = makeid(5);
                    idtime = makeid(5);
                    timeValues.push(idtime);
                    amountTime = makeid(5);
                    amountTimeValues.push(amountTime);
                    idpoint = makeid(5);
                    pointValues.push(idpoint);
                    amountPoint = makeid(5);
                    amountpointsValues.push(amountPoint);
                    var str = '<div class="fadeImage mySlides" style="display: block;">' +
                            '                                    <div class="numbertext"><strong class="numberOfSlides" style="float: left;">1 / 1</strong></div>' +
                            '                                   <div class="form">' +
                            '                                      <div class="row">' +
                            '                                         <textarea class="form-control-lg form-control textAreaAutoReziseNuevo questionInput" id="' + idNewQuestionID + '" rows="1" maxlength="120" minlength="1" type="text" required="true" style="display: inline-block; width: 100%; height: 107px; background-color: rgb(228, 226, 226); font-size: 2vw; overflow: hidden; text-align: center; font-weight: bold; color: black; border: 1px solid;" onblur="inputQuestions(this)" autofocus="">' + jsonResponse.quizPreguntas[i].question + '</textarea>' +
                            '                                    </div>' +
                            '                                   <div class="row" style="margin-top: 5px;">' +
                            '                                      <div class="text-left d-inline-block col-sm-3 timeToRespond">' +
                            '                                         <h6>Set&nbsp;the&nbsp;time&nbsp;to&nbsp;respond<br></h6>' +
                            '                                        <div class="text-center row">' +
                            '                                           <div class="col-sm-1 slidevertical">' +
                            '                                              <div id="' + idtime + '" class="slidevertical ui-slider ui-corner-all ui-slider-vertical ui-widget ui-widget-content" style="height: 130px;left: 50%;"><div class="ui-slider-range ui-corner-all ui-widget-header ui-slider-range-min" style="height: 0%;"></div><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default" style="bottom: 0%;"></span></div>' +
                            '                                         </div>' +
                            '                                        <div class="col-sm-3 slidevertical">' +
                            '                                           <div class="circle" style="display: inline-block;"><span id="' + amountTime + '" class="amountTime" style="color: white;font-size: 40px;font-weight: bold;line-height: 120px;">20s</span></div>' +
                            '                                      </div>' +
                            '                                 </div>' +
                            '                            </div>' +
                            '                           <div class="col-sm-6">' +
                            '                              <div class="drop-region" style="display: inline-block;border-style: dotted;width: 441px;" onclick="clickDropRegion(this)">' +
                            '                                 <div class="drop-message"><strong>Drag&nbsp;&amp;&nbsp;Drop&nbsp;image&nbsp;or&nbsp;click&nbsp;to&nbsp;upload<br></strong></div>' +
                            '                                <div class="image-preview-question"></div>' +
                            '                           </div>' +
                            '                      </div>' +
                            '                     <div class="text-right col-sm-3">' +
                            '                        <h6>Set&nbsp;the&nbsp;points&nbsp;of&nbsp;the&nbsp;question<br></h6>' +
                            '                       <div class="row">' +
                            '                          <div class="col-sm-10">' +
                            '                             <div class="circlePoints" style="display: inline-block;"><span id="' + amountPoint + '" class="amountPoints" style="color: white;font-size: 40px;font-weight: bold;line-height: 120px;">10pts</span></div>' +
                            '                        </div>' +
                            '                       <div class="col-sm-1">' +
                            '                          <div id="' + idpoint + '" style="height: 130px;left: 50%;" class="ui-slider ui-corner-all ui-slider-vertical ui-widget ui-widget-content"><div class="ui-slider-range ui-corner-all ui-widget-header ui-slider-range-min" style="height: 0%;"></div><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default" style="bottom: 0%;"></span></div>' +
                            '                     </div>' +
                            '                </div>' +
                            '           </div>' +
                            '      </div>' +
                            '                                        <h5 style="margin-bottom: 0px;">Options<br></h5>' +
                            '                                       <div class="form-row">' +
                            '                                          <div class="col-md-6 mb-6">' +
                            '                                             <div class="row">' +
                            '                                                <div class="col-md-10">' +
                            '                                                   <div class="row rowA" style="background-color: #990000;' +
                            '                                                       border: 1px solid;' +
                            '                                                      padding: 10px;' +
                            '                                                     box-shadow: 3px 4px #888888;' +
                            '                                                    color: white;"> ' +
                            '                                                 <div class="col-sm-2">' +
                            '                                                      <div style="margin: 0 auto;">' +
                            '                                                         <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
                            '                                                              width: 60px;' +
                            '                                                             border-radius: 60%;' +
                            '                                                            display: inline-block;' +
                            '                                                           font-size: 2vw;' +
                            '                                                          background-color: #292929;' +
                            '                                                         color: white;">' +
                            '                                                      <span style="margin:auto; height: 96%;">A</span>    ' +
                            '                                                 </span>' +
                            '                                            </div>' +
                            '                                       </div>' +
                            '                                      <div class="col-sm-10">' +
                            '                                         <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionAText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(153, 0, 0); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1">' + jsonResponse.quizPreguntas[i].opcionA + '</textarea>' +
                            '                                    </div>' +
                            '                               </div>' +
                            '                                                   </div>' +
                            '                                                  <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
                            '                                                     <div class="radio-toolbar"><input type="checkbox" id="' + opcionA + '" class="optionAcheckBox" onclick="checkBoxClick(this)"><label for="' + opcionA + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
                            '                                                </div>' +
                            '                                           </div>' +
                            '                                      </div>' +
                            '                                     <div class="col-md-6 mb-6">' +
                            '                                        <div class="row rowB">' +
                            '                                           <div class="col-md-10">' +
                            '                                              <div class="row" style="background-color: #86592d;' +
                            '                                                  border: 1px solid;' +
                            '                                                 padding: 10px;' +
                            '                                                box-shadow: 3px 4px #888888;' +
                            '                                               color: white;"> ' +
                            '                                             <div class="col-sm-2">' +
                            '                                                <div style="margin: 0 auto;">' +
                            '                                                   <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
                            '                                                        width: 60px;' +
                            '                                                       border-radius: 60%;' +
                            '                                                      display: inline-block;' +
                            '                                                     font-size: 2vw;' +
                            '                                                    background-color: #292929;' +
                            '                                                   color: white;">' +
                            '                                                <span style="margin:auto; height: 96%;">B</span>    ' +
                            '                                           </span>' +
                            '                                      </div>' +
                            '                                 </div>' +
                            '                                <div class="col-sm-10">' +
                            '                                   <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionBText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(134, 89, 45); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1">' + jsonResponse.quizPreguntas[i].opcionB + '</textarea>' +
                            '                              </div>' +
                            '                         </div>' +
                            '                    </div>' +
                            '                   <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
                            '                      <div class="radio-toolbar"><input type="checkbox" id="' + opcionB + '" class="optionBcheckBox" onclick="checkBoxClick(this)"><label for="' + opcionB + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
                            '                 </div>' +
                            '            </div>' +
                            '       </div>' +
                            '  </div>' +
                            ' <div class="form-row" style="margin-top: 10px;">' +
                            '                                            <div class="col-md-6 mb-6">' +
                            '                                               <div class="row rowC">' +
                            '                                                  <div class="col-md-10">' +
                            '                                                     <div class="row" style="background-color: #003380;' +
                            '                                                         border: 1px solid;' +
                            '                                                        padding: 10px;' +
                            '                                                       box-shadow: 3px 4px #888888;' +
                            '                                                      color: white;"> ' +
                            '                                                    <div class="col-sm-2">' +
                            '                                                       <div style="margin: 0 auto;">' +
                            '                                                          <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
                            '                                                               width: 60px;' +
                            '                                                              border-radius: 60%;' +
                            '                                                             display: inline-block;' +
                            '                                                            font-size: 2vw;' +
                            '                                                           background-color: #292929;' +
                            '                                                          color: white;">' +
                            '                                                       <span style="margin:auto; height: 96%;">C</span>    ' +
                            '                                                  </span>' +
                            '                                             </div>' +
                            '                                        </div>' +
                            '                                       <div class="col-sm-10">' +
                            '                                          <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionCText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(0, 51, 128); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1">' + jsonResponse.quizPreguntas[i].opcionC + '</textarea>' +
                            '                                                           </div>' +
                            '                                                      </div>' +
                            '                                                    </div>' +
                            '                                                   <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
                            '                                                      <div class="radio-toolbar"><input type="checkbox" class="optionCcheckBox" id="' + opcionC + '" onclick="checkBoxClick(this)"><label for="' + opcionC + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
                            '                                                 </div>' +
                            '                                            </div>' +
                            '                                       </div>' +
                            '                                      <div class="col-md-6 mb-6">' +
                            '                                         <div class="row rowD">' +
                            '                                            <div class="col-md-10">' +
                            '                                               <div class="row" style="background-color: #520066;' +
                            '                                                   border: 1px solid;' +
                            '                                                  padding: 10px;' +
                            '                                                 box-shadow: 3px 4px #888888;' +
                            '                                                color: white;"> ' +
                            '                                              <div class="col-sm-2">' +
                            '                                                 <div style="margin: 0 auto;">' +
                            '                                                    <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;' +
                            '                                                         width: 60px;' +
                            '                                                        border-radius: 60%;' +
                            '                                                       display: inline-block;' +
                            '                                                      font-size: 2vw;' +
                            '                                                     background-color: #292929;' +
                            '                                                    color: white;">' +
                            '                                                 <span style="margin:auto; height: 96%;">D</span>    ' +
                            '                                            </span>' +
                            '                                       </div>' +
                            '                                  </div>' +
                            '                                 <div class="col-sm-10">' +
                            '                                    <textarea class="form-control-lg form-control textAreaAutoReziseNuevo optionDText" rows="1" type="text" required="true" style="display: inline-block; width: 100%; height: 72px; border: none; background-color: rgb(82, 0, 102); font-size: 1vw; overflow: hidden; color: white; font-weight: bold;" onblur="inputQuestions(this)" maxlength="75" minlength="1">' + jsonResponse.quizPreguntas[i].opcionD + '</textarea>' +
                            '                               </div>' +
                            '                          </div>' +
                            '                     </div>' +
                            '                    <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">' +
                            '                       <div class="radio-toolbar"><input type="checkbox" id="' + opcionD + '" class="optionDcheckBox" onclick="checkBoxClick(this)"><label for="' + opcionD + '" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>' +
                            '                  </div>' +
                            '             </div>' +
                            '        </div>' +
                            '   </div>' +
                            '  </div>' +
                            '  </div>';
                    slideShowContainer.insertAdjacentHTML('afterbegin', str);
                    var dotsSlider = document.getElementById('dotsSlider');
                    var nuevoDot = dotsSlider.children.length + 1;
                    var dots = '<span class="dot" onclick="currentSlide(' + nuevoDot + ')"></span>';
                    dotsSlider.insertAdjacentHTML('afterbegin', dots);
                    if (jsonResponse.quizPreguntas[i].respuestaA === true) {
                        document.getElementById(opcionA).click();
                    }
                    if (jsonResponse.quizPreguntas[i].respuestaB === true) {
                        document.getElementById(opcionB).click();
                    }
                    if (jsonResponse.quizPreguntas[i].respuestaC === true) {
                        document.getElementById(opcionC).click();
                    }
                    if (jsonResponse.quizPreguntas[i].respuestaD === true) {
                        document.getElementById(opcionD).click();
                    }

                    initNewTextArea(idNewQuestionID);
                    initNewTextArea(opcionA);
                    initNewTextArea(opcionB);
                    initNewTextArea(opcionC);
                    initNewTextArea(opcionD);
                }

                var numberOfSlides = document.getElementsByClassName('numberOfSlides');
                for (var i = numberOfSlides.length - 1; i >= 0; i--) {
                    numberOfSlides[i].innerHTML = ((numberOfSlides.length) - i) + " / " + numberOfSlides.length;
                }
                var dropRegionClassses = document.getElementsByClassName('drop-region');
                for (var i = 0; i < dropRegionClassses.length; i++) {
                    dropRegionClassses[i].addEventListener('dragenter', preventDefault, false);
                    dropRegionClassses[i].addEventListener('dragleave', preventDefault, false);
                    dropRegionClassses[i].addEventListener('dragover', preventDefault, false);
                    dropRegionClassses[i].addEventListener('drop', preventDefault, false);
                    dropRegionClassses[i].addEventListener('drop', handleDrop, false);
                }

                myLoop(jsonResponse.quizPreguntas.length, timeValues, amountTimeValues, pointValues, amountpointsValues);
                let slideShowContainerAdd = document.getElementById('slideShowContainer');
                if (slideShowContainerAdd.children.length === 1) {
                    plusSlides(-1);
                } else {
                    plusSlides(slideShowContainerAdd.children.length);
                }
            }
        } else {
            console.log("Error, no existe la llave quizPreguntas");
        }
    }
}

var count = 0;
function myLoop(lenght, timeValues, amountTimeValues, pointValues, amountpointsValues) {
    $(function () {
        var actualidPoint = pointValues[count];
        var actualamountPoint = amountpointsValues[count];
        $("#" + actualidPoint).slider({
            orientation: "vertical",
            range: "min",
            min: 10,
            max: 100,
            step: 10,
            slide: function (event, ui) {
                createCookieObjectPreguntas();
                $("#" + actualamountPoint).html(ui.value + "pts");

            }
        });
        $("#" + actualamountPoint).html($("#" + actualidPoint).slider("value") + "pts");
        document.getElementById(actualidPoint).children[2].style = "background: green;";
    });
    $(function () {
        var actualidTime = timeValues[count];
        var actualAmountTime = amountTimeValues[count];
        $("#" + actualidTime).slider({
            orientation: "vertical",
            range: "min",
            min: 20,
            max: 300,
            step: 20,
            slide: function (event, ui) {
                createCookieObjectPreguntas();
                $("#" + actualAmountTime).html(ui.value + "s");

            }
        });
        $("#" + actualAmountTime).html($("#" + actualidTime).slider("value") + "s");
        document.getElementById(actualidTime).children[2].style = "background: blue;";
    });
    setTimeout(function () {
        count++;
        if (count < lenght) {
            myLoop(lenght, timeValues, amountTimeValues, pointValues, amountpointsValues);
        } else {
            count = 0;
        }
    }, 3000);
}
function myLoopCookie(lenght, timeValues, amountTimeValues, pointValues, amountpointsValues, slideTimeValue, slidePointsValue) {
    $(function () {
        var actualidPoint = pointValues[count];
        console.log("actualSlidePointsValue");
        console.log(slidePointsValue);
        var actualSlidePointsValue = slidePointsValue[count];
        if (actualSlidePointsValue.length === 0) {
            actualSlidePointsValue = "0%";
        }
        var actualamountPoint = amountpointsValues[count];
        $("#" + actualidPoint).slider({
            orientation: "vertical",
            range: "min",
            min: 10,
            max: 100,
            step: 10,
            slide: function (event, ui) {
                $("#" + actualamountPoint).html(ui.value + "pts");
                createCookieObjectPreguntas();
            }
        });
        //$("#" + actualamountPoint).html($("#" + actualidPoint).slider("value") + "pts");
        document.getElementById(actualidPoint).children[2].style = "height: " + actualSlidePointsValue + "; background: green;";
        document.getElementById(actualidPoint).children[1].style = "bottom: " + actualSlidePointsValue + ";";
    });
    $(function () {
        var actualidTime = timeValues[count];
        var actualAmountTime = amountTimeValues[count];
        var actualSlideTimeValue = slideTimeValue[count];
        if (actualSlideTimeValue.length === 0) {
            actualSlideTimeValue = "0%";
        }
        $("#" + actualidTime).slider({
            orientation: "vertical",
            range: "min",
            min: 20,
            max: 300,
            step: 20,
            slide: function (event, ui) {
                $("#" + actualAmountTime).html(ui.value + "s");
                createCookieObjectPreguntas();
            }
        });
        //$("#" + actualAmountTime).html($("#" + actualidTime).slider("value") + "s");
        document.getElementById(actualidTime).children[2].style = "height: " + actualSlideTimeValue + "; background: blue;";
        document.getElementById(actualidTime).children[1].style = "bottom: " + actualSlideTimeValue + "; ";
    });
    setTimeout(function () {
        count++;
        if (count < lenght) {
            myLoopCookie(lenght, timeValues, amountTimeValues, pointValues, amountpointsValues, slideTimeValue, slidePointsValue);
        } else {
            count = 0;
        }
    }, 3000);
}

// Add the following code if you want the name of the file appear on select
$(".custom-file-input").on("change", function () {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
function fakeButton(_this) {
    console.log("fakeButton");
    quiz.isFake = _this.checked;
    console.log(quiz);
}


function modifyText(_this) {
    console.log(_this);
    var value = _this.value;
    console.log(value);
    var phraseElementactual = _this.parentElement.children[1].innerHTML;
    console.log(phraseElementactual);
    _this.parentElement.children[1].innerHTML = value;
}

init();
var reziseInt = 0;
function initNewTextArea(idNewTextArea) {
    var observe;
    if (window.attachEvent) {
        console.log("window.attachEvent");
        observe = function (element, event, handler) {
            element.attachEvent('on' + event, handler);
        };
    } else {
        observe = function (element, event, handler) {
            element.addEventListener(event, handler, false);
        };
    }
    var text = document.getElementById(idNewTextArea);
    function resize() {
        text.style.height = 'auto';
        text.style.height = text.scrollHeight + 'px';
    }
    /* 0-timeout to get the already changed text */
    function delayedResize() {
        console.log("delayedResize");
        window.setTimeout(resize, 0);
    }
    observe(text, 'change', resize);
    observe(text, 'cut', delayedResize);
    observe(text, 'paste', delayedResize);
    observe(text, 'drop', delayedResize);
    observe(text, 'keydown', delayedResize);
    text.focus();
    text.select();
}


function init() {
    var observe;
    if (window.attachEvent) {
        console.log("window.attachEvent");
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
        console.log("delayedResize");
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




function create_cookie(name, value) {
    document.cookie = "" + name + "=" + value + "; expires=Thu, 18 Dec 2030 12:00:00 UTC; path=/";
}

function read_cookie(name) {
    var result = document.cookie.match(new RegExp(name + '=([^;]+)'));
    result && (result = JSON.parse(result[1]));
    return result;
}

function delete_cookie(name) {
    document.cookie = name, '=; expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/; domain=.', window.location.host.toString();
}