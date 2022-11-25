<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Sign up</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${contextPath}/css/start.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${contextPath}/js/darktheme.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/form-sign-up.css"/>
</head>
<body class="text-center">
    <main class="form-sign">
        <form action="${contextPath}/sign-up" method="post">
            <a href="${contextPath}/">
                <img class="mb-4" src="${contextPath}/images/AVIATALES_free-file.png" alt="logo" width="120" height="80">
            </a>
            <h1 class="h3 mb-3 fw-normal">Регистрация</h1>
            <div class="form-floating">
                <input id="floatingUsername" name="username" class="form-control" type="text">
                <label for="floatingUsername">Username</label>
            </div>
            <div class="form-floating">
                <input id="floatingInput" name="e-mail" class="form-control" type="email">
                <label for="floatingInput">Email address</label>
            </div>
            <div class="form-floating">
                <input id="floatingPassword" name="password" class="form-control" type="password">
                <label for="floatingPassword">Password</label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Регистрация</button>
            <a href="${contextPath}/sign-in">Уже есть аккаунт? Войти</a>
        </form>
    </main>
</body>
</html>
