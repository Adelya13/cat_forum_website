<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags" %>

<%@include file="/WEB-INF/view/_header.jsp" %>
<form action="<c:url value="/post"/>" method="post">
    <tt:header/>

    <div class="container-fluid post-body" id="post-body">

        <div class="card" >
            <div class="card-header">
                <h3>${tittle}</h3>
                <a href="<c:url value="/user?id=${post.authorId}"/>" style="text-decoration: none; color: black;">
                    <h6>автор: ${authorName}</h6>
                </a>
            </div>
            <div class="card-body">
                <p class="card-text">${postBody}</p>
                <div class="recipe-description">
                    <p>
                        Tags:
                        <c:forEach items="${tags}" var="tag">
                            <a href = "#">#${tag.tagName}</a>
                        </c:forEach>
                    </p>
                </div>
            </div>
        </div>

        <div class="my-3 p-3 bg-body rounded shadow-sm">
            <h6 class="border-bottom pb-2 mb-0">Комментарии</h6>

            <c:forEach items="${comments}" var="comment">
                <tt:comment comment="${comment}"/>
            </c:forEach>

            <div class="panel">
                <form>
                    <textarea name="writeComment" placeholder="Оставь свой комментарий" rows="2" class="form-control input-lg p-text-area"></textarea>

                    <small class="d-block text-end mt-3">
                        <button class="btn my-btn btn-add" type="submit" name="addComment">Add comment</button>
                    </small>
                </form>
            </div>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/view/_footer.jsp" %>
