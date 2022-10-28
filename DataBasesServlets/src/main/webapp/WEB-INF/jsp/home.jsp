<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home, sweet home</title>
    <style>
        form {
            padding-bottom: 5px;
        }
    </style>
</head>
<body>

<form class = "save" method="post">
    <label>Введите имя продукта:
        <input type="text" name="entity">
    </label>
    <input type="hidden" name="inputType" value="product">
    <input type="hidden" name="requestType" value="add">
    <button type="submit">Добавить продукт</button>
</form>

<form class = "save" method="post">
    <label>Введите имя магазина:
        <input type="text" name="entity">
    </label>
    <input type="hidden" name="inputType" value="store">
    <input type="hidden" name="requestType" value="add">
    <button type="submit">Добавить магазин</button>
</form>

<form class = "save" method="post">
    <label>Введите данные связи:
        <input placeholder="ID продукта" type="text" name="relationProductId">
        <input placeholder="ID магазина" type="text" name="relationStoreId">
        <input placeholder="Количество" type="text" name="relationAmount">
    </label>
    <input type="hidden" name="inputType" value="relation">
    <input type="hidden" name="requestType" value="add">
    <button type="submit">Добавить связь</button>
</form>

<form class = "update" method="post">
    <label>Введите id продукта:
        <input type="text" name="id">
    </label>
    <label>Введите имя продукта:
        <input type="text" name="entity">
    </label>
    <input type="hidden" name="inputType" value="product">
    <input type="hidden" name="requestType" value="update">
    <button type="submit">Изменить продукт</button>
</form>

<form class = "update" method="post">
    <label>Введите id магазина:
        <input type="text" name="id">
    </label>
    <label>Введите имя магазина:
        <input type="text" name="entity">
    </label>
    <input type="hidden" name="inputType" value="store">
    <input type="hidden" name="requestType" value="update">
    <button type="submit">Изменить магазин</button>
</form>

<form class = "update" method="post">
    <label>Введите id связи:
        <input type="text" name="id">
    </label>
    <label>Введите данные связи:
        <input placeholder="ID продукта" type="text" name="relationProductId">
        <input placeholder="ID магазина" type="text" name="relationStoreId">
        <input placeholder="Количество" type="text" name="relationAmount">
    </label>
    <input type="hidden" name="inputType" value="relation">
    <input type="hidden" name="requestType" value="update">
    <button type="submit">Изменить связь</button>
</form>
<form action="/tables" method="get">
    <button type="submit">Go to tables</button>
</form>
<c:if test="${error == true}">
    <p>Вводите данные правильно!</p>
</c:if>
</body>
</html>