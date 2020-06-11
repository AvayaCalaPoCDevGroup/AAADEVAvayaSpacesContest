<%-- 
Document   : index
Created on : May 12, 2020, 6:32:05 PM
Author     : umansilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>

    </head>
    <body>
        <h1>Parse Excel</h1>

        <input type="file" id="my_file_input" />
        <div id='my_file_output'></div>
    </body>
    <script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
    crossorigin="anonymous"></script>
    <script>

        var oFileIn;

        $(function () {
            oFileIn = document.getElementById('my_file_input');
            if (oFileIn.addEventListener) {
                oFileIn.addEventListener('change', filePicked, false);
            }
        });


        function filePicked(oEvent) {
            // Get The File From The Input
            var oFile = oEvent.target.files[0];
            var sFilename = oFile.name;
            // Create A File Reader HTML5
            var reader = new FileReader();

            // Ready The Event For When A File Gets Selected
            reader.onload = function (e) {
                var data = e.target.result;
                var cfb = XLSX.CFB.read(data, {type: 'binary'});
                var wb = XLSX.parse_xlscfb(cfb);
                // Loop Over Each Sheet
                wb.SheetNames.forEach(function (sheetName) {
                    // Obtain The Current Row As CSV
                    var sCSV = XLSX.utils.make_csv(wb.Sheets[sheetName]);
                    var oJS = XLSX.utils.sheet_to_row_object_array(wb.Sheets[sheetName]);

                    $("#my_file_output").html(sCSV);
                    console.log(oJS);
                });
            };

            // Tell JS To Start Reading The File.. You could delay this if desired
            reader.readAsBinaryString(oFile);
        }
    </script>
</html>
