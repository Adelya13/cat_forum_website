<%@tag description="Post cards" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<%@attribute name="comment" required="true" type="ru.kpfu.servlets.entities.Comment" %>

<div class="d-flex  pt-3">
    <p class="pb-3 mb-0 small lh-sm border-bottom w-100">
        <strong class="d-block text-dark" name="username">
            <a href="#" style="color:black;">${comment.authorName}</a>
        </strong>
        ${comment.text}
    </p>
</div>