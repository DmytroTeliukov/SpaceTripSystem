<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login form</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <jsp:include page="component/base-menu-navigation.jsp" />
</header>
<form action='<c:url value="/login" />' method="post">
    <div class="body-class">
        <div class="form">
            <h1> Login </h1>
            <input type="text" placeholder="E-mail" name="email" required>
            <input type="password" placeholder="Password" name="password" required>
            <button type="submit">Sign in</button>
            <p>${error}</p>
            <a href='<c:url value="/registration"/>'>Do not have an account? Sign up!</a>
        </div>
    </div>
</form>
<footer>
    <jsp:include page="component/footer.jsp" />
</footer>
</body>
</html>