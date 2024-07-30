<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../layout/header.jsp"%>
<style>
    .make_todo {
        display: flex;
        width: 95vw;
        margin-bottom: 5vh;
    }
    button{
        margin-left: auto;
    }
    a{
        text-decoration: none;
    }

    button a {
        color: white;
    }
    .message {
        width: 200px;
        height: 100px;
        background-color: lightblue;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        animation: fadeOut 3s forwards; /* 5초 동안 애니메이션이 실행되고 끝나면 사라짐 */
    }
    @keyframes fadeOut {
        0% {
            opacity: 1;
        }
        90% {
            opacity: 1;
        }
        100% {
            opacity: 0;
            display: none;
        }
    }
    .list-group{
        width: 90vw;
        padding-bottom: 5vh;
        margin: 0 auto;
    }
    .todo_num {
        font-size: 0.9em;
    }
    .alert {
        width: 50vw;
        margin: 0 auto;
        margin-bottom: 5vh;
        animation: fadeOut 2s forwards; /* 3초 동안 애니메이션이 실행되고 끝나면 사라짐 */
    }
</style>

<c:if test="${param.tno != null || param.result}">
    <div class="alert alert-primary" role="alert">
        처리되었습니다!
    </div>
</c:if>

<div class="list-group">
    <c:forEach items="${list}" var="todo">
        <a href="/todo/get?page=${pageInfo.page}&tno=${todo.tno}" class="list-group-item list-group-item-action">
            <div>
                <div class="todo_num">${todo.tno}</div>
                <div>${todo.title}</div>
            </div>
        </a>
    </c:forEach>
</div>

<div class="make_todo">
    <button type="button" class="btn btn-primary">
        <a href="/todo/register">Make TODO</a>
    </button>
</div>

<div style="display: flex; justify-content: center;">
    <ul class="pagination">
        <c:if test="${pageInfo.prev}">
            <li class="page-item"><a class="page-link" href="/todo/list?page=${pageInfo.start - 1 }">Previous</a></li>
        </c:if>

        <c:forEach begin="${pageInfo.start}" end="${pageInfo.end}" var="num">
            <li class="page-item ${pageInfo.page == num ?'active':''}"><a class="page-link" href="/todo/list?page=${num}">${num}</a></li>
        </c:forEach>

        <c:if test="${pageInfo.next}">
            <li class="page-item"><a class="page-link" href="/todo/list?page=${pageInfo.end + 1 }">Next</a></li>
        </c:if>
    </ul>
</div>


<script>
    window.onload = function() {
// Show the div when the page loads
        document.querySelector('.alert').style.display = 'block';
    };
    setTimeout(function() {
        history.replaceState(null, "", "/todo/list");
    });
</script>

<%@include file="../layout/footer.jsp"%>
