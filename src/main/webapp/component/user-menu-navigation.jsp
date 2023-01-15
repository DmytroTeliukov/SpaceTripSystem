<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="navigation_menu">
    <div class="navigation_link">
        <img src="../image/rocket_logo.png">
        <p>SPACE TRIP</p>
    </div>
    <div class="navigation_link">
        <a href='<c:url value="/history" />'>Orders</a>
        <a href='<c:url value="/trips" />'>Trips</a>
        <a href='<c:url value="/profile" />'>Profile</a>
        <a href='<c:url value="/exit" />'>Logout</a>
    </div>
</div>
</body>
</html>
