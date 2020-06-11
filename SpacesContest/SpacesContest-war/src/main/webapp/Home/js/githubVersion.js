/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

obtenerGitHubVersion();
function obtenerGitHubVersion() {

    console.log("obtenerGitHubVersion");
    var data = new FormData();
    var jsonObject = {
        "action": "obtenerGitHubVersion"
    };
    data.append("jsonObject", JSON.stringify(jsonObject));

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            try {
                var jsonObjectRequest = JSON.parse(this.responseText);
                if (jsonObjectRequest.status === "ok") {
                    document.getElementById('githubVersion').innerHTML = "";
                    document.getElementById('githubVersion').innerHTML = jsonObjectRequest.message;
                } else {
                    console.log(jsonObjectRequest.message);
                    document.getElementById('githubVersion').innerHTML = "";
                    document.getElementById('githubVersion').innerHTML = "3.7.0.0.0";
                }
            } catch (err) {
                console.log(err.toString());
                document.getElementById('githubVersion').innerHTML = "";
                document.getElementById('githubVersion').innerHTML = "3.7.0.0.0";
            }
        }
    });

    xhr.open("POST", getAbsolutePath() + "AvayaSpacesContest");

    xhr.send(data);

    function getAbsolutePath() {
        var loc = window.location;
        var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
        return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
    }

}
