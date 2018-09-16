<%--
  Created by IntelliJ IDEA.
  User: IEUser
  Date: 11.09.2018
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Ошибка</title>
</head>
<body>
Сообщение : ${errorMessage}
<br/>
<br/>

Вернуться на страницу <a href="<c:url value='/' />">Список всех деталей</a>
</body>
</html>
