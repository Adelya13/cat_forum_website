<%@tag description="header" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<header>
    <nav class="navbar navbar-expand-lg navbar-light mynavbar">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Cats</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<c:url value="/profile"/>">Главная</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/forum"/>">Форум</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/edit"/>">Настройки</a>
                    </li>
                    <button type="submit" id="changeTheam">change</button>
                </ul>
            </div>
        </div>
    </nav>
</header>