var overlayObjectPreview = {
    "questionOverlay": "",
    "timeOverlay": "",
    "imageOverlay": "",
    "pointsOverlay": "",
    "optionAOverLay": "",
    "optionBOverLay": "",
    "optionCOverLay": "",
    "optionDOverLay": ""
}

function on() {
    if (validateCurrentQuizCompleteMinValues()) {
        //FORMATEAMOS LA PREGUNTA EN EL PREVIEW
        document.getElementById('questionHeadingOverlay').innerHTML = "";
        document.getElementById('questionHeadingOverlay').innerHTML = overlayObjectPreview.questionOverlay;
        //FORMATEAMOS EL TIEMPO EN EL PREVIEW
        document.getElementById('amountTimeOverlay').innerHTML = "";
        document.getElementById('amountTimeOverlay').innerHTML = overlayObjectPreview.timeOverlay;
        //FORMATEAMOS LA IMAGEN EN EL PREVIEW
        document.getElementById('imageOverlay').src = "";
        document.getElementById('imageOverlay').src = overlayObjectPreview.imageOverlay;
        //FORMATEAMOS LA LOS PUNTOS EN EL PREVIEW
        document.getElementById('amount-pointsOverlay').innerHTML = "";
        document.getElementById('amount-pointsOverlay').innerHTML = overlayObjectPreview.pointsOverlay;
        //FORMATEAMOS OPCION A EN EL PREVIEW
        document.getElementById('optionAOverlay').innerHTML = "";
        document.getElementById('optionAOverlay').innerHTML = overlayObjectPreview.optionAOverLay;
        //FORMATEAMOS OPCION B EN EL PREVIEW
        document.getElementById('optionBOverlay').innerHTML = "";
        document.getElementById('optionBOverlay').innerHTML = overlayObjectPreview.optionBOverLay;
        //FORMATEAMOS OPCION C EN EL PREVIEW
        document.getElementById('optionCOverlay').innerHTML = "";
        document.getElementById('optionCOverlay').innerHTML = overlayObjectPreview.optionCOverLay;
        //FORMATEAMOS OPCION D EN EL PREVIEW
        document.getElementById('optionDOverlay').innerHTML = "";
        document.getElementById('optionDOverlay').innerHTML = overlayObjectPreview.optionDOverLay;
        //MOSTRAMOS EL PREVIEW
        document.getElementById("overlay").style.display = "block";
    }

}

function off() {
    document.getElementById("overlay").style.display = "none";
}

