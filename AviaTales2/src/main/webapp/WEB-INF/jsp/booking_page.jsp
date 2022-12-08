<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${contextPath}/css/start.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${contextPath}/js/darktheme.js"></script>
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
                            <li class="nav-item dropdown">
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
<section>
    <p>${requestScope.ticket}</p>
</section>
</body>
<script type="text/javascript" src="${contextPath}/js/darktheme.js"></script>
<script type="text/javascript" src="${contextPath}/js/input_check.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</html>
