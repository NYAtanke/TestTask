<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список всех деталей</title>

    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
            font-size: 16px;
        }
    </style>

</head>


<body>
    <h2>Список
        <c:choose>
            <c:when test="${filter=='needed'}">
                необходимых для сборки
            </c:when>
            <c:when test="${filter=='optional'}">
                опциональных
            </c:when>
            <c:otherwise>
                всех
            </c:otherwise>
        </c:choose>
        деталей
    </h2>
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
        <tr>
            <td colspan="5" align="center">
                Страницы:
                <c:forEach begin="1" end="${numPages}" varStatus="loop">
                    <a href="<c:url value='/list-${filter}-Page-${loop.index}' />">${loop.index}</a>
                </c:forEach>
            </td>
        </tr>
    </table>
    <br/>
    Можно собрать ${ComputerCount} ${nameCountComputer}
    <br/>
    <br/>

    <form ACTION="search" METHOD="POST">
        Поиск по наименованию детали: <INPUT TYPE="TEXT" NAME="SearchQuery"> <INPUT TYPE="SUBMIT" value="Искать">
    </form>
    <br/>
    <br/>
    Вы можете вывести следующие списки:
    <form action="<c:url value='/list-all-Page-1' />" method="get">
        <input type="submit" value="Всех деталей">
    </form>
    <form action="<c:url value='/list-needed-Page-1' />" method="get">
        <input type="submit" value="Деталей, необходимых для сборки">
    </form>
    <form action="<c:url value='/list-optional-Page-1' />" method="get">
        <input type="submit" value="Опциональных деталей">
    </form>
    <br/>
    <br/>
    <a href="<c:url value='/new' />">Добавить новую деталь</a>
</body>
</html>