function validateCurrentQuizCompleteMinValues() {

    let slideShowContainerOverLay = document.getElementById('slideShowContainer');
    //console.log(slideShowContainerOverLay);
    for (let index = 0; index < slideShowContainerOverLay.children.length; index++) {
        if (slideShowContainerOverLay.children[index].style.display === "block") {
            //console.log("Question");
            //console.log(slideShowContainerOverLay.children[index].children[1].children[0].children[0]);
            let questionOverlay = slideShowContainerOverLay.children[index].children[1].children[0].children[0].value;
            //console.log(questionOverlay);
            if (questionOverlay.length === 0) {
                inputQuestions(slideShowContainerOverLay.children[index].children[1].children[0].children[0]);
                return false;
            }
            //ASIGNAMOS LA PREGUNTA AL OBJETO
            overlayObjectPreview.questionOverlay = questionOverlay;

            //console.log("Time");
            //console.log(slideShowContainerOverLay.children[index].children[1].children[1].children[0].children[1].children[1].children[0].children[0].innerHTML);
            let timeOverlay = slideShowContainerOverLay.children[index].children[1].children[1].children[0].children[1].children[1].children[0].children[0].innerHTML;
            //console.log(timeOverlay);
            //ASIGNAMOS EL TIMEPO AL OBJETO
            overlayObjectPreview.timeOverlay = timeOverlay;

            //console.log("Image");
            //console.log(slideShowContainerOverLay.children[index].children[1].children[1].children[1].children[0].children[1]);
            let imgaePreviewQuestion = slideShowContainerOverLay.children[index].children[1].children[1].children[1].children[0].children[1];
            if (imgaePreviewQuestion.children.length === 0) {
                //console.log("Sin IMAGEN");
                //SRC = avaya image.png
                //ASIGNAMOS UNA IMAGEN POR DEFAULT
                overlayObjectPreview.imageOverlay = "Home/Transparent.png";
            } else {
                //console.log("Con imagen");
                //console.log(imgaePreviewQuestion.children[0].children[0].src);
                var imageB64 = imgaePreviewQuestion.children[0].children[0].src;
                //ASIGNAMOS LA IMAGEN SUBIDA AL OBJETO.
                overlayObjectPreview.imageOverlay = imageB64;
            }

            //console.log("Points");
            //console.log(slideShowContainerOverLay.children[index].children[1].children[1].children[2].children[1].children[0].children[0].children[0].innerHTML);
            let pointsOverlay = slideShowContainerOverLay.children[index].children[1].children[1].children[2].children[1].children[0].children[0].children[0].innerHTML;
            //ASIGNAMOS LOS PUNTOS AL OBJETO
            overlayObjectPreview.pointsOverlay = pointsOverlay;
            //console.log("OptionA");
            //console.log(slideShowContainerOverLay.children[index].children[1].children[3].children[0].children[0].children[0].children[0].children[1].children[0].value);
            let optionA = slideShowContainerOverLay.children[index].children[1].children[3].children[0].children[0].children[0].children[0].children[1].children[0].value;
            if (optionA.length === 0) {
                inputQuestions(slideShowContainerOverLay.children[index].children[1].children[3].children[0].children[0].children[0].children[0].children[1].children[0]);
                return false;
            }
            //ASIGNAMOS EL VALOR DE OPCION A AL OBJETO;
            overlayObjectPreview.optionAOverLay = optionA;
            //console.log("OptionB");
            //console.log(slideShowContainerOverLay.children[index].children[1].children[3].children[1].children[0].children[0].children[0].children[1].children[0].value);
            let optionB = slideShowContainerOverLay.children[index].children[1].children[3].children[1].children[0].children[0].children[0].children[1].children[0].value;
            //console.log(optionB);
            if (optionB.length === 0) {
                inputQuestions(slideShowContainerOverLay.children[index].children[1].children[3].children[1].children[0].children[0].children[0].children[1].children[0]);
                return false;
            }
            //ASIGNAMOS EL VALOR DE OPCION B AL OBJETO;
            overlayObjectPreview.optionBOverLay = optionB;
            //DIV INFERIROR
            //console.log("opton C");
            //console.log(slideShowContainerOverLay.children[index].children[1].children[4].children[0].children[0].children[0].children[0].children[1].children[0].value);
            let optionC = slideShowContainerOverLay.children[index].children[1].children[4].children[0].children[0].children[0].children[0].children[1].children[0].value;
            if (optionC.length === 0) {
                inputQuestions(slideShowContainerOverLay.children[index].children[1].children[4].children[0].children[0].children[0].children[0].children[1].children[0]);
                return false;
            }
            //ASIGNAMOS EL VALOR DE OPCION C AL OBJETO;
            overlayObjectPreview.optionCOverLay = optionC;
            //console.log("opton D");
            //console.log(slideShowContainerOverLay.children[index].children[1].children[4].children[1].children[0].children[0].children[0].children[1].children[0].value);
            let optionD = slideShowContainerOverLay.children[index].children[1].children[4].children[1].children[0].children[0].children[0].children[1].children[0].value;
            if (optionD.length === 0) {
                inputQuestions(slideShowContainerOverLay.children[index].children[1].children[4].children[1].children[0].children[0].children[0].children[1].children[0]);
                return false;
            }
            //ASIGNAMOS EL VALOR DE OPCION D AL OBJETO;
            overlayObjectPreview.optionDOverLay = optionD;
            break;
        }
    }
    console.log(overlayObjectPreview);
    return true;
}