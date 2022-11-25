<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="utf-8">
    <title>AviaTales - продажа дешевых авиабилетов</title>
    <link rel="stylesheet" href="${contextPath}/css/start.css"/>
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
        <h1 style="padding-bottom: 50px; text-align: center">Покупка авиабилетов</h1>
        <div class="container">
            <form name="startForm" id="sForm" class="row g-1 mx-auto" action="${contextPath}/search" method="post" onsubmit="return inCheck()">
                <div class="col">
                    <label for="from" class="form-label">Откуда</label>
                    <input list="brow" class="form-control" id="from" name="from" type="text" placeholder="Город">
                </div>
                <div class="col">
                    <label for="to" class="form-label">Куда</label>
                    <input list="brow" class="form-control" id="to" name="to" type="text" placeholder="Город">
                </div>
                <div class="col">
                    <label for="fdate" class="form-label">Дата отправления</label>
                    <input class="form-control" id="fdate" name="fdate" type="text" placeholder="Когда" onfocus="(this.type='date')" onblur="(this.type='text')">
                </div>
                <div class="col">
                    <label for="adate" class="form-label">Дата возвращения</label>
                    <input class="form-control" id="adate" name="adate" type="text" placeholder="Когда" onfocus="(this.type='date')" onblur="(this.type='text')">
                </div>
                <div class="col">
                    <label for="passengers" class="form-label">Количество пассажиров</label>
                    <input class="form-control" id="passengers" name="passengers_count" type="number">
                </div>
                <div class="col">
                    <button id="uuu" class="btn btn-primary" style="margin-top: 32px" type="submit" onclick="">Найти билеты</button>
                </div>
            </form>
            <div id="alert">
            </div>
            <datalist class="dropdown" id="brow">
                <c:forEach var="city" items="${requestScope.cities}">
                    <option value="${city}">
                </c:forEach>
            </datalist>
        </div>
    </section>
    <section>
        <div class="container">
            <h2>Отличные места для посещения</h2>
            <div style="padding-top: 15px" id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="row">
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <img src="${contextPath}/images/krasnaia-ploshchad-khram-vasiliia-blazhennogo-moskovskii-kre.jpg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <img src="${contextPath}/images/krasnaia-ploshchad-khram-vasiliia-blazhennogo-moskovskii-kre.jpg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <img src="${contextPath}/images/krasnaia-ploshchad-khram-vasiliia-blazhennogo-moskovskii-kre.jpg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="row">
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <img src="${contextPath}/images/krasnaia-ploshchad-khram-vasiliia-blazhennogo-moskovskii-kre.jpg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <img src="${contextPath}/images/krasnaia-ploshchad-khram-vasiliia-blazhennogo-moskovskii-kre.jpg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <img src="${contextPath}/images/krasnaia-ploshchad-khram-vasiliia-blazhennogo-moskovskii-kre.jpg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="row">
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <img src="${contextPath}/images/krasnaia-ploshchad-khram-vasiliia-blazhennogo-moskovskii-kre.jpg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <img src="${contextPath}/images/krasnaia-ploshchad-khram-vasiliia-blazhennogo-moskovskii-kre.jpg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <img src="${contextPath}/images/krasnaia-ploshchad-khram-vasiliia-blazhennogo-moskovskii-kre.jpg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
<footer>

</footer>
</body>
<script type="text/javascript" src="${contextPath}/js/darktheme.js"></script>
<script type="text/javascript" src="${contextPath}/js/input_check.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</html>
