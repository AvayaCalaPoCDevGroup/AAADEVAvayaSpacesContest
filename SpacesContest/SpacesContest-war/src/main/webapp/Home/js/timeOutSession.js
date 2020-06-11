/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let myTimeOut = setTimeout(myFunction, 3600000);
function myFunction() {
    var result = false;
    setTimeout(validateResponse, 300000);
    result = confirm("Your session has expired, do you want to continue?");
    function validateResponse() {
        if (result) {
            renovateSession();
        } else {
            closeSession();
        }
    }
    function renovateSession() {
        console.log("Session renovada");
        setTimeout(myFunction, 3600000);
    }
}

function renovateSession() {
    document.getElementById('loaderDisplay').classList.add('is-active');
    document.getElementById("loaderDisplay").setAttribute("data-text", "Renewing session.");
    var data = new FormData();
    data.append("action", "LogOut");
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            document.getElementById("loaderDisplay").classList.remove("is-active");
            document.getElementById("loaderDisplay").setAttribute("data-text", "");
            try {
                var jsonObject = JSON.parse(this.responseText);
                if (jsonObject.status === "ok") {
                    console.log("Session renovada");
                }
                if (jsonObject.status === "error") {
                    console.log(jsonObject.message);
                }
            } catch (error) {
                console.error(error);
            }
        }
    });
    xhr.open("POST", getAbsolutePath() + "LogIn");
    xhr.send(data);
}


function closeSession() {
    document.getElementById('loaderDisplay').classList.add('is-active');
    document.getElementById("loaderDisplay").setAttribute("data-text", "Closing session.");
    var data = new FormData();
    data.append("action", "LogOut");
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            document.getElementById("loaderDisplay").classList.remove("is-active");
            document.getElementById("loaderDisplay").setAttribute("data-text", "");
            try {
                var jsonObject = JSON.parse(this.responseText);
                if (jsonObject.status === "ok"
                        && jsonObject.message === "Log OUT") {
                    window.location.reload();
                }
                if (jsonObject.status === "error"
                        && jsonObject.message === "Usuario no Autorizado") {
                    window.location.reload();
                }
            } catch (error) {
                console.error(error);
                window.location.reload();
            }
        }
    });
    xhr.open("POST", getAbsolutePath() + "LogIn");
    xhr.send(data);
}




function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
}
