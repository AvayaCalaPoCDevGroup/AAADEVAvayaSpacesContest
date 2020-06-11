<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Create Quizes</title>
        <link rel="shortcut icon" href="Home/img/Icon-Avaya.png"/>
        <link rel="stylesheet" href="Home/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=ABeeZee">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="Home/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="Home/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="Home/fonts/simple-line-icons.min.css">
        <link rel="stylesheet" href="Home/fonts/fontawesome5-overrides.min.css">
        <link rel="stylesheet" href="Home/css/Fixed-Navbar.css">
        <link rel="stylesheet" href="Home/css/hoverControl.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/froala-editor@3.1.0/css/froala_editor.pkgd.min.css">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="Home/css/Overlay.css">
        <link rel="stylesheet" href="Home/css/Pretty-User-List.css">
        <link rel="stylesheet" href="Home/css/ProcessStep.css">
        <link rel="stylesheet" href="Home/css/quiz-card.css">
        <link rel="stylesheet" href="Home/css/sidebar.css">
        <link rel="stylesheet" href="Home/css/css-loader.css">
        <link rel="stylesheet" href="Home/css/mediaQuerysQuizCreate.css">
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
        <link rel="stylesheet" href="Home/css/jquery.enjoyhint.css">
    </head>
    <% String quizName = (String) request.getAttribute("quizName");%>

    <body class="text-center" style="padding-left: 0px;">
        <div class="loader loader-default" data-blink id="loaderDisplay"></div>
        
        <%@include file="Template/sidebar.jsp" %>
        
        <div></div>
        <div class="container-fluid article-clean">
            <nav class="navbar navbar-light navbar-expand-md">
                <div class="container-fluid">
                    <p class="text-left d-xl-flex justify-content-xl-start align-items-xl-start" style="width: 266px;color: rgb(0,0,0);font-size: 15px;font-family: Lora, serif;margin: 13px;">Avaya CALA PoC Development Group</p>
                    <a class="navbar-brand" href="#">
                        <p>Quiz name:&nbsp;<strong class="quizNameModify"><%=quizName%></strong></p>
                    </a>
                </div>
            </nav>
            <div id="quizName" style="display: none;"></div>
        </div>
        <div>
            <div class="container">
                <div class="row text-center process-row-nav-nav-tabs">
                    <!--<div class="col-md-3 col-lg-1 offset-lg-0" style="top: 15px;"><button class="btn btn-warning" type="button" style="line-height: 30px;border-radius: 100px;" onclick="prevBtn()"><i class="fa fa-chevron-left"></i></button></div>-->
                    <div class="col-md-3 movileDisplayIcons" style="background-size: 41px;">
                        <button class="btn btn-info btn-circle nameAndImage" type="button" style="line-height: 30px;border-radius: 100px;" onclick="processIcons('menu1')">
                            <i class="fa fa-info fa-3x" style="color: rgb(0,0,0);"></i></button>
                       <!-- <p class="text-center"><small>Quiz</small><br>Modify&nbsp;name&nbsp;and&nbsp;upload&nbsp;image<br><br></p>-->
                    </div>
                    <div class="col-md-3 movileDisplayIcons" style="background-size: 41px;"><button class="btn btn-circle preguntasCreation" type="button" style="line-height: 30px;border-radius: 100px;" onclick="processIcons('menu2')"><i class="fa fa-question fa-3x" style="color: rgb(0,0,0);"></i></button>
                      <!--  <p class="text-center"><small>Quiz</small><br>Create&nbsp;question(s)<br></p>-->
                    </div>
                    <div class="col-md-3 movileDisplayIcons" style="background-size: 41px;"><button class="btn btn-circle quizValidation" type="button" style="line-height: 30px;border-radius: 100px;" onclick="processIcons('menu3')"><i class="fa fa-check fa-3x" style="color: rgb(0,0,0);"></i></button>
                       <!--- <p class="text-center"><small>Quiz</small><br>Validate the data<br></p> -->
                    </div>
                    <div class="col-md-3 movileDisplayIcons"><button class="btn btn-circle createQuizBtn" type="button" style="line-height: 30px;border-radius: 100px;" title="Pressing will formally create the Quiz" id="createQuiz" disabled><i class="fas fa-check-double fa-3x" id="iconCreateQuiz" style="color: rgb(0,0,0);"></i></button>
                        <!-- <p class="text-center"><small>Quiz</small><br>Create Quiz<br></p> -->
                    </div>
                    <!--<div class="col-6 col-md-3 col-lg-1 offset-lg-0" style="top: 15px;"><button class="btn btn-success" type="button" style="line-height: 30px;border-radius: 100px;" onclick="nextBtn()"><i class="fa fa-chevron-right"></i></button></div>-->
                </div>
            </div>
        </div>
        <div class="tab-content" style="width: 100%;text-align: center;">
            <div id="menu1" class="tab-pane fade active show">
                <h3 class="text-center">Modify name and upload image</h3>
                <form>
                    <div class="form-group"><input class="form-control form-control inputNameQuiz" type="text" id="inputNameQuiz" placeholder="Enter the new Name" value="<%=quizName%>" onkeyup="changeNameByInput()" style="display: inline-block;width: 50%;"></div>
                </form>
                <div class="drop-region dropRegionImageQuiz" style="display: inline-block;border-style: dotted;width: 50%;" onclick="clickDropRegion(this)">
                    <div class="drop-message"><strong>Drag&nbsp;&amp;&nbsp;Drop&nbsp;image&nbsp;or&nbsp;click&nbsp;to&nbsp;upload<br></strong></div>
                    <div id="imagePreviewQuizImage" class="image-preview"></div>
                </div>
            </div>
            <div id="menu2" class="tab-pane fade active show">
                <h3>Create&nbsp;question(s)<br></h3>
                <div class="row no-gutters" style="display: none;" id="menuoptionsCreate">
                    <div class="col createQuestion" style="cursor: pointer;transition: 0.75s; background-color: green; color: white;" title="Press to add a basic form to build a question" onclick="addQuestion()"><i class="fa fa-plus" style="cursor: pointer;" title="Press to add a basic form to build a question"></i></div>
                    <div class="col uploadFile" style="cursor: pointer;transition: 0.75s; background-color: #f44611; color: white;" title="Press to upload questions regarding the excel file." onclick="uploadQuestions()"><i class="fa fa-upload" style="cursor: pointer;" title="Press to upload questions regarding the excel file."></i></div>
                    <div class="col fullScreen" style="cursor: pointer;transition: 0.75s; background-color: blue; color: white;" title="Press to view the question currently displayed in full screen." onclick="on()"><i class="icon-size-fullscreen" style="cursor: pointer;" title="Press to view the question currently displayed in full screen."></i></div>
                    <div class="col deleteQuiz" style="cursor: pointer;transition: 0.75s; background-color: red; color: white;" title="Press to delete the current question displayed." onclick="deleteForm()"><i class="fa fa-trash" style="cursor: pointer;" title="Press to delete the current question displayed."></i></div>
                </div>
                <div class="row no-gutters" style="margin-top: 20px;">
                    <div class="col-1"><a class="prev" onclick="plusSlides(1)"><i class="fa fa-chevron-left"></i></a></div>
                    <div class="col-10 text-center">
                        <div>
                            <div id="slideShowContainer" class="slideshow-container">
                                <div class="fadeImage mySlides">
                                    <div class="numbertext"><strong class="numberOfSlides" style="float: left;">1 / 1</strong></div>
                                    <div class="form">
                                        <div class="row">
                                            <textarea class="form-control-lg form-control textAreaAutoRezise questionInput" rows="1" maxlength="120" minlength="1" type="text" required="true" style="display: inline-block;width: 100%;height: 57px;border: none;background-color: #e4e2e2;font-size: 2vw;overflow: hidden;text-align: center;font-weight: bold; color: black; border: solid;border-width: 1px;"
                                                      onblur="inputQuestions(this)"
                                                      autofocus=""></textarea>
                                        </div>
                                        <div class="row" style="margin-top: 5px;">
                                            <div class="text-left d-inline-block col-sm-3 timeToRespond">
                                                <h6>Set&nbsp;the&nbsp;time&nbsp;to&nbsp;respond<br></h6>
                                                <div class="text-center row">
                                                    <div class="col-sm-1 slidevertical">
                                                        <div id="slider-vertical" class="slidevertical" style="height: 130px;left: 50%;"></div>
                                                    </div>
                                                    <div class="col-sm-3 slidevertical">
                                                        <div class="circle" style="display: inline-block;"><span class="amountTime" id="amount" style="color: white;font-size: 40px;font-weight: bold;line-height: 120px;">20s</span></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="drop-region" style="display: inline-block;border-style: dotted;width: 441px;" onclick="clickDropRegion(this)">
                                                    <div class="drop-message"><strong>Drag&nbsp;&amp;&nbsp;Drop&nbsp;image&nbsp;or&nbsp;click&nbsp;to&nbsp;upload<br></strong></div>
                                                    <div class="image-preview-question"></div>
                                                </div>
                                            </div>
                                            <div class="text-right col-sm-3">
                                                <h6>Set&nbsp;the&nbsp;points&nbsp;of&nbsp;the&nbsp;question<br></h6>
                                                <div class="row">
                                                    <div class="col-sm-10">
                                                        <div class="circlePoints" style="display: inline-block;"><span class="amountPoints" id="amount-points" style="color: white;font-size: 40px;font-weight: bold;line-height: 120px;">10pts</span></div>
                                                    </div>
                                                    <div class="col-sm-1">
                                                        <div id="slider-vertical-points" style="height: 130px;left: 50%;"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <h5 style="margin-bottom: 0px;">Options<br></h5>
                                        <div class="form-row colab">
                                            <div class="col-md-6 mb-6">
                                                <div class="row">
                                                    <div class="col-md-10">
                                                        <div class="row rowA" style="background-color: #990000;
                                                             border: 1px solid;
                                                             padding: 10px;
                                                             box-shadow: 3px 4px #888888;
                                                             color: white;"> 
                                                            <div class="col-sm-2">
                                                                <div style="margin: 0 auto;">
                                                                    <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;
                                                                          width: 60px;
                                                                          border-radius: 60%;
                                                                          display: inline-block;
                                                                          font-size: 2vw;
                                                                          background-color: #292929;
                                                                          color: white;">
                                                                        <span style="margin:auto; height: 96%;">A</span>    
                                                                    </span>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-10">
                                                                <textarea class="form-control-lg form-control textAreaAutoRezise optionAText" rows="1"  type="text" required="true" style="display: inline-block;width: 100%;height: 57px;border: none;background-color: #990000;font-size: 1vw;overflow: hidden;color: white; font-weight: bold;"onblur="inputQuestions(this)" maxlength="75" minlength="1"></textarea>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">
                                                        <div class="radio-toolbar"><input class="optionAcheckBox" type="checkbox" id="checkBoxA" onclick="checkBoxClick(this)"><label for="checkBoxA" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-6">
                                                <div class="row rowB">
                                                    <div class="col-md-10">
                                                        <div class="row" style="background-color: #86592d;
                                                             border: 1px solid;
                                                             padding: 10px;
                                                             box-shadow: 3px 4px #888888;
                                                             color: white;"> 
                                                            <div class="col-sm-2">
                                                                <div style="margin: 0 auto;">
                                                                    <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;
                                                                          width: 60px;
                                                                          border-radius: 60%;
                                                                          display: inline-block;
                                                                          font-size: 2vw;
                                                                          background-color: #292929;
                                                                          color: white;">
                                                                        <span style="margin:auto; height: 96%;">B</span>    
                                                                    </span>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-10">
                                                                <textarea class="form-control-lg form-control textAreaAutoRezise optionBText" rows="1"  type="text" required="true" style="display: inline-block;width: 100%;height: 57px;border: none;background-color: #86592d;font-size: 1vw;overflow: hidden;font-weight: bold; color: white; font-weight: bold;"onblur="inputQuestions(this)" maxlength="75" minlength="1"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">
                                                        <div class="radio-toolbar"><input class="optionBcheckBox" type="checkbox" id="checkBoxB" onclick="checkBoxClick(this)"><label for="checkBoxB" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-row colcd">
                                            <div class="col-md-6 mb-6">
                                                <div class="row rowC">
                                                    <div class="col-md-10">
                                                        <div class="row" style="background-color: #003380;
                                                             border: 1px solid;
                                                             padding: 10px;
                                                             box-shadow: 3px 4px #888888;
                                                             color: white;"> 
                                                            <div class="col-sm-2">
                                                                <div style="margin: 0 auto;">
                                                                    <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;
                                                                          width: 60px;
                                                                          border-radius: 60%;
                                                                          display: inline-block;
                                                                          font-size: 2vw;
                                                                          background-color: #292929;
                                                                          color: white;">
                                                                        <span style="margin:auto; height: 96%;">C</span>    
                                                                    </span>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-10">
                                                                <textarea class="form-control-lg form-control textAreaAutoRezise optionCText" rows="1"  type="text" required="true" style="display: inline-block;width: 100%;height: 57px;border: none;background-color: #003380;font-size: 1vw;overflow: hidden;color: white; font-weight: bold;"onblur="inputQuestions(this)" maxlength="75" minlength="1"></textarea>

                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">
                                                        <div class="radio-toolbar"><input class="optionCcheckBox" type="checkbox" id="checkBoxC" onclick="checkBoxClick(this)"><label for="checkBoxC" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-6">
                                                <div class="row rowD">
                                                    <div class="col-md-10">
                                                        <div class="row" style="background-color: #520066;
                                                             border: 1px solid;
                                                             padding: 10px;
                                                             box-shadow: 3px 4px #888888;
                                                             color: white;"> 
                                                            <div class="col-sm-2">
                                                                <div style="margin: 0 auto;">
                                                                    <span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;
                                                                          width: 60px;
                                                                          border-radius: 60%;
                                                                          display: inline-block;
                                                                          font-size: 2vw;
                                                                          background-color: #292929;
                                                                          color: white;">
                                                                        <span style="margin:auto; height: 96%;">D</span>    
                                                                    </span>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-10">
                                                                <textarea class="form-control-lg form-control textAreaAutoRezise optionDText" rows="1"  type="text" required="true" style="display: inline-block;width: 100%;height: 57px;border: none;background-color: #520066;font-size: 1vw;overflow: hidden; color: white; font-weight: bold;"onblur="inputQuestions(this)" maxlength="75" minlength="1"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2" style="position: relative; top: 13px; right: 9px;">
                                                        <div class="radio-toolbar"><input class="optionDcheckBox" type="checkbox" id="checkBoxD" onclick="checkBoxClick(this)"><label for="checkBoxD" style="width: 60px; height: 56px;"><i class="fa fa-remove fa-4x" style="color: rgb(252,252,252);"></i></label></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="dotsSlider" style="text-align: center;display: none;"><span class="dot" onclick="currentSlide(1)">Text</span></div>
                        </div>
                    </div>
                    <div class="col-1 text-left"><a class="next" onclick="plusSlides(-1)"><i class="fa fa-chevron-right"></i></a></div>
                </div>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadQuestionsModal" id="uploadQuestionsModalBTN" style="display: none;"></button>
                <!-- Modal -->
                <div class="modal fade" id="uploadQuestionsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Upload Questions</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h5>Only upload the template file with your questions.</h5>
                                <form>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="customFile">
                                        <label class="custom-file-label" for="customFile" style="text-align: left;">Choose file</label>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" onclick="uploadFileRest()">Upload File</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="overlay" onclick="off()">
                    <nav class="navbar navbar-light navbar-expand-md" style="background-color: #da281c;height: 40px;">
                        <div class="container-fluid"><img class="img-fluid" src="Home/img/Avaya%20logo%20transparente%20blanco.png" style="width: 70px;margin: 0px;margin-top: 7px;">

                        </div>
                    </nav>
                    <div class="row d-lg-flex justify-content-lg-center align-items-lg-center" style="word-break: break-all;">
                        <div class="col-12 text-center d-lg-flex justify-content-lg-center align-items-lg-center QuestionOverlayInput" style="background-color: #e4e2e2;height: 124px;word-break: break-all; margin-top: 35px; border: solid;">
                            <h5 class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" id="questionHeadingOverlay" style="/*background-color: #e4e2e2;*/font-size: 2.5vw;height: 75px;font-weight: bold;word-break: break-all;">Pregunta Uno, Respuesta A</h5>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 15px;">
                        <div class="col-md-4 d-lg-flex justify-content-lg-center align-items-lg-center"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" id="amountTimeOverlay" style="height: 150px;width: 150px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 3vw;background-color: blue;color: white;">20s</span></div>
                        <div class="col-md-4 d-lg-flex justify-content-lg-center align-items-lg-center" style="width: 350px; height: 300px;"><img class="img-fluid" style="max-width: 350px; max-height: 300px;" id="imageOverlay" src="http://localhost:8085/AvayaSpacesContest/Home/img/Transparent.png"></div>
                        <div class="col-md-4 d-lg-flex justify-content-lg-center align-items-lg-center"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" id="amount-pointsOverlay" style="height: 150px;width: 150px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 3vw;background-color: green;color: white;">10pt</span></div>
                    </div>

                    <div class="row position-sticky rowOverlayOptions" style="position: fixed;">
                        <div class="col OptionAOverlayDiv" style="background-color: #990000;
                             border: 1px solid;
                             padding: 10px;
                             box-shadow: 3px 4px #888888;
                             color: white;">
                            <div class="row">
                                <div class="col-2 d-lg-flex justify-content-lg-center align-items-lg-center" id="optionABoxCircle"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;width: 60px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: #292929;color: white;font-weight: bold;">A</span></div>
                                <div class="col-10 d-lg-flex justify-content-lg-start align-items-lg-center">
                                    <h1 id="optionAOverlay" style="font-size: 1.7vw;
                                        word-break: break-all;
                                        color: white;
                                        font-weight: bold;
                                        max-height: 100px;">Correcta</h1>
                                </div>
                            </div>
                        </div>
                        <div class="col OptionBOverlayDiv" style="background-color: #b37700;
                             border: 1px solid;
                             padding: 10px;
                             box-shadow: 3px 4px #888888;
                             color: white;">
                            <div class="row">
                                <div class="col-2 d-lg-flex justify-content-lg-center align-items-lg-center" id="optionBBoxCircle"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;width: 60px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: #292929;color: white;font-weight: bold;">B</span></div>
                                <div class="col-10 d-lg-flex justify-content-lg-start align-items-lg-center">
                                    <h1 id="optionBOverlay" style="font-size: 1.7vw;
                                        word-break: break-all;
                                        color: white;
                                        font-weight: bold;
                                        max-height: 100px;">Incorrecta</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row position-sticky" style="margin-top: 10px;position: fixed;">
                        <div class="col OptionCOverlayDiv" style="background-color: #003380;
                             border: 1px solid;
                             padding: 10px;
                             box-shadow: 3px 4px #888888;
                             color: white;">
                            <div class="row">
                                <div class="col-2 d-lg-flex justify-content-lg-center align-items-lg-center" id="optionCBoxCircle"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;width: 60px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: #292929;color: white;font-weight: bold;">C</span></div>
                                <div class="col-10 d-lg-flex justify-content-lg-start align-items-lg-center">
                                    <h1 id="optionCOverlay" style="font-size: 1.7vw;
                                        word-break: break-all;
                                        color: white;
                                        font-weight: bold;
                                        max-height: 100px;">Incorrecta</h1>
                                </div>
                            </div>
                        </div>
                        <div class="col OptionDOverlayDiv" style="background-color: #520066;
                             border: 1px solid;
                             padding: 10px;
                             box-shadow: 3px 4px #888888;
                             color: white;">
                            <div class="row">
                                <div class="col-2 d-lg-flex justify-content-lg-center align-items-lg-center" id="optionDBoxCircle"><span class="text-center d-lg-flex justify-content-lg-center align-items-lg-center" style="height: 60px;width: 60px;background-color: #bbb;border-radius: 50%;display: inline-block;font-size: 2vw;background-color: #292929;color: white; font-weight: bold;">D</span></div>
                                <div class="col-10 d-lg-flex justify-content-lg-start align-items-lg-center">
                                    <h1 id="optionDOverlay" style="font-size: 1.7vw;
                                        word-break: break-all;
                                        color: white;
                                        font-weight: bold;
                                        max-height: 100px;">Incorrecta</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="menu3" class="tab-pane fade">
                <h3>Validate the data</h3>
                <div class="row no-gutters progressBar">
                    <div class="col">
                        <div id="myProgress">
                            <div id="myBar"></div>
                        </div>
                    </div>
                </div><button class="btn btn-primary" id="initBarProgress" type="button" onclick="move()" style="display: none;">Move</button>
                <div class="container" id="resultsShow" style="margin-top: 15px;display: none;">
                    <div class="row">
                        <div class="col-sm-4 d-sm-flex justify-content-sm-start colorCustom">
                            <h6>Change the color of the letters:&nbsp;</h6><input type="color" style="border-radius: 50%;width: 6%;" onchange="changeColorFont(this.value)" value="#FFFFFF">
                        </div>
                        <div class="col-sm-3 d-sm-flex justify-content-sm-start fakeQuizBTNOption">
                            <input type="checkbox" data-toggle="toggle" data-on="Fake Quiz" data-off="Not Fake Quiz" data-onstyle="danger" data-offstyle="success" id="toggle-two" onchange="fakeButton(this)">
                        </div>
                        <div class="col-sm-5 d-sm-flex justify-content-sm-end colorCustomBack">
                            <h6>It is also possible to customize the back ground color:&nbsp;</h6><input type="color" style="border-radius: 50%;width: 6%;" value="#708090" onchange="changeColor(this.value)"></div>
                    </div>
                    <div class="photo-card" style="margin-top: 10px;">
                        <div id="photoBackGround" class="photo-background" style="background-image: url(&quot;Home/img/product-aeon-feature.jpg&quot;);">
                            <div class="d-lg-flex d-xl-flex justify-content-lg-end align-items-lg-end justify-content-xl-end text-block">
                                <h4 class="d-lg-flex d-xl-flex justify-content-lg-end align-items-lg-end align-items-xl-end" id="questionsNumber" style="margin-right: 6px;">5&nbsp;</h4>
                                <h4 class="d-lg-flex d-xl-flex justify-content-lg-end align-items-lg-end align-items-xl-end" id="questionsText" style="margin-right: -134px;">Questions</h4>
                            </div>
                        </div>
                        <div class="text-right photo-details" style="color: #212529;">
                            <div class="row">
                                <div class="col">
                                    <h1 class="text-left" id="quizNameCard">Quiz uno</h1>
                                </div>
                                <div class="col"><i class="fa fa-star-o" style="color: rgb(255,255,255);font-size: 21px;cursor: pointer;cursor: pointer;" onclick="isFavorite(this)"></i></div>
                            </div>
                            <p id="quizCreated">Created at 05 May 2020</p>
                        </div>
                    </div>
                    <h3 style="margin-top: 5px;">Define&nbsp;the&nbsp;order of&nbsp;each&nbsp;question<br></h3>
                    <div class="row" style="margin-top: 5px;">
                        <div class="col">
                            <ul id="sortable">
                                <li>
                                    <div class="border-dark shadow row">
                                        <div class="d-lg-flex align-items-lg-center col-md-1"><img class="img-fluid d-lg-flex align-items-lg-center" src="Home/img/1200px-No_image_available.svg.png"></div>
                                        <div class="col-sm-11">
                                            <div class="row d-lg-flex" style="margin: 11px;">
                                                <div class="col d-lg-flex align-items-lg-center">
                                                    <h4 class="text-left d-lg-flex align-items-lg-center">Pregunta</h4>
                                                </div>
                                                <div class="col">
                                                    <h4 class="text-right">10pts</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!-- Modal -->
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" id="launchModal" style="display: none;"></button>
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content modalCreatedQuiz">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel"></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col" style="text-align: center;">
                                        <h4 id="modalBody"></h4>
                                    </div>
                                </div>
                                <div style="display: none;" id="uuidQuiz"></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary backToMenuBTN" data-dismiss="modal" onclick="backToMenu()">Back to menu</button>
                                <button type="button" class="btn btn-info" data-dismiss="modal" onclick="createAnotherQuiz()">Create another quiz</button>
                                <!--<button type="button" class="btn btn-success" onclick="playQuiz()">Play quiz</button>-->
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <script src="Home/js/jquery.min.js"></script>
        <script src="Home/bootstrap/js/bootstrap.min.js"></script>
        <script src="Home/js/DragAndDrop.js"></script>
        <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/froala-editor@3.1.0/js/froala_editor.pkgd.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="Home/js/Overlay.js"></script>
        <script src="Home/js/Hint/enjoyhint.min.js"></script>
        <script src="Home/js/Hint/jquery.enjoyhint.js"></script>
        <script src="Home/js/Hint/HintAppQuizCreate.js"></script>
        <script src="Home/js/Slide.js"></script>
        <script src="Home/js/sidebar.js"></script>
        <script src="Home/js/CrateQuiz.js"></script>
        <script src="Home/js/timeOutSession.js"></script>
        <script src="Home/js/githubVersion.js"></script>
    </body>

</html>