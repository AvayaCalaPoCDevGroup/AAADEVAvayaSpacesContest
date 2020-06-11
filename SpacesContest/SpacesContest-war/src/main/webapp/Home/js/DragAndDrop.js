/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//var // where files are dropped + file selector is opened
//        dropRegion = document.getElementById("drop-region"),
//        // where images are previewed
//        imagePreviewRegion = document.getElementById("image-preview");


var dropRegionClassses = document.getElementsByClassName('drop-region');

for (var i = 0; i < dropRegionClassses.length; i++) {
    dropRegionClassses[i].addEventListener('dragenter', preventDefault, false);
    dropRegionClassses[i].addEventListener('dragleave', preventDefault, false);
    dropRegionClassses[i].addEventListener('dragover', preventDefault, false);
    dropRegionClassses[i].addEventListener('drop', preventDefault, false);
    dropRegionClassses[i].addEventListener('drop', handleDrop, false);
    
}

// open file selector when clicked on the drop region
var fakeInput = document.createElement("input");
fakeInput.type = "file";
fakeInput.accept = "image/*";
fakeInput.multiple = true;

var thisGlobal;

//dropRegion.addEventListener('click', function () {
//    fakeInput.click();
//});

function clickDropRegion(_this){
    thisGlobal = _this;
    fakeInput.click();
};


fakeInput.addEventListener("change", function () {
    var files = fakeInput.files;
    handleFiles(files);
});


function preventDefault(e) {
    e.preventDefault();
    e.stopPropagation();
}

//dropRegion.addEventListener('dragenter', preventDefault, false);
//dropRegion.addEventListener('dragleave', preventDefault, false);
//dropRegion.addEventListener('dragover', preventDefault, false);
//dropRegion.addEventListener('drop', preventDefault, false);
//document.getElementById('prueba').addEventListener('drop', handleDrop, false);


function handleDrop(e) {
    for (var i = 0; i < e.path.length; i++) {
        if(e.path[i].className === "drop-region"){
            thisGlobal = e.path[i];
        }
    }
    var dt = e.dataTransfer,
            files = dt.files;

    if (files.length) {

        handleFiles(files);

    } else {

        // check for img
        var html = dt.getData('text/html'),
                match = html && /\bsrc="?([^"\s]+)"?\s*/.exec(html),
                url = match && match[1];



        if (url) {
            uploadImageFromURL(url);
            return;
        }

    }


    function uploadImageFromURL(url) {
        var img = new Image;
        var c = document.createElement("canvas");
        var ctx = c.getContext("2d");

        img.onload = function () {
            c.width = this.naturalWidth;     // update canvas size to match image
            c.height = this.naturalHeight;
            ctx.drawImage(this, 0, 0);       // draw in image
            c.toBlob(function (blob) {        // get content as PNG blob

                // call our main function
                handleFiles([blob]);

            }, "image/png");
        };
        img.onerror = function () {
            alert("Error in uploading");
        };
        img.crossOrigin = "";              // if from different origin
        img.src = url;
    }

}


function handleFiles(files) {
    for (var i = 0, len = files.length; i < len; i++) {
        if (validateImage(files[i]))
            previewAnduploadImage(files[i]);
    }
}

function validateImage(image) {
    // check the type
    var validTypes = ['image/jpeg', 'image/png', 'image/gif'];
    if (validTypes.indexOf(image.type) === -1) {
        alert("Invalid File Type");
        return false;
    }

    // check the size
    var maxSizeInBytes = 10e6; // 10MB
    if (image.size > maxSizeInBytes) {
        alert("File too large");
        return false;
    }

    return true;

}

function previewAnduploadImage(image) {

    thisGlobal.style.border = "";
    console.log(thisGlobal.children[1]);
    var divImagePreview = thisGlobal.children[1];
    if (divImagePreview.children.length > 0) {
        divImagePreview.innerHTML = "";
    }

    // create FormData
    // container
    var imgView = document.createElement("div");
    imgView.className = "image-view";
    divImagePreview.appendChild(imgView);

    // previewing image
    var img = document.createElement("img");
    imgView.appendChild(img);

    // progress overlay
    var overlay = document.createElement("div");
    imgView.appendChild(overlay);

    // read the image...
    var reader = new FileReader();
    reader.onload = function (e) {
        img.src = e.target.result;
    };
    reader.readAsDataURL(image);

    var formData = new FormData();
    formData.append('image', image);

}