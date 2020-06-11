/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

initTour();



function initTour() {
    var json = read_cookie("tourQuiz");
    if (json !== null) {
        if (json.estatus === "ON") {
            startTour();
        }
    }

}

function startTour() {
    var enjoyhint_instance = new EnjoyHint({

        onStart: function () {
            document.getElementsByClassName('enjoyhint')[0].style.zIndex = "2000";
            document.getElementById('exampleModal').classList.remove('fade');
            document.getElementsByClassName('createQuestion')[0].setAttribute('onclick', "");
            document.getElementsByClassName('uploadFile')[0].setAttribute('onclick', "");
            document.getElementsByClassName('fullScreen')[0].setAttribute('onclick', "");
            document.getElementsByClassName('deleteQuiz')[0].setAttribute('onclick', "");
        },
        onEnd: function () {
            document.getElementsByClassName('createQuestion')[0].setAttribute('onclick', "addQuestion()");
            document.getElementsByClassName('uploadFile')[0].setAttribute('onclick', "uploadQuestions()");
            document.getElementsByClassName('fullScreen')[0].setAttribute('onclick', "on()");
            document.getElementsByClassName('deleteQuiz')[0].setAttribute('onclick', "deleteForm()");
            document.getElementById('exampleModal').classList.add('fade');
            var jsonTour = {
                "estatus": "ON",
                "step": "TWO"
            };
            create_cookie("tourQuiz", JSON.stringify(jsonTour));

        },
        onSkip: function () {
            document.getElementById('exampleModal').classList.add('fade');
            document.getElementsByClassName('createQuestion')[0].setAttribute('onclick', "addQuestion()");
            document.getElementsByClassName('uploadFile')[0].setAttribute('onclick', "uploadQuestions()");
            document.getElementsByClassName('fullScreen')[0].setAttribute('onclick', "on()");
            document.getElementsByClassName('deleteQuiz')[0].setAttribute('onclick', "deleteForm()");
            var jsonTour = read_cookie("tourQuiz");
            if (jsonTour !== null) {
                //delete_cookie("tourQuiz", JSON.stringify(jsonTour));
                document.cookie = "tourQuiz=" + JSON.stringify(jsonTour) + "; expires=Thu, 18 Dec 1970 12:00:00 UTC; path=/";
            }
        }
    });
    var enjoyhint_script_steps = [{
            'click .nameAndImage': 'Press the icon to modify the name of the Quiz and attach a meaningful image.'
        },
        {
            'next .inputNameQuiz': 'Here you can modify the name of the Quiz, in case you need it.'
        },
        {
            'next .dropRegionImageQuiz': 'Drag an image in this area or click to open your files and include a header image to the Quiz.'
        },
        {
            'click .preguntasCreation': 'Tap the icon to create questions to enable the form.'
        },
        {
            'next .slideshow-container': 'This section creates the question you want to include in the quiz. fill the fields to continue the guide'
        },
        {
            'next .createQuestion': 'Every time you want to include a new empty question to the quiz select this option.'
        },
        {
            'next .uploadFile': 'In case you have the quiz in the Excel format this button will allow you to upload the file to pre-fill the question forms.'
        },
        {
            'next .fullScreen': 'When you finish filling out the question you will be able to watch it in play mode. Remember to click anywhere to exit play mode.'
        },
        {
            'next .deleteQuiz': 'Be careful with this button, every time you press it you will delete the question from the questions section.'
        },
        {
            'click .quizValidation': 'Select Validate Data to check that all the minimum fields to create a question are entered.'
        },
        {
            'next .progressBar': 'The progress bar validate that all the data is complete, in case any field is missing one of the sections above will be marked as red'
        },
        {
            'next .colorCustom': 'Click the gray circle to change the color of the letters.'
        },
        {
            'next .fakeQuizBTNOption': 'The fake Quiz option allows you to create a fake quiz, where the results of this quiz will not be taken into account when closing the event and determining the winners per event.'
        },
        {
            'next .colorCustomBack': 'Click the gray circle to change the background color of the label corresponding to the Quiz. With which you can quickly identify the Quiz.'
        },
        {
            'next .photo-card': 'It is a preview of the card corresponding to the Quiz, which you will see in the initial menu in the following steps.'
        },
        {
            'next .ui-sortable': 'In this section you can take and reposition each question to determine the order in which they will be displayed in Play mode.'
        },
        {
            'click .createQuizBtn': "Press this button as long as you are sure you want to create the Quiz and continue with the tour."
        },
        {
            selector: '.modalCreatedQuiz', //jquery selector
            event: 'click',
            description: 'It will show that the Quiz has been successfully created. Press Return to menu to validate that the created quiz is in the quiz tray.',
            timeout: 3000
        }
    ];

    enjoyhint_instance.set(enjoyhint_script_steps);

    enjoyhint_instance.run();
    document.getElementById('kinetic_container').style.width = "0";
}


function create_cookie(name, value) {
    document.cookie = "" + name + "=" + value + "; expires=Thu, 18 Dec 2030 12:00:00 UTC; path=/";
}

function read_cookie(name) {
    var result = document.cookie.match(new RegExp(name + '=([^;]+)'));
    result && (result = JSON.parse(result[1]));
    return result;
}

function delete_cookie(name, value) {
    console.log("DELETE QUIZ COOKIE");
    document.cookie = "" + name + "=" + value + "; expires=Thu, 18 Dec 1970 12:00:00 UTC; path=/";
}