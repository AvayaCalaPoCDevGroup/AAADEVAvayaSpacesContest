<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Avaya Spaces Contest</title>
        <link rel="shortcut icon" href="Home/img/Icon-Avaya.png"/>
        <link rel="stylesheet" href="Home/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Ubuntu">
        <link rel="stylesheet" href="Home/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="Home/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="Home/fonts/fontawesome5-overrides.min.css">
        <link rel="stylesheet" href="Home/css/Article-Clean.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="Home/css/sidebar.css">
        <link rel="stylesheet" href="Home/css/SideBarAvaya.css">
        <link rel="stylesheet" href="Home/css/mediaQuerysQuizView.css">
        <link rel="stylesheet" href="Home/css/podium.css">
        <link rel="stylesheet" href="Home/css/Simple-Vertical-Navigation-Menu-v-10.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

        <link rel="stylesheet" href="Home/css/jquery.enjoyhint.css">
    </head>
    <% String preguntas = (String) request.getAttribute("preguntas");%>
    <% String nombreEvento = (String) request.getAttribute("nombreEvento");%>
    <% String nombreQuiz = (String) request.getAttribute("nombreQuiz");%>
    <% String estatusQuiz = (String) request.getAttribute("estatusQuiz");%>
    <body style="padding-left: 0px;">
        <div class="loader loader-default" data-blink id="loaderDisplay"></div>
        <div id="jsonRequest" style="display: none;"><%=preguntas%></div>
        <%@include file="Template/sidebar.jsp" %>
        <div class="container-fluid article-clean" style="padding-left: 10px;">
            <nav class="navbar navbar-light navbar-expand-md navBarToTour" style="background-color: #da281c;height: 40px;">
                <div class="container-fluid"><img class="img-fluid" src="Home/img/Avaya%20logo%20transparente%20blanco.png" style="width: 70px;margin: 0px;margin-top: 7px;">
                    <p class="text-left d-xl-flex justify-content-xl-start align-items-xl-start QuizNameAndEvento" style="width: 30%;color: rgb(0,0,0);font-size: 10px;font-family: Lora, serif;margin: 13px;     color: white; font-weight: bold;"><%= nombreQuiz%> <br> <%= nombreEvento%></p>
                    <p class="text-left d-xl-flex justify-content-xl-start align-items-xl-start" style="width: 254px;color: rgb(0,0,0);font-size: 15px;font-family: Lora, serif;margin: 13px;"> <p id="idconcurso" style="display: none;"></p></p>
                    <p class="text-left d-xl-flex justify-content-xl-start align-items-xl-start" style="width: 254px;color: rgb(0,0,0);font-size: 15px;font-family: Lora, serif;margin: 13px;"><p id="estatusQuiz" style="display: none;"><%=estatusQuiz%></p></p>
                    <div class="row" style="margin-top: 5px; width: 100%;">
                        <div class="col-md-4 d-lg-flex justify-content-lg-center align-items-lg-center RocketLauncher" id="columnIconPuplicQuestion" style="margin-bottom: 5px;">
                            <i class="fa fa-rocket fa-2x" style="color: white;cursor: pointer;" onclick="publicQuestion()"></i>
                        </div>
                        <div class="col-md-4 d-lg-flex justify-content-lg-center align-items-lg-center llaveDelConcurso" id="divLlaveDelConcurso">
                            <h3 id="llaveDelConcurso" title="Press to copy the Quiz key to the clip board." style="font-weight: bold; font-size: 25px; color: white; cursor: pointer;"></h3>
                        </div>
                        <div class="col-md-4 d-lg-flex justify-content-lg-center align-items-lg-center">
                            <i class="fas fa-eye fa-2x EyeResponses" style="color: white;cursor: pointer; display: none;" onclick="seeResponses(this)" id="seeResonseIcon"></i>
                        </div>
                    </div>
                    <p class="text-left d-xl-flex justify-content-xl-start align-items-xl-start" style="width: 254px;color: rgb(0,0,0);font-size: 15px;font-family: Lora, serif;margin: 13px;"></p>
                    <p class="text-left d-xl-flex justify-content-xl-start align-items-xl-start" style="width: 254px;color: white;font-size: 13px;font-family: Lora, serif;margin: 40px;"><p style="color: white; font-size: 13px; margin-top: 15px; margin-right: 15px; display: none; width: 150px;">00:00</p></p>


                    <button class="btn btn-primary text-right d-lg-flex justify-content-lg-end OpenSlidesMenu" id="openNav-1" type="button" onclick="toogledSideMenuQuizes()">
                        <i class="fa fa-question-circle-o"></i>
                    </button>
        <button class="btn btn-success text-right d-lg-flex justify-content-lg-end EditQuestion" id="editQuuestion" type="button" onclick="editQuestion()" style="display: none;">
                        <i class="fas fa-edit"></i>
                    </button>
                    <div class="btn-group" style="height: 27px">
                        <button class="btn btn-info d-lg-flex justify-content-lg-end startEnrollBTN" type="button" onclick="startEnrollRequest()" id="clockDownEnroll" style="height: 27px;">
                            <p style="font-weight: bold; font-size: 16px; margin-top: -4px;">Enroll</p>
                        </button>
                        <button type="button" class="btn btn-info dropdown-toggle dropdown-toggle-split EnrollDropDown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu MenuDeEnrolamiento">
                            <a class="dropdown-item" onclick="enroledPeople()" style="cursor: pointer;" id="enorolledPeople">Enrolled People: </a>
                            <a class="dropdown-item" onclick="openEnroledPeople()" style="cursor: pointer;">Open Enroled People</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" onclick="sendDirectMessageConfirmation()" style="cursor: pointer;" id="sendMessageConfirmation">Send direct message confirmation</a>
                        </div>
                    </div>  



                    <button class="btn btn-danger closeQuiz" type="button" style="height: 27px;" data-toggle="modal" data-target="#exampleModal">
                        <p style="font-weight: bold; font-size: 16px; margin-top: -4px;" id="closeBtn">Close</p>
                    </button>                
                    <button class="btn btn-warning d-lg-flex justify-content-lg-end" type="button" onclick="prevBtn()">
                        <i class="fa fa-chevron-left"></i>
                    </button>
                    <div class="row botonesDeNavegacion" style="height: 39px;">

                        <div class="col">
                            <p id="textNumberView" style="color: white; text-align: center; font-weight: bold; font-size: 25px;">1</p><p id="idQuestion" style="display: none;"></p>
                        </div>
                        <div class="col d-lg-flex justify-content-lg-end"><button class="btn" type="button"><i class="fa fa-edit" style="display: none;"></i></button></div>

                    </div>
                    <button class="btn btn-success d-lg-flex justify-content-lg-end" type="button" onclick="nextBtn()">
                        <i class="fa fa-chevron-right"></i>
                    </button>
                </div>
            </nav>
            <div id="mySidebar" class="w3-sidebar w3-bar-block w3-card w3-animate-left" style="display: none;padding-bottom: 50px;width: 400px;">
                <div class="row">
                    <div class="col d-block SlidesBar" id="columnQuizesSideBar">

                    </div>
                </div>
                <div class="row">
                    <div class="col"><button class="btn btn-primary btn-block" type="button" style="display: none;">Create Quiz</button></div>
                </div>
            </div>
            <div id="main"></div>
            <div class="w3-container" id="quizViewContainer" style="display: none;">
                <div class="row d-lg-flex justify-content-lg-center align-items-lg-center" style="word-break: break-all;">
                    <div class="col-12 text-center d-lg-flex justify-content-lg-center align-items-lg-center textQuestion" style="background-color: #e4e2e2;border: solid;height: 124px;word-break: break-all; margin-top: 35px;">
                        <h5 class="text-center d-lg-flex justify-content-lg-center align-items-lg-center TextQuiz" id="textQuizView" style="color: black; font-size: 2.5vw;height: 75px;font-weight: bold;word-break: break-all;">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.<br></h5>
                    </div>
                </div>
                <div class="row" style="display: none; text-align: center; margin-top: 15px;" id="canvasRow" >
                    <div class="col"></div>
                    <div class="col-sm-12 text-center d-lg-flex justify-content-lg-center align-items-lg-center CanvasContainer" style="text-align: center;" id="canvasPieChart">
                        <canvas id="pieChartRespuestas" style="max-width: 38%"></canvas>
                    </div>
                    <div class="col"></div>
                </div>
                <div class="row" style="margin-top: 15px;" id="imageViewPrincipal">
                    <div class="col-md-4 d-lg-flex justify-content-lg-center align-items-lg-center" id="textTimeViewDiv">
                        <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center TimeCircle" id="textTimeView" style="height: 150px;width: 150px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 3vw;background-color: blue;color: white;">20s</span>
                    </div>
                    <div class="col-md-4 d-lg-flex justify-content-lg-center align-items-lg-center imageDIV" style="width: 350px; height: 300px;">
                        <img class="img-fluid" style="max-width: 350px; max-height: 300px;" id="textImageView" src="Home/img/EBCproof-8.jpg"></div>
                    <div class="col-md-4 d-lg-flex justify-content-lg-center align-items-lg-center" id="textPointsViewDiv">
                        <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center PointsCircle" id="textPointsView" style="height: 150px;width: 150px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 3vw;background-color: green;color: white;">10pts</span>
                    </div>
                </div>

                <div style="   position: fixed; left: 0; bottom: 0; width: 100%;">
                    <div class="row position-sticky rowAB" style="position: fixed; height: 100px;">
                        <div class="col" style="background-color: #990000;
                             border: 1px solid;
                             padding: 10px;
                             box-shadow: 3px 4px #888888;
                             color: white;">
                            <div class="row">
                                <div class="col-2 d-lg-flex justify-content-lg-center align-items-lg-center" id="optionABoxCircle"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 50px;width: 50px;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: #292929;color: white;">A</span></div>
                                <div
                                    class="col-10 d-lg-flex justify-content-lg-start align-items-lg-center" id="textOptnAViewDiv">
                                    <h1 id="textOptnAView" style="font-size: 1.7vw;
                                        word-break: break-all;
                                        max-height: 50px;
                                        color: white;
                                        font-weight: bold;
                                        height: 51px;">It is a long established fact that a reader will be distracted by the reada<br></h1>
                                </div>
                            </div>
                        </div>
                        <div class="col" style="background-color: #b37700;
                             border: 1px solid;
                             padding: 10px;
                             box-shadow: 3px 4px #888888;
                             color: white;">
                            <div class="row">
                                <div class="col-2 d-lg-flex justify-content-lg-center align-items-lg-center" id="optionBBoxCircle"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 50px;width: 50px;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: #292929;color: white;">B</span></div>
                                <div
                                    class="col-10 d-lg-flex justify-content-lg-start align-items-lg-center" id="textOptnBViewDiv">
                                    <h1 id="textOptnBView" style="font-size: 1.7vw;
                                        word-break: break-all;
                                        max-height: 50px;
                                        color: white;
                                        font-weight: bold;
                                        height: 51px;">It is a long established fact that a reader will be distracted by the reada<br></h1>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row position-sticky rowCD" style="position: fixed; height: 100px;">
                        <div class="col" style="background-color: #003380;
                             border: 1px solid;
                             padding: 10px;
                             box-shadow: 3px 4px #888888;
                             color: white;">
                            <div class="row">
                                <div class="col-2 d-lg-flex justify-content-lg-center align-items-lg-center" id="optionCBoxCircle"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 50px;width: 50px;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: #292929;color: white;">C</span></div>
                                <div
                                    class="col-10 d-lg-flex justify-content-lg-start align-items-lg-center" id="textOptnCViewDiv">
                                    <h1 id="textOptnCView" style="font-size: 1.7vw;
                                        word-break: break-all;
                                        max-height: 50px;
                                        color: white;
                                        font-weight: bold;
                                        height: 51px;">It is a long established fact that a reader will be distracted by the reada<br></h1>
                                </div>
                            </div>
                        </div>
                        <div class="col" style="background-color: #520066;
                             border: 1px solid;
                             padding: 10px;
                             box-shadow: 3px 4px #888888;
                             color: white;">
                            <div class="row">
                                <div class="col-2 d-lg-flex justify-content-lg-center align-items-lg-center" id="optionDBoxCircle"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 50px;width: 50px;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: #292929;color: white;">D</span></div>
                                <div
                                    class="col-10 d-lg-flex justify-content-lg-start align-items-lg-center" id="textOptnDViewDiv">
                                    <h1 id="textOptnDView" style="font-size: 1.7vw;
                                        word-break: break-all;
                                        max-height: 50px;
                                        color: white;
                                        font-weight: bold;
                                        height: 51px;">It is a long established fact that a reader will be distracted by the reada<br></h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>



            </div>

            <div class="w3-cointainer" id="podiumContainer" style="display: none;">

                <div id="podium-box" class="row" style="height: 100%;
                     position: fixed;
                     left: 0;
                     bottom: 0;
                     width: 100%;
                     /* background-color: red; */
                     color: white;
                     text-align: center;
                     background: url('Home/img/avayaFondo.png')
                     ">
                    <button type="button" class="btn btn-default cerrarPodium" style="position: fixed;
                            top:0;
                            right:0;
                            z-index:10;" onclick="cerrarPodium()">

                        <i class="fas fa-window-close fa-3x" style="color: black; background-color: white;"></i></button>
                    <div class="col-md-4 step-container m-0 p-0">
                        <div style="    margin: auto;
                             width: 100%;
                             padding: 10px;">
                            <span class="dot" style="  height: 190px;
                                  width: 190px;
                                  background-color: yellowgreen;
                                  border-radius: 50%;
                                  display: inline-block;
                                  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                                  ">
                                <img src="https://storage.googleapis.com/onesna/pictures/pfpic_umansilla_189bab3a-eb2d-41fe-9274-ba45de7b845e" style="margin: auto;
                                     width: 100%;
                                     padding: 10px;
                                     border-radius: 50%;" id="segundoLugarImage">      

                            </span>
                        </div>

                        <div id="second-step" class="bg-blue step centerBoth podium-number" style="background-color: yellowgreen; height: 335px;">
                            <i class="fas fa-medal" style="color: blue; margin-top: 10px;"></i>
                            <h6 style="  margin: auto;
                                width: 50%;
                                font-size: 20px;
                                font-weight: bold;
                                padding: 10px; display: none;" id="segundoLugarFastest"><i class="fas fa-tachometer-alt fa-1x" style="color: white;"></i> fastest 
                            </h6>
                            <h3 style="  margin: auto;
                                width: 50%;
                                font-size: 30px;
                                font-weight: bold;" id="segundoLugarPuntos">50pts
                            </h3>
                            <div style="font-weight: bold;
                                 font-weight: bold;
                                 color: white;
                                 margin: auto;
                                 width: 50%;
                                 font-size: 30px;" id="sedundoLugarNombre">
                                Uriel Mansilla
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 step-container m-0 p-0">
                        <div style="    margin: auto;
                             width: 100%;
                             padding: 10px;">
                            <span class="dot" style="  height: 190px;
                                  width: 190px;
                                  background-color: #dc3545;
                                  border-radius: 50%;
                                  display: inline-block;
                                  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                                  ">
                                <img src="https://storage.googleapis.com/onesna/pictures/pfpic_kinichman_5aa27f74-727a-4cc7-91d5-1d8bfea14435" style="margin: auto;
                                     width: 100%;
                                     padding: 10px;
                                     border-radius: 50%;" id="primerLugarImage">      

                            </span>
                        </div>

                        <div id="first-step" class="bg-blue step centerBoth podium-number" style="background-color: #dc3545; height: 435px;">
                            <i class="fas fa-trophy fa-2x" style="color: #FFCC00; margin-top: 10px;"></i>
                            <h6 style="  margin: auto;
                                width: 50%;
                                font-size: 20px;
                                font-weight: bold;
                                padding: 10px; display: none;" id="primerLugarFastest" ><i class="fas fa-tachometer-alt fa-1x" style="color: white;"></i> fastest 
                            </h6>
                            <h3 style="  margin: auto;
                                width: 50%;
                                font-size: 30px;
                                font-weight: bold;" id="primerLugarPuntos">50pts
                            </h3>
                            <div style="font-weight: bold;
                                 font-weight: bold;
                                 color: white;
                                 margin: auto;
                                 width: 50%;
                                 font-size: 30px;" id="primerLugarNombre">
                                Kinich Uriel
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 step-container m-0 p-0">
                        <div style="    margin: auto;
                             width: 100%;
                             padding: 10px;">
                            <span class="dot" style="  height: 190px;
                                  width: 190px;
                                  background-color: green;
                                  border-radius: 50%;
                                  display: inline-block;
                                  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                                  ">
                                <img src="https://storage.googleapis.com/onesna/pictures/pfpic_umansilla_189bab3a-eb2d-41fe-9274-ba45de7b845e" style="margin: auto;
                                     width: 100%;
                                     padding: 10px;
                                     border-radius: 50%;" id="tercerLugarImage">      

                            </span>
                        </div>

                        <div id="third-step" class="bg-blue step centerBoth podium-number" style="background-color: green; height: 225px;">
                            <i class="fas fa-medal" style="color: blue; margin-top: 10px;"></i>
                            <h3 style="  margin: auto;
                                width: 50%;
                                font-size: 30px;
                                font-weight: bold;" 
                                id="tercerLugarPuntos">50pts
                            </h3>
                            <div style="font-weight: bold;
                                 color: white;
                                 margin: auto;
                                 width: 50%;
                                 font-size: 30px;" id="tercerLugarNombre">
                                Uriel Mansilla
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"><i class="fas fa-exclamation-triangle" style="color: #FFCC00;"></i>Warning message<i class="fas fa-exclamation-triangle" style="color: #FFCC00;"></i></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Are you sure you want to close the Quiz?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal" id="closeModal"><i class="fas fa-times" style="color: white;"></i></button>
                            <button type="button" class="btn btn-danger" onclick="closeQuiz()">Accept</button>
                        </div>
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-primary" id="closeEditModeBtn" data-toggle="modal" data-target="#modalCloseEditMode" style="display: none;"></button>
            <!-- Modal -->
            <div class="modal fade" id="modalCloseEditMode" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">You are about to close the edit mode</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-weight: bold; color: black;">Please carefully review the options presented:</h4>
                            <h5>Edit in all Quiz:</h5>
                            <h5>Changes will be reflected in this event and in all future events and edit mode ends.</h5>
                            <h5>Edit in this Quiz:</h5>
                            <h5>Changes will be reflected only in this event and edit mode ends.</h5>
                            <h5>Cancel edition:</h5>
                            <h5>Return the original values to the question and end the edit mode.</h5>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel edition</button>
                            <button type="button" class="btn btn-warning">Edit in all Quiz</button>
                            <button type="button" class="btn btn-success">Edit in this Quiz</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="Home/js/jquery.min.js"></script>
        <script src="Home/bootstrap/js/bootstrap.min.js"></script>
        <script src="Home/js/Hint/enjoyhint.min.js"></script>
        <script src="Home/js/Hint/jquery.enjoyhint.js"></script>
        <script src="Home/js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
        <script src="Home/js/SearchMenuQuizes.js"></script>
        <script src="Home/js/sideBarMenu.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="Home/js/app.js"></script>
        <script src="Home/js/Hint/HintAppQuizView.js"></script>
        <script src="Home/js/timeOutSession.js"></script>
        <script src="Home/js/githubVersion.js"></script>
    </body>

</html>