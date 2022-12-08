<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Search page</title>
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
    <div class="container">
        <form class="row g-1" action="${contextPath}/search" method="post" onsubmit="return inCheck()">
            <div class="col">
                <label for="from" class="form-label">Откуда</label>
                <input class="form-control" id="from" name="from" type="text" placeholder="Город">
            </div>
            <div class="col">
                <label for="to" class="form-label">Куда</label>
                <input class="form-control" id="to" name="from" type="text" placeholder="Город">
            </div>
            <div class="col">
                <label for="fdate" class="form-label">Дата отправления</label>
                <input class="form-control" id="fdate" name="flying_date" type="text" placeholder="Когда" onfocus="(this.type='date')" onblur="(this.type='text')">
            </div>
            <div class="col">
                <label for="adate" class="form-label">Дата возвращения</label>
                <input class="form-control" id="adate" name="arrival_date" type="text" placeholder="Когда" onfocus="(this.type='date')" onblur="(this.type='text')">
            </div>
            <div class="col">
                <button class="btn btn-primary" style="width: 200px; margin-top: 32px" type="submit">Найти билеты</button>
            </div>
        </form>
    </div>
</section>
    <section style="padding-top: 50px">

        <c:choose>
        <c:when test="${empty requestScope.tickets}">
        <div>
            <h2>Сожалеем, у нас нет билетов для вас :(</h2>
        </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="ticket" items="${requestScope.tickets}">
            <div class="container border border-2" style="width: 40%; padding: 10px 15px; margin-top: 10px">
                <h3>Билет</h3>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            Cамолет:
                            <p>${ticket.fromPlaneName}</p>
                        </div>
                        <div class="col">
                            Место:
                            <p>${ticket.seatPlace}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Куда:
                            <p>${ticket.toCityName}</p>
                        </div>
                        <div class="col">
                            Откуда:
                            <p>${ticket.fromCityName}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Дата:
                            <p>${ticket.fromDate}</p>
                        </div>
                        <div class="col">
                            Время вылета:
                            <p>${ticket.fromFlightTime}</p>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            Cамолет:
                            <p>${ticket.toPlaneName}</p>
                        </div>
                        <div class="col">
                            Место:
                            <p>${ticket.seatPlace}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Куда:
                            <p>${ticket.fromCityName}</p>
                        </div>
                        <div class="col">
                            Откуда:
                            <p>${ticket.toCityName}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Дата:
                            <p>${ticket.toDate}</p>
                        </div>
                        <div class="col">
                            Время вылета:
                            <p>${ticket.toFlightTime}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Цена:
                            <p>${ticket.price}</p>
                        </div>
                    </div>
                    <form action="${contextPath}/search/booking" method="get">
                        <input type="hidden" name="ticket" value="${ticket.id}">
                        <div class="row">
                            <div class="col">
                                <input class="btn btn-primary" type="submit" value="Забронировать">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            </c:forEach>
        </c:otherwise>
        </c:choose>
</section>
</body>
<script type="text/javascript" src="${contextPath}/js/darktheme.js"></script>
<script type="text/javascript" src="${contextPath}/js/input_check.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</html>
