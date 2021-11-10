<%--
  Created by IntelliJ IDEA.
  User: Adelya
  Date: 08.11.2021
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags" %>

<%@include file="/WEB-INF/view/_header.jsp" %>
<form action="<c:url value="/addCat"/>" method="post">
    <tt:header/>
    <div class="container-fluid post-body"  >

        <h1 class="text-decoration">Добавь котика</h1>
        <hr>
        <!-- edit form column -->
        <div class="col-md-9 personal-info">
            <form class="form-horizontal">
                <div class="form-group ">
                    <div class="mb-3">
                        <label class="col-lg-3 control-label">Имя кота</label>
                        <div class="col-lg-8">
                            <input name="catname" class="form-control" type="text" value="">
                        </div>

                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label">Информация о коте</label>
                        <textarea name="description" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                    </div>

                    <h6>Выберите фото</h6>
                    <input type="file" class="form-control">

                    <div>${catmessage}</div>
                    <button type="submit" class="btn my-btn btn-add " name="addbtn">
                        добавить
                    </button>
                </div>
            </form>
        </div>
        <hr>
        <div>${infoMessage}</div>
        <div class="row cats">
            <c:forEach items="${allcats}" var="cat">
                <tt:cat cat="${cat}"/>
            </c:forEach>
        </div>
    </div>

</form>
<%@include file="/WEB-INF/view/_footer.jsp" %>

