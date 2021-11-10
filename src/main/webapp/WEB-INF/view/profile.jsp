<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/view/_header.jsp" %>

<form action="<c:url value="/profile"/>" method="post">
   <tt:header/>
    <div class="container-fluid main-body" id="main-body">

        <div class="body-info" id="userinfo">
                <button type="submit" name="addCatBtn" class="btn my-btn item-margins" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                    Добавь котика
                </button>
                <div class="card" style="width: 18rem;">
                    <img src="${pageContext.request.contextPath}/photo/user_avatars/default.jpg" class="card-img-top img-fluid">
                    <div class="card-body">
                        <h5 class="card-title">${username}</h5>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">${email}</li>
                    </ul>
                </div>
            <div class="cats">
                <div>${infoMessage}</div>
                <c:forEach items="${allcats}" var="cat">
                    <tt:cat cat="${cat}"/>
                </c:forEach>
            </div>
        </div>

        <div class="body-function" id="text">
            <div class="panel">
                <input type="text" name="tittle" placeholder="Tittle" rows="2" class="form-control input-lg item-margins" />
                <textarea name="write_post" placeholder="Что хочешь написать о котиках сегодня?" rows="2" class="form-control input-lg item-margins"></textarea>
                <input type="text" name="tags" id="tags" placeholder="add tags" rows="2" class="form-control input-lg item-margins" />
                <h2>${message}</h2>
                <footer class="panel-footer">
                    <button class="btn my-btn btn-add" type="submit" name="add_post" id="add-post">Post</button>
                </footer>
            </div>
            <span class="text-in-main">
                    <h2>${postMessage}</h2>
            </span>
            <div class="posts">
                <c:forEach items="${allposts}" var="post">
                    <tt:post post="${post}"/>
                </c:forEach>
            </div>
        </div>

    </div>
</form>
<%@include file="/WEB-INF/view/_footer.jsp" %>