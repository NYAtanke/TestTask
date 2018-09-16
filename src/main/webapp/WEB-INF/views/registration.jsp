<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:choose>
        <c:when test="${edit}">
            <title>Форма редактирования детали</title>
        </c:when>
        <c:otherwise>
            <title>Форма регистрации новой детали</title>
        </c:otherwise>
    </c:choose>

<style>

    .error {
        color: #ff0000;
    }
</style>

</head>

<body>

<c:choose>
    <c:when test="${edit}">
        <h2>Форма редактирования детали</h2>
        </c:when>
    <c:otherwise>
        <h2>Форма регистрации новой детали</h2>
    </c:otherwise>
</c:choose>

    <form:form method="POST" modelAttribute="part">
        <form:input type="hidden" path="id" id="id"/>
        <table>
            <tr>
                <td><label for="title">Наименование: </label> </td>
                <td><form:input path="title" id="title"/></td>
                <td><form:errors path="title" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="isNeeded">Необходимость: </label> </td>
                <td><form:checkbox path="isNeeded" id="isNeeded"/></td>
                <td><form:errors path="isNeeded" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="quantity">Количество: </label> </td>
                <td><form:input type="number" min="0" path="quantity" id="quantity"/></td>
                <td><form:errors path="quantity" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Сохранить"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Добавить"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>
    <br/>
    <br/>
    Вернуться на страницу <a href="<c:url value='/' />">Список всех деталей</a>
</body>
</html>