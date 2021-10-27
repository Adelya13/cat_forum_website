<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/WEB-INF/view/_header.jsp" %>


<div class="login-page">
    <div class="form">
        <form class="login-form" action="<c:url value="/signin"/>" method="post">

            <input type="email" name="email" placeholder="email"/>
            <input type="password" name="password" placeholder="password"/>

            <div>${message}</div>

            <button class="btn my-btn" type="submit" name="signinbtn">signin</button>

            <p class="message">Not registered? <button type="submit" name="loginbtn" >Create an account</button> </p>

        </form>
    </div>
</div>
<%@include file="/WEB-INF/view/_footer.jsp" %>
