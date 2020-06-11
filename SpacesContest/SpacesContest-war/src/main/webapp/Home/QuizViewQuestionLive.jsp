<%-- 
    Document   : QuizViewQuestionLive
    Created on : Jun 8, 2020, 10:19:10 AM
    Author     : umansilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enroled People Live</title>
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
        <link rel="stylesheet" href="Home/css/sidebar.css">
        <link rel="stylesheet" href="Home/css/SideBarAvaya.css">
        <link rel="stylesheet" href="Home/css/mediaQuerysQuizView.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
    </head>
    <% String enrolados = (String) request.getAttribute("enrolados"); %>
    <% String answers = (String) request.getAttribute("answers"); %>
    <% String llave = (String) request.getAttribute("llave"); %>
    <% String pregunta = (String) request.getAttribute("pregunta");%>
    <% String integrantesEvento = (String) request.getAttribute("integrantesEvento");%>
    <body style="padding-left: 0px;">
        <div id="enrolados" style="display: none;"><%=enrolados%></div>
        <div id="llave" style="display: none;"><%=llave%></div>
        <div id="answers" style="display: none;"><%=answers%></div>
        <div id="pregunta" style="display: none;"><%=pregunta%></div>
        <div class="loader loader-default" data-blink id="loaderDisplay"></div>
        <%@include file="Template/sidebar.jsp" %>
        <div class="container-fluid article-clean" style="padding-left: 10px;">

            <nav class="navbar navbar-light navbar-expand-md">
                <div class="container-fluid">
                    <p class="text-left d-xl-flex justify-content-xl-start align-items-xl-start" style="width: 254px;color: rgb(0,0,0);font-size: 15px;font-family: Lora, serif;margin: 13px;">Avaya CALA PoC Develpment Group</p>

                </div>
            </nav>
            <h5>Total registrados al evento: <%=integrantesEvento%></h5>

            <hr>
            <div class="row">
                <div class="col">
                    <h5>Enroled People</h5>
                    <table id="enroladosTable" class="table table-bordered display compact order-column" style="width:100%">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>eMail</th>
                                <th>Enroled time</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="enroladosTableBody">
                            <tr>
                                <td>Uriel Mansilla</td>
                                <td>umansilla@avaya.com</td>
                                <td>08-06-2020 10:30:00</td>
                                <td>
                                    <div class="row">
                                        <div class="col">
                                            <div class="btn-group" role="group" aria-label="Basic example">
                                                <button type="button" class="btn btn-danger" onclick="unroll()">Unrolling</button>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>

                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>eMail</th>
                                <th>Enroled time</th>
                                <th>Actions</th>

                            </tr>
                        </tfoot>
                    </table>
                </div>
                <hr>
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h5>Responses by Question</h5>
                        </div>
                        <div class="col-sm-2">
                            <button type="button" class="btn btn-primary btn-sm" onclick="cleanResponses()">Clean</button>
                        </div>
                    </div>
                    
                    <h6 id="question">¿Question?</h6>
                    <table id="responsesByQuestion" class="table table-bordered display compact order-column" style="width:100%">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>eMail</th>
                                <th>Answer</th>
                            </tr>
                        </thead>
                        <tbody id="responsesByQuestionBody">

                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>eMail</th>
                                <th>Answer</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>


        </div>
        <script src="Home/js/jquery.min.js"></script>
        <script src="Home/bootstrap/js/bootstrap.min.js"></script>
        <script src="Home/js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
        <script src="Home/js/sideBarMenu.js"></script>
        <script src="Home/js/timeOutSession.js"></script>
        <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
        <script src="Home/js/QuizViewQuestionLive.js"></script>
    </body>
</html>
