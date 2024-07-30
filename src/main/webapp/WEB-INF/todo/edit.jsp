<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../layout/header.jsp"%>

<h3>Edit Page</h3>

<form action="/todo/edit" method="post">
    <div>
        <lable>TNO</lable>
        <input type="text" name="tno" value="${todo.tno}" readonly>
    </div>
    <div>
        <lable>Title</lable>
        <input type="text" name="title" value="${todo.title}">
    </div>
    <div>
        <lable>Writer</lable>
        <input type="text" name="writer" value="${todo.writer}">
    </div>
    <div>
        <button>Modify</button>
    </div>
</form>

<form action="/todo/delete" method="post">
    <input type="hidden" name="tno" value="${todo.tno}">
    <button>Remove</button>
</form>

<%@include file="../layout/footer.jsp"%>