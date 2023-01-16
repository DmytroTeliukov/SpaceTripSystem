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
    <jsp:include page="component/admin-menu-navigation.jsp" />
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
                    <input type="number" placeholder="Price" name="price" min="1" step="0.01"  required>
                    <input type="number" placeholder="Count places"  name="countVacancies" min="1"  required>
                    <select name="planet" id="planetSelect" onchange="javascript:flip();">
                        <option url="image/Mercury.png" value="MERCURY">MERCURY</option>
                        <option url="image/Venera.png" value="VENERA">VENERA</option>
                        <option url="image/Earth.png" value="EARTH">EARTH</option>
                        <option url="image/Mars.png" value="MARS">MARS</option>
                        <option url="image/Jupiter.png" value="JUPITER">JUPITER</option>
                        <option url="image/Saturn.png" value="SATURN">SATURN</option>
                        <option url="image/Uranus.png" value="URANUS">URANUS</option>
                        <option url="image/Neptune.png" value="NEPTUNE">NEPTUNE</option>
                    </select>
                    <script src="js/choose-planet.js"></script>
                    <c:if test="${empty operator}">
                        <a href='<c:url value="/choose-operator" />'>Choose operator</a>
                    </c:if>
                    <c:if test="${not empty operator}">
                        <div class="chosed-item"><p>Operator:</p><p>${operator.lastname} ${operator.firstname}</p></div>
                        <a href='<c:url value="/choose-operator" />'>Change operator</a>
                        <button  type="submit" >Add trip</button>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>