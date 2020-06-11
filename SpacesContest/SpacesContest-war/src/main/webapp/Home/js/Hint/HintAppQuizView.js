/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
console.log("HintAppQuizView");
continueTour();
function continueTour() {
    var json = read_cookie("tourQuiz");
    if (json !== null) {
        if (json.estatus === "ON" && json.step === "THREE") {
            thirdPartTour();
        }
    }

}
function thirdPartTour() {
    var enjoyhint_instance = new EnjoyHint({
        onStart: function () {
        },
        onEnd: function () {
            var jsonTour = read_cookie("tourQuiz");
            if (jsonTour !== null) {
                //delete_cookie("tourQuiz", JSON.stringify(jsonTour));
                document.cookie = "tourQuiz=" + JSON.stringify(jsonTour) + "; expires=Thu, 18 Dec 1970 12:00:00 UTC; path=/";
            }
        },
        onSkip: function () {
            var jsonTour = read_cookie("tourQuiz");
            if (jsonTour !== null) {
                //delete_cookie("tourQuiz", JSON.stringify(jsonTour));
                document.cookie = "tourQuiz=" + JSON.stringify(jsonTour) + "; expires=Thu, 18 Dec 1970 12:00:00 UTC; path=/";
            }
        }
    });
//    var enjoyhint_script_steps = [{
//            'next .navBarToTour': 'En el modo play todas las herramientas están en la parte superior, presiona next para continuar'
//        },
//        {
//            'next .QuizNameAndEvento': 'Se muestra el nombre del Quiz, y nombre del evento'
//        },
//        {
//            'click .startEnrollBTN': 'El botón enroll nos sirve para que las personas registradas al evento puedan participar en este Quiz. Abre tu aplicación de Avaya Spacces preparáte para mandar un mensaje directo al Bot "avaya.notificaciones"'
//        },
//        {
//            'next .llaveDelConcurso': 'Escribe la clave del concurso como mensaje directo al Bot "avaya.notficaciones", una vez enviado el mensaje te deberá contestar de inmediato, en caso que no te conteste, revisa que te hayas registrado al evento con tu cuenta de Avaya Sapces'
//        }, {
//            'click .EnrollDropDown': 'Presiona el botón para continuar'
//        }, {
//            'next .MenuDeEnrolamiento': 'Una vez que terminen los dos minutos de enrolamiento podrás comprobar cuantas personas se enrolaron al Quiz y podrás enviarle una confirmación de enrolamiento.'
//        }, {
//            'click .OpenSlidesMenu': 'Ahora abriremos la sección de preguntas, presiona el botón para continuar'
//        }, {
//            'next .SlidesBar': 'Esta sección te permitirá observar todas las preguntas en orden, podrás observar el estatus actual por pregunta y seleccionar la que deseas mostar en pantalla'
//        }, {
//            'click .OpenSlidesMenu': 'Cerraremos sección de preguntas, presiona el botón para continuar'
//        }, {
//            'click .RocketLauncher': 'Es momento de lanzar nuestra primer pregunta, presiona el ícono de la nave espacial para continuar'
//        }, {
//            'next .TimeCircle': 'El tiempo ha comenzado a descendeer, contesta antes que se termine el tiempo para que se tome encuenta tu respuesta'
//        }, {
//            'next .PointsCircle': 'Valor en puntos de la pregunta.'
//        }, {
//            'next .TextQuiz': 'La pregunta que ha sido lanzada debe de coincidir con el mensaje que recibió. Presione next cuando el tiempo haya teminado'
//        }, {
//            'next .CanvasContainer': 'Una vez terminado el tiempo para responder se muestra una gráfica de pastel con el porcentaje por opción que los participantes contestaron. Presione next para continuar'
//        }, {
//            'click .EyeResponses': 'Al terminar el tiempo para responder podrá ver la respuesta correcta presionando el icóno del ojo, presione el ícono para continuar'
//        }, {
//            'next .botonesDeNavegacion': 'En esta sección podrá navegar entre preguntas, siempre llendo hacia delante o hacia atras, el número de enmedio se actualizará dependiendo del slide en el que se encuentra'
//        }, {
//            'click .closeQuiz': 'Seleccone cerrar evento para ver el podium y los 3 primeros lugar'
//        }, {
//            selector: '.cerrarPodium', //jquery selector
//            event: 'click',
//            description: 'Presione el icono de cerrar para continuar',
//            timeout: 10000
//        }, {
//            'click .abirrMenu': 'Seleccione la opción para regresar al Menu principal'
//        }, {
//            'click .backToMenu': 'Gracias por tomar este tour, para finalizar presione en Quiz Menu o en la opción de Skip.'
//        }
//    ];
    var enjoyhint_script_steps = [{
                        'next .navBarToTour': 'In play mode all tools are at the top, press next to continue'
                },
                {
                        'next .QuizNameAndEvento': 'The name of the Quiz is displayed, and the name of the event'
                },
                {
                        'click .startEnrollBTN': 'The enroll button is used so that people registered to the event can participate in this Quiz. Open your Avaya Spacces application, prepare to send a direct message to the Bot "avaya.notifications" '
                },
                {
                        'next .llaveDelConcurso': 'Write the contest key as a direct message to the Bot "avaya.notficaciones", once the message has been sent it should reply immediately, in case it does not answer, check that you have registered for the event with your Avaya Sapces account '
                }, {
                        'click .EnrollDropDown': 'Press the button to continue'
                }, {
                        'next .EnrollmentMenu': 'Once the two minutes of enrollment have finished you will be able to check how many people have enrolled in the Quiz and you can send them a confirmation of enrollment.'
                }, {
                        'click .OpenSlidesMenu': 'Now we will open the questions section, press the button to continue'
                }, {
                        'next .SlidesBar': 'This section will allow you to see all the questions in order, you will be able to see the current status per question and select the one you want to show on screen'
                }, {
                        'click .OpenSlidesMenu': 'We will close the questions section, press the button to continue'
                }, {
                        'click .RocketLauncher': 'Its time to launch our first question, press the spaceship icon to continue'
                }, {
                        'next .TimeCircle': 'The time has started to drop, answer before the time is up so that your answer is taken into account'
                }, {
                        'next .PointsCircle': 'Point value of the question.'
                }, {
                        'next .TextQuiz': 'The question that has been launched must match the message you received. Press next when the time is up '
                }, {
                        'next .CanvasContainer': 'Once the time to answer is over, a pie chart is shown with the percentage per option that the participants answered. Press next to continue '
                }, {
                        'click .EyeResponses': 'At the end of the time to answer you will be able to see the correct answer by pressing the eye icon, press the icon to continue'
                }, {
                        'next .Navigation buttons': 'In this section you can navigate between questions, always going forward or backward, the middle number will be updated depending on the slide you are on'
                }, {
                        'click .closeQuiz': 'Select to close the event to see the podium and the first 3 places'
                }, {
                        selector: '.closePodium', // jquery selector
                        event: 'click',
                        description: 'Press the close icon to continue',
                        timeout: 10000
                }, {
                        'click .abirrMenu': 'Select the option to return to the Main Menu'
                }, {
                        'click .backToMenu': 'Thank you for taking this tour, to finish press on Quiz Menu or on the Skip option.'
                }
        ];
    enjoyhint_instance.set(enjoyhint_script_steps);
    enjoyhint_instance.run();
}


function create_cookie(name, value) {
    document.cookie = "" + name + "=" + value + "; expires=Thu, 18 Dec 2030 12:00:00 UTC; path=/";
}

function read_cookie(name) {
    var result = document.cookie.match(new RegExp(name + '=([^;]+)'));
    result && (result = JSON.parse(result[1]));
    return result;
}
