<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contacts</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
        <jsp:include page="component/base-menu-navigation.jsp" />
</header>
<div class="body">
    <div class="profile">
        <div class="profile-header">
            <p>Contacts</p>
        </div>
        <hr>
        <div class="profile-information">
            <p>Phone: 0987654321</p>
            <p>E-mail: spacetrip@gmail.com</p>
        </div>
    </div>
</div>
<footer>
    <jsp:include page="component/footer.jsp" />
</footer>
</body>
</html>
