<%@tag description="Post cards" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<%@attribute name="post" required="true" type="ru.kpfu.servlets.entities.Post" %>

<div class="card recipe-card">
    <div class="card-header recipe-title">
        <h4><a href="<c:url value="/post?id=${post.id}"/>">${post.name}</a></h4>
        <h6><a href="<c:url value="/user?id=${post.authorId}"/>">${post.authorName}</a></h6>
    </div>


    <div class="card-body">
        <p class="card-text">
        <div class="card-text-wrapper">
                ${post.body}
        </div>
        </p>

        <div class="recipe-description">

            <p>
                Tags:
                <c:forEach items="${post.tags}" var="tag">
                    <a href = "#">#${tag.tagName}</a>
                </c:forEach>
            </p>

        </div>
    </div>

</div>


