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
<form action="<c:url value="/edit"/>" method="post" enctype="multipart/form-data">
    <tt:header/>
    <div class="container-fluid post-body"  >
        <div class="">
            <h1 class="text-decoration">Редактирование профиля</h1>
            <hr>
            <div class="row">
                <!-- left column -->
                <form>
                    <div class="col-md-3">
                        <div class="text-center">
                            <img src="${pageContext.request.contextPath}/photo/user_avatars/default.jpg" class="avatar img-circle img-thumbnail" alt="avatar">
                            <h6>${username}</h6>
                            <h6>${email}</h6>
                            <input type="file" name="file">

                            <button type="submit" name="editphoto" class="btn my-btn btn-add ">
                                изменить фото
                            </button>
                        </div>
                    </div>
                </form>

                <div class="col-md-9 personal-info">
                    <h3>Персональная информация</h3>
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="mb-3">
                                <label class="col-lg-3 control-label">имя</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="username" type="text" value="${username}">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label class="col-lg-3 control-label"  >email</label>
                                <div class="col-lg-8">
                                    <input class="form-control" type="email" name="email" value="${email}">
                                </div>
                            </div>
                            <div>${message}</div>
                            <button type="submit" name="editbtn" class="btn my-btn btn-add ">
                                отредактировать
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</form>
<%@include file="/WEB-INF/view/_footer.jsp" %>
