<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: StripeScott
  Date: 16.10.2022
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DB Tables</title>
    <style>
        form{
            float: left;
            margin-right: 10px;
        }
        table{
            border: 1px solid black;
            border-collapse: collapse;
        }
        th {
            border: 1px solid black;
        }
        td {
            border: 1px solid black;
        }
        .form-table{
            float: right;
        }
    </style>
</head>
<form method="get">
    <input type="text" name="searchProduct">
    <input type="hidden" name="searchType" value="product">
    <button type="submit">Поиск продукта</button>
</form>
<form method="get">
    <input type="text" name="searchStore">
    <input type="hidden" name="searchType" value="store">
    <button type="submit">Поиск магазина</button>
</form>
<form method="get">
    <input type="text" name="searchRelationProductId">
    <input type="text" name="searchRelationStoreId">
    <input type="hidden" name="searchType" value="relation">
    <button type="submit">Поиск связей</button>
</form>
<form method="get">
    <button type="submit" name="searchType" value="back">Сбросить фильтры</button>
</form>
<br>
<form method="post" class="form-table">
<table>
    <caption>Products</caption>
    <tr>
        <th></th>
        <th>id</th>
        <th>name</th>
    </tr>
    <c:forEach var="product" items="${listOfProducts}">
    <tr>
        <td>
            <input type="checkbox" name="productDelete" value="${product.id}">
        </td>
        <td><c:out value="${product.id}" /></td>
        <td><c:out value="${product.name}" /></td>
    </tr>
    </c:forEach>
</table>
    <input type="hidden" name="deleteType" value="product">
    <input type="submit" value="Delete">
</form>
<form method="post" class="form-table">
<table>
    <caption>Stores</caption>
    <tr>
        <th></th>
        <th>id</th>
        <th>name</th>
    </tr>
    <c:forEach var="store" items="${listOfStores}">
        <tr>
            <td>
                <input type="checkbox" name="storeDelete" value="${store.id}">
            </td>
            <td><c:out value="${store.id}" /></td>
            <td><c:out value="${store.name}" /></td>
        </tr>
    </c:forEach>
</table>
    <input type="hidden" name="deleteType" value="store">
<input type="submit" value="Delete">
</form>
<form method="post" class="form-table">
<table>
    <caption>Relations</caption>
    <tr>
        <th></th>
        <th>id</th>
        <th>product id</th>
        <th>store id</th>
        <th>amount</th>
    </tr>
    <c:forEach var="relation" items="${listOfRelations}">
        <tr>
            <td>
                <input type="checkbox" name="relationDelete" value="${relation.id}">
            </td>
            <td><c:out value="${relation.id}" /></td>
            <td><c:out value="${relation.productId}" /></td>
            <td><c:out value="${relation.storeId}" /></td>
            <td><c:out value="${relation.amount}" /></td>
        </tr>
    </c:forEach>
</table>
    <input type="hidden" name="deleteType" value="relation">
    <input type="submit" value="Delete">
</form>
<br />
<form action="/" method="get">
    <button type="submit">Вернуться</button>
</form>
</body>
</html>
