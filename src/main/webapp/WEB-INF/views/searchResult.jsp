<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Результаты поиска</title>

    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
            font-size: 16px;
        }
    </style>

</head>


<body>
<h2>Результат поиска</h2>
<table border="1">
    <tr>
        <td>Наименование</td><td>Необходимость</td><td>Количество</td><td>Действия</td>
    </tr>
    <c:forEach items="${parts}" var="part">
        <tr>
            <td>${part.title}</td>
            <td align="center">
                <c:choose>
                    <c:when test="${part.isNeeded}">
                        Да
                    </c:when>
                    <c:otherwise>
                        Нет
                    </c:otherwise>
                </c:choose>
            </td>
            <td align="center">${part.quantity}</td>
            <td>
                <a href="<c:url value='/edit-${part.id}-part' />">Редактировать</a>
                &nbsp
                <a href="<c:url value='/delete-${part.id}-part' />">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<br/>
<br/>
<form ACTION="search" METHOD="POST">
    Поиск по наименованию детали: <INPUT TYPE="TEXT" NAME="SearchQuery"> <INPUT TYPE="SUBMIT" value="Искать">
</form>
<br/>
Вернуться на страницу <a href="<c:url value='/' />">Список всех деталей</a>
</body>
</html>