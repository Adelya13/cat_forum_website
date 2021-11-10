<%@tag description="Cat cards" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<%@attribute name="cat" required="true" type="ru.kpfu.servlets.entities.Cat" %>

<div class="card" style="width: 18rem;">
    <img src="${pageContext.request.contextPath}/photo/cat_avatars/default_cat.jpg" class="card-img-top img-fluid">
    <div class="card-body">
        <h5 class="card-title">${cat.name}</h5>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item">${cat.description}</li>
    </ul>
</div>