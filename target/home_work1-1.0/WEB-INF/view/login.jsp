<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/WEB-INF/view/_header.jsp" %>

<div class="login-page">
    <div class="form">
        <form class="login-form" action="<c:url value="/login"/>" method="post">

            <input type="text" name="username" placeholder="username"/>
            <input type="email" name="email" placeholder="email"/>
            <input type="password" name="password" placeholder="password"/>

            <div>${message}</div>

            <button class="btn my-btn" type="submit" name="loginbtn">login</button>

            <p class="message">Registered? <button type="submit" name="signinbtn" >Sign in to your account</button> </p>

        </form>
    </div>
</div>

<%@include file="/WEB-INF/view/_footer.jsp" %>
