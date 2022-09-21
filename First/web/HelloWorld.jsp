<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% PrintWriter outln = response.getWriter(); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Greetings!</title>
    <style>
        body{
            background: #000 url("HelloServletResources/Tavern.jpg") no-repeat;
            background-size: cover;
        }
        img{
            width:100px;
            height: 100px;
        }
        a {
            position: absolute;
            top: 210px;
            left: 740px;
        }
        h1{
            color: gold;
            font-family: Papyrus,serif;
            text-align: center;
        }
        #click{
            position: absolute;
            bottom: 50px;
            left: 500px;
        }
    </style>
</head>
<body>
<main>
    <h1>Everyone’s welcome to my inn! There’s rarely a dull moment around here!</h1>
    <% outln.println("<a href = \"" + request.getContextPath() + "/content-list\">" +
        "<img src = \"HelloServletResources/lamp.png\" alt=\"lamp\"></a>"); %>
    <h1 id="click">(Click on the icon with the yellow pattern)</h1>
</main>
</body>
</html>