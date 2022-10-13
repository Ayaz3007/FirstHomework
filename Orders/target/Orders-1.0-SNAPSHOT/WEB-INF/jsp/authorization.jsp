<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<form action="/order_delivery" method="get">
    <input type="text" placeholder="Введите логин" name="login">
    <input type="text" placeholder="Введите пароль" name="password">
    <button type="submit">Sign in</button>
</form>
<form action="/orders_list" method="get">
    <button type="submit">Go to orders list</button>
</form>
<form action="/create_order">
    <button type="submit">Create order</button>
</form>

</body>
</html>