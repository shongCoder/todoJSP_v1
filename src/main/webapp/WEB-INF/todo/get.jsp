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
        color: black;
    }
    button a {
        color: white;
    }
</style>

${todo}

<div class="make_todo">
    <button type="button" class="btn btn-primary">
        <a href="/todo/edit?tno=${todo.tno}">Modify/Delete</a>
    </button>
    <button type="button" class="btn btn-secondary">
        <a href="/todo/list?page=${page}">show list</a>
    </button>
</div>

<%@include file="../layout/footer.jsp"%>