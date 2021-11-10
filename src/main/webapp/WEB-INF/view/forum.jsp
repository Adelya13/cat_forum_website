
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLEncoder"%>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/view/_header.jsp" %>
<form action="<c:url value="/forum"/>" method="post">
    <tt:header/>
    <div class="container-fluid post-body" id="post-body">
        <div class="panel">
            <input class="form-control me-2" type="search" name="searchTag" placeholder="Search" value="${tag}" aria-label="Search">
            <footer class="panel-footer">
                <button class="btn my-btn btn-add" type="submit" name="searchBtn" id="add-post">Post</button>
            </footer>
        </div>
        <span class="text-in-main">
                <h2>${message}</h2>
        </span>
        <div class="posts">
            <c:forEach items="${allposts}" var="post">
                <tt:post post="${post}"/>
            </c:forEach>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/view/_footer.jsp" %>





