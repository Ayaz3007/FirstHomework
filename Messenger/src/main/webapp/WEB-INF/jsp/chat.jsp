<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: StripeScott
  Date: 30.09.2022
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
</head>
<body>
<!-- Поле для ввода сообщения -->
<form action="" method="get">
    <input type="text" name="message">
    <button type="submit">Send</button>
</form>

<% for(String message: (List<String>) request.getAttribute("messages")) { %>
    <p><%= message%></p>
<% } %>

</body>
</html>
