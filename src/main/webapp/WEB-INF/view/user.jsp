<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/view/_header.jsp" %>
<form action="<c:url value="/user"/>" method="post">
    <tt:header/>

    <div class="container-fluid main-body" id="main-body">

        <div class="body-info" id="userinfo">

            <div class="card" style="width: 18rem;">
                <img src="${pageContext.request.contextPath}/photo/user_avatars/default.jpg" class="card-img-top img-fluid">
                <div class="card-body">
                    <h5 class="card-title">${guestUsername}</h5>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">${guestEmail}</li>
                </ul>

            </div>

            <div class="cats">
                <c:forEach items="${allcats}" var="cat">
                    <tt:cat cat="${cat}"/>
                </c:forEach>

            </div>

        </div>

        <div class="body-function" id="text">
            <div class="posts">
                <c:forEach items="${allposts}" var="post">
                    <tt:post post="${post}"/>
                </c:forEach>

            </div>
        </div>

    </div>
</form>
<%@include file="/WEB-INF/view/_footer.jsp" %>