<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Подтверждение регистрации/редактирования</title>
</head>
<body>
    Сообщение : ${success}
    <br/>
    <br/>

    Вернуться на страницу <a href="<c:url value='/' />">Список всех деталей</a>
</body>

</html>