<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add trip</title>
    <link rel="stylesheet" href="сss/style.css">
</head>
<body>
<header>
    <jsp:include page="component/user-menu-navigation.jsp" />
</header>
<div class="body">
    <div class="order">
        <div class="planet-image">
            <img src="image/Mercury.png" id="imagePlanet" alt="planet"/>
        </div>
        <div class="order-form">
            <div class="order-title">
                <p> Trip </p> <hr>
            </div>
            <div class="order-input-field">
                <form action='<c:url value="/add-trip" />' method="post">
                    <input type="date" placeholder="Date" name="date" required>
                    <input type="time" placeholder="time" name="time" required>
                    <input type="number" placeholder="Price" name="cost" min="1" step="0.01"  required>
                    <input type="number" placeholder="Count places"  name="countVacancies" min="1"  required>
                    <div class="chosed-item"><p>Trip №${trip.id} </p><p>${trip.planet} - ${trip.started}</p></div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>