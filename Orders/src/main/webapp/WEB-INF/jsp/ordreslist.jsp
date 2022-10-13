<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: StripeScott
  Date: 13.10.2022
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders list</title>
</head>
<body>
<% for(List<String> order: (List<List<String>>) request.getAttribute("ordersList")){ %>
<div>
    <%= String.join(", ", order)%>
</div>
<%}%>
</body>
</html>
