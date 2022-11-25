<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="utf-8">
    <title>Settings</title>
    <link rel="stylesheet" href="${contextPath}/css/settings.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <nav class="navbar fixed-top navbar-expand-lg bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="${contextPath}/">
                <img width="75" height="50" src="${contextPath}/images/AVIATALES_free-file.png" alt="logo">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav  ms-auto">
                    <c:choose>
                        <c:when test="${sessionScope.authenticated != true}">
                            <li class="nav-item">
                                <a class="nav-link ms-auto text-white" href="${contextPath}/sign-in">Войти</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${contextPath}/sign-up">Регистрация</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item dropdown text-white">
                                <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Профиль
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end">
                                    <li><a class="dropdown-item" href="${contextPath}/settings">Настройки</a></li>
                                    <c:if test="${sessionScope.isAdmin == true}">
                                        <li><a class="dropdown-item" href="${contextPath}/admin">Админ. центр</a></li>
                                    </c:if>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="#">Темная тема</a></li>
                                </ul>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</header>
<section style="padding-top: 100px">
    <div class="container border border-2 rounded" style="width: 40%; padding: 20px 15px;">
        <h3>Личные данные</h3>
        <form class="row g-3 form-floating" action="" method="post">
            <div class="col-8">
                <input class="form-control" id="phone" name="phone" placeholder="Phone" type="text" aria-label="Phone">
            </div>
            <div class = "col-4">
                <input class="form-control btn btn-primary" type="submit" value="Добавить телефон">
            </div>
        </form>
        <form class="row g-3 form-floating" action="" method="post">
            <div class="col-8">
                <input class="form-control" id="email" name="email" placeholder="E-mail" type="text" aria-label = "E-mail">
            </div>
            <div class = "col-4">
                <input class="form-control btn btn-primary" type="submit" value="Изменить e-mail">
            </div>
        </form>
    </div>
</section>
<section style="padding-top: 50px">
    <div class="container border border-2 rounded" style="width: 40%; padding: 10px 15px;">
        <h3>Личные данные</h3>
        <div class="row gx-3">
            <form class="col orm-floating" action="" method="post">
                <div class="">
                    <button class="form-control btn btn-primary" type="submit" name="exit">Выйти из аккаунта</button>
                </div>
            </form>
            <form class="col form-floating" action="" method="post">
                <div class="">
                    <button class="form-control btn btn-primary" type="submit" name="delete">Удалить аккаунт</button>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
<script type="text/javascript" src="${contextPath}/js/darktheme.js"></script>
<script type="text/javascript" src="${contextPath}/js/input_check.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</html>
