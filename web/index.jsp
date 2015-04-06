<%-- 
    Document   : index
    Created on : Oct 5, 2014, 11:37:57 PM
    Author     : fmsis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JS-Java Online Chess Agent</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
         <script type="text/javascript" src="jquery-2.1.1.js"></script>
        <script type="text/javascript" src="chessboard.js"></script>
        <script type="text/javascript" src="stopwatch.js"></script>
    </head>
    <body onload="init()">
        <div id="container">
        <div id="header">White Move</div>
        <div id="leftnav">
        <div class="example">
                <div class="label">Count up from now</div>
                <div id="demo1" class="demo">00:00:00</div>
            </div>
<!--            <div class="example">
                <div class="label">Click to toggle (start/stop)</div>
                <div id="demo2" class="demo">00:00:00</div>
            </div>
            <div class="example">
                <div class="label">Click to reset</div>
                <div id="demo3" class="demo">00:00:00</div>
            </div>
            <div class="example">
                <div class="label">Count up from seed time (10000000 milliseconds)</div>
                <div id="demo4" class="demo">00:00:00</div>
            </div>
            <div class="example">
                <div class="label">2 second update interval</div>
                <div id="demo5" class="demo">00:00:00</div>
            </div>             -->
        </div>

        <div id="rightnav"></div>

        <div id="body"><canvas id="chess" width="800" height="800"></canvas></div>
        <div id="footer">Copyright@Md.Shafikul Islam</div>
        </div>
        <script>      
        $(document).ready(function() {
            $('#demo1').stopwatch().stopwatch('start');
//            $('#demo2').stopwatch().click(function(){ 
//                $(this).stopwatch('toggle');
//            });
//            $('#demo3').stopwatch().click(function(){ 
//                $(this).stopwatch('reset');
//            }).stopwatch('start');
//            $('#demo4').stopwatch({startTime: 10000000}).stopwatch('start');
//            $('#demo5').stopwatch({updateInterval: 2000}).stopwatch('start');           
        });
        </script>
    </body>
</html>

