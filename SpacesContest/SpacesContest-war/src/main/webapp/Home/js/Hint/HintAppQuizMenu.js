/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


console.log("HintAppQuizMenu");
continueTour();

function continueTour() {
    var json = read_cookie("tourQuiz");
    if (json !== null) {
        if (json.estatus === "ON" && json.step === "TWO") {
            secondPartTour();
        }
    }
}

function secondPartTour() {
    var enjoyhint_instance = new EnjoyHint({
        onStart: function () {
            document.getElementsByClassName('enjoyhint')[0].style.zIndex = "2000";
            document.getElementById('createEvent').classList.remove('fade');
            document.getElementById('manageEvents').classList.remove('fade');
            document.getElementById('eventCreated').classList.remove('fade');
        },
        onEnd: function () {
            document.getElementById('createEvent').classList.add('fade');
            document.getElementById('manageEvents').classList.add('fade');
            document.getElementById('eventCreated').classList.add('fade');
            var jsonTour = {
                "estatus": "ON",
                "step": "THREE"
            };
            create_cookie("tourQuiz", JSON.stringify(jsonTour));
        },
        onSkip: function () {
            document.getElementById('createEvent').classList.add('fade');
            document.getElementById('manageEvents').classList.add('fade');
            document.getElementById('eventCreated').classList.add('fade');
            var jsonTour = read_cookie("tourQuiz");
            if (jsonTour !== null) {
                //delete_cookie("tourQuiz", JSON.stringify(jsonTour));
                document.cookie = "tourQuiz=" + JSON.stringify(jsonTour) + "; expires=Thu, 18 Dec 1970 12:00:00 UTC; path=/";
            }
        }
    });

    var enjoyhint_script_steps = [{
            'next .QuizesGroup': 'Now you will see that the created quiz is in the tray of quizes. You need to create an event. Press Next to continue'
        }, {
            'click .CrearEvento': 'Press the Create Event button.'
        }, {
            'next .CreateEventModal': 'The section enter the name of the event has been opened.'
        }, {
            'next .inputEventName': 'Now you must enter the name of the event the same one that will be used as the name for the private space that will be created in Avaya Spaces.'
        }, {
            'click .saveCrearEvento': 'Press save to confirm the creation of the event'
        },
        {
            selector: '.EventCreatedModal', //jquery selector
            event: 'click',
            description: 'In this section the creation of the event has been confirmed, by pressing this button you will join the private space as administrator. Remember that you must be logged in with your Avaya Spaces account.',
            timeout: 3000
        },
        {
            'click .CerrarModalDeAvayaSpaces': 'Press close to continue the tour.'
        }, {
            'click .manageEventsButon': 'Press Manage Events to observe the created events.'
        }, {
            'next .manageEventsModalContent': 'In this section you will see all the events you have created.'
        }, {
            'click .manageEventsModalBody': 'Press Register Page to get the registration page by event. Each user who wants to participate in the event will have to register.'
        }, {
            'next .alert-success': 'The registration address has been copied to your clipboard, open a new tab and register for your event.'
        }, {
            'click .closeModalEvent': 'Once you have registered for the event, close this section to assign the event to a quiz in the Quizi tray.'
        }, {
            'next .QuizesGroup': 'From the quiz tray press "No associated event" and from the list select the created event.'
        }, {
            'click .btnToPlay': 'Select play to continue play mode.'
        }
    ];

    enjoyhint_instance.set(enjoyhint_script_steps);

    enjoyhint_instance.run();

}


function virtualTour() {
    var enjoyhint_instance = new EnjoyHint({
        onStart: function () {
            document.getElementsByClassName('enjoyhint')[0].style.zIndex = "2000";
            document.getElementById('createQuizModal').classList.remove('fade');
        },
        onEnd: function () {
            document.getElementById('createQuizModal').classList.add('fade');
            var jsonTour = {
                "estatus": "ON",
                "step": "ONE"
            };
            create_cookie("tourQuiz", JSON.stringify(jsonTour));

        },
        onSkip: function () {
            document.getElementById('createQuizModal').classList.add('fade');
            var jsonTour = read_cookie("tourQuiz");
            if (jsonTour !== null) {
                //delete_cookie("tourQuiz", JSON.stringify(jsonTour));
                document.cookie = "tourQuiz=" + JSON.stringify(jsonTour) + "; expires=Thu, 18 Dec 1970 12:00:00 UTC; path=/";
            }
        }


    });
    var enjoyhint_script_steps = [{
            'click .CreateQuizBTN': 'The first necessary step is to create a Quiz, so you must click on "Create Quiz"'
        },
        {
            'next .modalCreateQuiz': 'Now you will see the form to create a quiz. Press next to coninue.'
        },
        {
            'next .inputQuizName': 'Enter the name you want to baptize the quiz and press next to coninue.'
        },
        {
            'click .SaveBTN': "Pressing save will redirect you to the form to enter questions, photos and more to the quiz."
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

function delete_cookie(name) {
    document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}