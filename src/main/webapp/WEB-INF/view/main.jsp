<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/WEB-INF/view/_header.jsp" %>

<div class="container-fluid align-content-center" >
    <main class="form-main">
        <form action="<c:url value="/main"/>" method="post">
            <div class="mb-3">
                <div class="form-floating">
                    <h2 style="margin-top: 25vh; text-align: center; font-family: cursive;">
                        Hello, ${username}
                    </h2>


                </div>
            </div>
        </form>
    </main>
</div>

<%@include file="/WEB-INF/view/_footer.jsp" %>
