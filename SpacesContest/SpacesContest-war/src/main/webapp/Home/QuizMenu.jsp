<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Quizes Menu</title>
        <link rel="shortcut icon" href="Home/img/Icon-Avaya.png"/>
        <link rel="stylesheet" href="Home/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Ubuntu">
        <link rel="stylesheet" href="Home/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="Home/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="Home/fonts/ionicons.min.css">
        <link rel="stylesheet" href="Home/fonts/line-awesome.min.css">
        <link rel="stylesheet" href="Home/fonts/fontawesome5-overrides.min.css">
        <link rel="stylesheet" href="Home/css/Article-Clean.css">
        <link rel="stylesheet" href="Home/css/dh-card-image-left-dark.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js">
        <link rel="stylesheet" href="Home/css/SearchCardsQuizes.css">
        <link rel="stylesheet" href="Home/css/sidebar.css">
        <link rel="stylesheet" href="Home/css/SideBarAvaya.css">
        <link rel="stylesheet" href="Home/css/jquery.enjoyhint.css">
    </head>
    <% String jsonRequest = (String) request.getAttribute("quizes");%>

    <body style="padding-left: 0px;">
        <div style="display: none;" id="jsonRequest"><%=jsonRequest%></div>
        <div class="loader loader-default" data-blink id="loaderDisplay"></div>
        <%@include file="Template/sidebar.jsp" %>
        <div class="container-fluid article-clean">
            <nav class="navbar navbar-light navbar-expand-md">
                <div class="container-fluid">
                    <p class="text-left d-xl-flex justify-content-xl-start align-items-xl-start" style="width: 254px;color: rgb(0,0,0);font-size: 15px;font-family: Lora, serif;margin: 13px;">Avaya CALA PoC Develpment Group</p>

                    <!-- Example single danger button -->
                    <div class="btn-group">
                        <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-hands-helping"></i>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" style="cursor: pointer;" onclick="virtualTour()">Take the virtual Guide</a>
                            <!--   <div class="dropdown-divider"></div>
                               <a class="dropdown-item" style="cursor: pointer;" disbled>Download Manual</a> -->
                        </div>
                    </div>
                </div>
            </nav>
            <div class="modal fade" role="dialog" tabindex="-1" id="createQuizModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content modalCreateQuiz">
                        <div class="modal-header">
                            <h4 class="modal-title">Create Quiz</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fas fa-window-close"></i></span></button></div>
                        <div class="modal-body">
                            <p>Enter the quiz name and press save</p>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa-question-circle" style="font-size: 24px;"></i>
                                    </span>
                                </div><input type="text" id="inputQuizName" class="inputQuizName" style="width: 417px;" required="" maxlength="50" minlength="1"></div>
                        </div>
                        <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Close</button>
                            <button class="btn btn-primary SaveBTN" id="createQuizSave" type="button">Save</button></div>
                    </div>
                </div>
            </div>
            <div class="modal fade" role="dialog" tabindex="-1" id="eventCreated">
                <div class="modal-dialog" role="document">
                    <div class="modal-content EventCreatedModal">
                        <div class="modal-header">
                            <h4 class="modal-title">Event created Successfully</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fas fa-window-close"></i></span></button></div>
                        <div class="modal-body">
                            <p>The event <span id="eventNameCreated"></span> was created successfully just like the space in Avaya Spaces.</p>
                            <span id="joinURL" style="display: none;" class="joinURLClass"></span>
                            <span id="registerPageLink" style="display: none;" class="registerPageLink"></span>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-light CerrarModalDeAvayaSpaces" type="button" data-dismiss="modal">Close</button>
                            <button class="btn btn-info openRegisterPage" onclick="openRegisterPage()" type="button" data-dismiss="modal">Open register page</button>
                            <button class="btn btn-success JoinAvayaSpaces" onclick="joinAvayaSpaces()" type="button">Go to the created space</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" role="dialog" tabindex="-1" id="createEvent">
                <div class="modal-dialog" role="document">
                    <div class="modal-content CreateEventModal">
                        <div class="modal-header">
                            <h4 class="modal-title">Create Event</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fas fa-window-close"></i></span></button></div>
                        <div class="modal-body">
                            <p>Enter the event name and press save to create a Space in Avaya Spaces</p>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fas fa-rocket" style="font-size: 24px; color: red;"></i>
                                    </span>
                                </div>
                                <input type="text" id="inputEventName" class="inputEventName" style="width: 417px;" required="" maxlength="50" minlength="1"></div>
                        </div>
                        <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal" id="createQuizModalClose">Close</button>
                            <button class="btn btn-primary saveCrearEvento" onclick="createEventRest()" type="button">Save</button></div>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="manageEvents" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content manageEventsModalContent" >
                        <div class="modal-header" style="text-align: center;">
                            <h5 class="modal-title" id="exampleModalLabel" style="font-weight: bold; width: 50%; margin: auto;">Manage Events</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body manageEventsModalBody" id="manageEventsModelBody">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary closeModalEvent" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- MODAL SET DESCRIPTION -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg" style="display: none;" id="descriptionEvent">Large modal</button>

            <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content" style="width: 150%; left: -150px;">
                        <div class="modal-header">
                            <h5 class="modal-title" id="idEventForRegisterPage" style="display: none;"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <p style="font-family: Arial; font-size: 18px; font-weight: 700; color: #147cc9; margin-bottom: 0px;">Summary</p>
                                    <hr style="margin: 2px;">
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <p style="margin-bottom: 0px;">Add up to 5 titles to the registration page.</p>
                                        </div>
                                        <div class="col-sm-2">
                                            <i title="Press to add a subtitle" class="fas fa-plus-circle" style="cursor: pointer;" onclick="agregarInputSubTitleEvent()"></i>
                                            <i title="Press to remove a subtitle" class="fas fa-minus-circle" style="cursor: pointer;" onclick="disminuirSubTitleEvent()"></i>
                                        </div>
                                    </div>
                                    <div id="inputSubTitles">
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <p style="margin-bottom: 0px;">Add the general description of the event.</p>
                                        </div>
                                    </div>
                                    <div>
                                        <textarea class="form-control" id="textAreaEvent" rows="3"></textarea>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="row">
                                        <div class="col-sm-9">
                                            <p style="font-family: Arial; font-size: 18px; font-weight: 700; color: #147cc9; margin-bottom: 0px;">Speakers</p>
                                        </div>
                                        <div class="col-sm3">
                                            <div class="btn-group btn-group-sm" role="group" aria-label="Basic example">
                                                <button type="button" class="btn btn-primary" onclick="createSpeakerBTN()">Create Speaker</button>
                                            </div>
                                        </div>
                                    </div>
                                    <hr style="margin: 2px;">
                                    <div class="form-group">
                                        <label for="exampleFormControlSelect2">Current speakers at the event.</label>
                                        <select multiple class="form-control" id="exampleFormControlSelect2">
                                        </select>
                                    </div>
                                    <div class="row">
                                       <div class="col">
                                            <select class="custom-select custom-select-sm speakers-select" onchange="speakersUserSelect(this)">
                                                <option selected>Select a Speakers</option>
                                            </select>
                                        </div>
                                    </div>
                                    <hr style="margin: 2px;">
                                    <div id="speakerDisplaySection">
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer" style="display: block;">
                            <div class="row">
                                <div class="col-sm-8">
                                    <div class="alert alert-success" role="alert" id="modalDescriptionEventsSuccessAlert" style="display: none; margin-bottom: 0px;"></div>
                                    <div class="alert alert-danger" role="alert" id="modalDescriptionEventDangerAlert" style="display:none; margin-bottom: 0px;"></div>
                                    <div class="alert alert-warning" role="alert" id="modalDescriptionEventWarningAlert" style="display:none; margin-bottom: 0px;"></div>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary" onclick="saveChangesRegisterPageInfo(this)">Save changes</button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteQuiz" id="deleteQuizBTN" style="display: none;">

            </button>
            <!-- Modal -->
            <div class="modal fade" id="deleteQuiz" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" >Warning</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" id="labelDeleteQuiz" style="text-align: center;">
                            ...
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal" id="closeBtnDeleteQuiz">Close</button>
                            <button type="button" class="btn btn-danger" id="deleteQuizConfirmation">DELETE</button>
                        </div>
                    </div>
                </div>
            </div>

            <div id="myMenu" class="w3-sidebar w3-bar-block w3-light-grey w3-card">
                <h3 class="text-center shadow">Quiz Menu</h3><input type="text" id="mySearch" onkeyup="myFunctionSearch()" placeholder="Search..." title="Write your quiz" style="width: 100%;font-size: 18px;padding: 11px;border: 1px solid #ddd;">
                <button class="btn w3-button w3-block w3-left-align" type="button" onclick="myAccFunc('demoAcc')"><i class="fa fa-question"></i>&nbsp;My Quizes&nbsp;&nbsp;<i class="fa fa-sort-down"></i></button>
                <div id="demoAcc" class="w3-hide w3-white w3-card">
                </div>
                <button class="btn w3-button w3-block w3-left-align" type="button" onclick="myAccFunc('favorites')"><i class="fa fa-star-o"></i>&nbsp;Favorites&nbsp; &nbsp;<i class="fa fa-caret-down"></i></button>
                <div id="favorites" class="w3-hide w3-white w3-card">

                </div>
                <button class="btn manageEventsButon" type="button" data-toggle="modal" data-target="#manageEvents" onclick="mangeEventsBTN()">&nbsp;&nbsp;<i class="fas fa-tasks"></i></i>&nbsp;Manage Events</button></div>
            <div
                class="w3-container" style="margin-left: 200px;">
                <div class="row">
                    <div class="col">
                        <button class="btn btn-light CreateQuizBTN" data-toggle="modal" data-target="#createQuizModal" type="button">Create Quiz&nbsp;<i class="fa fa-plus"></i></button>
                        <button class="btn btn-light CrearEvento" data-toggle="modal" data-target="#createEvent" type="button">Create Event&nbsp;<i class="fa fa-plus"></i></button>
                        <button class="btn btn-light" data-toggle="modal" data-target="#eventCreated" type="button" id="btnEventCreated" style="display: none;"></button>
                    </div>
                    <div class="col">
                    </div>
                </div>
                <div id="quizesGroup" class="QuizesGroup">
                </div>
            </div>
        </div>
        <script src="Home/js/githubVersion.js"></script>
        <script src="Home/js/jquery.min.js"></script>
        <script src="Home/bootstrap/js/bootstrap.min.js"></script>
        <script src="Home/js/Hint/enjoyhint.min.js"></script>
        <script src="Home/js/Hint/jquery.enjoyhint.js"></script>
        <script src="Home/js/sidebar.js"></script>
        <script src="Home/js/sideBarMenu.js"></script>
        <script src="Home/js/quizes.js"></script>
        <script src="Home/js/SearchMenuQuizes.js"></script>
        <script src="Home/js/Hint/HintAppQuizMenu.js"></script>
        <script src="Home/js/timeOutSession.js"></script>
    </body>
</html>