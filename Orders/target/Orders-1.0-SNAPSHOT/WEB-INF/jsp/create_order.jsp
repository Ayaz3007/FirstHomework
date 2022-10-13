<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: StripeScott
  Date: 13.10.2022
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create order</title>
</head>
<body>
<h3>Список продуктов:</h3>
<form method="post">
    <% for(String product: (List<String>)request.getAttribute("productsList")) { %>
    <input type="checkbox" name="<%=product%>" value="true"><%= product%>
</</input>
    <%}%>
    <button type="submit">Confirm order</button>
</form>


</body>
</html>
